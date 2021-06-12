package mx.axxib.aforedigitalgt.ctrll;


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
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.serv.CerInaProServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** Funcionalidad: Controlador - Certificación de inactividad - Proceso
//** Desarrollador: JJSC
//** Fecha de creación: 18/Ene/2021
//** Última modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "cerInaProceso")
@ELBeanName(value = "cerInaProceso")
public class CerInaProcesoCtrll extends ControllerBase {

	@Autowired
	private CerInaProServ service;

	@Getter
	@Setter
	private String radioSelected;

	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	private Date fecActual;
	
	@Getter
	private String disabled;
	

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
			disabled="true";
			fecActual=DateUtil.getNowDate();		
			init=false;
		}
	}
	public void radioSelected() {
		fecha = null;
		disabled="false";	
	}
	public void reset() {
		fecha = null;		
		radioSelected = null;
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if (isFormValid(pr)) {
			try {
				pr.setDescProceso(radioSelected.equals("1") ? "Diagnóstico de parcialidades" : "Aprobación de movimientos");
				EjecucionResult resp = service.ejecutarProceso(Integer.valueOf(radioSelected), fecha);
				if(resp.getOn_Estatus()==1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));						
				}else {
					if(resp.getOn_Estatus()== 2) {
						GenerarErrorNegocio(resp.getOcMensaje());
					} else if(resp.getOn_Estatus()== 0) {
						pr.setStatus(resp.getOcMensaje());
					} 
				}		
			} catch (Exception e) {
				pr=GenericException(e);
			}finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}else {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public boolean isFormValid(ProcessResult pr) {
		if (radioSelected == null) {
			UIInput radio = (UIInput) findComponent("radSelect");
			radio.setValid(false);		
			pr.setDescProceso("Debe seleccionar una opción");
			pr.setStatus("Selección requerida");
			return false;
		}
		if (fecha == null) {
			UIInput radio = (UIInput) findComponent("fCapturada");
			radio.setValid(false);

			pr.setDescProceso("Debe seleccionar una fecha");
			pr.setStatus("Selección requerida");
			return false;
		}
		return true;
	}
}
