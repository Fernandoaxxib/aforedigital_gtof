package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.MonitorProcesosRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ObtieneJobs;
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio del monitor de procesos
//** Interventor Principal: JSAS
//** Fecha Creación: 19/Nov/2020
//** Última Modificación:
//***********************************************//
@Service
public class MonitorProcesosServ extends ServiceBase {

	@Autowired
	private MonitorProcesosRepo monitorRepo;
	
	public ObtieneMonitorOut getMonitor() throws AforeException {
		try {
			return monitorRepo.getMonitor();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public ObtieneJobsOut getJobs() throws AforeException {
		try {
			return monitorRepo.getJobs();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut ejecutar(ObtieneJobs job) throws AforeException {
		try {
			return monitorRepo.ejecutar(job);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
