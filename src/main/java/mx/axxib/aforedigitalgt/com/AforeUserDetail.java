package mx.axxib.aforedigitalgt.com;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AforeUserDetail")
public class AforeUserDetail implements UserDetailsService {

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		AforeUser user = new AforeUser(username, "1");
		
		HashMap<String, Boolean> permisos = new HashMap<String, Boolean>();
		permisos.put("moduloPagos", true);
		permisos.put("actualizar", true);
		permisos.put("ejecutar", true);
		user.setPermisos(permisos);


		return user;

	}





}