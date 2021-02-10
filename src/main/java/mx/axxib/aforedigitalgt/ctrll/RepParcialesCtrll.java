package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.serv.RepParcialesServ;

@Scope(value = "session")
@Component(value = "repParcialesConsar")
@ELBeanName(value = "repParcialesConsar")
public class RepParcialesCtrll extends ControllerBase {

    @Autowired		
	private RepParcialesServ service;
    
    @Getter
    @Setter
    private Date fechaInicio;
    @Getter
    @Setter
    private Date fechaFin;
    @Getter
    @Setter
    private String ruta;
    @Getter
    @Setter
    private String archivo;
    
    @Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			reset();
		}
	}
    
    public void reset() {}
    
}
