package mx.axxib.aforedigitalgt.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.datacontract.schemas._2004._07.DomainService_Entities.DTOLogin;
import org.datacontract.schemas._2004._07.DomainService_Entities.DTOResponseLogin;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tempuri.BasicHttpBinding_IDomainServiceStub;
import org.tempuri.IDomainServiceProxy;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Clase que personaliza la autenticación de spring security haciendo uso de un servicio rest para validar el usuario y contraseña
//** Interventor Principal: JSAS
//** Fecha Creación: 26/Julio/2021
//** Última Modificación:
//***********************************************//
@Component
public class AforeAuthenticationProvider implements AuthenticationProvider {

	@Value("${servicio.Login}")
	private String urlLogin;

	/*
	 * @Override public Authentication authenticate(Authentication authentication)
	 * throws AuthenticationException {
	 * 
	 * String name = authentication.getName(); String password =
	 * authentication.getCredentials().toString();
	 * 
	 * Authentication auth = null; IDomainServiceProxy proxyy = new
	 * IDomainServiceProxy(); DTOLogin login = new DTOLogin(); DTOResponseLogin
	 * respLogin = new DTOResponseLogin(); BasicHttpBinding_IDomainServiceStub
	 * service = (BasicHttpBinding_IDomainServiceStub) proxyy.getIDomainService();
	 * 
	 * try { login.setGroupMember(null); login.setUserName(name);
	 * login.setPassword(password); System.out.println("usuario: " + name);
	 * 
	 * respLogin = service.authenticate(login);
	 * 
	 * System.out.println("resultado: " + respLogin.getResult() +
	 * respLogin.getUser().getFullName());
	 * 
	 * if (respLogin.getResult()) { AforeUser user = new AforeUser(name, ""); //
	 * permisos dummy HashMap<String, Boolean> permisos = new HashMap<String,
	 * Boolean>(); permisos.put("moduloPagos", true); permisos.put("actualizar",
	 * true); permisos.put("ejecutar", true); user.setPermisos(permisos);
	 * 
	 * return new UsernamePasswordAuthenticationToken(user, password, new
	 * ArrayList<>());
	 * 
	 * } else { throw new
	 * BadCredentialsException("Se presentó un incidente inesperado, favor de contactar al administrador del sitio. Coderror: "
	 * +respLogin.getResult()); } } catch (Exception e) {
	 * 
	 * }
	 * 
	 * }
	 */

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Authentication auth = null; 
		IDomainServiceProxy proxyy = new IDomainServiceProxy(); 
		DTOLogin login = new DTOLogin(); 
		DTOResponseLogin respLogin = new DTOResponseLogin(); 
		BasicHttpBinding_IDomainServiceStub	service = (BasicHttpBinding_IDomainServiceStub) proxyy.getIDomainService();
		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject userJson = new JSONObject();
		userJson.put("usuario", name);
		userJson.put("pass", password);

		
		try {
			login.setGroupMember(null);
			login.setUserName(name);
			login.setPassword(password);
			respLogin = service.authenticate(login);			
		}catch(Exception ex) {
			throw new BadCredentialsException("Se presentó un incidente inesperado, favor de contactar al administrador del sitio");
		}

		if (respLogin.getResult()) {
			AforeUser user = new AforeUser(name, "");
			// permisos dummy
			HashMap<String, Boolean> permisos = new HashMap<String, Boolean>();
			permisos.put("moduloPagos", true);
			permisos.put("actualizar", true);
			permisos.put("ejecutar", true);
			user.setPermisos(permisos);

			return new UsernamePasswordAuthenticationToken(user, password, new ArrayList<>());
		} else {			
				throw new BadCredentialsException("Se presentó un incidente inesperado, favor de contactar al administrador del sitio.");			
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
