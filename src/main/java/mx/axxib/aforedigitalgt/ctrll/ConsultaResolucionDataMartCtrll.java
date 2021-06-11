package mx.axxib.aforedigitalgt.ctrll;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionDataMartOut;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionesNombreOut;
import mx.axxib.aforedigitalgt.serv.ConsultaResolucionDataMartServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "consultaResolucionDataMart")
@ELBeanName(value = "consultaResolucionDataMart")
public class ConsultaResolucionDataMartCtrll extends ControllerBase {

	@Autowired
	private ConsultaResolucionDataMartServ consultaResolucionDataMartServ;
	
	@Getter
	private ConsultaResolucionesNombreOut consultaResolucionesNombreOut;
	
	@Getter
	private List <ConsultaResolucionDataMartOut> listaCurp;
	
	@Getter
	@Setter
	private String nss;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private Integer cuenta;
	
	@Getter
	@Setter
	private Integer totalSolicitud;	
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	private String mensajeSolicitud;
	
	
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			nss = null;
			listaCurp=null;
			totalSolicitud=0;
			consultaResolucionesNombreOut=null;
			init = false;
			
		}
		
	}
	
	public void getCuentaNombre()  {
		ProcessResult pr = new ProcessResult();
		try {
		
		
		iniciar();
		listaCurp=null;
		consultaResolucionesNombreOut=null;
		totalSolicitud=0;
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por NSS");
		if (nss != null && !nss.equals("") ) {
			
			//if (ValidateUtil.isInteger(nss) ) {
			//if (nss.matches("[0-9]*")) {
			if(isNSS(pr)) {
			
				consultaResolucionesNombreOut=consultaResolucionDataMartServ.getCuentaNombre(Long.valueOf(nss).longValue());
			
				System.out.println("VALOR DE consultaResolucionesNombreOut:"+consultaResolucionesNombreOut);
				
				if(consultaResolucionesNombreOut.getP_ESTATUS().equals("1")) {
					
					if( consultaResolucionesNombreOut.getCursor().size()>0) {
						listaCurp=consultaResolucionesNombreOut.getCursor();
						
						totalSolicitud=consultaResolucionesNombreOut.getCursor().size();	
						pr.setStatus("Consulta Exitosa por NSS");
					}else {
						pr.setStatus("No se encontraron resultados por Consulta NSS");
						mensajeTabla = "Sin información";
					}
				} else {
					if (consultaResolucionesNombreOut.getP_ESTATUS().equals("2")) {
						GenerarErrorNegocio(consultaResolucionesNombreOut.getP_MENSAJE());
					} else if (consultaResolucionesNombreOut.getP_ESTATUS().equals("0")) {
						pr.setStatus("Verifique su NSS, no existen datos para :"+nss);
					}
				}
				
			}

		}else {
				UIInput input = (UIInput) findComponent("nss");
				input.setValid(false);
				pr.setStatus("Ingrese el NSS");
				
			}
			}catch (Exception e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
	
	}
		
	public boolean isNSS(ProcessResult pr) {

		Pattern pattern = Pattern.compile("\\d{11}"); 
		if (!pattern.matcher(nss.toUpperCase()).matches() || nss.equals("00000000000")  ) {//if (!pattern.matcher(curp_o_nssIn.toUpperCase()).matches())
			UIInput radio = (UIInput) findComponent("nss");
			radio.setValid(false);
			pr.setStatus("Ingresar NSS Valido");
			return false;
		}
		return true;
	}
	
	}
