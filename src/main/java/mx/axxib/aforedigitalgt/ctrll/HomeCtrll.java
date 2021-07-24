package mx.axxib.aforedigitalgt.ctrll;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;


@Scope(value = "session")
@Component(value = "home")
@ELBeanName(value = "home")
@Join(path = "/home", to = "/web/operaciones.jsf")
public class HomeCtrll extends ControllerBase {



	@Autowired
	private AforeMessage aforeMessage;

	@Getter
	@Setter
	private String usuario;
	@Getter
	@Setter
	private Integer count;
	

	@Override
	public void iniciar() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params.size() == 1) {
			String param = params.get("redirect");
			if(param != null) {
				if(param.equals("true")) {
					FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/web/operaciones.jsf");
					//FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
				}

			}
		}
	}

	public String saludo() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return aforeMessage.getMessage("index.bienvenido", new Object[] { authentication.getName() });
		} catch (Exception e) {
			GenericException(e);
		}
		return "";
	}
}
