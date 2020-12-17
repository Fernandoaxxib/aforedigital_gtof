package mx.axxib.aforedigitalgt.ctrll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/javax.faces.resource/**", "/login*", "api/*").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login.jsf").defaultSuccessUrl("/api/moduloPagos.jsf")
				.failureUrl("/login.jsf?error=true").and().logout().logoutSuccessUrl("/login.jsf").and().csrf()
				.disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = passwordEncoder();
		String result = encoder.encode("1");

//		auth.inMemoryAuthentication().withUser("user").password(result).roles("USER").and().withUser("admin")
//				.password(result).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("1").password(result).roles("ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}