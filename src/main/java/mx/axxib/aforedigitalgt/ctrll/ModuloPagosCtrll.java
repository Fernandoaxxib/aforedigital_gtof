package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.serv.ModPagosService;

@Scope(value = "session")
@Component(value = "moduloPagos")
@ELBeanName(value = "moduloPagos")
public class ModuloPagosCtrll extends ControllerBase{

	@Autowired
	private ModPagosService service;
	
	@Getter
	@Setter
	private String tiposPagos;
	@Getter
	@Setter
	private String institucion;
	@Getter
	@Setter
	private String pagosAfiliados;
	@Getter
	@Setter
	private String procesosRetiros;
	@Getter
	@Setter
	private Date fechaProceso;
	@Getter
	@Setter
	private Date fechaProcesoPagos;
	
	@PostConstruct
	public void init() {
		fechaProceso= new Date();
		fechaProcesoPagos= new Date();
	}
	public void fechaProceso() {}
	
	public void refresh() {
		try {
			if(fechaProceso!=null && fechaProcesoPagos!=null ) {
				String resp=service.refresh("SI", fechaProceso, fechaProcesoPagos);
				addMessageOK(resp);
			}else {
				addMessageFail("Seleccione la fecha de proceso y la fecha de retiro");
			}
			
		} catch (Exception e) {
			GenericException(e);
		}

	}
	
	public void generarFondos() {
		
		if(fechaProceso!=null && pagosAfiliados!=null ) {
			try {
				EjecucionResult res=service.generarFondos(fechaProceso, procesosRetiros, tiposPagos, pagosAfiliados, institucion);
				addMessageOK(res.getOcMensaje());
			} catch (Exception e) {
				GenericException(e);
			}
		}else {
			addMessageFail("Seleccione el tipo de fondo y la fecha de proceso.");
		}
	}
	
	public void generarPagos() {
		if(procesosRetiros!=null && tiposPagos!=null && institucion!=null && fechaProcesoPagos!=null ) {
			try {
				service.generarPagos(fechaProcesoPagos, procesosRetiros, institucion, tiposPagos);
				addMessageOK("Proceso realizado.");
			} catch (Exception e) {
				GenericException(e);
			}
		}else {
			addMessageFail("Seleccione el proceso de retiro, institución, tipo de pago y fecha de retiro.");
		}
	}
	public void addMessageOK(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void addMessageFail(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	
}
