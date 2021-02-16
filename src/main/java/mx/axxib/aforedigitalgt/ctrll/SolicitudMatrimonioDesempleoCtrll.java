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
	List<VerSolicitudChequeOut> verSolicitudChequeOut;
	
	@Getter
	@Setter
	List<VerPagoChequeOut> verPagoChequeOut;
	
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
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void consultarSolicitud() {
		try {
			verSolicitudChequeOut=solicitudMatrimonioDesempleoServ.getVerSolicitudCheque();
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void consultarPago() {
		try {
			verPagoChequeOut=solicitudMatrimonioDesempleoServ.getVerPagosCheque();
		}catch (Exception e) {
			GenericException(e);
		}
	}
}
