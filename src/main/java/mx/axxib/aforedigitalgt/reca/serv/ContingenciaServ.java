package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.ContingenciaRepo;
import mx.axxib.aforedigitalgt.reca.eml.SellosOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Contingencia
//** Interventor Principal: JSAS
//** Fecha Creación: 27/Ene/2022
//** Última Modificación:
//***********************************************//

@Service
public class ContingenciaServ extends ServiceBase {

	@Autowired
	private ContingenciaRepo repo;
	
	public SellosOut sellos(Integer folio) throws AforeException {
		try {
			return repo.sellos(folio);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut asignar(String sello, String folioSol) throws AforeException {
		try {
			return repo.asignar(sello, folioSol);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut expediente(String seleccion, String noSolicitud) throws AforeException {
		try {
			return repo.expediente(seleccion, noSolicitud);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
