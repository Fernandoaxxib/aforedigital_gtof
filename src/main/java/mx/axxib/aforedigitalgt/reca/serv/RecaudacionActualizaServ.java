package mx.axxib.aforedigitalgt.reca.serv;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionRepo;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudación - Actualiza
//** Interventor Principal: JJSC
//** Fecha Creación: 07/03/2022
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionActualizaServ extends ServiceBase {

	@Autowired
	private RecaudacionRepo repo;

	public RecaudacionOut getListaLote() throws AforeException {
		try {
			return repo.getListaLote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RecaudacionOut actualizarSaldos(String ic_id_operacion, Date id_fecha_transf, Integer in_secuencia_lote,
			BigDecimal in_TOT_COMPRAR, Date ic_FECHA_43BIS) throws AforeException {
		try {
			return repo.actualizarSaldos(ic_id_operacion, id_fecha_transf, in_secuencia_lote, in_TOT_COMPRAR,
					ic_FECHA_43BIS);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RecaudacionOut obtenerMontoTotal(String ic_id_operacion, Date id_fecha_transf, Integer in_secuencia_lote)
			throws AforeException {
		try {
			return repo.obtenerMontoTotal(ic_id_operacion, id_fecha_transf, in_secuencia_lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}