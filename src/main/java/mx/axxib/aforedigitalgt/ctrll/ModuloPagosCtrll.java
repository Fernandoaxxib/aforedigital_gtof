package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Scope(value = "session")
@Component(value = "moduloPagos")
@ELBeanName(value = "moduloPagos")
public class ModuloPagosCtrll extends ControllerBase{

	@Getter
	private String pagosAfiliados;
	@Getter
	private Date fechaProceso;

	public void refresh() {
		try {
			
			addMessage("Welcome to PrimeFaces!!");
		} catch (Exception e) {
			GenericException(e);
		}

	}
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	
}
