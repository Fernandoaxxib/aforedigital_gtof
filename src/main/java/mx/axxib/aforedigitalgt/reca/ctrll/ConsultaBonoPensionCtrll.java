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
import mx.axxib.aforedigitalgt.reca.eml.BonoOut;
import mx.axxib.aforedigitalgt.reca.eml.FechaCorteOut;
import mx.axxib.aforedigitalgt.reca.serv.ConsultaBonoPensionServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de  Consulta bono pensión
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "consultaBono")
@ELBeanName(value = "consultaBono")
public class ConsultaBonoPensionCtrll extends ControllerBase {

	@Autowired
	private ConsultaBonoPensionServ serv;
	
	
	@Getter
	private BonoOut bono;
	

	@Getter
	private FechaCorteOut fechaCorte;
	
	@Getter
	@Setter
	private String curp;
	
	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	@Setter
	private boolean mostrar;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			curp = null;
			limpiar();
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		
		fecha = null;
		bono = null;
		fechaCorte = null;
		mostrar = false;
		
	}
	
	public boolean isFormValid(ProcessResult pr) {
		if(curp == null || curp.equals("")) {
			UIInput fini = (UIInput) findComponent("curp");
			fini.setValid(false);
			pr.setStatus("CURP es requerido");
			return false;
		} else {
			if(!ValidateUtil.isCURP(curp)) {
				pr.setStatus("CURP no válido");
				return false;
			}
		}
		return true;
	}
	
	public boolean isFormValid2(ProcessResult pr) {
		if (fecha == null) {
			UIInput fini = (UIInput) findComponent("fecha");
			fini.setValid(false);

			pr.setStatus("Debe elegir un fecha");
			return false;
		}
		return true;
	}

	public void consultarBono() {
		ProcessResult pr = new ProcessResult();
		try {
			limpiar();
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar");
			if (isFormValid(pr)) {
				BonoOut res = serv.consultaBono(curp);  
				if (res.getEstatus() == 1) { 
					bono = res;
					mostrar = true;
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
	
	public void fechaCorte() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar fecha");
			if (isFormValid2(pr)) {
				FechaCorteOut res = serv.fechaCorte(fecha, bono.getCodCuenta());  
				if (res.getEstatus() == 1) { 
					fechaCorte = res;
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
