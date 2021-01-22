package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.LoteOP85Out;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.RetParImssOP8586Service;

@Scope(value = "session")
@Component(value = "retParImssOP8586")
@ELBeanName(value = "retParImssOP8586")
public class RetParImssOP8586Ctrll extends ControllerBase {

	@Autowired
	private RetParImssOP8586Service service;
	
	@Getter
	@Setter
	private List<LoteOP85Out> listLotes;
	
	@Getter
	@Setter
	private String ruta;
	
	@Getter
	@Setter
	private String archivo;
	
	@Getter
	@Setter
	private String ruta2;
	
	@Getter
	@Setter
	private String archivo2;
	
	@Getter
	@Setter
	private String ruta3;
	
	@Getter
	@Setter
	private String archivo3;
	
	@Getter
	@Setter
	private ProcesoOut proceso;
	
	@Getter
	@Setter
	private Date fecIni;
	
	@Getter
	@Setter
	private Date fecFin;
	
	@Getter
	@Setter
	private String lote;
	
	@Getter
	private Date today;

	@PostConstruct
	public void init() {
		ruta="/iprod/PROCESAR/TRANSMISION/AFORE/RETIROS";
		ruta2="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
		ruta3="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
		today= new Date();
	}
	
	public void getLotes() {
		try {
			listLotes=service.getLotesOP85();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	public void addMessageOK(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageFail(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
