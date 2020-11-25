package mx.axxib.aforedigitalgt.com;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AforeMessage {

	@Autowired
    private MessageSource messageSource;
	
	@Value("${lang}")
	private String idioma;
	
	private Locale locale;
	
	public String getMessage(String key, Object[] params) {
		if(locale == null) {
			locale = new Locale(idioma);
		}
		return  messageSource.getMessage(key, params, locale);
	}
	
	public String getOnlyMessage(String key, Object[] params) {
		if(locale == null) {
			locale = new Locale(idioma);
		}
		return  messageSource.getMessage(key, params, locale).split("\\|")[1];
	}
	
	public String getCode(String key, Object[] params) {
		if(locale == null) {
			locale = new Locale(idioma);
		}
		return  messageSource.getMessage(key, params, locale).split("\\|")[0];
	}
	
	public void showMessageFaces(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", message));
	}
}
