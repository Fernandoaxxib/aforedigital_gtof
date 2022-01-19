package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.AplicarLoteRecaudaRepo;
import mx.axxib.aforedigitalgt.reca.eml.RespAplicarOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Aplicar lote recauda Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 18/01/2022
//** Última Modificación:
//***********************************************//

@Service
public class AplicarLoteRecaudaServ extends ServiceBase {
	
	@Autowired
	private AplicarLoteRecaudaRepo repo;
	
	public RespAplicarOut ejecutar(String P_lote_recauda, Integer P_fecha_mov, String P_lote_bono, String P_opciones)
			throws AforeException {
		try {
			return repo.ejecutar(P_lote_recauda, P_fecha_mov, P_lote_bono, P_opciones);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespAplicarOut getLotesBono(String P_lote_carga) throws AforeException {
		try {
			return repo.getLotesBono(P_lote_carga);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespAplicarOut getLotesReca(String P_lote_carga) throws AforeException {
		try {
			return repo.getLotesReca(P_lote_carga);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}		
}
