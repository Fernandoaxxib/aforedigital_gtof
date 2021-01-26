package mx.axxib.aforedigitalgt.ctrll;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Scope(value = "session")
@Component(value = "menu")
@ELBeanName(value = "menu")
public class MenuCtrll extends ControllerBase {

	@Getter
	@Setter
	private String menu;
	
	@Getter
	@Setter
	private String submenu;
	
	public void navigateMenu() {
		if(menu != null) {
		    FacesContext context = FacesContext.getCurrentInstance();
		    NavigationHandler navigationHandler = context.getApplication()
		            .getNavigationHandler();
		    submenu = menu;
		    navigationHandler.handleNavigation(context, null, menu
		            + "?init=true&faces-redirect=true");
		}
	}
	
	public void navigateSub() {
		if(submenu != null) {
		    FacesContext context = FacesContext.getCurrentInstance();
		    NavigationHandler navigationHandler = context.getApplication()
		            .getNavigationHandler();
		    navigationHandler.handleNavigation(context, null, submenu
		            + "?init=true&faces-redirect=true");
		}
	}
	
	public void transacciones() {
		menu = "/api/aprobSolicTipRetiro.jsf";
		submenu = menu;
		navigateMenu();
	}
	
	public void monitor() {
		menu = "/api/monitorProcesos.jsf";
		submenu = menu;
		navigateMenu();
	}
	
	public void recaudaciones() {
		menu = null;
		submenu = menu;
		navigateMenu();
	}
	
	public void reportes() {
		menu = "/api/reporteLiquidacion.jsf";
		submenu = menu;
		navigateMenu();
	}
}
