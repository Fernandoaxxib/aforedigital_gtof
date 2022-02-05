package mx.axxib.aforedigitalgt.reca.ctrll;

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
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarLoteOp71Out;
import mx.axxib.aforedigitalgt.reca.eml.LoteOp71Out;
import mx.axxib.aforedigitalgt.reca.eml.OperacionOut;
import mx.axxib.aforedigitalgt.reca.eml.SectorOut;
import mx.axxib.aforedigitalgt.reca.eml.SieforeOut;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarLoteOp71Serv;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Liquidar lote OP71
//** Interventor Principal: JJSC
//** Fecha Creación: 18/01/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "liquidarLoteOp71")
@ELBeanName(value = "liquidarLoteOp71")
public class LiquidarLoteOp71Ctrll extends ControllerBase {

	@Autowired
	private LiquidarLoteOp71Serv service;

	@Getter
	@Setter
	private String lote;

	@Getter
	@Setter
	private List<LoteOp71Out> listLotes;

	@Getter
	@Setter
	private List<LoteOp71Out> filtro;

	@Getter
	@Setter
	private LoteOp71Out selectedLote;
	@Getter
	@Setter
	private LoteOp71Out lote1;
	@Getter
	@Setter
	private Date fecha;
	@Getter
	@Setter
	private List<OperacionOut> listaOperaciones;
	@Getter
	@Setter
	private String id_operacion;
	@Getter
	@Setter
	private String fec_lote;
	@Getter
	@Setter
	private String consecutivo_dia;
	@Getter
	@Setter
	private List<SieforeOut> listaSiefore;
	@Getter
	@Setter
	private SieforeOut selectedSiefore;
	@Getter
	@Setter
	private List<SectorOut> listaSectores;
	@Getter
	@Setter
	private SectorOut selectedSector;

	@Getter
	private Date fecActual;

	@Getter
	@Setter
	private String border;

	@Getter
	@Setter
	private String agrupacion;

	@Getter
	@Setter
	private boolean band;

	@Getter
	@Setter
	private String selected;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				init = false;
				band = false;
				lote = null;
				lote1 = null;
				fecha = null;
				border = null;
				filtro = null;
				selected = null;
				fec_lote = null;
				listLotes = null;
				agrupacion = null;
				id_operacion = null;
				selectedLote = null;
				listaSiefore = null;
				selectedSector = null;
				consecutivo_dia = null;
				selectedSiefore = null;
				listaOperaciones = null;
				fecActual = DateUtil.getNowDate();
				getLotes();
				getSiefore();
				getSectores();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getSiefore() throws Exception {
		LiquidarLoteOp71Out resp = service.getSiefore();
		if (resp.getOn_Estatus() == null) {
			listaSiefore = resp.getListaSiefore();
		} else {
			if (resp.getOn_Estatus() == 2) {
				GenerarErrorNegocio(resp.getOc_Mensaje());
			}
		}
	}

	public void getSectores() throws Exception {
		LiquidarLoteOp71Out resp = service.getSectores();
		if (resp.getOn_Estatus() == null) {
			listaSectores = resp.getListaSectores();
		} else {
			if (resp.getOn_Estatus() == 2) {
				GenerarErrorNegocio(resp.getOc_Mensaje());
			}
		}
	}

	public void getLotes() throws Exception {
		try {
			if (listLotes == null) {
				LiquidarLoteOp71Out resp = service.getLote();
				if (resp.getOn_Estatus() == null) {
					listLotes = resp.getListaLotes();
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					}
				}
			} else {
				PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
			}
			lote = null;
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteOp71Out car = (LoteOp71Out) value;
		return car.getFec_lote().toString().contains(filterText);
	}

