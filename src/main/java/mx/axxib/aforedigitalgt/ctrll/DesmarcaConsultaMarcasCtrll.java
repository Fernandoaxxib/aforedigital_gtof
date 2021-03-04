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
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.DesmarcaCargaConsultaMasivaService;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "desmarcaConsultaMarcas")
@ELBeanName(value = "desmarcaConsultaMarcas")
public class DesmarcaConsultaMarcasCtrll extends ControllerBase {
	
	
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
	private String selectedTipoClave;
	
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
			rutaCarga="/RESPALDOS/operaciones/pruebas";		
			today= new Date();
			reset();
		}
	}
	
	public void reset() {
		nombreArchivoCarga=null;
		selectedTipoClave=null;
	}
	
	public void cargarArchivo() {
		
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Consulta Marca por Nss y/o Curp");
		try {System.out.println("VALOR DE rutaCarga:"+rutaCarga+" /nombreArchivoCarga:"+nombreArchivoCarga);
		
		if(nombreArchivoCarga== null || nombreArchivoCarga.isEmpty()) {
			UIInput input = (UIInput) findComponent("nombreCarga");
			input.setValid(false);
			pr.setStatus("Ingrese nombre de archivo");
		}else {
		
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//			Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
			String resp =cargaMasiva.consultaMarcasArchivo(rutaCarga, nombreArchivoCarga);
//			Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
			if(resp.equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")) {
//				proceso.setAbrevProceso(resp);//"Generar reporte"
//				proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
//				addMessageOK(resp);
				pr.setStatus("Proceso ejecutado Correctamente");
				}else {
//					proceso.setAbrevProceso(resp);//"Generar reporte"
//					proceso.setEstadoProceso("FALLIDO");
//					 addMessageFail(resp);
					pr.setStatus("Error al ejecutar por Nss y Curp");
				}
//			if(msg.trim().toUpperCase().equals("OK")) {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
//			} else {
//				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
//			}
		}
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
		pr.setDescProceso("Consulta Marca por Proceso Operativo");
		
		try {System.out.println("REVERSA CARGA valor de selectedTipoClave:"+selectedTipoClave);
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//			Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
		
		if(selectedTipoClave==null || selectedTipoClave.isEmpty()) {
			 UIInput inputTipo = (UIInput) findComponent("tipoProceso");
			 inputTipo.setValid(false);				
			 pr.setStatus("Ingresar Tipo Proceso ");
			
		}else {
			String resp =cargaMasiva.consultaMarcas(selectedTipoClave,selectedTipoClave);
//			Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
			
			if(resp.equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")) {
//				proceso.setAbrevProceso(resp);//"Generar reporte"
//				proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
//				addMessageOK(resp);
				pr.setStatus("Proceso ejecutado Correctamente");
				}else {
//					proceso.setAbrevProceso(resp);//"Generar reporte"
//					proceso.setEstadoProceso("FALLIDO");
//					 addMessageFail(resp);
					pr.setStatus("Error al ejecutar por Proceso operativo");
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
