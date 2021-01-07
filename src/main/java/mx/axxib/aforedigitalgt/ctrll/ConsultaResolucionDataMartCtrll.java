package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionDataMartOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaOut;
import mx.axxib.aforedigitalgt.serv.ConsultaResolucionDataMartService;

@Scope(value = "session")
@Component(value = "consultaResolucionDataMart")
@ELBeanName(value = "consultaResolucionDataMart")
public class ConsultaResolucionDataMartCtrll extends ControllerBase {
	
	@Autowired
	ConsultaResolucionDataMartService consultaResolucionDataMartService;
	
	@Getter
	ConsultarNombreCuentaOut consultarNombreCuentaOut;
	
	@Getter
	@Setter
	List<ConsultaResolucionDataMartOut> listConsultaResolucionDataMartOut;
	
	@Getter
	@Setter
	private String nss;
	
	public void getCuentaNombre(String nss)  {
		try {
			consultarNombreCuentaOut=consultaResolucionDataMartService.getCuentaNombre(nss);
		}catch (Exception e) {
			GenericException(e);
		}
		
		
		}
	public void getConsultarTraspasos() {
		try {
			listConsultaResolucionDataMartOut=consultaResolucionDataMartService.getConsultarTraspasos();
		}catch (Exception e) {
			GenericException(e);
		}
			
	}
}
