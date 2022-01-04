package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.LiquidarMovimientosRepo;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarBusquedaOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de liquidar movimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 03/Ene/2022
//** Última Modificación:
//***********************************************//

@Service
public class LiquidarMovimientosServ extends ServiceBase {

	@Autowired
	private LiquidarMovimientosRepo repo;
	
	
	public LiquidarRenOut lote(Date fecha) throws AforeException {
		try {
			return repo.lote(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public LiquidarBusquedaOut buscarFecha(Date fecha) throws AforeException {
		try {
			return repo.buscarFecha(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public LiquidarBusquedaOut buscarLote(String lote, Date fecha) throws AforeException {
		try {
			return repo.buscarLote(lote, fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut liquidar(String lote, Date fecha) throws AforeException {
		try {
			return repo.liquidar(lote, fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
