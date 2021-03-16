package mx.axxib.aforedigitalgt.ctrll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasos;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosIn;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosOut;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExces;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesIn;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesOut;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiro;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonto;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoCorteIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorCTIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorIn;
import mx.axxib.aforedigitalgt.serv.VentaTitulosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "ventaTitulos")
@ELBeanName(value = "ventaTitulos")
public class VentaTitulosCtrll extends ControllerBase {

	@Autowired
	HikariDataSource dataSource;

	@Autowired
	private VentaTitulosServ ventaTitulosService;

	@Autowired
	private AforeMessage aforeMessage;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	private Date fechaActual;

	@Getter
	@Setter
	private String opcion;

	@Getter
	@Setter
	private String lote;

	@Getter
	private List<ObtenerTipoRetiro> tipoRetiros;

	@Getter
	@Setter
	private ObtenerTipoRetiro selectedTipoRetiro;

	@Getter
	private List<ObtenerLoteTraspasos> loteTraspasos;

	@Getter
	@Setter
	private ObtenerLoteTraspasos selectedLoteTraspaso;

	@Getter
	private List<ObtenerRgDevExces> rgDevExces;

	@Getter
	@Setter
	private ObtenerRgDevExces selectedrgDevExces;

	@Getter
	private List<ObtieneMonto> montos;

	@Getter
	private boolean mostrarVenta;

	@Getter
	private boolean mostrarVentaCT;

	@Getter
	private boolean disRetiros;

	@Getter
	private boolean disTraspasos;

	@Getter
	private boolean disDev;

	@Getter
	private boolean disLote;

	@Getter
	private String mensajeTabla;

	@Getter
	private Integer tablaCount;

	@Getter
	@Setter
	private ObtieneMonto selectedSiefore;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			// Limpiar objetos
			tablaCount = null;
			mensajeTabla = null;
			opcion = "T";
			selectedTipoRetiro = null;
			selectedLoteTraspaso = null;
			selectedrgDevExces = null;
			lote = null;
			montos = null;
			selectedSiefore = null;

			// Establecer valor inicial
			mostrarVenta = false;
			mostrarVentaCT = false;
			disRetiros = true;
			disTraspasos = true;
			disDev = true;
			disLote = true;
			fechaActual = new Date();
			fechaInicial = fechaActual;
			fechaFinal = fechaActual;

			// Obtener catálogos necesarios
			try {
				obtenerTipoRetiro();
				obtenerLoteTraspaso();
				obtenerRgDevExces();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void obtenerTipoRetiro() throws Exception {
		ObtenerTipoRetiroIn parametros = new ObtenerTipoRetiroIn();
		parametros.setFecha(fechaInicial);
		ObtenerTipoRetiroOut res = ventaTitulosService.getObtenerTipoRetiro(parametros);
		if(res.getEstatus() == 1) {
			tipoRetiros = res.getRetiro();
		} else {
			if(res.getEstatus() == 2) {
				GenerarErrorNegocio(res.getMensaje());
			}
		}
	}

	public void obtenerLoteTraspaso() throws Exception {
		ObtenerLoteTraspasosIn parametros = new ObtenerLoteTraspasosIn();
		parametros.setFecha(fechaInicial);
		ObtenerLoteTraspasosOut res = ventaTitulosService.getObtenerLoteTraspasos(parametros);
		if(res.getEstatus() == 1) {
			loteTraspasos = res.getTraspaso();
		} else {
			if(res.getEstatus() == 2) {
				GenerarErrorNegocio(res.getMensaje());
			}
		}
	}

	public void obtenerRgDevExces() throws Exception {
		ObtenerRgDevExcesIn parametros = new ObtenerRgDevExcesIn();
		parametros.setFecha(fechaInicial);
		ObtenerRgDevExcesOut res = ventaTitulosService.getObtenerRgDevExces(parametros);
		if(res.getEstatus() == 1) {
			rgDevExces = res.getRgDevExces();
		} else {
			if(res.getEstatus() == 2) {
				GenerarErrorNegocio(res.getMensaje());
			}
		}
	}

	public void changeOpcion() {
		switch (opcion) {
		case "T":
			disRetiros = true;
			disTraspasos = true;
			disDev = true;
			disLote = true;
			break;
		case "R":
			disRetiros = false;
			disTraspasos = true;
			disDev = true;
			disLote = true;
			break;
		case "A":
			disRetiros = true;
			disTraspasos = false;
			disDev = true;
			disLote = true;
			break;
		case "V":
			disRetiros = true;
			disTraspasos = true;
			disDev = false;
			disLote = true;
			break;
		case "L":
			disRetiros = true;
			disTraspasos = true;
			disDev = true;
			disLote = false;
			break;
		}
	}

	public boolean isFormValid(ProcessResult pr) {
		if (!DateUtil.isValidDates(fechaInicial, fechaFinal)) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);

			pr.setStatus("La fecha final debe ser mayor o igual a la inicial");
			return false;
		}

