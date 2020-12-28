package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;

@Scope(value = "session")
@Component(value = "reinversionProceso")
@ELBeanName(value = "reinversionProceso")
public class ReinvercionProceso extends ControllerBase {
	
	
	
	
	@Getter
	@Setter
	private boolean value1;
	
	@Getter
	@Setter
    private boolean value2;
	
	@Getter
	@Setter
    private boolean value3;  
	
	@Getter
	@Setter
    private boolean value4;
    
	@Getter
	@Setter
    private String noCuenta;
	
	@Getter
	@Setter
    private Date   fechaUltima;
	
	
	public void getEjecutarProceso() {
		try {
			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	
	private Integer progress;  
	public Integer getProgress() {  
	if(progress == null) {  
	progress = 0;  
	}  
	else {  
	progress = progress + (int)(Math.random() * 15);  
	  
	if(progress > 100)  
	progress = 100;  
	}  
	return progress;  
	}  
	public void setProgress(Integer progress) {  
	this.progress = progress;  
	}  
	public void onComplete() {  
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));  
	}  
	public void cancel() {  
	progress = null;  
	}  
}
