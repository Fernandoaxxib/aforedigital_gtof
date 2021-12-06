package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.reca.dal.CentroPatronalEmpresarialRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Centro Patronal Empresarial
//** Interventor Principal: JSAS
//** Fecha Creación: 23/Nov/2021
//** Última Modificación:
//***********************************************//

@Service
public class CentroPatronalEmpresarialServ extends ServiceBase {

	@Autowired
	private CentroPatronalEmpresarialRepo repo;
	
}
