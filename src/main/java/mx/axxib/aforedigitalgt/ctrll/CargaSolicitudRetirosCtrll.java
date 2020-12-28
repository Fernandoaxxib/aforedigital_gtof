package mx.axxib.aforedigitalgt.ctrll;

import org.primefaces.PrimeFaces;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import java.util.concurrent.TimeUnit;


import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.serv.CargasRetirosParcialesServ;

@Scope(value = "session")
@Component(value = "cargaSolicitudRetiros")
@ELBeanName(value = "cargaSolicitudRetiros")
public class CargaSolicitudRetirosCtrll extends ControllerBase {
	
	@Getter
	@Setter
	private String nombre;
	
	@Autowired
	private  CargasRetirosParcialesServ cargasRetirosParcialesServ;
	
	public void ejecutarCarga(ActionEvent ae) {
	      //simulating order placement
	      try {
	          TimeUnit.SECONDS.sleep(5);
	        
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      } 
	         

	      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
	              "Se creo el archivo", "Cero error");
	      //RequestContext.getCurrentInstance().showMessageInDialog(message);
	      //above method deprecated in 6.2, use following instead
	      PrimeFaces.current().dialog().showMessageDynamic(message);
	  }
	
	public void cargaSolicitudInicio() {
		try {
			 cargasRetirosParcialesServ.cargaSolicitudInicio();
		}
			 catch (AforeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
}