	public void onRowSelect(SelectEvent<LoteOp71Out> event) {
		lote1 = new LoteOp71Out();
		lote1.setId_operacion(event.getObject().getId_operacion());
		lote1.setConsecutivo_dia(event.getObject().getConsecutivo_dia());
		lote1.setFec_lote(event.getObject().getFec_lote());
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			// lote = lote1.getId_operacion();
			selectedSiefore = null;
			agrupacion = null;
			id_operacion = lote1.getId_operacion();
			consecutivo_dia = Integer.toString(lote1.getConsecutivo_dia());
			fec_lote = Integer.toString(lote1.getFec_lote());
		}
	}

	public void consultar() {
		listaOperaciones = null;
		ProcessResult pr = new ProcessResult();
		try {
			pr.setDescProceso("Consultar montos");
			pr.setFechaInicial(DateUtil.getNowDate());

			if (isFormValid(pr)) {
				LiquidarLoteOp71Out resp = service.getDetalle(id_operacion, fec_lote, consecutivo_dia,
						selectedSiefore.getCod_inversion(), agrupacion);
				if (resp.getOn_Estatus() == 1) {
					listaOperaciones = resp.getListaOperaciones();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getOc_Mensaje());
						listaOperaciones = resp.getListaOperaciones();
					}
				}
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void generarInterfase() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setDescProceso("Generar Interfase");
			pr.setFechaInicial(DateUtil.getNowDate());

			if (isFormValid2(pr)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String fechaComoCadena = sdf.format(fecha);

				LiquidarLoteOp71Out resp = service.generarInterface(id_operacion, fec_lote, consecutivo_dia,
						fechaComoCadena, selectedSector.getClave_procesar(), selectedSector.getCod_inversion());
				if (resp.getOn_Estatus() == 1) {
					listaOperaciones = resp.getListaOperaciones();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getOc_Mensaje());
					}
				}
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public boolean isFormValid(ProcessResult pr) {
		if (id_operacion == null) {
			border = "2px solid #ff0028 !important;";
			pr.setStatus("Por favor seleccione la información requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			return false;
		} else {
			border = "";
			if (selectedSiefore == null) {
				UIInput siefore = (UIInput) findComponent("combSiefore");
				siefore.setValid(false);
				pr.setStatus("Por favor seleccione Siefore");
				pr.setFechaFinal(DateUtil.getNowDate());
				return false;
			} else {
				if (agrupacion == null) {
					UIInput siefore = (UIInput) findComponent("combAgrupacion");
					siefore.setValid(false);
					pr.setStatus("Por favor seleccione agrupación");
					pr.setFechaFinal(DateUtil.getNowDate());
					return false;
				}
			}
		}
		return true;
	}

	public boolean isFormValid2(ProcessResult pr) {
		if (id_operacion == null) {
			border = "2px solid #ff0028 !important;";
			pr.setStatus("Por favor seleccione la información requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			return false;
		} else {
			border = "";
			if (selectedSector == null) {
				UIInput siefore = (UIInput) findComponent("combSectores");
				siefore.setValid(false);
				pr.setStatus("Por favor seleccione Siefore");
				pr.setFechaFinal(DateUtil.getNowDate());
				return false;
			} else {
				if (fecha == null) {
					UIInput siefore = (UIInput) findComponent("fAplicado");
					siefore.setValid(false);
					pr.setStatus("Por favor seleccionar la fecha de aplicado");
					pr.setFechaFinal(DateUtil.getNowDate());
					return false;
				}
			}
		}
		return true;
	}

	public void liquidar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Liquidar lote");
		pr.setFechaInicial(DateUtil.getNowDate());
		if (listaOperaciones != null) {
			if (!listaOperaciones.isEmpty()) {
				if (isFormValid(pr)) {
					try {
						listaOperaciones.forEach(x -> {
							try {
								LiquidarLoteOp71Out resp = service.liquidar(id_operacion, fec_lote, consecutivo_dia,
										Double.parseDouble(x.getOn_monto_liquidado()),
										Double.parseDouble(x.getOn_importes_aceptados()),
										selectedSiefore.getCod_inversion(), agrupacion);
							} catch (NumberFormatException | AforeException e) {
								GenericException(e);
							}
						});

						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} catch (Exception e) {
						pr = GenericException(e);
					} finally {
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				}
			}else {
				pr.setStatus("NO EXISTEN DATOS PARA LIQUIDAR");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			pr.setStatus("Por favor consultar los montos");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void habilitar() {
		fecha = null;
		selectedSector = null;
		if (band) {
			selected = "1";
		} else {
			selected = "0";
		}
	}
}
