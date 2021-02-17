package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;
import mx.axxib.aforedigitalgt.serv.MonitorProcesosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

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

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			actualizar();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void actualizar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			consultarMonitor();
			consultarJobs();
			pr.setDescProceso("Actualizar monitor");
			pr.setStatus("Actualizado correctamente");
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void consultarMonitor() throws Exception {
		selectedMonitor = null;
		monitor = monitorService.getMonitor();
		if (monitor != null && monitor.size() > 0) {
			selectedMonitor = monitor.get(0);
		}

	}

	public void consultarJobs() throws Exception {
		selectedJob = null;
		jobs = monitorService.getJobs();
		if (jobs != null && jobs.size() > 0) {
			selectedJob = jobs.get(0);
		}
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());

			if (selectedJob != null) {
				pr.setDescProceso("Ejecutar job " + selectedJob.getJob());
				String msg = monitorService.ejecutar(selectedJob);
				if (msg.trim().toUpperCase().equals("OK")) {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					// FacesContext.getCurrentInstance().addMessage(null, new
					// FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
					pr.setStatus(msg);
				} else {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
					// FacesContext.getCurrentInstance().addMessage(null, new
					// FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
					pr.setStatus(msg);
				}
			} else {
				pr.setDescProceso("Ejecutar job");
				String msg = aforeMessage.getMessage(ConstantesMsg.SELECCION_REQUERIDA, null);
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
				pr.setStatus(msg);
			}

		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

}
