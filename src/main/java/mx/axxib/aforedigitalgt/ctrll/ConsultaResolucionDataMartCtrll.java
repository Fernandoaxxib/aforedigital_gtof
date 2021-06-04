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
			//consultaResolucionesNombreOut=consultaResolucionDataMartServ.getCuentaNombre(Integer.parseInt(nss));Long.valueOf(String s).longValue();
				consultaResolucionesNombreOut=consultaResolucionDataMartServ.getCuentaNombre(Long.valueOf(nss).longValue());
			if(consultaResolucionesNombreOut  != null && consultaResolucionesNombreOut.getCursor() != null && consultaResolucionesNombreOut.getCursor().size()>0) {
				listaCurp=consultaResolucionesNombreOut.getCursor();
				
				totalSolicitud=consultaResolucionesNombreOut.getCursor().size();	
			
				if ( consultaResolucionesNombreOut.getCursor().size() == 0) {
					mensajeTabla = "Sin información";
					pr.setStatus("No se encontraron resultados");
				}else {
				
				//mensajeSolicitud=consultaResolucionesNombreOut.getP_MENSAJE();
				pr.setStatus("Consulta Exitosa");//"Consulta Exitosa"
				//System.out.println(" VALOR SEC PENSION: "+listaCurp.get(0).getRegimen());
				}
			
			}else {
				pr.setStatus("No se encontraron resultados");
				mensajeTabla = "Sin información";
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
		if (!pattern.matcher(nss.toUpperCase()).matches()) {//if (!pattern.matcher(curp_o_nssIn.toUpperCase()).matches())
			UIInput radio = (UIInput) findComponent("nss");
			radio.setValid(false);
			pr.setStatus("Ingresar NSS Valido");
			return false;
		}
		return true;
	}
	
	}
