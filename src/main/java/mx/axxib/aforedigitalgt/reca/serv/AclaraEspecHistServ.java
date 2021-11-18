package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.AclaracionesEspecialesRepo;
import mx.axxib.aforedigitalgt.reca.eml.InfoPunteoOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Aclaraciones Especiales - Histórico
//** Interventor Principal: JJSC
//** Fecha Creación: 17/NOV/2021
//** Última Modificación:
//***********************************************//

@Service
public class AclaraEspecHistServ  extends ServiceBase{

	@Autowired
	private AclaracionesEspecialesRepo repo;

	
	public InfoPunteoOut cargarInfoHist(String ic_Nss) throws AforeException {
		try {
			return repo.cargarInfoHist(ic_Nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public InfoPunteoOut llenarDatosPunteo(String ic_Nss) throws AforeException {
		try {
			
			return repo.llenarDatosPunteo(ic_Nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
