package mx.axxib.aforedigitalgt.reca.ctrll;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.DatosInicialesFocapdom;
import mx.axxib.aforedigitalgt.reca.eml.FocapdomOut;
import mx.axxib.aforedigitalgt.reca.serv.FocapdomCapturaServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de FOCAPDOM - Captura
//** Interventor Principal: JJSC
//** Fecha Creación: 24/03/2022
//** Última Modificación:

@Scope(value = "session")
@Component(value = "focapdomCaptura")
@ELBeanName(value = "focapdomCaptura")
public class FocapdomCapturaCtrll extends ControllerBase {
	
	@Autowired
	private FocapdomCapturaServ service;
	
	@Getter
	@Setter
	private DatosInicialesFocapdom datosIniciales;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				init = false;
				initDatosIniciales();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}
	
	public void initDatosIniciales() throws Exception{
		FocapdomOut resp=service.getDatosIniciales();
		datosIniciales=resp.getDatosIniciales();
	}

}