		if (opcion == null) {
			UIInput radio = (UIInput) findComponent("customRadio");
			radio.setValid(false);

			pr.setStatus("Selección requerida");
			return false;
		}

		return true;
	}

	public void buscarMontos() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			if (isFormValid(pr)) {

				selectedSiefore = null;
				mostrarVenta = false;
				mostrarVentaCT = false;
				montos = new ArrayList<ObtieneMonto>();
				ObtieneMontoOut res = null;
				
				switch (opcion) {
				case "T":
					ObtieneMontoTotalIn parametrosT = new ObtieneMontoTotalIn();
					parametrosT.setFechaFinal(fechaFinal);
					parametrosT.setFechaInicial(fechaInicial);
					pr.setDescProceso("Búsqueda por todos los módulos");
					res = ventaTitulosService.getObtieneMontoTotal(parametrosT);
					break;
				case "R":
					ObtieneMontoRetiroIn parametrosR = new ObtieneMontoRetiroIn();
					parametrosR.setFechaFinal(fechaFinal);
					parametrosR.setFechaInicial(fechaInicial);
					pr.setDescProceso("Búsqueda por retiros");
					if (selectedTipoRetiro != null) {
						parametrosR.setTipoRetiro(selectedTipoRetiro.getDescripcion());
						parametrosR.setTipoTransaccion(selectedTipoRetiro.getTipoTransaccion());
						parametrosR.setSubTipoTransaccion(selectedTipoRetiro.getSubTipoTransaccion());
						res = ventaTitulosService.getObtieneMontoRetiro(parametrosR);
					} else {
						UIInput fini = (UIInput) findComponent("comboRetiros");
						fini.setValid(false);
						String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO, new Object[] { "Retiros" });
						pr.setStatus(msg);
					}

					break;
				case "A":
					ObtieneMontoTraspasosIn parametrosA = new ObtieneMontoTraspasosIn();
					parametrosA.setFecha(fechaInicial);
					pr.setDescProceso("Búsqueda por traspasos");
					if (selectedLoteTraspaso != null) {
						parametrosA.setLoteTraspaso(selectedLoteTraspaso.getIdLote());
						res = ventaTitulosService.getObtieneMontoTraspasos(parametrosA);
					} else {
						UIInput fini = (UIInput) findComponent("comboTraspasos");
						fini.setValid(false);
						String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO,
								new Object[] { "Traspasos Afore-Afore" });
						pr.setStatus(msg);
					}
					break;
				case "V":
					ObtieneMontoDevIn parametrosV = new ObtieneMontoDevIn();
					parametrosV.setFecha(fechaInicial);
					pr.setDescProceso("Búsqueda por dev pago");
					if (selectedrgDevExces != null) {
						parametrosV.setLoteRev(selectedrgDevExces.getIdLote());
						res = ventaTitulosService.getObtieneMontoDev(parametrosV);
					} else {
						UIInput fini = (UIInput) findComponent("comboDev");
						fini.setValid(false);
						String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO,
								new Object[] { "Dev pago excs lote" });
						pr.setStatus(msg);
					}
					break;
				case "L":
					ObtieneMontoCorteIn parametrosL = new ObtieneMontoCorteIn();
					parametrosL.setFecha(fechaInicial);
					pr.setDescProceso("Búsqueda por lote");
					if (lote != null) {
						if (!ValidateUtil.isValidLote(lote)) {
							UIInput fini = (UIInput) findComponent("inputLote");
							fini.setValid(false);
							pr.setDescProceso("Búsqueda por lote");
							pr.setStatus("Lote no válido");
							break;
						}
						parametrosL.setLoteCorte(lote);
						res = ventaTitulosService.getObtieneMontoCorte(parametrosL);
					} else {
						UIInput fini = (UIInput) findComponent("inputLote");
						fini.setValid(false);
						String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO,
								new Object[] { "Lote corte" });
						pr.setStatus(msg);
					}
					break;
				}
				
				if(res != null) {
					if(res.getEstatus() == 1) {
						montos = res.getMonto();
						if(montos.size() > 0 && !opcion.equals("T")) {
							if(opcion.equals("L")) {
								mostrarVentaCT = true;
							} else {
								mostrarVenta = true;
							}
							selectedSiefore = montos.get(0);
						} else if (montos.size() == 0) {
							mensajeTabla = "Sin información";
						}
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						if(res.getEstatus() == 2) {
							GenerarErrorNegocio(res.getMensaje());
						} else if(res.getEstatus() == 0) {
							pr.setStatus(res.getMensaje());
						} 
					}
				}
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			if (montos != null)
				tablaCount = montos.size();
		}
	}

	public void ventaTitulosMonitor() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Venta de títulos");
			if (selectedSiefore != null) {
				VentaTitulosMonitorIn parametros = new VentaTitulosMonitorIn();
				parametros.setSeleccion(opcion);
				parametros.setIndCuotaRend("C"); // De acuerdo a BD se manda fijo C
				parametros.setFechaI(fechaInicial);
				parametros.setFecha(fechaFinal);
				parametros.setSiafore(selectedSiefore.getSiefore());
				parametros.setRetiroAforeMnd(selectedSiefore.getRetiroAforeMND());
				parametros.setValorCuota(selectedSiefore.getValCuota());
				parametros.setUsuario(dataSource.getUsername());
				switch (opcion) {
				case "R":
					parametros.setTransacMov(selectedTipoRetiro.getTipoTransaccion());
					parametros.setSubtransMov(selectedTipoRetiro.getSubTipoTransaccion());
					break;
				case "A":
					parametros.setIdLote(selectedLoteTraspaso.getIdLote());
					break;
				case "V":
					parametros.setLoteRev(selectedrgDevExces.getIdLote());
					break;
				}
				BaseOut res = ventaTitulosService.ventaTitulosMonitor(parametros);
				if(res.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if(res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if(res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					} 
				}
			} else {
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.SELECCION_REQUERIDA, null));
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void ventaTitulosMonitorCT() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Venta de títulos CT");

			if (selectedSiefore != null && opcion.equals("L")) {
				VentaTitulosMonitorCTIn parametros = new VentaTitulosMonitorCTIn();
				parametros.setIndCuotaRend("C"); // De acuerdo a BD se manda fijo C
				parametros.setSiafore(selectedSiefore.getSiefore());
				parametros.setRetiroAforeMnd(selectedSiefore.getRetiroAforeMND());
				parametros.setValorCuota(selectedSiefore.getValCuota());
				parametros.setUsuario(dataSource.getUsername());
				parametros.setLoteCorte(lote);

				BaseOut res = ventaTitulosService.ventaTitulosMonitorCT(parametros);
				if(res.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if(res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if(res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					} 
				}
			} else {
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.SELECCION_REQUERIDA, null));
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
}
