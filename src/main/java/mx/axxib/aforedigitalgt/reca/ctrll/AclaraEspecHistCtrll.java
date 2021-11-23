package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.AclaraEspecHistServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aclaraciones Especiales - Histórico
//** Interventor Principal: JJSC
//** Fecha Creación: 17/NOV/2021
//** Última Modificación:

@Scope(value = "session")
@Component(value = "aclaraEspecHist")
@ELBeanName(value = "aclaraEspecHist")
public class AclaraEspecHistCtrll extends ControllerBase {
	
	@Autowired
	private AclaraEspecHistServ service;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}

}
