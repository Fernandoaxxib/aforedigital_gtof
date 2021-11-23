package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.NominaEmpleadosBanServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Nómina Empleados Grupo Financiero Banorte
//** Interventor Principal: JJSC
//** Fecha Creación: 22/NOV/2021
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "nominaEmpleadosBan")
@ELBeanName(value = "nominaEmpleadosBan")
public class NominaEmpleadosBanCtrll extends ControllerBase{

	@Autowired
	private NominaEmpleadosBanServ serv;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {						
			init = false;
		}
	}
}
