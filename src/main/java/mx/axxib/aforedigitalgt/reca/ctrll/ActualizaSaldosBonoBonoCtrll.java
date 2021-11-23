package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.ActualizaSaldosBonoBonoServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Actualiza Saldos y Bono de Pensión - Bono
//** Interventor Principal: JJSC
//** Fecha Creación: 18/NOV/2021
//** Última Modificación:

@Scope(value = "session")
@Component(value = "actualizaSaldosBonoBono")
@ELBeanName(value = "actualizaSaldosBonoBono")
public class ActualizaSaldosBonoBonoCtrll extends ControllerBase{
	
	@Autowired
	private  ActualizaSaldosBonoBonoServ service;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}
	
}
