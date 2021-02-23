package mx.axxib.aforedigitalgt.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ObtieneJobs;
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;

@Repository
@Transactional
public class MonitorProcesosRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public ObtieneMonitorOut getMonitor() throws AforeException {
//	    PROCEDURE PRC_RET_OBTIENE_MONITOR(P_DATOS_MUNITOR OUT SYS_REFCURSOR, 
//                P_ESTATUS OUT NUMBER,
//              P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE)
					.concat(".").concat(Constantes.MONITOR_PROCESOS_OBTIENE_MONITOR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ObtieneMonitor");

			query.registerStoredProcedureParameter("P_DATOS_MUNITOR", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			ObtieneMonitorOut res = new ObtieneMonitorOut();
			res.setMonitor(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtieneJobsOut getJobs() throws AforeException {
//	    PROCEDURE PRC_RET_OBTIENE_JOBS(P_DATOS_JOB OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE)
					.concat(".").concat(Constantes.MONITOR_PROCESOS_OBTIENE_JOBS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ObtieneJobs");

			query.registerStoredProcedureParameter("P_DATOS_JOB", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);


			ObtieneJobsOut res = new ObtieneJobsOut();
			res.setJobs(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ejecutar(ObtieneJobs job) throws AforeException {
//	    PROCEDURE PRC_RET_EJECUTA_JOB(P_JobNumber IN NUMBER, 
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MONITOR_PROCESOS_PACKAGE)
					.concat(".").concat(Constantes.MONITOR_PROCESOS_EJECUTA_JOB);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_JobNumber", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_JobNumber", job.getJob());

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
