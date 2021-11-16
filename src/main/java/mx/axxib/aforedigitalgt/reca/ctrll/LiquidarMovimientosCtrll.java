package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de liquidar movimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Nov/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "liquidarMovimientos")
@ELBeanName(value = "liquidarMovimientos")
public class LiquidarMovimientosCtrll extends ControllerBase {

	

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			limpiar();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		
	}

	
}
