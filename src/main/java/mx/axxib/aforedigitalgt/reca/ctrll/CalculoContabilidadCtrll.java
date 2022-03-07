package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.Date;

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
import mx.axxib.aforedigitalgt.reca.serv.CalculoContabilidadServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Cálculo Contabilidad
//** Interventor Principal: JSAS
//** Fecha Creación: 8/Mar/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "calculoCont")
@ELBeanName(value = "calculoCont")
public class CalculoContabilidadCtrll extends ControllerBase {

	@Autowired
	private CalculoContabilidadServ serv;
	
	
	@Getter
	@Setter
	private Date fecha;
	
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
		fecha = null;
		
	}
	
	
	public boolean isFormValid(ProcessResult pr) {
		if (fecha == null) {
			UIInput fini = (UIInput) findComponent("fecha");
			fini.setValid(false);

			pr.setStatus("Debe elegir un fecha");
			return false;
		}
		
		return true;
	}

	
	
	public void calculoDiario() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cálculo diario");
			if (isFormValid(pr)) {
				BaseOut res = serv.calculoDiario(fecha);  
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
	
	public void archivo() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Archivo de contabilidad");
			if (isFormValid(pr)) {
				BaseOut res = serv.archivoContabilidad(fecha);  
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
	
	public void calculoBono() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cálculo del bono");
			if (isFormValid(pr)) {
				BaseOut res = serv.calculoBono(fecha);  
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
	
	public void generacionAuto() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Generación automática");
			if (isFormValid(pr)) {
				BaseOut res = serv.generacionAuto(fecha);  
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
