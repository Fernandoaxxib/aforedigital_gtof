package mx.axxib.aforedigitalgt.ctrll;

import java.util.Iterator;
import java.util.List;

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
			init = false;
			
		}
	
	}
	
	public void getCuentaNombre()  {
		ProcessResult pr = new ProcessResult();
		try {
		
		
		iniciar();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por NSS");
		if (nss != null && !nss.equals("") ) {
			
			//if (ValidateUtil.isInteger(nss) ) {
			if (nss.matches("[0-9]*")) {
			
			consultaResolucionesNombreOut=consultaResolucionDataMartServ.getCuentaNombre(Integer.parseInt(nss));
			
			if(consultaResolucionesNombreOut  != null && consultaResolucionesNombreOut.getCursor() != null && consultaResolucionesNombreOut.getCursor().size()>0) {
				listaCurp=consultaResolucionesNombreOut.getCursor();
				
				totalSolicitud=consultaResolucionesNombreOut.getCursor().size();	
				
//				 for (ConsultaResolucionDataMartOut model : listaCurp) {
//			            System.out.println(model.getRegimen());
//			        }
				
				Iterator<ConsultaResolucionDataMartOut> nombreIterator = listaCurp.iterator();
				while(nombreIterator.hasNext()){
					ConsultaResolucionDataMartOut elemento = nombreIterator.next();
					
				}
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
		} else {
			UIInput input = (UIInput) findComponent("nss");
			input.setValid(false);
			pr.setStatus("NSS no válido ingrese digitos");
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
	
	}
