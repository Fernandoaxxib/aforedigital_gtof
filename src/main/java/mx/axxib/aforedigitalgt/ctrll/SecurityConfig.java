package mx.axxib.aforedigitalgt.ctrll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;

import mx.axxib.aforedigitalgt.com.JsfRedirectStrategy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		     http
		     	.authorizeRequests()
			     	.antMatchers("/javax.faces.resource/**", "/login*", "api/*","resources/**","css/**").permitAll()
			     	.anyRequest().authenticated()
		     	.and().exceptionHandling().accessDeniedPage("/error")
		     	.and().formLogin()
		     		.loginPage("/login.jsf")
		     		.defaultSuccessUrl("/home")
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
		BCryptPasswordEncoder encoder = passwordEncoder();
		String result = encoder.encode("1");

//		auth.inMemoryAuthentication().withUser("user").password(result).roles("USER").and().withUser("admin")
//				.password(result).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("sygno.afore").password(result).roles("ADMIN");

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}