package mx.axxib.aforedigitalgt.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;

@Repository
public class MonitorProcesosRepo {

	private final EntityManager entityManager;
	
	@Autowired
	public MonitorProcesosRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<ObtieneMonitorOut> getMonitor() {
		
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE).concat(".").concat(Constantes.MONITOR_PROCESOS_OBTIENE_MONITOR);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "MonitorOut");

		query.registerStoredProcedureParameter("P_DATOS_MUNITOR", void.class, ParameterMode.REF_CURSOR);		
		List<ObtieneMonitorOut> res = query.getResultList();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<ObtieneJobsOut> getJobs() {
		
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE).concat(".").concat(Constantes.MONITOR_PROCESOS_OBTIENE_JOBS);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "JobsOut");

		query.registerStoredProcedureParameter("P_DATOS_JOB", void.class, ParameterMode.REF_CURSOR);		
		List<ObtieneJobsOut> res = query.getResultList();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public String ejecutar(ObtieneJobsOut job) {
		
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE).concat(".").concat(Constantes.MONITOR_PROCESOS_EJECUTA_JOB);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_JobNumber", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);		
		
		query.setParameter("P_JobNumber", job.getJob());
		
		String res = (String) query.getOutputParameterValue("P_MENSAJE");
		return res;
	}
}
