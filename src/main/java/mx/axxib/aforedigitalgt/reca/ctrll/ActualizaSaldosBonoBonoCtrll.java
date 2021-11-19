package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Actualiza Saldos y Bono de Pensión - Bono
//** Interventor Principal: JJSC
//** Fecha Creación: 18/NOV/2021
//** Última Modificación:

@Scope(value = "session")
@Component(value = "actualizaSaldosBonoBono")
@ELBeanName(value = "actualizaSaldosBonoBono")
public class ActualizaSaldosBonoBonoCtrll extends ControllerBase{

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}
	
}
