package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.regex.Pattern;

import javax.faces.component.UIInput;

import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ConsultarDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosIcefasOut;
import mx.axxib.aforedigitalgt.eml.CpDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoInsertTablaOut;
import mx.axxib.aforedigitalgt.serv.ConsultarMarcasCuentasService;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "consultarMarcasCuentas")
@ELBeanName(value = "consultarMarcasCuentas")
public class ConsultarMarcasCuentasCtrll extends ControllerBase {

	@Autowired
	ConsultarMarcasCuentasService consultarMarcasCuentasServices;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	List<CpDatosIcefasOut> cpDatos;
	
	@Getter
	@Setter
	private CpDatosIcefasOut cpDatos2;
	
	@Getter
	List<ConsultarDatosIcefasOut> cpCursor;
	
	@Getter
	@Setter
	private String nombre;
	@Getter
	@Setter
	private Integer cuenta;
	@Getter
	@Setter
	private String curp_o_nssIn;
	
	@Getter
	@Setter
	private String curp_o_nss_Out;
	@Getter
	private String mensaje;
	
	@Getter
	@Setter
	private String codCuenta;
	@Getter
	@Setter
	private String codTipoSaldo;
	@Getter
	@Setter
	private String claveProceso;
	@Getter
	@Setter
	private String estado;
	
	@Getter
	@Setter
	private String nss;
	 
	@Getter
	@Setter
	private String curp;
	
	@Getter
	@Setter
	private String valor;
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	@Setter
	private Integer totalSolicitud;	
	
	@Getter
	@Setter
	private Integer totalNSSCURP;	
	
