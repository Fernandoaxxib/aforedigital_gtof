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
import mx.axxib.aforedigitalgt.reca.eml.Aportaciones;
import mx.axxib.aforedigitalgt.reca.eml.AportacionesOut;
import mx.axxib.aforedigitalgt.reca.eml.Independientes;
import mx.axxib.aforedigitalgt.reca.eml.IndependientesOut;
import mx.axxib.aforedigitalgt.reca.serv.AportacionesVoluntariasServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Interfaces
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Mar/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "aporVol")
@ELBeanName(value = "aporVol")
public class AportacionesVoluntariasCtrll extends ControllerBase {

	@Autowired
	private AportacionesVoluntariasServ serv;
	
	
	@Getter
	private List<Aportaciones> aportaciones;
	
	@Getter
	private List<Independientes> independientes;
	
	@Getter
	@Setter
	private Integer sucursal;
	
	@Getter
	@Setter
	private Integer sucursal2;
	
	@Getter
	private String nombre;
	
	@Getter
	private String nombre2;
	
		
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
		aportaciones = null;
		independientes = null;
		sucursal = null;
		sucursal2 = null;
		nombre = null;
		nombre2 = null;
	}
	
	
	public boolean isFormValid(ProcessResult pr) {
		if (sucursal == null) {
			UIInput radio = (UIInput) findComponent("sucursal");
			radio.setValid(false);

			pr.setStatus("Debe introducir una sucursal");
			return false;
		}
		return true;
	}
	
	public boolean isFormValid2(ProcessResult pr) {
		if (sucursal2 == null) {
			UIInput radio = (UIInput) findComponent("sucursal2");
			radio.setValid(false);

			pr.setStatus("Debe introducir una sucursal");
			return false;
		}
		return true;
	}
	

	public void consultarAportaciones() {
		ProcessResult pr = new ProcessResult();
		try {
			aportaciones = null;
			nombre = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar voluntarias");
			if (isFormValid(pr)) {
				AportacionesOut res = serv.aportaciones(sucursal);  
				if (res.getEstatus() == 1) { 
					aportaciones = res.getDatos();
					nombre = res.getNombre();
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
	
	public void consultarIndependientes() {
		ProcessResult pr = new ProcessResult();
		try {
			independientes = null;
			nombre2 = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar independientes");
			if (isFormValid2(pr)) {
				IndependientesOut res = serv.independientes(sucursal2);  
				if (res.getEstatus() == 1) { 
					independientes = res.getDatos();
					nombre2 = res.getNombre();
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
