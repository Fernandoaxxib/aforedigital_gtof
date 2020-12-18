package mx.axxib.aforedigitalgt.ctrll;


import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "operaciones")
@ELBeanName(value = "operaciones")
@Join(path = "/operaciones", to = "/view/operaciones.jsf")
public class OperacionesCtrll extends ControllerBase{

	
}
