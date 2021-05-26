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
	private String radioSelected;
	

	@Getter
	private Date today;
	

	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			radioSelected="1";
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
		}catch (Exception e) {
			GenericException(e);
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
		     pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());//"Consulta Exitosa"
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
			
			
			
			if((selectedTipoClave != null ) && (!nssCURP.equals("") || nssCURP!="")  ) {
			
				 String[] parts = selectedTipoClave.split("-");
				 String part1 = parts[0]; // 
				 String part2 = parts[1]; // 
				System.out.println("VALOR DE RADIOSELECT;"+radioSelected+ "  nssCURP"+nssCURP);
				if(nssCURP.length()==11  && (radioSelected.equals("1") || radioSelected== "1")) {//&& StringUtils.isNumeric(nssCURP)
					System.out.println("ES NSS *****************");	
					
					
					desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaIndividualCuenta(Integer.parseInt(radioSelected),nssCURP, null,part1);	
					System.out.println("validarNssRfc: "+desmarcaCargaConsultaMasivaOut);
					System.out.println("ESTATUS: "+desmarcaCargaConsultaMasivaOut.getOn_Estatus()+ " MENSAJE: "+desmarcaCargaConsultaMasivaOut.getP_Mensaje());
					if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 1) {
						pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());//"Consulta Exitosa"
						
						
					}else {
						if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 2) {
							GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
						} else if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 0) {
							pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
						}
					}	 
				}
				if(StringUtils.isNumeric(nssCURP)==false && (radioSelected.equals("1") || radioSelected== "1" )){
					 UIInput inputNss = (UIInput) findComponent("nssCURP");
			 		 inputNss.setValid(false);
			 		 
					 pr.setStatus("Ingresar NSS Valido ");	//selectedTipoClave
					 
				}
				if(StringUtils.isNumeric(nssCURP)==true && nssCURP.length()!=11 && (radioSelected.equals("1") || radioSelected== "1" )){
					 UIInput inputNss = (UIInput) findComponent("nssCURP");
			 		 inputNss.setValid(false);
					 pr.setStatus("Ingresar NSS Valido ");	//selectedTipoClave
					 
				}
				if (ValidateUtil.isCURP(nssCURP) && (radioSelected.equals("2") || radioSelected== "2")) {
				 System.out.println("ES CURP *****************");
					 desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaIndividualCuenta(Integer.parseInt(radioSelected),null, nssCURP,part1);
					 System.out.println("ESTATUS: "+desmarcaCargaConsultaMasivaOut.getOn_Estatus()+ " MENSAJE: "+desmarcaCargaConsultaMasivaOut.getP_Mensaje());
						if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 1) {
							pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());//"Consulta Exitosa"
							
							
						}else {
							if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 2) {
								GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
							} else if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 0) {
								pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
							}
					 
					}
						 
				}
				if(ValidateUtil.isCURP(nssCURP) ==false && (radioSelected.equals("2") || radioSelected== "2") ) {
									
				 		 UIInput inputCurp = (UIInput) findComponent("nssCURP");
						 inputCurp.setValid(false);
						 pr.setStatus("Ingresar CURP Valido ");
				
				}
			

			}else {
				int bandera=0;

				if((selectedTipoClave == null || selectedTipoClave == "") && (nssCURP.equals("") || nssCURP=="") &&  radioSelected.equals("1") && bandera==0 ) {//
				UIInput nsssCURP = (UIInput) findComponent("nssCURP");
				nsssCURP.setValid(false);
				UIInput tipoProceso = (UIInput) findComponent("tipoProceso");
				tipoProceso.setValid(false);
				pr.setStatus("Clave Proceso y NSS es requerido");//
				bandera=1;
				}
				
				if((selectedTipoClave == null || selectedTipoClave == "") && (nssCURP.equals(null) || nssCURP=="") && radioSelected.equals("2") && bandera==0) {//
					UIInput nsssCURP = (UIInput) findComponent("nssCURP");
					nsssCURP.setValid(false);
					UIInput tipoProceso = (UIInput) findComponent("tipoProceso");
					tipoProceso.setValid(false);
					pr.setStatus("Clave Proceso y CURP es requerido");//
					bandera=1;
					}
				if((nssCURP.equals(null) || nssCURP=="") && radioSelected.equals("1") && bandera==0) {//
					UIInput nsssCURP = (UIInput) findComponent("nssCURP");
					nsssCURP.setValid(false);
					pr.setStatus("NSS es requerido");//
					bandera=1;
					}
				if((nssCURP.equals(null) || nssCURP=="") && radioSelected.equals("2") && bandera==0 ) {//
					UIInput nsssCURP = (UIInput) findComponent("nssCURP");
					nsssCURP.setValid(false);
					pr.setStatus("CURP es requerido");//
					bandera=1;
					}
				if((selectedTipoClave == null || selectedTipoClave == "") && bandera==0 ) {//
					UIInput tipoProceso = (UIInput) findComponent("tipoProceso");
					tipoProceso.setValid(false);
					pr.setStatus("Clave Proceso es requerido");//
					bandera=1;
					}
				

			}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	
	public void reversaDesmarcaMasiva() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Reversa Carga Archivo");
		try {
		System.out.println("DESMARCA MASIVA REVERSA");
		
		if(selectedTipoClave==null || selectedTipoClave.isEmpty()) {
			 UIInput inputTipo = (UIInput) findComponent("tipoProceso");
			 inputTipo.setValid(false);				
			 pr.setStatus("Seleccionar Clave Proceso ");
			
		}else {
		
		desmarcaCargaConsultaMasivaOut =cargaMasiva.desmarcaMasivaCuenta();
		
		System.out.println("reversaDesmarcaMasiva: "+desmarcaCargaConsultaMasivaOut);
		if(desmarcaCargaConsultaMasivaOut.getOn_Estatus()==1 ) {
			pr.setStatus("Proceso ejecutado Correctamente");
				}else {
					if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 2) {
						GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
					} else if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 0) {
						pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
					}
				}	 
		}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	

	
	
}
