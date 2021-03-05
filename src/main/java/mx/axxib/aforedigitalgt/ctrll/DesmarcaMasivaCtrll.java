package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

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
@Component(value = "desmarcaMasiva")
@ELBeanName(value = "desmarcaMasiva")
public class DesmarcaMasivaCtrll extends ControllerBase {
	
	
	@Autowired
	private DesmarcaCargaConsultaMasivaService cargaMasiva;
	
	@Autowired
	private AforeMessage aforeMessage;
	
//	@Getter
//	@Setter
//	private String rutaCarga;
//	 
//	@Getter
//	@Setter
//	private String nombreArchivoCarga;
	
	@Getter
	@Setter
	private String desmarcaNSS;
	
	@Getter
	@Setter
	private String desmarcaCURP;
	 
	@Getter
	@Setter
	private String selectedTipoClave;
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
			
			today= new Date();
			reset();
		}
	}
	
	public void reset() {
		desmarcaNSS=null;
		desmarcaCURP=null;
		selectedTipoClave=null;
	}
	
//	public void cargarArchivo() {
//		if(nombreArchivoCarga==null || nombreArchivoCarga.isEmpty()) {
//			addMessageFail("Ingrese el nombre del archivo.");
//		}else {
//		try {System.out.println("VALOR DE rutaCarga:"+rutaCarga+" /nombreArchivoCarga:"+nombreArchivoCarga);
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//			Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
//			String resp =cargaMasiva.ejecutarArchivoCarga(rutaCarga, nombreArchivoCarga);
//			Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
//			proceso.setAbrevProceso("Generar reporte");
//			proceso.setEstadoProceso("Proceso ejecutado");		
//			addMessageOK(resp);
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
//	}
	
