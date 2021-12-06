package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.reca.dal.LiquidarRendimientosRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 30/Nov/2021
//** Última Modificación:
//***********************************************//

@Service
public class LiquidarRendimientosServ extends ServiceBase {

	@Autowired
	private LiquidarRendimientosRepo repo;
	
}
