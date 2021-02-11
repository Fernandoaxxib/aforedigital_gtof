package mx.axxib.aforedigitalgt.dal;

import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;

@Repository
@Transactional
public class MonitorProcesosRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public List<ObtieneMonitorOut> getMonitor() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE)
					.concat(".").concat(Constantes.MONITOR_PROCESOS_OBTIENE_MONITOR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "MonitorOut");

			query.registerStoredProcedureParameter("P_DATOS_MUNITOR", void.class, ParameterMode.REF_CURSOR);
			List<ObtieneMonitorOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtieneJobsOut> getJobs() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE)
					.concat(".").concat(Constantes.MONITOR_PROCESOS_OBTIENE_JOBS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "JobsOut");

			query.registerStoredProcedureParameter("P_DATOS_JOB", void.class, ParameterMode.REF_CURSOR);
			List<ObtieneJobsOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String ejecutar(ObtieneJobsOut job) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE)
					.concat(".").concat(Constantes.MONITOR_PROCESOS_EJECUTA_JOB);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_JobNumber", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_JobNumber", job.getJob());

			String res = (String) query.getOutputParameterValue("P_MENSAJE");
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
