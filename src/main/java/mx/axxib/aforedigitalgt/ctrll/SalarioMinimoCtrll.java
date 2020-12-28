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
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;
import mx.axxib.aforedigitalgt.serv.SalarioMinimoService;

@Scope(value = "session")
@Component(value = "salarioMinimo")
@ELBeanName(value = "salarioMinimo")
public class SalarioMinimoCtrll extends ControllerBase {
	
	@Autowired
	private SalarioMinimoService salarioMinService;
	
	@Setter
	@Getter
	private String zona;
	
	@Getter
	@Setter
	private Date fechaIni;
	
	@Getter
	@Setter
	private Integer idUsuario;
	
	@Getter
	@Setter
	private SalarioMinimoOut salarioMinOut;
	
	
	public void getSalarioMinimo() {
		try {
			salarioMinOut = salarioMinService.getSalarioMinimo(idUsuario,fechaIni);
			
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
	
//	public void getZona() {
//		try {
//			zona = "A";
//			
//		} catch (Exception e) {
//			GenericException(e);
//		}
//	}
}
