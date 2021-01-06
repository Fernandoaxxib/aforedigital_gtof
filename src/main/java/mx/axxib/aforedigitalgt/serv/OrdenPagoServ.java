package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.OrdenPagoDAO;
import mx.axxib.aforedigitalgt.eml.OrdenPagoArchivoOut;
import mx.axxib.aforedigitalgt.eml.OrdenPagoFechasOut;

@Service
public class OrdenPagoServ extends ServiceBase {
	
	@Autowired
	private OrdenPagoDAO  ordenPagoDAO;
	
	public OrdenPagoFechasOut cargaFechas() throws AforeException {
		try {
			return ordenPagoDAO.cargaFechas();
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	public String obtenerNombre(Date fechaInicio, Date fechaFin,Integer registroReporte) throws AforeException {
		try {
			return ordenPagoDAO.obtenerNombre(fechaInicio,fechaFin,registroReporte);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public OrdenPagoArchivoOut creaReporte(String tipoReporte ) throws AforeException {
		try {
			return ordenPagoDAO.creaReporte(tipoReporte);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String generaReporte(Date fechaInicio, Date fechaFin ) throws AforeException{
		try {
			return ordenPagoDAO.generaReporte(fechaInicio,fechaFin);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
