package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.AclaraEspecPunteoServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aclaraciones Especiales - Punteo
//** Interventor Principal: JJSC
//** Fecha Creación: 17/NOV/2021
//** Última Modificación:

@Scope(value = "session")
@Component(value = "aclaraEspecPunteo")
@ELBeanName(value = "aclaraEspecPunteo")
public class AclaraEspecPunteoCtrll extends ControllerBase {

	@Autowired 
	private AclaraEspecPunteoServ service;
	 
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}
}
