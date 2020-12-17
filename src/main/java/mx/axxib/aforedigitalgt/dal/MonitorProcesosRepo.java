package mx.axxib.aforedigitalgt.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.Constantes;
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
		//StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		query.registerStoredProcedureParameter("P_DATOS_MUNITOR", void.class, ParameterMode.REF_CURSOR);		
		List<ObtieneMonitorOut> res = query.getResultList();
		return res;
//		List<Object> res2 = query.getResultList();
//		return null;
	}
}
