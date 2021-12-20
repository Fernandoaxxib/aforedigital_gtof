package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.RecaudacionIMSSProcesoServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudacion IMSS Proceso
//** Interventor Principal: JSAS
//** Fecha Creación: 13/Dic/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "recaudacionProceso")
@ELBeanName(value = "recaudacionProceso")
public class RecaudacionIMSSProcesoCtrll extends ControllerBase {

	@Autowired
	private RecaudacionIMSSProcesoServ serv;

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
