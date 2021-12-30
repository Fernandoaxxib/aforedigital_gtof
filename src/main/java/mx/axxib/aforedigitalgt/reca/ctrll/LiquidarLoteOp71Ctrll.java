package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarLoteOp71Serv;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Liquidar lote OP71
//** Interventor Principal: JJSC
//** Fecha Creación: 30/DIC/2021
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "liquidarLoteOp71")
@ELBeanName(value = "liquidarLoteOp71")
public class LiquidarLoteOp71Ctrll extends ControllerBase {

	@Autowired
	private LiquidarLoteOp71Serv service;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
		
		}
	}
}
