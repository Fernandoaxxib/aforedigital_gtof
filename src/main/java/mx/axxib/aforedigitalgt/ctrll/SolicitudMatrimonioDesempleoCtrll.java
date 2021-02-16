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
import mx.axxib.aforedigitalgt.eml.VerPagoChequeOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;
import mx.axxib.aforedigitalgt.serv.SolicitudMatrimonioDesempleoServ;

@Scope(value = "session")
@Component(value = "verDatosCheque")
@ELBeanName(value = "verDatosCheque")
public class SolicitudMatrimonioDesempleoCtrll extends ControllerBase{
	
	
	@Autowired
	private SolicitudMatrimonioDesempleoServ  solicitudMatrimonioDesempleoServ;
	
	@Getter
	@Setter
	VerChequeOut verChequeOut;
	
	@Getter
	@Setter
	VerSolicitudChequeOut verSolicitudChequeOut;
	
	@Getter
	@Setter
	VerPagoChequeOut verPagoChequeOut;
	
	@Getter
	@Setter
	private String nns;
	
//	@Getter
//	@Setter
//	private String codCuenta;
//	
	@Getter
	@Setter
	private String curp;
	
	
	public void consultar() {
		try {System.out.println("VALOR DE NNS:"+nns);
			verChequeOut=solicitudMatrimonioDesempleoServ.getVerCheque(nns);
			System.out.println("VALOR DE verChequeOut: "+verChequeOut);
			
			
			consultarPago(verChequeOut.getCuenta());
			System.out.println("VALOR DE consultarPago");
			
			consultarSolicitud(verChequeOut.getCuenta());
			System.out.println("VALOR DE consultarSolicitud");
			
			
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
	
	public void consultarSolicitud(String cuenta) {
		try {
			verSolicitudChequeOut=solicitudMatrimonioDesempleoServ.getVerSolicitudCheque(cuenta);
			System.out.println("VALOR DE consultarSolicitud: "+verSolicitudChequeOut.getVerSolicitudChequeListOut().size());
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void consultarPago(String cuenta) {
		try {
			verPagoChequeOut=solicitudMatrimonioDesempleoServ.getVerPagosCheque(cuenta);
			System.out.println("VALOR DE consultarPago: "+verPagoChequeOut.getVerPagoChequeListOut().size());
		}catch (Exception e) {
			GenericException(e);
		}
	}
}
