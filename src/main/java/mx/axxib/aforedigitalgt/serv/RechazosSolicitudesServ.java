package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.RechazosSolicitudesRepo;
import mx.axxib.aforedigitalgt.eml.CatRechazosOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteIn;
import mx.axxib.aforedigitalgt.eml.GeneraReporteOut;
import mx.axxib.aforedigitalgt.eml.RechazosOut;

@Service
public class RechazosSolicitudesServ extends ServiceBase {

	@Autowired
	private RechazosSolicitudesRepo rechazos;
	
	public RechazosOut getConsultaFolio(int folio) throws AforeException {
		try {
			return rechazos.getConsultaFolio(folio);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RechazosOut getConsultaResolucion(String resolucion) throws AforeException {
		try {
			return rechazos.getConsultaResolucion(resolucion);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RechazosOut getConsultaNSS(String nss) throws AforeException {
		try {
			return rechazos.getConsultaNSS(nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public List<CatRechazosOut> getCatalogo() throws AforeException {
		try {
			return rechazos.getCatalogo();
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
