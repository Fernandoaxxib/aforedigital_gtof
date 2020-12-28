package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosOut;
import mx.axxib.aforedigitalgt.serv.ConsultarTraspasosService;

@Scope(value = "session")
@Component(value = "consultarTraspasos")
@ELBeanName(value = "consultarTraspasos")
public class ConsultarTraspasosCtrll extends ControllerBase {

	@Autowired
	ConsultarTraspasosService consultarTraspasosServices;
	
	@Getter
	List<ConsultarTraspasosOut> consultarTraspasosOut;
	
	@Getter
	@Setter
	ConsultarNombreCuentaOut consultarNombreCuentaOut;
	
	@Getter
	@Setter
	private String nss;
	 
	@Getter
	@Setter
	private String curp;
	
	public void ejecutarConsultaNss() {
		try {
		consultarTraspasosOut=consultarTraspasosServices.getConsultarTraspasos();			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutarConsultaCurp() {
			try {
			consultarTraspasosOut=consultarTraspasosServices.getConsultarTraspasos();			
			}catch (Exception e) {
				GenericException(e);
			}
	}
	
	public void ejecutarConsultarTraspasos() {
		try {
		consultarTraspasosOut=consultarTraspasosServices.getConsultarTraspasos();			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
}
