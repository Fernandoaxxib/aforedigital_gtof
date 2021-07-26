package mx.axxib.aforedigitalgt.com;

import java.util.ArrayList;
import java.util.HashMap;

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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject userJson = new JSONObject();
		userJson.put("usuario", name);
		userJson.put("pass", password);

		HttpEntity<String> request = new HttpEntity<String>(userJson.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		AuthUserResponse res;
		
		try {
			res = restTemplate.postForObject(urlLogin, request, AuthUserResponse.class);
		}catch(Exception ex) {
			throw new BadCredentialsException("Se presentó un incidente inesperado, favor de contactar al administrador del sitio");
		}

		if (res.getCodRespuesta() != null && res.getCodRespuesta().equals("1")) {
			AforeUser user = new AforeUser(name, res.getToken());
			// permisos dummy
			HashMap<String, Boolean> permisos = new HashMap<String, Boolean>();
			permisos.put("moduloPagos", true);
			permisos.put("actualizar", true);
			permisos.put("ejecutar", true);
			user.setPermisos(permisos);

			return new UsernamePasswordAuthenticationToken(user, password, new ArrayList<>());
		} else {
			if (res.getCodError() != null && res.getMensaje() != null) {
				throw new BadCredentialsException(res.getMensaje());
			} else {
				throw new BadCredentialsException("Se presentó un incidente inesperado, favor de contactar al administrador del sitio. Coderror: "+res.getCodError());
			}
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
