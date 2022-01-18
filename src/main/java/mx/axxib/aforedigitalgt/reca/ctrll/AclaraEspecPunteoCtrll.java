package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.List;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.DatosPunteoOut;
import mx.axxib.aforedigitalgt.reca.serv.AclaraEspecPunteoServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aclaraciones Especiales - Punteo
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:

@Scope(value = "session")
@Component(value = "aclaraEspecPunteo")
@ELBeanName(value = "aclaraEspecPunteo")
public class AclaraEspecPunteoCtrll extends ControllerBase {

	@Autowired 
	private AclaraEspecPunteoServ service;
	
	@Getter
	private List<DatosPunteoOut> datosPunteo;
	 
	@Getter
	@Setter
	private List<DatosPunteoOut> selectedDato;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;
			datosPunteo=null;
			selectedDato=null;
			llenarDatosPunteo();
		}
	}
	
	public void llenarDatosPunteo() {
		
		try {
			datosPunteo= service.llenarDatosPunteo().getDatosPunteo();
		} catch (AforeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
