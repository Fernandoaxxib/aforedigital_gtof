package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoCliente;
import mx.axxib.aforedigitalgt.serv.ConsultaSaldoService;

@Scope(value = "session")
@Component(value = "consultaSaldos")
@ELBeanName(value = "consultaSaldos")
public class ConsultaSaldosClienteCtrll extends ControllerBase {
	
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Autowired
	private ConsultaSaldoService consultaSaldoService;
	
	@Getter
	@Setter
	private List< ConsultaSaldoCliente> consultaSaldo;
	
	
	public void consultarNombre() {
		try {
			consultaSaldo=consultaSaldoService.getConsultaNombre();
		} catch (Exception e) {
		GenericException(e);
	}
	}
	public void consultarId() {
		try {
			consultaSaldo=consultaSaldoService.getConsultaId();
		} catch (Exception e) {
		GenericException(e);
	}
	}
	

}
