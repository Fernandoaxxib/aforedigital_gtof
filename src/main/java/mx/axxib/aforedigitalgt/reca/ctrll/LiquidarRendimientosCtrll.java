package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarMovimientosServ;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarRendimientosServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Nov/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "liquidarRendimientos")
@ELBeanName(value = "liquidarRendimientos")
public class LiquidarRendimientosCtrll extends ControllerBase {

	@Autowired
	private LiquidarRendimientosServ serv;

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
