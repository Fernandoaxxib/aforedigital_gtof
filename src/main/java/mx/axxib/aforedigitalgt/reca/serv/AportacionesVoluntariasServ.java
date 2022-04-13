package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.AportacionesVoluntariasRepo;
import mx.axxib.aforedigitalgt.reca.eml.AportacionesVoluntariasOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudacion ISSSTE
//** Interventor Principal: EAG
//** Fecha Creación: 06/Abril/2022
//** Última Modificación:
//***********************************************//

@Service
public class AportacionesVoluntariasServ extends ServiceBase {

	@Autowired
	private AportacionesVoluntariasRepo repo;
	

	public AportacionesVoluntariasOut consultaAportacionesVoluntarias(Integer sucursal) throws AforeException {
		try {
			return repo.consultaAportacionesVoluntarias(sucursal);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public AportacionesVoluntariasOut consultaAportacionesVoluntariasIndependientes(Integer sucursal) throws AforeException {
		try {
			return repo.consultaAportacionesVoluntariasIndependientes(sucursal);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
