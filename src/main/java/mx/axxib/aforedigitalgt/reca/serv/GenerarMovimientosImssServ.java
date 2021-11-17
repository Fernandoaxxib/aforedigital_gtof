package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.GenerarMovimientosImssRepo;
import mx.axxib.aforedigitalgt.reca.eml.MovimientosOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Generación de Movimientos Semanas de Cotizaciónn IMSS
//** Interventor Principal: JJSC
//** Fecha Creación: 16/NOV/2021
//** Última Modificación:
//***********************************************//
@Service
public class GenerarMovimientosImssServ extends ServiceBase {

	@Autowired
	private GenerarMovimientosImssRepo repo;
	
	public MovimientosOut getDetalle() throws AforeException {
		try {
			return repo.getDetalle();
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
	
	public MovimientosOut generarMovimientos(Date PFEC_INI,Date PFEC_FIN,Integer pcant) throws AforeException{
		try {
			return repo.generarMovimientos(PFEC_INI,PFEC_FIN,pcant);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
}
