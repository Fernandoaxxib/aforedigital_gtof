package mx.axxib.aforedigitalgt.reca.ctrll;

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
import mx.axxib.aforedigitalgt.reca.eml.AportacionesVoluntariasOut;
import mx.axxib.aforedigitalgt.reca.serv.AportacionesVoluntariasServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Interfaces de Aportaciones Voluntarias
//** Interventor Principal: EAG
//** Fecha Creación: 07/Abril/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "aportacionesVoluntarias")
@ELBeanName(value = "aportacionesVoluntarias")
public class AportacionesVoluntariasCtrll extends ControllerBase {

	@Autowired
	private AportacionesVoluntariasServ serv;
	
	@Getter
	@Setter
	private Integer sucursal;
	
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
		
		sucursal = null;
		
	}
	
	public void consultaAportacionesVoluntarias() {
		ProcessResult pr = new ProcessResult();
		try {
			if (sucursal != null) {
				AportacionesVoluntariasOut res = serv.consultaAportacionesVoluntarias(sucursal);
				if (res.getEstatus() == 1) { 
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			} else {
				UIInput fini = (UIInput) findComponent("sucursal");
				fini.setValid(false);

				pr.setStatus("Debe elegir una sucursal");
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			resultados.add(pr);
		}
	}
	
	
	public void consultaAportacionesVoluntariasIndependientes() {
		ProcessResult pr = new ProcessResult();
		try {
			if (sucursal != null) {
				AportacionesVoluntariasOut res = serv.consultaAportacionesVoluntarias(sucursal);
				if (res.getEstatus() == 1) { 
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			} else {
				UIInput fini = (UIInput) findComponent("sucursal");
				fini.setValid(false);

				pr.setStatus("Debe elegir una sucursal");
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			resultados.add(pr);
		}
	}
	
	
}
