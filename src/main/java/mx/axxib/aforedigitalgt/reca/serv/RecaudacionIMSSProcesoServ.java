package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionIMSSProcesoRepo;
import mx.axxib.aforedigitalgt.reca.eml.LotesOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaProcesoEjecutarIn;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudacion IMSS Proceso
//** Interventor Principal: JSAS
//** Fecha Creación: 10/Ene/2022
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionIMSSProcesoServ extends ServiceBase {

	@Autowired
	private RecaudacionIMSSProcesoRepo repo;
	
	public LotesOut lotes() throws AforeException {
		try {
			return repo.lotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut procesoEjecutar(RecaProcesoEjecutarIn in) throws AforeException {
		try {
			return repo.procesoEjecutar(in);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
