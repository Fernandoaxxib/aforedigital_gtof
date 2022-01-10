package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.AclaraEspecProcesosServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aclaraciones Especiales - Procesos
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:

@Scope(value = "session")
@Component(value = "aclaraEspecProcesos")
@ELBeanName(value = "aclaraEspecProcesos")
public class AclaraEspecProcesosCtrll extends ControllerBase {

	@Autowired
	private AclaraEspecProcesosServ service;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		}
	}
}
