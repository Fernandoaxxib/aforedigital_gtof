package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.StringUtils;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DesmarcaCargaConsultaMasivaOut;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.TipoProcesoOut;
import mx.axxib.aforedigitalgt.serv.DesmarcaCargaConsultaMasivaService;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

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
	private String nssCURP;
	 
	@Getter
	@Setter
	private String selectedTipoClave;
	
	@Getter
	@Setter
	private DesmarcaCargaConsultaMasivaOut desmarcaCargaConsultaMasivaOut;
	
	@Getter
	@Setter
	private List<TipoProcesoOut> listaTipoProceso;
	
	@Getter
	@Setter
	private Integer radioSelected;
	
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
			consultarTodo();
			today= new Date();
			reset();
		}
	}
	
	public void reset() {
		desmarcaNSS=null;
		desmarcaCURP=null;
		selectedTipoClave=null;
		nssCURP=null;
	}
	
	public List<TipoProcesoOut> consultarTodo(){
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Cargar Clave Proceso");
		try {
			listaTipoProceso=cargaMasiva.consultarTodo();
			pr.setStatus("Proceso ejecutado Correctamente");
			System.out.println("VALOR DE  LISTA TIPO PROCESO "+listaTipoProceso.size()+" VALOR: "+listaTipoProceso.get(0));
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		return listaTipoProceso;
	}
	
	
	public void desmarcaMasivaCuenta() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Desmarca Masiva Cuentas");
		try {
			 desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaMasivaCuenta();
			 pr.setStatus("Proceso ejecutado Correctamente");
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	} 
		
	public void validarNssRfc() {
	ProcessResult pr = new ProcessResult();
	pr.setFechaInicial(DateUtil.getNowDate());
	pr.setDescProceso("Desmarca Masiva Cuentas");
		try {
			if (nssCURP != null && !nssCURP.equals("") ) {
				
				 String[] parts = selectedTipoClave.split("-");
				 String part1 = parts[0]; // 123
				 String part2 = parts[1]; // 654321
				
				if((nssCURP.length() > 0 && nssCURP.length()<=11) && StringUtils.isNumeric(nssCURP) && radioSelected== 1) {
						
					desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaIndividualCuenta(radioSelected,desmarcaNSS, null,part1);	
					
					System.out.println("ESTATUS: "+desmarcaCargaConsultaMasivaOut.getOn_Estatus()+ " MENSAJE: "+desmarcaCargaConsultaMasivaOut.getP_Mensaje());
					if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 1) {
						pr.setStatus("Proceso ejecutado Correctamente");//"Consulta Exitosa"
						
						
					}else {
						if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 2) {
							GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
						} else if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 0) {
							pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
						}
					//pr.setStatus("Proceso ejecutado Correctamente");
					
					
					System.out.println("ES NSS");
					}	 
				}
				if(StringUtils.isNumeric(nssCURP)==false && (nssCURP.length() > 0 && nssCURP.length()<=11) ){
					 UIInput inputNss = (UIInput) findComponent("nssCURP");
			 		 inputNss.setValid(false);
					 pr.setStatus("Ingresar NSS Valido ");	
				}
				if (ValidateUtil.isCURP(nssCURP)) {
				 
					 desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaIndividualCuenta(radioSelected,null, desmarcaCURP,part1);
					 System.out.println("ESTATUS: "+desmarcaCargaConsultaMasivaOut.getOn_Estatus()+ " MENSAJE: "+desmarcaCargaConsultaMasivaOut.getP_Mensaje());
						if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 1) {
							pr.setStatus("Proceso ejecutado Correctamente");//"Consulta Exitosa"
							
							
						}else {
							if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 2) {
								GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
							} else if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 0) {
								pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
							}
					 
					 //pr.setStatus("Proceso ejecutado Correctamente");
					 System.out.println("ES CURP");
						}
				}
				if(ValidateUtil.isCURP(nssCURP) ==false && nssCURP.length()>11 ) {
									
				 		 UIInput inputCurp = (UIInput) findComponent("nssCURP");
						 inputCurp.setValid(false);
						 pr.setStatus("Ingresar CURP Valido ");
				
				}
			
			 
			}else {
				UIInput input = (UIInput) findComponent("nssCURP");
				input.setValid(false);
				pr.setStatus("NSS o CURP es requerido");
				
			}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
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
		pr.setDescProceso("Reversa Carga Archivo");
		try {System.out.println("DESMARCA MASIVA REVERSA");
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//			Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
		desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaMasivaCuenta();
		
		//Ordenas Empleados con Lambda Expresion
       // Collections.sort(listaTipoProceso, (x, y) -> x.getCLAVE_PROCESO().compareToIgnoreCase(y.getCLAVE_PROCESO()));
      //  System.out.println("VALOR DE  LISTA TIPO PROCESO ORDENADA "+listaTipoProceso.size()+" VALOR: "+listaTipoProceso.get(0));
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
		pr.setDescProceso("Desmarca Individual Cuenta");
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
			 if (validarCURP(desmarcaCURP) ) {//&& isNumeric(desmarcaNSS)
				 
					
//					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//					Date today= new Date();		
//					proceso = new ProcesoOut();
//					proceso.setFechahoraInicio(format.format(today));
				
				 String[] parts = selectedTipoClave.split("-");
				 String part1 = parts[0]; // 123
				 String part2 = parts[1]; // 654321
				 
				 desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaIndividualCuenta(0,desmarcaNSS, desmarcaCURP,part1);
//					Date today2= new Date();		
//					proceso.setFechahoraFinal(format.format(today2));
				 
				 
				 if(desmarcaCargaConsultaMasivaOut.getOn_Estatus()!=1 ) {
//					if(desmarcaCargaConsultaMasivaOut.getP_Mensaje().contains("OCURRIO UN ERROR")) {
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
				 		
				 		 if ((validarCURP(desmarcaCURP)==false)) { // && (isNumeric(desmarcaNSS)==false)
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
