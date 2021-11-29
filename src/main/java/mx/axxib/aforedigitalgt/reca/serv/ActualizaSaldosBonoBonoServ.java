package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.ActualizaSaldosBonoRepo;
import mx.axxib.aforedigitalgt.reca.eml.RecaIssLoteOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaIssSieforeOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Actualiza Saldos y Bono de Pensión - Bono
//** Interventor Principal: JJSC
//** Fecha Creación: 18/NOV/2021
//** Última Modificación:
//***********************************************//

@Service
public class ActualizaSaldosBonoBonoServ extends ServiceBase{

	@Autowired
	private ActualizaSaldosBonoRepo repo;
	
	
	public RecaIssLoteOut getBonoLote() throws AforeException {
		try {
			return repo.getBonoLote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RecaIssSieforeOut getBonoSiefore() throws AforeException {
		try {
			return repo.getBonoSiefore();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut ejecutarBono() throws AforeException {
		try {
			return repo.ejecutarBono();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
