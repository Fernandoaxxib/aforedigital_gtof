package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CerInaRepRepo;
import mx.axxib.aforedigitalgt.eml.ReporteOut;

@Service
public class CerInaRepServ extends ServiceBase {

	@Autowired
	private CerInaRepRepo repo;
	
	public ReporteOut procesarReporte(Date p_Fecha_Inicial,Date p_Fecha_Final,String p_Opcion_Reporte) throws AforeException {
		try {
			return repo.procesarReporte(p_Fecha_Inicial, p_Fecha_Final, p_Opcion_Reporte);
		}	catch(Exception e) {
			throw GenericException(e);
		}	
	}
	
}
