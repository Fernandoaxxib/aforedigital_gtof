package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.DesmarcaCargaConsultaMasivaService;

@Scope(value = "session")
@Component(value = "desmarcaMasiva")
@ELBeanName(value = "desmarcaMasiva")
public class DesmarcaMasivaCtrll extends ControllerBase {
	
	
	@Autowired
	private DesmarcaCargaConsultaMasivaService cargaMasiva;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private String rutaCarga;
	 
	@Getter
	@Setter
	private String nombreArchivoCarga;
	
//	@Getter
//	@Setter
//	private String desmarcaNSS;
//	
//	@Getter
//	@Setter
//	private String desmarcaCURP;
//	 
//	@Getter
//	@Setter
//	private String desmarcaCLAVE;
//	
//	@Getter
//	@Setter
//	private String claveMarca;
//	 
//	@Getter
//	@Setter
//	private String descripcionMarca;
//	
//	@Getter
//	@Setter
//	private String rutaMarca;
//	 
//	@Getter
//	@Setter
//	private String nombreMarca;
	
	@Getter
	private Date today;
	
	@Getter
	@Setter
	private ProcesoOut proceso;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			rutaCarga="/RESPALDOS/operaciones";		
			today= new Date();
			reset();
		}
	}
	
	public void reset() {
		nombreArchivoCarga=null;
	}
	
	public void cargarArchivo() {
		if(nombreArchivoCarga==null || nombreArchivoCarga.isEmpty()) {
			addMessageFail("Ingrese el nombre del archivo.");
		}else {
		try {System.out.println("VALOR DE rutaCarga:"+rutaCarga+" /nombreArchivoCarga:"+nombreArchivoCarga);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
			Date today= new Date();		
			proceso = new ProcesoOut();
			proceso.setFechahoraInicio(format.format(today));
			String resp =cargaMasiva.ejecutarArchivoCarga(rutaCarga, nombreArchivoCarga);
			Date today2= new Date();		
			proceso.setFechahoraFinal(format.format(today2));
			proceso.setAbrevProceso("Generar reporte");
			proceso.setEstadoProceso("Proceso ejecutado");		
			addMessageOK(resp);
//			if(msg.trim().toUpperCase().equals("OK")) {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
//			} else {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
//			}
		}catch (Exception e) {
			GenericException(e);
		}
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
	
//	public void desmarcaIndividualCuenta() {
//		try {System.out.println("VALOR DE desmarcaNSS:"+desmarcaNSS+" /desmarcaCURP:"+desmarcaCURP+" /desmarcaCLAVE:"+desmarcaCLAVE);
//			String msg =cargaMasiva.desmarcaIndividualCuenta(desmarcaNSS, desmarcaCURP,desmarcaCLAVE);
//			if(msg.trim().toUpperCase().equals("OK")) {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
//			} else {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
//			}
//		}catch (Exception e) {
//			GenericException(e);
//		}
//	}
	
//	public void consultaMarcas() {
//		try {System.out.println("VALOR DE claveMarca:"+claveMarca+" /descripcionMarca:"+descripcionMarca);
//			String msg =cargaMasiva.consultaMarcas(claveMarca, descripcionMarca);
//			if(msg.trim().toUpperCase().equals("OK")) {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
//			} else {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
//			}
//		}catch (Exception e) {
//			GenericException(e);
//		}
//	}
	
//	public void consultaMarcasArchivo() {
//		try {System.out.println("VALOR DE rutaMarca:"+rutaMarca+" /nombreMarca:"+nombreMarca);
//			String msg =cargaMasiva.consultaMarcasArchivo(rutaMarca, nombreMarca);
//			if(msg.trim().toUpperCase().equals("OK")) {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
//			} else {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
//			}
//		}catch (Exception e) {
//			GenericException(e);
//		}
//	}
	
	public void addMessageOK(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public void addMessageFail(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
