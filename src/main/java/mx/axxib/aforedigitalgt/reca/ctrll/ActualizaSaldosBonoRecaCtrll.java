package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.ActualizaSaldosBonoRecaServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Actualiza Saldos y Bono de Pensión - Recaudación
//** Interventor Principal: JJSC
//** Fecha Creación: 18/NOV/2021
//** Última Modificación:

@Scope(value = "session")
@Component(value = "actualizaSaldosBonoReca")
@ELBeanName(value = "actualizaSaldosBonoReca")
public class ActualizaSaldosBonoRecaCtrll extends ControllerBase {

	@Autowired
	private ActualizaSaldosBonoRecaServ service;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}
	
}
