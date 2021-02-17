package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.StringTokenizer;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.VerChequeOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeListOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeListOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;
import mx.axxib.aforedigitalgt.serv.SolicitudMatrimonioDesempleoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

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
	private Integer totalSolicitud;	
	
	@Getter
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
	private List <VerPagoChequeListOut> listPagoChequeOut;
	
	@Getter
	@Setter
	private String nns;
	

	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String cuenta;
	
	@Getter
	private String mensajeTabla;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
		
		nns=null;
		mensajeTabla=null;
		totalSolicitud=null;
		totalPago=null;
	}
	}
	
	public void consultar() {
		try {System.out.println("VALOR DE NNS:"+nns);
			verChequeOut=solicitudMatrimonioDesempleoServ.getVerCheque(nns);
			System.out.println("VALOR DE verChequeOut: "+verChequeOut);
			nombre=verChequeOut.getNombre();
			cuenta=verChequeOut.getCuenta();
//			consultarPago(verChequeOut.getCuenta());
//			System.out.println("VALOR DE consultarPago");
//			
//			consultarSolicitud(verChequeOut.getCuenta());
//			System.out.println("VALOR DE consultarSolicitud");
			
			
//			StringTokenizer tokens=new StringTokenizer(verChequeOut.getNombre()," ");
//		        int nDatos=tokens.countTokens();
//		        String [] datos=new String[nDatos];
//		        int i=0;
//		        while(tokens.hasMoreTokens()){
//		            String str=tokens.nextToken();
//		           // datos[i]=Double.valueOf(str).doubleValue();
//		            datos[i]=str;
//		            System.out.println(datos[i]);
//		            i++;
//		        }
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void botonDatosCheque() {
		consultarSolicitud(cuenta);
		consultarPago(cuenta);
	}
	
	public void consultarSolicitud(String cuenta) {
		try {
			VerSolicitudChequeOut res=solicitudMatrimonioDesempleoServ.getVerSolicitudCheque(cuenta);
			System.out.println("VALOR DE consultarSolicitud: "+res.getVerSolicitudChequeListOut().size());
			if (res != null && res.getVerSolicitudChequeListOut() != null && res.getVerSolicitudChequeListOut().size() > 0) {
				listSolicitudChequeOut = res.getVerSolicitudChequeListOut();
				totalSolicitud = res.getVerSolicitudChequeListOut().size();
				mensajeSolicitud=res.getMensaje();
			}
		
		}catch (Exception e) {
			GenericException(e);
		} 
	}
	
	public void consultarPago(String cuenta) {
		try {
			VerPagoChequeOut res=solicitudMatrimonioDesempleoServ.getVerPagosCheque(cuenta);
			System.out.println("VALOR DE consultarPago: "+res.getVerPagoChequeListOut().size());
			if (res != null && res.getVerPagoChequeListOut() != null && res.getVerPagoChequeListOut().size() > 0) {
				listPagoChequeOut = res.getVerPagoChequeListOut();
				totalPago = res.getVerPagoChequeListOut().size();
				mensajePago=res.getMensaje();
			}
		
		}catch (Exception e) {
			GenericException(e);
		}
	}
}
