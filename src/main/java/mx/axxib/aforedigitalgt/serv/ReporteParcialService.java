package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReporteParcialDAO;

@Service
public class ReporteParcialService extends ServiceBase {
	
	@Autowired
	ReporteParcialDAO reporteParcial;
	
	public String crearReporteParcial(Date fechaIni,Date fechaFin, String ruta, String nombre) throws AforeException{
		try {
			return reporteParcial.crearReporteParcial(fechaIni,fechaFin,ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
