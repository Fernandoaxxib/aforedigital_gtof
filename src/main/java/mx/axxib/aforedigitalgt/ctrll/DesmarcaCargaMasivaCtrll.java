package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DesmarcaCargaConsultaMasivaOut;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.DesmarcaCargaConsultaMasivaService;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "desmarcaCargaMasiva")
@ELBeanName(value = "desmarcaCargaMasiva")
public class DesmarcaCargaMasivaCtrll extends ControllerBase {
	
	
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
	
	
	@Getter
	@Setter
	private DesmarcaCargaConsultaMasivaOut desmarcaCargaConsultaMasivaOut;
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
	
//	@Getter
//	@Setter
//	private ProcesoOut proceso;
	
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
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Cargar Archivo");
		try {
		if(nombreArchivoCarga==null || nombreArchivoCarga.isEmpty()) {
			//addMessageFail("Ingrese el nombre del archivo.");
			UIInput input = (UIInput) findComponent("nombreCarga");
			input.setValid(false);
			pr.setStatus("Ingrese nombre para la carga de Archivo");
		}else {
			if((nombreArchivoCarga.endsWith(".txt") || nombreArchivoCarga.endsWith(".TXT")) && (nombreArchivoCarga.length()>4)) {
			desmarcaCargaConsultaMasivaOut =cargaMasiva.ejecutarArchivoCarga(rutaCarga, nombreArchivoCarga);
			
			//if(desmarcaCargaConsultaMasivaOut.getP_Mensaje().equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...") ) {
			if(desmarcaCargaConsultaMasivaOut.getOn_Estatus()==1 ) {
			pr.setStatus("Proceso ejecutado Correctamente");
//			proceso.setAbrevProceso(resp);//"Generar reporte"
//			proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
//			addMessageOK(resp);
			}else {
//				proceso.setAbrevProceso(resp);//"Generar reporte"
//				proceso.setEstadoProceso("FALLIDO");
//				 addMessageFail(resp);
				pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
			}
			}else {
				UIInput input = (UIInput) findComponent("nombreCarga");
				input.setValid(false);
				pr.setStatus("Ingrese archivo con extensión .txt");	
			}
		}
//			if(msg.trim().toUpperCase().equals("OK")) {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
//			} else {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
//			}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	
	public void reversaArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Cargar Archivo");
		try {
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//			Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
		desmarcaCargaConsultaMasivaOut =cargaMasiva.reversaArchivoCarga();
//			Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
//			if(desmarcaCargaConsultaMasivaOut.getP_Mensaje().equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")) {
		if(desmarcaCargaConsultaMasivaOut.getOn_Estatus()==1 ) {
//				proceso.setAbrevProceso(resp);//"Generar reporte"
//				proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
//				addMessageOK(resp);
				pr.setStatus("Proceso ejecutado Correctamente");
				}else {
//					proceso.setAbrevProceso(resp);//"Generar reporte"
//					proceso.setEstadoProceso("FALLIDO");
//					 addMessageFail(resp);
					pr.setStatus("Error al ejecutar la reversa de Archivo");
				}
//			if(msg.trim().toUpperCase().equals("OK")) {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
//			} else {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
//			}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
//	public void desmarcaMasiva() {
//		try {System.out.println("REVERSA CARGA");
//			String msg =cargaMasiva.desmarcaMasivaCuenta();
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
