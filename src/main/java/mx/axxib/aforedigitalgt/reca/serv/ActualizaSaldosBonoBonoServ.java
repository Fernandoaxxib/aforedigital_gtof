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
//** Fecha Creación: 10/01/2022
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
	
	public RespuestaOut ejecutarBono(String ic_Lote1, String ic_Fecha_Aplicado1, String ic_Cod_Inversion1) throws AforeException {
		try {
			return repo.ejecutarBono( ic_Lote1,  ic_Fecha_Aplicado1,  ic_Cod_Inversion1);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut getMontosBonoPen(String ic_lote1, Integer in_cod_inversion1) throws AforeException {
		try {
			return repo.getMontosBonoPen(ic_lote1, in_cod_inversion1);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}

}
