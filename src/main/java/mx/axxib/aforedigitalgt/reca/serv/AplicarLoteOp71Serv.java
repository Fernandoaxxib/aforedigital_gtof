package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.AplicarLoteOp71Repo;
import mx.axxib.aforedigitalgt.reca.eml.RespAplicarOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de aplicar lote intereses en tránsito Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 16/02/2022
//** Última Modificación:
//***********************************************//

@Service
public class AplicarLoteOp71Serv extends ServiceBase {

	@Autowired
	private AplicarLoteOp71Repo repo;
	
	public RespAplicarOut ejecutar(String P_lote_recauda, Date P_fecha_mov, String P_OPCION) throws AforeException {
		try {			
			return repo.ejecutar(P_lote_recauda, P_fecha_mov, P_OPCION);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespAplicarOut getLotes() throws AforeException {
		try {			
			return repo.getLotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
