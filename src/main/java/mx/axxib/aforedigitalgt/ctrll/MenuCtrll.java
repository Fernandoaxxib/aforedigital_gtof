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
	@Setter
	private String menuSeleccionado;

	@Getter
	public HashMap<String, Boolean> permisos;

	@PostConstruct
	public void init() {
		permisos = ((AforeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPermisos();
	}

	public void navigateMenu() {
		if (menu != null) {
			submenu = menu;
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
					.handleNavigation(FacesContext.getCurrentInstance(), null, menu + "?init=true&faces-redirect=true");
		}
	}

	public void navigateSub() {
		if (submenu != null) {
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
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

	public void graficas() {
		menuSeleccionado="";
		menu = "/web/graficas.jsf";
		submenu = menu;
		navigateMenu();
	}
	

	public void selectedMenu(String opcion) {
		switch (opcion) {

		case "1": {
			menuSeleccionado = "APROBACIÓN DE SOLICITUDES POR TIPO DE RETIRO";
			submenu = "/web/aprobSolicTipRetiro.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "2": {
			menuSeleccionado = "DESEMPLEO PARCIALIDADES";
			submenu = "/web/modDesParc.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "3": {
			menuSeleccionado = "VENTA DE TÍTULOS";
			submenu = "/web/ventaTitulos.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "4": {
			menuSeleccionado = "CARGA OP84 Y OP85";
			submenu = "/web/retParImssOP84.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "5": {
			menuSeleccionado = "NOTIFICACIONES DE PAGO";
			submenu = "/web/notificacionPagos.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "6": {
			menuSeleccionado = "CARGA MASIVA RIGHT FAX";
			submenu = "/web/cargaRight.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "7": {
			menuSeleccionado = "CERTIFICACIÓN DE INACTIVIDAD";
			submenu = "/web/cerInaProceso.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "8": {
			menuSeleccionado = "RECHAZO DE SOLICITUDES";
			submenu = "/web/rechazosSolicitudes.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "9": {
			menuSeleccionado = "REINVERSIONES A BÁSICAS PARCIALIDADES (DESEMPLEO)";
			submenu = "/web/reInverModDesProceso.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		case "10": {
			menuSeleccionado = "DESMARCA MASIVA DE CUENTAS (CARGA BATCH)";
			submenu = "/web/desmarcaCargaMasiva.jsf";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, submenu + "?init=true&faces-redirect=true");
			break;
		}
		}
	}
}
