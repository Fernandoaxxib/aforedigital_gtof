package mx.axxib.aforedigitalgt.ctrll;

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
import mx.axxib.aforedigitalgt.eml.PermisoResult;
import mx.axxib.aforedigitalgt.serv.PermisoService;

@Scope(value = "session")
@Component(value = "home")
@ELBeanName(value = "home")
@Join(path = "/home", to = "/view/introduccion.jsf")
public class HomeCtrll extends ControllerBase {

	@Autowired
	private PermisoService personService;

	@Autowired
	private AforeMessage aforeMessage;

	@Getter
	@Setter
	private String usuario;
	@Getter
	@Setter
	private Integer count;
	@Getter
	@Setter
	private PermisoResult permisos;

	public void listAllPersons() {
		try {
			//listPersons = personService.list();
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void listAllPersonsSP() {
		try {
			//listPersons = personService.listSP();
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void getPersonFullNameById() {
		try {
			permisos = personService.getPermisosUsuario(usuario);
			count = permisos.getDatos().size();
		} catch (Exception e) {
			GenericException(e);
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
