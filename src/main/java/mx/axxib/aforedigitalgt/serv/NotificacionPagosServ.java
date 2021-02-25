package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.NotificacionPagosRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ExportarIn;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;

@Service
public class NotificacionPagosServ extends ServiceBase {

	@Autowired
	private NotificacionPagosRepo notificacionRepo;
	
	public LlenaInfoOut llenaInfo(Date fecha) throws AforeException {
		try {
			return notificacionRepo.llenaInfo(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut enviaFecha(Date fecha) throws AforeException {
		try {
			return notificacionRepo.enviaFecha(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut exportar(ExportarIn parametros) throws AforeException {
		try {
			return notificacionRepo.exportar(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