	@Getter
	@Setter
	private String radioSelected;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
		radioSelected="1";
		curp_o_nssIn=null;
		limpiar();
		init = false;	
	}
	}
	private void limpiar() {
		nombre=null;
		nss=null;
		curp =null;
		cuenta=null;
		cpDatos=null;
		cpCursor=null;
		totalSolicitud=0;
		totalNSSCURP=0;
	}
	public void validarNssRfc() {
		ProcessResult pr = new ProcessResult();
		limpiar();
		pr.setFechaInicial(DateUtil.getNowDate());
		try {
		
		System.out.println("VALOR DE curp_o_nssIn: "+curp_o_nssIn);
			if (curp_o_nssIn != null && !curp_o_nssIn.equals("") ) {
				int bandera=0;	
				
					if (ValidateUtil.isCURP(curp_o_nssIn)) {
						pr.setDescProceso("Búsqueda por CURP");

						try {
							ConsultarNombreCuentaIcefasOut res=consultarMarcasCuentasServices.getConsultarCurp(curp_o_nssIn);	
							System.out.println("VALOR DE RES: "+res);
							if(res.getStatus()==1) {
									if (res.getCpDatos().size() > 0) {
										totalNSSCURP=res.getCpDatos().size();
										cpDatos=res.getCpDatos();
										nombre=res.getNombre();
										cuenta=res.getCuenta();
										nss=res.getCurp_o_nss();
										mensaje=res.getMensaje();
										curp=curp_o_nssIn;
										valor="1";
										pr.setStatus("Consulta Exitosa por CURP");
									}else {
										pr.setStatus("No se encontraron resultados por NSS");
										mensajeTabla = "Sin información";
									}
							}else {
								if(res.getStatus()==2) {
									GenerarErrorNegocio(res.getMensaje());	
								} else if (res.getStatus() == 0) {
									pr.setStatus("Verifique su CURP, no existen datos para :"+curp_o_nssIn);//res.getMensaje()
								}
							}
							
						}catch (Exception e) {
							pr=GenericException(e);
							 bandera=1;
						}
						bandera=1;
				    }
					if(isNSS()) {
						pr.setDescProceso("Búsqueda por NSS");						
						try {
							ConsultarNombreCuentaIcefasOut res=consultarMarcasCuentasServices.getConsultarNss(curp_o_nssIn);	
							System.out.println("VALOR DE RES: "+res);
									if (res.getStatus()==1) {	
										if(res.getCpDatos().size() > 0) {
											totalNSSCURP=res.getCpDatos().size();
											cpDatos=res.getCpDatos();
											nombre=res.getNombre();
											cuenta=res.getCuenta();
											curp=res.getCurp_o_nss();
											mensaje=res.getMensaje();
											nss=curp_o_nssIn;
											valor="1";
											pr.setStatus("Consulta Exitosa por NSS");	
										}else {
											pr.setStatus("No se encontraron resultados por NSS");
											mensajeTabla = "Sin información";
										}
							}else {
								if(res.getStatus()==2) {
									GenerarErrorNegocio(res.getMensaje());	
								} else if (res.getStatus() == 0) {
									pr.setStatus("Verifique su NSS, no existen datos para :"+curp_o_nssIn);//res.getMensaje()
								}
							}
							bandera=1;
						}catch (Exception e) {
							// TODO: handle exception
							//pr.setStatus("No se encontraron resultados por NSS");
							pr=GenericException(e);
							 bandera=1;
						}
				    }
					if(curp_o_nssIn.length() != 11 && StringUtils.isNumeric(curp_o_nssIn) == true) {
				
						
						 UIInput inputCurp = (UIInput) findComponent("curpNSS");
						 inputCurp.setValid(false);
						 pr.setDescProceso("Búsqueda por NSS");
						 pr.setStatus("Ingresar NSS Valido ");
						 bandera=1;
				    }
					if(curp_o_nssIn.equals("00000000000") && bandera==0) {
								 
						UIInput inputCurp = (UIInput) findComponent("curpNSS");
						 inputCurp.setValid(false);
						 pr.setDescProceso("Búsqueda por NSS");
						 pr.setStatus("Ingresar NSS Valido ");
						 bandera=1;	
					}
					if( ValidateUtil.isCURP(curp_o_nssIn) == false && bandera==0) { // && curp_o_nssIn.length() != 18
						
				 		 UIInput inputCurp = (UIInput) findComponent("curpNSS");
						 inputCurp.setValid(false);
						 pr.setDescProceso("Búsqueda por CURP");
						 pr.setStatus("Ingresar CURP Valido ");
				
					}
				}else {
					UIInput input = (UIInput) findComponent("curpNSS");
					input.setValid(false);
					pr.setStatus("NSS o CURP es requerido");
					
				}
		}catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
	
	public void ejecutarConsultarTraspasos() {
		ProcessResult pr = new ProcessResult();
		//limpiar();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Detalle Marca Cuenta");	
		try {
			System.out.println("VALOR DE RES CUENTA: "+cuenta+"  TIPO SALDO: "+cpDatos2.getCOD_TIPSALDO()+" CLAVE PROCESO: "+cpDatos2.getCLAVE_PROCESO()+" ESTADO:  " +cpDatos2.getESTADO());			
			ConsultarTraspasosIcefasOut res=consultarMarcasCuentasServices.getConsultarTraspasos(Integer.toString(cuenta), cpDatos2.getCOD_TIPSALDO(), Integer.toString(cpDatos2.getCLAVE_PROCESO()), cpDatos2.getESTADO());			
			System.out.println("VALOR DE RES: "+res);
			if(res.getStatus()==1) {
					if( res.getCpCursor().size() > 0) {
						cpCursor=res.getCpCursor();
						mensaje=res.getMensaje();
						pr.setStatus("Consulta Exitosa Detalle Marca Cuenta");
					}else {
						pr.setStatus("No se encontraron resultados por Detalle Marca Cuenta");
						mensajeTabla = "Sin información";
					}
			}else {
				if(res.getStatus()==2) {
					GenerarErrorNegocio(res.getMensaje());	
				} else if (res.getStatus() == 0) {
					pr.setStatus("Verifique Detalle Marca Cuenta, no existen datos para :"+cuenta +" y "+cpDatos2.getCLAVE_PROCESO());//res.getMensaje()
				}
			}
		}catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
	
	public boolean isNSS() {

		Pattern pattern = Pattern.compile("\\d{11}"); 
		if (!pattern.matcher(curp_o_nssIn.toUpperCase()).matches() || curp_o_nssIn.equals("00000000000") ) {//if (!pattern.matcher(curp_o_nssIn.toUpperCase()).matches())
//			UIInput radio = (UIInput) findComponent("curpNSS");
//			radio.setValid(false);
//			pr.setStatus("Ingresar NSS Valido");
//			pr.setFechaFinal(DateUtil.getNowDate());
//			resultados.add(pr);
			return false;
		}
		return true;
	}


}
