package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionIMSSReportesRepo;
import mx.axxib.aforedigitalgt.reca.eml.LotesOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaProcesoEjecutarIn;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudacion IMSS Reportes
//** Interventor Principal: JSAS
//** Fecha Creación: 13/Ene/2022
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionIMSSReportesServ extends ServiceBase {

	@Autowired
	private RecaudacionIMSSReportesRepo repo;
	
	public LotesOut lotes() throws AforeException {
		try {
			return repo.lotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut reporteEjecutar(RecaProcesoEjecutarIn in) throws AforeException {
		try {
			return repo.reporteEjecutar(in);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
