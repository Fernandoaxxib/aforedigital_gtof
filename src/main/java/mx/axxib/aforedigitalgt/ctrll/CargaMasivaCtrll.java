package mx.axxib.aforedigitalgt.ctrll;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.serv.CargaMasivaService;

@Scope(value = "session")
@Component(value = "cargaMasiva")
@ELBeanName(value = "cargaMasiva")
public class CargaMasivaCtrll extends ControllerBase {
	
	
	@Autowired
	private CargaMasivaService cargaMasiva;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private String rutaCarga;
	 
	@Getter
	@Setter
	private String nombreCarga;
	
	@Getter
	@Setter
	private String desmarcaNSS;
	
	@Getter
	@Setter
	private String desmarcaCURP;
	 
	@Getter
	@Setter
	private String desmarcaCLAVE;
	
	@Getter
	@Setter
	private String claveMarca;
	 
	@Getter
	@Setter
	private String descripcionMarca;
	
	@Getter
	@Setter
	private String rutaMarca;
	 
	@Getter
	@Setter
	private String nombreMarca;
	
	public void cargarArchivo() {
		try {System.out.println("VALOR DE rutaCarga:"+rutaCarga+" /nombreCarga:"+nombreCarga);
			String msg =cargaMasiva.ejecutarArchivoCarga(rutaCarga, nombreCarga);
			if(msg.trim().toUpperCase().equals("OK")) {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void reversaArchivo() {
		try {System.out.println("REVERSA CARGA");
			String msg =cargaMasiva.reversaArchivoCarga();
			if(msg.trim().toUpperCase().equals("OK")) {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void desmarcaMasiva() {
		try {System.out.println("REVERSA CARGA");
			String msg =cargaMasiva.desmarcaMasivaCuenta();
			if(msg.trim().toUpperCase().equals("OK")) {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void desmarcaIndividualCuenta() {
		try {System.out.println("VALOR DE desmarcaNSS:"+desmarcaNSS+" /desmarcaCURP:"+desmarcaCURP+" /desmarcaCLAVE:"+desmarcaCLAVE);
			String msg =cargaMasiva.desmarcaIndividualCuenta(desmarcaNSS, desmarcaCURP,desmarcaCLAVE);
			if(msg.trim().toUpperCase().equals("OK")) {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void consultaMarcas() {
		try {System.out.println("VALOR DE claveMarca:"+claveMarca+" /descripcionMarca:"+descripcionMarca);
			String msg =cargaMasiva.consultaMarcas(claveMarca, descripcionMarca);
			if(msg.trim().toUpperCase().equals("OK")) {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void consultaMarcasArchivo() {
		try {System.out.println("VALOR DE rutaMarca:"+rutaMarca+" /nombreMarca:"+nombreMarca);
			String msg =cargaMasiva.consultaMarcasArchivo(rutaMarca, nombreMarca);
			if(msg.trim().toUpperCase().equals("OK")) {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}
}
