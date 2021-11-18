package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.reca.dal.LiquidarMovimientosRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de liquidar movimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Nov/2021
//** Última Modificación:
//***********************************************//

@Service
public class LiquidarMovimientosServ extends ServiceBase {

	@Autowired
	private LiquidarMovimientosRepo repo;
	
}
