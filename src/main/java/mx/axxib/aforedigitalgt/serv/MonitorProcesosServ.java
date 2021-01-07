package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.MonitorProcesosRepo;

import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;

import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;

@Service
public class MonitorProcesosServ extends ServiceBase {

	@Autowired
	private MonitorProcesosRepo monitorRepo;
	
	public List<ObtieneMonitorOut> getMonitor() throws AforeException {
		try {
			return monitorRepo.getMonitor();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public List<ObtieneJobsOut> getJobs() throws AforeException {
		try {
			return monitorRepo.getJobs();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String ejecutar(ObtieneJobsOut job) throws AforeException {
		try {
			return monitorRepo.ejecutar(job);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
