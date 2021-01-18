package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.CargaRightIn;
import mx.axxib.aforedigitalgt.eml.CargaRightOut;
import mx.axxib.aforedigitalgt.serv.CargaRightServ;

@Scope(value = "session")
@Component(value = "cargaRight")
@ELBeanName(value = "cargaRight")
public class CargaRightCtrll extends ControllerBase {

	@Autowired
	private CargaRightServ cargaService;

	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	@Setter
	private String archivo;
	
	@Getter
	private String mensaje;
	
	@Getter
	private String linea;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			fecha = null;
			archivo = null;
			mensaje = null;
			linea = null;
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}
	
	public void crucePrevio() {
		try {
			CargaRightIn parametros = new CargaRightIn();
			parametros.setFecha(fecha);
			parametros.setNombreArchivo(archivo);
			CargaRightOut res = cargaService.getCrucePrevio(parametros);
			mensaje = res.getMensaje();
			linea = res.getLinea();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void cargar() {
		try {
			CargaRightIn parametros = new CargaRightIn();
			parametros.setFecha(fecha);
			parametros.setNombreArchivo(archivo);
			CargaRightOut res = cargaService.getCarga(parametros);
			mensaje = res.getMensaje();
			linea = res.getLinea();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
}
