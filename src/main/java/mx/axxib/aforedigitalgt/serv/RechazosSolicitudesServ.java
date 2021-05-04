package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.RechazosSolicitudesRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.CatRechazosOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteIn;
import mx.axxib.aforedigitalgt.eml.GeneraReporteOut;
import mx.axxib.aforedigitalgt.eml.RechazosOut;

@Service
public class RechazosSolicitudesServ extends ServiceBase {

	@Autowired
	private RechazosSolicitudesRepo rechazos;
	
	public RechazosOut getConsultaRechazos(String parametro, Integer tipo) throws AforeException {
		try {
			return rechazos.getConsultaRechazos(parametro, tipo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public CatRechazosOut getCatalogo() throws AforeException {
		try {
			return rechazos.getCatalogo();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut ingresarRechazos(RechazosOut parametros) throws AforeException  {
		try {
			return rechazos.ingresarRechazos(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public GeneraReporteOut generaReporte(GeneraReporteIn parametros) throws AforeException {
		try {
			return rechazos.generaReporte(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
