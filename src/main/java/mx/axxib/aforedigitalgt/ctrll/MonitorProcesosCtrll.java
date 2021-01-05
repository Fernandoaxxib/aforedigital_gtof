package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import lombok.Getter;

import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;

import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;
import mx.axxib.aforedigitalgt.serv.MonitorProcesosServ;

@Scope(value = "session")
@Component(value = "monitorProcesos")
@ELBeanName(value = "monitorProcesos")
public class MonitorProcesosCtrll extends ControllerBase {

	@Autowired
	private MonitorProcesosServ monitorService;

	@Autowired
	private AforeMessage aforeMessage;

	@Getter
	private List<ObtieneMonitorOut> monitor;
	
	@Getter
	private List<ObtieneJobsOut> jobs;
	
	@Getter
	@Setter
	private ObtieneMonitorOut selectedMonitor;
	
	@Getter
	@Setter
	private ObtieneJobsOut selectedJob;



	public void consultarMonitor() {
		try {
			monitor = monitorService.getMonitor();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void consultarJobs() {
		try {
			jobs = monitorService.getJobs();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutar() {
		try {
			if(selectedJob != null) {
				String msg = monitorService.ejecutar(selectedJob);
				if(msg.trim().toUpperCase().equals("OK")) {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
				} else {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
				}
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

}
