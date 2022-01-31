package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.Sello;
import mx.axxib.aforedigitalgt.reca.eml.SellosOut;
import mx.axxib.aforedigitalgt.reca.serv.ContingenciaServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Contingencia
//** Interventor Principal: JSAS
//** Fecha Creación: 27/Ene/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "contingencia")
@ELBeanName(value = "contingencia")
public class ContingenciaCtrll extends ControllerBase {

	@Autowired
	private ContingenciaServ serv;
	
	@Getter
	@Setter
	private Integer folioSol;
	
	@Getter
	private List<Sello> sellos;
	
	@Getter
	@Setter
	private Sello selectedSello;
	
	@Getter
	@Setter
	private String noSol;
	
	@Getter
	@Setter
	private String opcion;


	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			limpiar();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		folioSol = null;
		sellos = null;
		selectedSello = null;
		noSol = null;
		opcion = null;
	}
	
	public void buscarSellos() {
		ProcessResult pr = new ProcessResult();
		try {
			sellos = null;
			selectedSello = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar sellos");
			if (isFormValid(pr)) {
				SellosOut res = serv.sellos(folioSol);
				if (res.getEstatus() == 1) { 
					sellos = res.getSellos();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
	
	
	public boolean isFormValid(ProcessResult pr) {
		if (folioSol == null) {
			UIInput fini = (UIInput) findComponent("folioSol");
			fini.setValid(false);

			pr.setStatus("Debe introducir un folio");
			return false;
		}
		
		return true;
	}

	public void asignar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Asignar");
			if (selectedSello != null) {
				BaseOut res = serv.asignar(selectedSello.getSello(), folioSol.toString());
				if (res.getEstatus() == 1) { 
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
	
	public boolean isFormValid2(ProcessResult pr) {
		if (noSol == null) {
			UIInput fini = (UIInput) findComponent("noSol");
			fini.setValid(false);

			pr.setStatus("Debe introducir un número de solicitud");
			return false;
		}
		if (opcion == null) {
			UIInput radio = (UIInput) findComponent("opcion");
			radio.setValid(false);

			pr.setStatus("Debe elegir una opción");
			return false;
		}
		return true;
	}
	
	public void enviarExpediente() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Enviar expediente");
			if (isFormValid2(pr)) {
				BaseOut res = serv.expediente(opcion, noSol); //opcion="MASI" || "lo que sea para individual" 
				if (res.getEstatus() == 1) { 
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
	
}
