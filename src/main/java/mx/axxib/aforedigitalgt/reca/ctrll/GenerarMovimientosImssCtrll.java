package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Generación de Movimientos Semanas de Cotizaciónn IMSS
//** Interventor Principal: JJSC
//** Fecha Creación: 16/NOV/2021
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "generarMovimientosImss")
@ELBeanName(value = "generarMovimientosImss")
public class GenerarMovimientosImssCtrll extends ControllerBase {

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}
}
