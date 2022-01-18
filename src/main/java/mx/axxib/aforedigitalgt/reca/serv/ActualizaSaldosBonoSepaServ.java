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
//** FUNCIONALIDAD DEL OBJETO: Servicio de Actualiza Saldos y Bono de Pensión - Separación
//** Interventor Principal: JJSC
//** Fecha Creación: 10/01/2021
//** Última Modificación:
//***********************************************//

@Service
public class ActualizaSaldosBonoSepaServ extends ServiceBase {


	@Autowired
	private ActualizaSaldosBonoRepo repo;
	
	
	public RecaIssLoteOut getSeparacionLote() throws AforeException {
		try {
			return repo.getSeparacionLote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut ejecutarSeparacion(String ic_Lote,String ic_Fecha_Aplicado,String ic_Cod_Inversion ) throws AforeException {
		try {
			return repo.ejecutarSeparacion(ic_Lote,ic_Fecha_Aplicado,ic_Cod_Inversion);
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
	
	public RespuestaOut getMontosSepa(String ic_lotes, Integer in_cod_inversions) throws AforeException {
		try {			
			return repo.getMontosSepa(ic_lotes, in_cod_inversions);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
