package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.List;
import java.util.Locale;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.DatosTrabajador;
import mx.axxib.aforedigitalgt.reca.eml.LoteRecaudacionIssste;
import mx.axxib.aforedigitalgt.reca.eml.PlancharCurpOut;
import mx.axxib.aforedigitalgt.reca.serv.PlancharCurpServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Planchar Curp
//** Interventor Principal: JJSC
//** Fecha Creación: 14/03/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "plancharCurp")
@ELBeanName(value = "plancharCurp")
public class PlancharCurpCtrll extends ControllerBase {

	@Autowired
	private PlancharCurpServ service;

	@Getter
	@Setter
	private List<LoteRecaudacionIssste> listLotes;

	@Getter
	@Setter
	private List<LoteRecaudacionIssste> filtro;

	@Getter
	@Setter
	private LoteRecaudacionIssste selectedLote;

	@Getter
	@Setter
	private LoteRecaudacionIssste lote1;

	@Getter
	@Setter
	private List<DatosTrabajador> listDatosTrabajadores;

	@Getter
	@Setter
	private List<DatosTrabajador> selectedTrabajadores;

	@Getter
	@Setter
	private String disabled;

	@Getter
	@Setter
	private String disabled2;

	@Getter
	@Setter
	private String lote;

	@Getter
	@Setter
	private String border;

	@Getter
	private Integer seleccionados;

	@Getter
	@Setter
	private boolean seleccionado;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				init = false;
				lote = null;
				border = "";
				lote1 = null;
				filtro = null;
				disabled = "true";
				disabled2 = "true";
				listLotes = null;
				selectedLote = null;
				selectedTrabajadores = null;
				listDatosTrabajadores = null;
				seleccionados = 0;
				getLotes();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getLotes() throws Exception {
		PlancharCurpOut resp = service.getLotes();
		if (resp.getEstatus() == 1) {
			this.listLotes = resp.getListLotes();
		} else {
			if (resp.getEstatus() == 2) {
				GenerarErrorNegocio(resp.getMensaje());
			}
		}
	}

	public void procesar() {		
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Procesar");
		pr.setFechaInicial(DateUtil.getNowDate());
		
			if (selectedTrabajadores != null) {
				selectedTrabajadores.forEach(x -> {
					ProcessResult pr2 = new ProcessResult();
					pr2.setFechaInicial(DateUtil.getNowDate());
					pr2.setDescProceso("Procesar");
					try {
						PlancharCurpOut resp2 = service.procesar("1", lote, x.getCurp(), x.getNss(),
								x.getResulOpera());
						if (resp2.getEstatus() == 1) {							
							pr2.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						} else {
							if (resp2.getEstatus() == 2) {
								GenerarErrorNegocio(resp2.getMensaje());
							} else if (resp2.getEstatus() == 0) {
								pr2.setStatus(resp2.getMensaje());
							}
						}
					} catch (Exception e) {
						pr2=GenericException(e);
					}finally {
						pr2.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr2);
					}
				});
			} else {
				pr.setStatus("Por favor seleccione por lo menos un registro");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}		
	}

	public void consultar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Consultar datos");
		pr.setFechaInicial(DateUtil.getNowDate());
		try {
			if (lote != null) {
				seleccionados = 0;
				PlancharCurpOut resp2 = service.getDatosTrabajadores(lote);
				if (resp2.getEstatus() == 1) {
					this.listDatosTrabajadores = resp2.getListDatosTrabajadores();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					if (this.listDatosTrabajadores.size() > 0) {
						disabled = "false";
					} else {
						disabled = "true";
						disabled2 = "true";
					}
				} else {
					if (resp2.getEstatus() == 2) {
						GenerarErrorNegocio(resp2.getMensaje());
					} else if (resp2.getEstatus() == 0) {
						pr.setStatus(resp2.getMensaje());
					}
				}
			} else {
				border = "2px solid #ff0028 !important;";
				pr.setStatus("Por favor seleccione un lote");
				pr.setFechaFinal(DateUtil.getNowDate());
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void onRowSelect(SelectEvent<LoteRecaudacionIssste> event) {
		lote1 = new LoteRecaudacionIssste();
		lote1.setLote_carga(event.getObject().getLote_carga());
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteRecaudacionIssste car = (LoteRecaudacionIssste) value;
		return car.getLote_carga().toString().contains(filterText);
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			lote = lote1.getLote_carga();
			border = "";
		}
	}

	public void seleccion(SelectEvent<DatosTrabajador> event) {
		if (!selectedTrabajadores.contains(event.getObject())) {
			selectedTrabajadores.add(event.getObject());
		}
		if (selectedTrabajadores != null) {
			seleccionados = selectedTrabajadores.size();
			if (seleccionados > 0) {
				disabled2 = "false";
			} else {
				disabled2 = "true";
			}
		}
	}

	public void deseleccion(UnselectEvent<DatosTrabajador> event) {
		if (selectedTrabajadores.contains(event.getObject())) {
			selectedTrabajadores.remove(event.getObject());
		}
		if (selectedTrabajadores != null) {
			seleccionados = selectedTrabajadores.size();
			if (seleccionados > 0) {
				disabled2 = "false";
			} else {
				disabled2 = "true";
			}
		}
	}

	public void marcarTodos() {
		if (selectedTrabajadores != null) {
			seleccionados = selectedTrabajadores.size();
			if (seleccionados > 0) {
				disabled2 = "false";
			} else {
				disabled2 = "true";
			}
		}
	}

	public void desmarcarTodos() {
		if (selectedTrabajadores != null) {
			seleccionados = selectedTrabajadores.size();
			if (seleccionados > 0) {
				disabled2 = "false";
			} else {
				disabled2 = "true";
			}
		}
	}
}
