package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionIsssteArchivoRespuestaRepo;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteArchivoRespuestaOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudacion ISSSTE
//** Interventor Principal: EAG
//** Fecha Creación: 04/Abril/2022
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionIsssteArchivoRespuestaServ extends ServiceBase {

	@Autowired
	private RecaudacionIsssteArchivoRespuestaRepo repo;
	
	
	public RecaudacionIsssteArchivoRespuestaOut lote() throws AforeException {
		try {
			return repo.lote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RecaudacionIsssteArchivoRespuestaOut iniciaProceso(String lote) throws AforeException {
		try {
			return repo.iniciaProceso(lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
