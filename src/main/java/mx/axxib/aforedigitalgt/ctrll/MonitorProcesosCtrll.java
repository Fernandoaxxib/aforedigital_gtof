package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ObtieneJobs;
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitor;
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
	private List<ObtieneMonitor> monitor;

	@Getter
	private List<ObtieneJobs> jobs;

	@Getter
	@Setter
	private ObtieneMonitor selectedMonitor;

	@Getter
	@Setter
	private ObtieneJobs selectedJob;
	
	@Getter
	private Integer tablaCount;

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
			tablaCount = null;
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
		ObtieneMonitorOut res = monitorService.getMonitor();
		if(res.getEstatus() == 1) {
			monitor = res.getMonitor();
			if (monitor != null && monitor.size() > 0) {
				selectedMonitor = monitor.get(0);
				tablaCount = monitor.size();
			}
		} else {
			if(res.getEstatus() == 2) {
				AforeException e = new AforeException("101", "Error en base de datos", res.getMensaje());
				throw e;
			}
		}
	}

	public void consultarJobs() throws Exception {
		selectedJob = null;
		ObtieneJobsOut res = monitorService.getJobs();
		if(res.getEstatus() == 1) {
			jobs = res.getJobs();
			if (jobs != null && jobs.size() > 0) {
				selectedJob = jobs.get(0);
			}
		} else {
			if(res.getEstatus() == 2) {
				AforeException e = new AforeException("101", "Error en base de datos", res.getMensaje());
				throw e;
			}
		}
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());

			if (selectedJob != null) {
				pr.setDescProceso("Ejecutar job " + selectedJob.getJob());
				BaseOut res = monitorService.ejecutar(selectedJob);
				if (res.getEstatus() == 1) {
					String msg = "Job ejecutado correctamente";
					// FacesContext.getCurrentInstance().addMessage(null, new
					// FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
					pr.setStatus(msg);
				} else {
					if(res.getEstatus() == 2) {
						AforeException e = new AforeException("101", "Error en base de datos", res.getMensaje());
						throw e;
					}
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
