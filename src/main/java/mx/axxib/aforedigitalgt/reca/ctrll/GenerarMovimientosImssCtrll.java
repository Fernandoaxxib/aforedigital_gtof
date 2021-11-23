package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.GenerarMovimientosImssServ;

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

	@Autowired
	private GenerarMovimientosImssServ service;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}
}
