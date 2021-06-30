package mx.axxib.aforedigitalgt.com;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AforeUser implements UserDetails{

	private HashMap<String, Boolean> permisos;
			
	private String user;
	private String pass;
	
	private static final long serialVersionUID = 1L;
	
	public AforeUser(String pUser, String pPass) {
		user = pUser;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode(pPass);
		
		pass = result;
		

	}
	
	public HashMap<String, Boolean> getPermisos() {
		return permisos;
	}
	
	public void setPermisos(HashMap<String, Boolean> permisos) {
		this.permisos = permisos;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	public String getPassword() {
		return pass;
	}

	@Override
	public String getUsername() {
		return user;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
