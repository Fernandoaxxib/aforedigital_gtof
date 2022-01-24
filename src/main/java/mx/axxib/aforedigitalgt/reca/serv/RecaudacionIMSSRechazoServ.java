package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionIMSSRechazoRepo;
import mx.axxib.aforedigitalgt.reca.eml.LotesOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaRechazoEjecutarIn;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionPatronalOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudacion IMSS Rechazo
//** Interventor Principal: JSAS
//** Fecha Creación: 24/Ene/2022
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionIMSSRechazoServ extends ServiceBase {

	@Autowired
	private RecaudacionIMSSRechazoRepo repo;
	
	public LotesOut lotes() throws AforeException {
		try {
			return repo.lotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RecaudacionPatronalOut patronal(RecaRechazoEjecutarIn in) throws AforeException {
		try {
			return repo.patronal(in);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut rechazoEjecutar(RecaRechazoEjecutarIn in) throws AforeException {
		try {
			return repo.rechazoEjecutar(in);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
