package mx.axxib.aforedigitalgt;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Clase principal de spring boot
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@SpringBootApplication(scanBasePackages={"mx.axxib.aforedigitalgt.*"})
public class AforedigitalgtApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AforedigitalgtApplication.class, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AforedigitalgtApplication.class);
    }
	
	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean<FacesServlet>(servlet, "*.jsf");
	}
	
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("primefaces.THEME", "glass-x");
		};
	}

	@Bean
	public FilterRegistrationBean<RewriteFilter> rewriteFilter() {
		FilterRegistrationBean<RewriteFilter> rwFilter = new FilterRegistrationBean<RewriteFilter>(new RewriteFilter());
		rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
				DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}

    @Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
		return messageSource;
	}
    

}