//	public void reversaArchivo() {
//		try {System.out.println("REVERSA CARGA");
//			String msg =cargaMasiva.reversaArchivoCarga();
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
	
	public void reversaDesmarcaMasiva() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Cargar Archivo");
		try {System.out.println("DESMARCA MASIVA REVERSA");
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//			Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
			String resp =cargaMasiva.desmarcaMasivaCuenta();
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
					pr.setStatus("Error al ejecutar la carga de Archivo");
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
	
	public void desmarcaIndividualCuenta() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Desmarca Masiva");
		try {System.out.println("VALOR DE desmarcaNSS:"+desmarcaNSS+" /desmarcaCURP:"+desmarcaCURP+" /selectedTipoClave:"+selectedTipoClave);
		
		
			if(desmarcaCURP == null || desmarcaCURP.isEmpty() || desmarcaNSS == null || desmarcaNSS.isEmpty() || selectedTipoClave==null || selectedTipoClave.isEmpty()) {
				boolean bandera=false;
				// addMessageFail("Ingresar CURP VALIDO");
				 if((desmarcaCURP == null || desmarcaCURP.isEmpty()) && (desmarcaNSS == null || desmarcaNSS.isEmpty()) && (selectedTipoClave==null || selectedTipoClave.isEmpty())) {
				 UIInput inputCurp = (UIInput) findComponent("curp");
				 inputCurp.setValid(false);
				 UIInput inputNss = (UIInput) findComponent("nss");
				 inputNss.setValid(false);
				 UIInput inputTipo = (UIInput) findComponent("tipoProceso");
				 inputTipo.setValid(false);				
				 pr.setStatus("Ingresar Curp,Nss y Tipo Proceso ");
				 bandera=true;
				 }
				 if((desmarcaCURP == null || desmarcaCURP.isEmpty()) && (desmarcaNSS == null || desmarcaNSS.isEmpty()) ) {
					 UIInput inputCurp = (UIInput) findComponent("curp");
					 inputCurp.setValid(false);
					 UIInput inputNss = (UIInput) findComponent("nss");
					 inputNss.setValid(false);
					 pr.setStatus("Ingresar Curp y Nss ");
					 bandera=true;
					 }
				 if((selectedTipoClave==null || selectedTipoClave.isEmpty()) && (desmarcaNSS == null || desmarcaNSS.isEmpty()) ) {
					 UIInput inputTipo = (UIInput) findComponent("tipoProceso");
					 inputTipo.setValid(false);				
					 UIInput inputNss = (UIInput) findComponent("nss");
					 inputNss.setValid(false);
					 pr.setStatus("Ingresar Tipo Proceso y Nss ");
					 bandera=true;
					 }
				 if((selectedTipoClave==null || selectedTipoClave.isEmpty()) && (desmarcaCURP == null || desmarcaCURP.isEmpty()) ) {
					 UIInput inputCurp = (UIInput) findComponent("curp");
					 inputCurp.setValid(false);
					 UIInput inputTipo = (UIInput) findComponent("tipoProceso");
					 inputTipo.setValid(false);				
					 pr.setStatus("Ingresar Tipo Proceso y Curp ");
					 bandera=true;
				 }
				if((selectedTipoClave==null || selectedTipoClave.isEmpty()) && bandera==false) {
					 UIInput inputTipo = (UIInput) findComponent("tipoProceso");
					 inputTipo.setValid(false);				
					 pr.setStatus("Ingresar Tipo Proceso");
				 }
				 if((desmarcaCURP == null || desmarcaCURP.isEmpty()) && bandera==false) {
					 UIInput inputCurp = (UIInput) findComponent("curp");
					 inputCurp.setValid(false);
					 pr.setStatus("Ingresar Curp ");
				 }
				 if((desmarcaNSS == null || desmarcaNSS.isEmpty()) && bandera==false) {
					 UIInput inputCurp = (UIInput) findComponent("nss");
					 inputCurp.setValid(false);
					 pr.setStatus("Ingresar Nss");
				 }
//				 if(desmarcaNSS == null || desmarcaNSS.isEmpty()) {
//					 UIInput input = (UIInput) findComponent("nss");
//						input.setValid(false);
//						 pr.setStatus("Ingresar Curp,Nss, Tipo Proceso ");
//				 }	
//		
//			if(desmarcaNSS == null || desmarcaNSS.isEmpty()) {
			
			
			
//		}else {
//			UIInput input = (UIInput) findComponent("nss");
//			input.setValid(false);
//			pr.setStatus("Ingresar Nss ");
//		}
	 
		 }else {
			 if (validarCURP(desmarcaCURP) && isNumeric(desmarcaNSS)) {
				 
					
//					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//					Date today= new Date();		
//					proceso = new ProcesoOut();
//					proceso.setFechahoraInicio(format.format(today));
					String resp =cargaMasiva.desmarcaIndividualCuenta(desmarcaNSS, desmarcaCURP,selectedTipoClave);
//					Date today2= new Date();		
//					proceso.setFechahoraFinal(format.format(today2));
					if(resp.contains("OCURRIO UN ERROR")) {
						pr.setStatus("Error al ejecutar la desmarca masiva");
//						proceso.setAbrevProceso("ERROR: <DECLARACION SENCILLA DEL FALLO PRESENTADO DURANTE EL PROCESO>");//"Generar reporte"
//						proceso.setEstadoProceso("FALLIDO");
//						addMessageFail("ERROR: <DECLARACION SENCILLA DEL FALLO PRESENTADO DURANTE EL PROCESO>");
						}else {
								pr.setStatus("Proceso ejecutado Correctamente");
//							 	proceso.setAbrevProceso(resp);//"Generar reporte"
//								proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
//								addMessageOK(resp);
						}
					
					
				 	}else{
						 //addMessageFail("Ingresar solo  digitos");
				 		boolean bandera=false;
				 		System.out.println("");
				 		 if ((validarCURP(desmarcaCURP)==false) && (isNumeric(desmarcaNSS)==false)) {
				 		 UIInput inputNss = (UIInput) findComponent("nss");
				 		 inputNss.setValid(false);
						 UIInput inputCurp = (UIInput) findComponent("curp");
						 inputCurp.setValid(false);
						 pr.setStatus("Ingresar Curp y Nss Valido ");
						 bandera=true;
				 		 }
				 		 if ((validarCURP(desmarcaCURP)==false) && bandera==false) {
					 		 UIInput inputCurp = (UIInput) findComponent("curp");
							 inputCurp.setValid(false);
							 pr.setStatus("Ingresar Curp Valido ");
						 }
				 		if ((isNumeric(desmarcaNSS)==false) && bandera==false) {
				 			UIInput inputNss = (UIInput) findComponent("nss");
					 		 inputNss.setValid(false);
							 pr.setStatus("Ingresar Nss Valido ");
						 }
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
	private boolean validarCURP(String curp)
	{ String regex = 
	  "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
	  "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
	  "[HM]{1}" +
	  "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
	  "[B-DF-HJ-NP-TV-Z]{3}" +
	  "[0-9A-Z]{1}[0-9]{1}$";

	  Pattern patron = Pattern.compile(regex);
	  if(!patron.matcher(curp).matches())
	  { 
		  return false;
	  }else
	  { 
		  return true;
	  }
	}
	private boolean isNumeric(String cadena){
        try {
                Integer.parseInt(cadena);
                return true;
        } catch (NumberFormatException nfe){
                return false;
        }
    }
}
