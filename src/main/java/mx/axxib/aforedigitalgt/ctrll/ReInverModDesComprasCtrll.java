package mx.axxib.aforedigitalgt.ctrll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UIInput;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DetCompraOut;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.LoteCOut;
import mx.axxib.aforedigitalgt.serv.ReInverModDesComprasServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** Funcionalidad: Controlador - Reinversiones a básicas parcialidades - Compras
//** Desarrollador: JJSC
//** Fecha de creación: 01/Feb/2021
//** Última modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "reinversionModDesCompras")
@ELBeanName(value = "reinversionModDesCompras")
public class ReInverModDesComprasCtrll extends ControllerBase {

	@Autowired
	private ReInverModDesComprasServ service;

	@Getter
	@Setter
	private Date fecha;

	@Getter
	@Setter
	private String lote;

	@Getter
	@Setter
	private LoteCOut lote1;

	@Getter
	List<DetCompraOut> detalleCompra;

	@Getter
	@Setter
	private List<LoteCOut> listLotes;
	@Getter
	@Setter
	private List<LoteCOut> filtro;

	@Getter
	@Setter
	private LoteCOut selectedLote;

	@Getter
	@Setter
	private Double montoTotal;

	@Getter
	@Setter
	private Double accionesTotal;
	@Getter
	private Date fecActual;
	@Getter
	private boolean disabled;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			accionesTotal = 0.0;
			detalleCompra = null;
			disabled = true;
			fecha = DateUtil.getNowDate();
			fecActual = DateUtil.getNowDate();
			filtro = null;
			lote = null;
			lote1 = null;
			listLotes = null;
			montoTotal = 0.0;	
			selectedLote=null;
		}
	}

	public void getLotes() {
		try {
			if (listLotes == null) {
				listLotes = service.getLotes();
			}
			PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void opcionSeleccionada2() throws Exception {
		if (lote1 != null) {
			lote = lote1.getId_lote();
			detalleCompra();
		}
	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());

		if (fecha != null) {
			if (lote != null) {
				if (detalleCompra != null && !detalleCompra.isEmpty()) {
					pr.setDescProceso("Generar Reporte");
					try {
						SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						String f = format.format(fecha);
						EjecucionResult res = service.generarReporte(lote.toString(), montoTotal.toString(), f);
						if (res.getOn_Estatus() == 1) {
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						} else {
							if (res.getOn_Estatus() == 2) {
								GenerarErrorNegocio(res.getOcMensaje());
							} else if (res.getOn_Estatus() == 0) {
								pr.setStatus(res.getOcMensaje());
							}
						}
					} catch (Exception e) {
						pr = GenericException(e);
					} finally {
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				} else {
					pr.setDescProceso("Generar Reporte");
					pr.setStatus("No se encontró información para generar el reporte.");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			} else {
				pr.setDescProceso("Falta definidir el lote");
				pr.setStatus("Información requerida");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			UIInput radio = (UIInput) findComponent("fecha");
			radio.setValid(false);
			pr.setDescProceso("Seleccione la fecha de aplicado");
			pr.setStatus("Selección requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void comprar() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if (fecha != null) {
			if (lote != null && !lote.isEmpty()) {
				pr.setDescProceso("Compra");
				try {
					EjecucionResult res = service.comprar(Integer.valueOf(lote), fecha);
					if (res.getOn_Estatus() == 1) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						if (res.getOn_Estatus() == 2) {
							GenerarErrorNegocio(res.getOcMensaje());
						} else if (res.getOn_Estatus() == 0) {
							pr.setStatus(res.getOcMensaje());
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}

			} else {
				pr.setDescProceso("Falta definidir el lote");
				pr.setStatus("Información requerida");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			UIInput radio = (UIInput) findComponent("fecha");
			radio.setValid(false);
			pr.setDescProceso("Seleccione la fecha de aplicado");
			pr.setStatus("Selección requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}

	}

	public void detalleCompra() throws Exception {
		montoTotal = 0.0;
		accionesTotal = 0.0;
		detalleCompra = service.getDetalleCompra(lote);
		if (detalleCompra != null && !detalleCompra.isEmpty()) {
			detalleCompra.forEach(p -> {
				montoTotal = montoTotal + p.getMONTO();
				accionesTotal = accionesTotal + p.getACCIONES();
			});
			disabled = false;
		} else {
			disabled = true;
		}
	}

	public void onRowSelect(SelectEvent<LoteCOut> event) {
		lote1 = new LoteCOut();
		lote1.setId_lote(event.getObject().getId_lote());
		fecha = event.getObject().getFEC_MOVIMTO();
	}

	public void selectLote() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String f = format.format(fecha);
		lote = "04" + f;
		detalleCompra();
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		LoteCOut car = (LoteCOut) value;

		String fechaOperacion = cadenaFecha(car.getFEC_MOVIMTO());
		return car.getId_lote().toString().contains(filterText) || fechaOperacion.contains(filterText);
	}

	private String cadenaFecha(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = dateFormat.format(fecha);
		return strDate;
	}
}
