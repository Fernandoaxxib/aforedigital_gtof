package mx.axxib.aforedigitalgt.ctrll;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeUser;

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
	
	@Getter
	public HashMap<String, Boolean> permisos;
	
	@PostConstruct
	public void init() {
		permisos = ((AforeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPermisos();
	}
	
	public void navigateMenu() {
		if(menu != null) {
		    submenu = menu;
		    FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, menu + "?init=true&faces-redirect=true");
		}
	}
	
	public void navigateSub() {
		if(submenu != null) {
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
		}
	}
	
	public void transacciones() {
		menu = "/web/aprobSolicTipRetiro.jsf";
		submenu = menu;
		navigateMenu();
	}
	
	public void monitor() {
		menu = "/web/monitorProcesos.jsf";
		submenu = menu;
		navigateMenu();
	}
	

	
	public void reportes() {
		menu = "/web/consultarMarcasCuentas.jsf";
		submenu = menu;
		navigateMenu();
	}
	
	public void catalogos() {
		menu = "/web/valoresUMA.jsf";
		submenu = menu;
		navigateMenu();
	}
}
