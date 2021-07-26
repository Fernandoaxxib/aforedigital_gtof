package mx.axxib.aforedigitalgt.ctrll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;

import mx.axxib.aforedigitalgt.com.AforeAuthenticationProvider;
import mx.axxib.aforedigitalgt.com.AforeUserDetail;
import mx.axxib.aforedigitalgt.com.JsfRedirectStrategy;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Clase que controla la seguridad del sitio, las rutas protegidas y redireccionamientos
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("AforeUserDetail")
	AforeUserDetail aforeUserDetail;
	
	@Autowired
	AforeAuthenticationProvider aforeAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		     http
		     	.authorizeRequests()
			     	.antMatchers("/javax.faces.resource/**", "/login*", "api/*","resources/**","css/**").permitAll()
			     	.anyRequest().authenticated()

		     	.and().formLogin()
		     		.loginPage("/login.jsf")
		     		.defaultSuccessUrl("/web/operaciones.jsf", true)
		     		.failureUrl("/login.jsf?error=true")
				.and().logout()
					.logoutSuccessUrl("/login.jsf")
					.invalidateHttpSession(true)
				.and().csrf().disable();
		     
		     InvalidSessionStrategy invalidSessionStrategy = new JsfRedirectStrategy();
		     
		     
			http.sessionManagement().invalidSessionStrategy(invalidSessionStrategy )
		        .invalidSessionUrl("/invalidSession.html")
		     	.maximumSessions(1).expiredUrl("/login?expired=true");
			
			
			
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder encoder = passwordEncoder();
//		String result = encoder.encode("1");

		//auth.userDetailsService(aforeUserDetail);
		auth.authenticationProvider(aforeAuthenticationProvider);
		//auth.inMemoryAuthentication().withUser("sygno.afore").password(result).roles("ADMIN");
		
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}