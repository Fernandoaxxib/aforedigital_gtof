package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.LiquidarLoteOp71Repo;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarLoteOp71Out;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Liquidar lote op71
//** Interventor Principal: JJSC
//** Fecha Creación: 30/DIC/2021
//** Última Modificación:
//***********************************************//

@Service
public class LiquidarLoteOp71Serv extends ServiceBase{
	
	@Autowired
	private LiquidarLoteOp71Repo repo;
	
	public LiquidarLoteOp71Out getLote() throws AforeException {
		try {
			return repo.getLote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public LiquidarLoteOp71Out getSiefore() throws AforeException {
		try {
			return repo.getSiefore();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public LiquidarLoteOp71Out getSectores() throws AforeException {
		try {
			return repo.getSectores();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public LiquidarLoteOp71Out generarInterface(String ic_identif_operacion, String ic_fecha_transferencia,
			String ic_secuencia_lote, String id_fec_aplicado, String ic_descripcion1, String ic_cod_inversion)
			throws AforeException {
		try {			
			return repo.generarInterface(ic_identif_operacion, ic_fecha_transferencia, ic_secuencia_lote, id_fec_aplicado, ic_descripcion1, ic_cod_inversion);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public LiquidarLoteOp71Out liquidar(String ic_identif_operacion,
            String ic_fecha_transferencia,String ic_secuencia_lote,
            Double in_monto_liquidado,
            Double in_importes_aceptados,
            String ic_siefore,
            String ic_agrupacion)
			throws AforeException {
		try {			
			return repo.liquidar(ic_identif_operacion, ic_fecha_transferencia, ic_secuencia_lote, in_monto_liquidado, in_importes_aceptados, ic_siefore, ic_agrupacion);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
