package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.eml.PermisoResult;
import mx.axxib.aforedigitalgt.eml.TiposReportes;
import mx.axxib.aforedigitalgt.serv.OrdenPagoServ;

@Scope(value = "session")
@Component(value = "ordenPago")
@ELBeanName(value = "ordenPago")
public class OrdenPagoCtrll extends ControllerBase {

	@Autowired
	private OrdenPagoServ ordenPagoServ;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private Date fechaInicio;
	
	@Getter
	@Setter
	private Date fechaUltima;
	
	@Getter
	@Setter
	private TiposReportes tiposReportes;
	
	@Getter
	@Setter
	private Boolean radio1;
	
	@Getter
	@Setter
	private Boolean radio2;
	
	public void cargaFechas() {
	try {
		
	}catch (Exception e) {
		GenericException(e);
	}	
		
	}
	
	public void obtenerNombre() {
		try {
			ordenPagoServ.obtenerNombre(fechaInicio, fechaInicio, 1);
		}catch (Exception e) {
			GenericException(e);
		}		
		
	}
	
	public void creaReporte() {
		try {
			ordenPagoServ.creaReporte(nombre);
		}catch (Exception e) {
			GenericException(e);
		}	
	}
	
	public void generaReporte() {
		try {
			ordenPagoServ.generaReporte(fechaInicio, fechaUltima);
		}catch (Exception e) {
			GenericException(e);
		}	
	}
}
