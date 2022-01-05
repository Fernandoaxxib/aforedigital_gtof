package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.LiquidarRendimientosRepo;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenConsultaOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 05/Ene/2022
//** Última Modificación:
//***********************************************//

@Service
public class LiquidarRendimientosServ extends ServiceBase {

	@Autowired
	private LiquidarRendimientosRepo repo;
	
	public LiquidarRenOut lote() throws AforeException {
		try {
			return repo.lote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public LiquidarRenConsultaOut consulta(String lote) throws AforeException {
		try {
			return repo.consulta(lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut liquidar(String lote, String tipo) throws AforeException {
		try {
			return repo.liquidar(lote, tipo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
