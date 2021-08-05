package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
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
import mx.axxib.aforedigitalgt.eml.DatosAccesoOut;
import mx.axxib.aforedigitalgt.eml.DatosUsuarioOut;
import mx.axxib.aforedigitalgt.eml.InfoUsuarioOut;
import mx.axxib.aforedigitalgt.serv.HomeServ;


@Scope(value = "session")
@Component(value = "home")
@ELBeanName(value = "home")
@Join(path = "/home", to = "/web/operaciones.jsf")
public class HomeCtrll extends ControllerBase {



	@Autowired
	private AforeMessage aforeMessage;
	@Autowired
	private HomeServ service;

	@Getter
	@Setter
	private String usuario;
	@Getter
	@Setter
	private Integer count;
	@Getter
	@Setter
	private String nombreUsuario;
	@Getter
	@Setter
	private String perfilUsuario;
	@Getter
	@Setter
	private String emailUsuario;

	@Override
	public void iniciar() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		try {
			validarAcceso();
		} catch (Exception e) {
			 GenericException(e);
		}
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
	
	public void exit() throws Exception {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();		
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
		.handleNavigation(FacesContext.getCurrentInstance(), null, "/login.jsf?init=true&faces-redirect=true");
	}
	
	public void validarAcceso() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String cuenta=authentication.getName();
		DatosAccesoOut resp=service.validarAcceso(cuenta);
		if(resp!=null) {
			if(resp.getP_ID_USUARIO()>0) {
				InfoUsuarioOut resp2=service.getObtenerDatosUsuario(resp.getP_ID_USUARIO());
				if(resp2!=null) {
					 List<DatosUsuarioOut> datosUsuario=resp2.getDatosUsuario();
					 this.nombreUsuario=datosUsuario.get(0).getNOMBRE()+" "+datosUsuario.get(0).getAPELLIDO_PATERNO()+" "+datosUsuario.get(0).getAPELLIDO_MATERNO();
				     this.perfilUsuario=datosUsuario.get(0).getDESCRIP_PERFIL();
				     this.emailUsuario=datosUsuario.get(0).getEMAIL();
				}
			}
		}
		
	}
}
