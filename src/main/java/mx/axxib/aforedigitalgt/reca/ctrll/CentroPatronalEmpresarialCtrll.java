package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.CentroPatronalEmpresarialServ;
import mx.axxib.aforedigitalgt.serv.CargaRightServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Centro Patronal Empresarial
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Nov/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "centroPatronal")
@ELBeanName(value = "centroPatronal")
public class CentroPatronalEmpresarialCtrll extends ControllerBase {

	@Autowired
	private CentroPatronalEmpresarialServ serv;

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
