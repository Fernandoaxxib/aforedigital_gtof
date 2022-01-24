package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIInput;

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
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.Lote;
import mx.axxib.aforedigitalgt.reca.eml.LotesOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaRechazoEjecutarIn;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionPatronal;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionPatronalOut;
import mx.axxib.aforedigitalgt.reca.serv.RecaudacionIMSSRechazoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudacion IMSS Rechazo
//** Interventor Principal: JSAS
//** Fecha Creación: 24/Nov/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "recaudacionRechazo")
@ELBeanName(value = "recaudacionRechazo")
public class RecaudacionIMSSRechazoCtrll extends ControllerBase {

	@Autowired
	private RecaudacionIMSSRechazoServ serv;

	@Getter
	@Setter
	private Lote lote;

	@Getter
	@Setter
	private List<Lote> lotes;

	@Getter
	@Setter
	private List<RecaudacionPatronal> rechazos;

	@Getter
	private String mensajeTabla;

	@Getter
	private boolean mostrarEjecutar;

	@Getter
	private Integer seleccionados;

	@Getter
	@Setter
	private List<RecaudacionPatronal> selectedSolicitud;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			lote = null;
			lotes = null;
			limpiar();
			consultarLotes();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		seleccionados = null;
		mensajeTabla = null;
		mostrarEjecutar = false;
		rechazos = null;
	}

	public void consultarLotes() {
		ProcessResult pr = new ProcessResult();

		try {

			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar lotes");

			LotesOut res = serv.lotes();
			if (res.getEstatus() == null) { // TODO sustituir null por 1, actualmente el stored no devuelve status
				lotes = res.getLotes();
				if (lotes.size() == 0) {
					pr.setStatus("No se encontraron lotes");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}

		} catch (Exception e) {
			resultados.add(GenericException(e));
		}
	}
	
	public void changeLote()  {
		 limpiar();
	}

	public boolean isFormValid(ProcessResult pr) {
		if (lote == null) {
			UIInput radio = (UIInput) findComponent("lotes");
			radio.setValid(false);

			pr.setStatus("Debe elegir un Id operación");
			return false;
		}

		return true;
	}

	public void buscar() {
		ProcessResult pr = new ProcessResult();

		try {
			limpiar();
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar");

			if (isFormValid(pr)) {
				RecaRechazoEjecutarIn parametros = new RecaRechazoEjecutarIn();
				if (lote != null) {
					parametros.setFechaLote(lote.getFechaLote());
					parametros.setIdOperacion(lote.getIdOperacion());
					parametros.setSecLote(lote.getSecLote());
				}
				RecaudacionPatronalOut res; //serv.patronal(parametros);
				res = new RecaudacionPatronalOut();
				if (res.getEstatus() == null) { // TODO sustituir null por 1
					rechazos = res.getRechazos();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					if (rechazos != null && rechazos.size() > 0) {
						mostrarEjecutar = true;
					} else {
						//mensajeTabla = "Sin información";
						//TODO: Dummy información, quitar al final
						mostrarEjecutar = true;
						RecaudacionPatronal o1 = new RecaudacionPatronal();
						o1.setNss("AAAAAAAAAA");
						RecaudacionPatronal o2 = new RecaudacionPatronal();
						o2.setNss("BBBBBBBBBB");
						rechazos = new ArrayList<RecaudacionPatronal>();
						rechazos.add(o1);
						rechazos.add(o2);
					}
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Procesar");
			if (isFormValid(pr) && selectedSolicitud != null && selectedSolicitud.size() > 0) {
				for(RecaudacionPatronal r : selectedSolicitud){
					RecaRechazoEjecutarIn parametros = new RecaRechazoEjecutarIn();
					if (lote != null) {
						parametros.setFechaLote(lote.getFechaLote());
						parametros.setIdOperacion(lote.getIdOperacion());
						parametros.setSecLote(lote.getSecLote());
						parametros.setConsecutivo(r.getConsecutivo());
						parametros.setIdServicio(r.getIdServicio());
						parametros.setNss(r.getNss());
						parametros.setTipoRegistro(r.getTipoRegistro());
					}
					BaseOut res; //serv.rechazoEjecutar(parametros);
					res = new BaseOut();
					if (res.getEstatus() == null) { // TODO sustituir null por 1
						//pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						if (res.getEstatus() == 2) {
							GenerarErrorNegocio(res.getMensaje());
						} else if (res.getEstatus() == 0) {
							pr.setStatus(res.getMensaje());
						}
					}
				}
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public int getCount() {
		if (rechazos != null) {
			return rechazos.size();
		} else {
			return 0;
		}
	}

	public void seleccion(SelectEvent<RecaudacionPatronal> event) {
		if (!selectedSolicitud.contains(event.getObject())) {
			selectedSolicitud.add(event.getObject());
		}
		if (selectedSolicitud != null) {
			seleccionados = selectedSolicitud.size();
		}
	}

	public void deseleccion(UnselectEvent<RecaudacionPatronal> event) {
		if (selectedSolicitud.contains(event.getObject())) {
			selectedSolicitud.remove(event.getObject());
		}

		if (selectedSolicitud != null) {
			seleccionados = selectedSolicitud.size();
		}
	}

	public void marcarTodos() {
		if (selectedSolicitud != null) {
			seleccionados = selectedSolicitud.size();
		}
	}

	public void desmarcarTodos() {
		if (selectedSolicitud != null) {
			seleccionados = selectedSolicitud.size();
		}
	}

}
