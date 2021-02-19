package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.StringTokenizer;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudOut;
import mx.axxib.aforedigitalgt.eml.VerChequeOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeListOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeListOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;
import mx.axxib.aforedigitalgt.serv.SolicitudMatrimonioDesempleoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "verDatosCheque")
@ELBeanName(value = "verDatosCheque")
public class SolicitudMatrimonioDesempleoCtrll extends ControllerBase{
	
	
	@Autowired
	private SolicitudMatrimonioDesempleoServ  solicitudMatrimonioDesempleoServ;
	
	@Getter
	@Setter
	private VerChequeOut verChequeOut;
	
	@Getter
	@Setter
	private Integer totalSolicitud;	
	
	@Getter
	@Setter
	private Integer totalPago;	
	
	@Getter
	private String mensajeSolicitud;
	
	@Getter
	private String mensajePago;
	
	@Getter
	@Setter
	private List<VerSolicitudChequeListOut> listSolicitudChequeOut;
	
	@Getter
	@Setter
	private VerSolicitudChequeListOut selectSolicitudChequeOut;
	
	@Getter
	@Setter
	private List <VerPagoChequeListOut> listPagoChequeOut;
	
	@Getter
	@Setter
	private VerPagoChequeListOut selectPagoChequeOut;
	
	@Getter
	@Setter
	private String nss;
	

	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String apellidoPaterno;
	
	@Getter
	@Setter
	private String apellidoMaterno;
	
	@Getter
	@Setter
	private String cuenta;
	
	@Getter
	private String mensajeTabla;
	
//	@Getter
//	@Setter
//	public List<ProcessResult> resultados;
//	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			nss = null;
			limpiarPantalla();
			init = false;
		}
	
	}
	
	private void limpiarPantalla() {
		mensajeTabla=null;
		totalSolicitud=null;
		totalPago=null;
	}
	
	public void consultar() {
		ProcessResult pr = new ProcessResult();
		try {System.out.println("VALOR DE NNS:"+nss);
		limpiarPantalla();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por NSS");
		if (nss != null && !nss.equals("") ) {
			if (ValidateUtil.isNSS(nss)) {
			verChequeOut=solicitudMatrimonioDesempleoServ.getVerCheque(nss);
			System.out.println("VALOR DE verChequeOut: "+verChequeOut);
			//nombre=verChequeOut.getNombre();
			cuenta=verChequeOut.getCuenta();
//			consultarPago(verChequeOut.getCuenta());
//			System.out.println("VALOR DE consultarPago");
//			
//			consultarSolicitud(verChequeOut.getCuenta());
//			System.out.println("VALOR DE consultarSolicitud");
			
			
			StringTokenizer tokens=new StringTokenizer(verChequeOut.getNombre()," ");
		        int nDatos=tokens.countTokens();
		        String [] datos=new String[nDatos];
		        int i=0;
		        while(tokens.hasMoreTokens()){
		            String str=tokens.nextToken();
		           // datos[i]=Double.valueOf(str).doubleValue();
		            datos[i]=str;
		            System.out.println(datos[i]);
		            i++;
		        }
		        
		        if(datos.length == 4) {
		        String nombre1= datos[0];
		        String nombre2= datos[1];
		        nombre=nombre1+" "+nombre2;
		       
		        apellidoPaterno=datos[2];
		        apellidoMaterno=datos[3];
		        System.out.println("Valor concatenado:"+nombre);
		        System.out.println("Valor concatenado:"+apellidoPaterno);
		        System.out.println("Valor concatenado:"+apellidoMaterno);
		        }else {
		        	nombre= datos[0];
			       		       
			        apellidoPaterno=datos[1];
			        apellidoMaterno=datos[2];
			        System.out.println("Valor concatenado nombre1:"+nombre);
			        System.out.println("Valor concatenado apellidoPaterno:"+apellidoPaterno);
			        System.out.println("Valor concatenado apellidoMaterno:"+apellidoMaterno);	
		        }
			} else {
				UIInput input = (UIInput) findComponent("nss");
				input.setValid(false);
				pr.setStatus("NSS no válido");
			}
		} else {
			UIInput input = (UIInput) findComponent("nss");
			input.setValid(false);
			pr.setStatus("NSS es requerido");
		}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public void botonDatosCheque() {
		consultarSolicitud(cuenta);
		
	}
	
	public void consultarSolicitud(String cuenta) {
		try {
			VerSolicitudChequeOut res=solicitudMatrimonioDesempleoServ.getVerSolicitudCheque(cuenta);
			consultarPago(cuenta);
			System.out.println("VALOR DE consultarSolicitud: "+res.getVerSolicitudChequeListOut().size());
			System.out.println("VALOR DE NNS:"+res.getVerSolicitudChequeListOut());
			if (res != null && res.getVerSolicitudChequeListOut() != null && res.getVerSolicitudChequeListOut().size() > 0) {
				listSolicitudChequeOut = res.getVerSolicitudChequeListOut();
				totalSolicitud = res.getVerSolicitudChequeListOut().size();
				if ( res.getVerSolicitudChequeListOut().size() == 0) {
					mensajeTabla = "Sin información";
				}
				System.out.println("VALOR DE totalSolicitud:"+totalSolicitud);
				mensajeSolicitud=res.getMensaje();
			}
		
		}catch (Exception e) {
			resultados.add(GenericException(e));
		} 
	}
	
	public void consultarPago(String cuenta) {
		try {
			VerPagoChequeOut res=solicitudMatrimonioDesempleoServ.getVerPagosCheque(cuenta);
			System.out.println("VALOR DE consultarPago: "+res.getVerPagoChequeListOut().size());
			System.out.println("VALOR DE NNS:"+res.getVerPagoChequeListOut());
			if (res != null && res.getVerPagoChequeListOut() != null && res.getVerPagoChequeListOut().size() > 0) {
				listPagoChequeOut = res.getVerPagoChequeListOut();
				totalPago = res.getVerPagoChequeListOut().size();
				if ( res.getVerPagoChequeListOut().size() == 0) {
					mensajeTabla = "Sin información";
				}
				mensajePago=res.getMensaje();
			}
		
		}catch (Exception e) {
			resultados.add(GenericException(e));
		}
	}
}
