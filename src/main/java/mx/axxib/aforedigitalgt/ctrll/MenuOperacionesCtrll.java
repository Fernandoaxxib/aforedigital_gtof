package mx.axxib.aforedigitalgt.ctrll;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Scope(value = "session")
@Component(value = "menuOperaciones")
@ELBeanName(value = "menuOperaciones")
public class MenuOperacionesCtrll extends ControllerBase {

	@Getter
	@Setter
	private String outcome;
	
	public void navigate() {
		if(outcome != null) {
		    FacesContext context = FacesContext.getCurrentInstance();
		    NavigationHandler navigationHandler = context.getApplication()
		            .getNavigationHandler();
		    navigationHandler.handleNavigation(context, null, outcome
		            + "?init=true&faces-redirect=true");
		}
	}
	
	public void transacciones() {
		outcome = "/api/aprobSolicTipRetiro.jsf";
		navigate();
	}
	
	public void monitor() {
		outcome = "/api/monitorProcesos.jsf";
		navigate();
	}
	
	public void recaudaciones() {
		outcome = null;
		navigate();
	}
	
	public void reporte() {
		outcome = null;
		navigate();
	}
}
