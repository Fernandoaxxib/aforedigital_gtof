package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.serv.ReporteParcialService;

@Scope(value = "session")
@Component(value = "reporteParcial")
@ELBeanName(value = "reporteParcial")
public class ReporteParcial extends ControllerBase {
	
	@Autowired
	ReporteParcialService reporteParcial;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private Date fechaIni;
	
	@Getter
	@Setter
	private Date fechaFin;
	
	@Getter
	@Setter
	private String rutaParcial;
	 
	@Getter
	@Setter
	private String nombreParcial;
	
	
	public void ejecutarReporteParcial() {
		try {
			String msg=reporteParcial.crearReporteParcial(fechaIni, fechaFin, rutaParcial, nombreParcial);		
				if(msg.trim().toUpperCase().equals("OK")) {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
				} else {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
				}
			
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
 
    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }

}
