package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.CerInaProServ;

@Scope(value = "session")
@Component(value = "cerInaProceso")
@ELBeanName(value = "cerInaProceso")
public class CerInaProcesoCtrll extends ControllerBase {

    @Autowired
	private CerInaProServ service;
    
    @Getter
	@Setter
	private String radioSelected;
    
    @Getter
	@Setter
	private ProcesoOut proceso;
    
    @Getter
	@Setter
	private Date fecha;
    
    @Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			reset();
		}
	}
    
    public void reset() {
    	fecha=null;
    	proceso=null;
    	radioSelected=null;
    }
    public void ejecutar() {
    	if(radioSelected !=null) {
    		if(fecha!=null) {
    			  try {
    				    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
    					Date today= new Date();	
    					proceso= new ProcesoOut();	
    					proceso.setFechahoraInicio(format.format(today));
    					String resp= service.ejecutarProceso(Integer.valueOf(radioSelected), fecha);
    					Date today2= new Date();	
    					proceso.setFechahoraFinal(format.format(today2));	
    					proceso.setAbrevProceso(radioSelected.equals("1")?"Diagnóstico de parcialidades":"Aprobación de movimientos");    				
    					proceso.setEstadoProceso(resp);	   				 
    			  }catch(Exception e) {
    				  GenericException(e);
    			  }
    		}else {
    			addMessageFail("Seleccione la fecha.");
    		}
    	}else {
    		addMessageFail("Seleccione una opción.");
    	}
    }
    public void addMessageOK(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void addMessageFail(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
