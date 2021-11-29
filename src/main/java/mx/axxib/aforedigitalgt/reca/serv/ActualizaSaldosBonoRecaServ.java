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
//** FUNCIONALIDAD DEL OBJETO: Servicio de Actualiza Saldos y Bono de Pensión - Recaudación
//** Interventor Principal: JJSC
//** Fecha Creación: 18/NOV/2021
//** Última Modificación:
//***********************************************//

@Service
public class ActualizaSaldosBonoRecaServ extends ServiceBase {
	
	@Autowired
	private ActualizaSaldosBonoRepo repo;
	
	
	public RecaIssLoteOut getIssLote() throws AforeException {
		try {
			return repo.getIssLote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public RecaIssSieforeOut getIssSiefore() throws AforeException {
		try {			
			return repo.getIssSiefore();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RespuestaOut ejecutarRecaudacion() throws AforeException {
		try {			
			return repo.ejecutarRecaudacion();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
