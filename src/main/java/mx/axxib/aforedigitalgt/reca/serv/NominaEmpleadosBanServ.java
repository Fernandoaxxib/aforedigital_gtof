package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.reca.dal.NominaEmpleadosBanRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Nómina Empleados Grupo Financiero Banorte
//** Interventor Principal: JJSC
//** Fecha Creación: 22/NOV/2021
//** Última Modificación:
//***********************************************//

@Service
public class NominaEmpleadosBanServ extends ServiceBase{
	
	@Autowired
	private NominaEmpleadosBanRepo repo;

}
