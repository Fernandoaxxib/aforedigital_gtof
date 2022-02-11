package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.List;
import java.util.Locale;
import javax.faces.component.UIInput;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.FechaOut;
import mx.axxib.aforedigitalgt.reca.eml.InteresesTranOut;
import mx.axxib.aforedigitalgt.reca.eml.LoteCargaOut;
import mx.axxib.aforedigitalgt.reca.eml.LoteRevOut;
import mx.axxib.aforedigitalgt.reca.serv.GeneracionLotesInteresesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Generación de lotes de intereses transito Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 07/02/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "generacionLotesIntereses")
@ELBeanName(value = "generacionLotesIntereses")
public class GeneracionLotesInteresesCtrll extends ControllerBase {

	@Autowired
	private GeneracionLotesInteresesServ service;

	@Getter
	@Setter
	private String radioSelected;
	@Getter
	@Setter
	private String archivo;
	@Getter
	@Setter
	private String ruta;
	@Getter
	@Setter
	private String archivo2;
	@Getter
	@Setter
	private String ruta2;
	@Getter
	@Setter
	private String lote;
	@Getter
	private String border;
	@Getter
	@Setter
	private List<FechaOut> listFechas;
	@Getter
	@Setter
	private FechaOut selectedFecha;
	@Getter
	@Setter
	private LoteCargaOut selectedLote;
	@Getter
	@Setter
	private LoteRevOut selectedLoteRev;
	@Getter
	@Setter
	private LoteRevOut lote1;
	@Getter
	@Setter
	private List<LoteCargaOut> listLotes;
	@Getter
	@Setter
	private List<LoteRevOut> filtro;
	@Getter
	@Setter
	private List<LoteRevOut> listLotesRev;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				radioSelected = "20";
				ruta = "/iprod/PROCESAR/RECEPCION/AFORE/RECAUDACION/INTERESESTRANSITO";
				ruta2 = "/iprod/PROCESAR/RECEPCION/AFORE/RECAUDACION/INTERESESTRANSITO";
				archivo = null;
				archivo2 = null;
				border = "";
				lote = null;
				listFechas = null;
				selectedFecha = null;
				selectedLote = null;
				selectedLoteRev = null;
				listLotes = null;
				listLotesRev = null;
				filtro = null;
				getLotes();
				getFechas();
				getLotesRev();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getFechas() throws Exception {
		InteresesTranOut resp = service.getFechas();
		if (resp.getP_ESTATUS() == 1) {
			listFechas = resp.getListFechas();
		} else if (resp.getP_ESTATUS() == 2) {
			GenerarErrorNegocio(resp.getP_MENSAJE());
		}
	}

	public void getLotes() throws Exception {
		InteresesTranOut resp = service.getLotes();
		if (resp.getP_ESTATUS() == 1) {
			listLotes = resp.getListLotes();
		} else if (resp.getP_ESTATUS() == 2) {
			GenerarErrorNegocio(resp.getP_MENSAJE());
		}
	}

	public void getLotesRev() throws Exception {
		InteresesTranOut resp = service.getLotesRev();
		if (resp.getP_ESTATUS() == 1) {
			listLotesRev = resp.getListatLotesRev();
		} else if (resp.getP_ESTATUS() == 2) {
			GenerarErrorNegocio(resp.getP_MENSAJE());
		}
	}

	public void radioSelected() {
		border="";
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteRevOut car = (LoteRevOut) value;
		return car.getLote_carga().toString().contains(filterText);
	}

	public void onRowSelect(SelectEvent<LoteRevOut> event) {
		lote1 = new LoteRevOut();
		lote1.setLote_carga(event.getObject().getLote_carga());
		lote1.setIMP_TOTAL_IT(event.getObject().getIMP_TOTAL_IT());
		lote1.setRegistros(event.getObject().getRegistros());
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			lote = lote1.getLote_carga();
		}
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();
		switch (radioSelected) {
		case "10": {
			pr.setDescProceso("GENERAR ARCHIVO OP71");
			pr.setFechaInicial(DateUtil.getNowDate());
			if (validacion2(pr)) {
				try {
					InteresesTranOut resp = service.generarLotes(10, null, selectedLote.getLote_carga(), archivo2,
							ruta2, null);
					if (resp.getP_ESTATUS() == 1) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						if (resp.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(resp.getP_MENSAJE());
						} else if (resp.getP_ESTATUS() == 0) {
							pr.setStatus(resp.getP_MENSAJE());
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
			break;
		}
		case "20": {
			pr.setDescProceso("CARGAR ARCHIVO OP71");
			pr.setFechaInicial(DateUtil.getNowDate());
			if (validacion1(pr)) {
				try {
					InteresesTranOut resp = service.generarLotes(20, null, null, archivo, ruta, null);
					if (resp.getP_ESTATUS() == 1) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						if (resp.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(resp.getP_MENSAJE());
						} else if (resp.getP_ESTATUS() == 0) {
							pr.setStatus(resp.getP_MENSAJE());
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
			break;
		}
		case "30": {
			pr.setDescProceso("REVERSA DE CARGA DE ARCHIVOS");
			pr.setFechaInicial(DateUtil.getNowDate());
			if (lote != null) {
				try {
					InteresesTranOut resp = service.generarLotes(30, null, null, null, null, lote);
					if (resp.getP_ESTATUS() == 1) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						if (resp.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(resp.getP_MENSAJE());
						} else if (resp.getP_ESTATUS() == 0) {
							pr.setStatus(resp.getP_MENSAJE());
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}else {
				border = "2px solid #ff0028 !important;";
				pr.setStatus("Se requiere el número de lote");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
			break;
		}
		}
	}

	public boolean validacion1(ProcessResult pr) {
		if (archivo == null || archivo.isEmpty()) {
			UIInput radio = (UIInput) findComponent("archivo");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		}
		return true;
	}

	public boolean validacion2(ProcessResult pr) {
		if (selectedFecha == null) {
			UIInput radio = (UIInput) findComponent("combFecha");
			radio.setValid(false);
			pr.setStatus("La fecha de operación es requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else if (selectedLote == null) {
			UIInput radio = (UIInput) findComponent("combLote");
			radio.setValid(false);
			pr.setStatus("Seleccione el lote");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else if (archivo2 == null || archivo2.isEmpty()) {
			UIInput radio = (UIInput) findComponent("archivo2");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		}
		return true;
	}
}