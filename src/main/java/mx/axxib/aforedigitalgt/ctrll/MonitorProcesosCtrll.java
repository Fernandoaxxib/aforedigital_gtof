package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
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
	// @Setter
	private List<ObtieneMonitorOut> monitor;

	public int getCount() {
		if (monitor != null) {
			return monitor.size();
		}
		return 0;
	}

	public void consultarMonitor() {
		try {
			monitor = monitorService.getMonitor();
		} catch (Exception e) {
			GenericException(e);
		}

	}

}
