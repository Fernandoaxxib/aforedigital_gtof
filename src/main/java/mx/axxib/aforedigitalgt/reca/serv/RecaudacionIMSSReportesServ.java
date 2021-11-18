package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.reca.dal.RecaudacionIMSSReportesRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudacion IMSS Reportes
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Nov/2021
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionIMSSReportesServ extends ServiceBase {

	@Autowired
	private RecaudacionIMSSReportesRepo repo;
}
