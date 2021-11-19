package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.reca.dal.ActualizaSaldosBonoRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Actualiza Saldos y Bono de Pensión - Separación
//** Interventor Principal: JJSC
//** Fecha Creación: 18/NOV/2021
//** Última Modificación:
//***********************************************//

@Service
public class ActualizaSaldosBonoSepaServ extends ServiceBase {


	@Autowired
	private ActualizaSaldosBonoRepo repo;
}
