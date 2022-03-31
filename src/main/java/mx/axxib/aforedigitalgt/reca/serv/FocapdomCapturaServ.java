package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.FocapdomRepo;
import mx.axxib.aforedigitalgt.reca.eml.FocapdomOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio FOCAPDOM Carga
//** Interventor Principal: JJSC
//** Fecha Creación: 24/03/2022
//** Última Modificación:
//***********************************************//
@Service
public class FocapdomCapturaServ extends ServiceBase {
	
	@Autowired
	private FocapdomRepo repo;
	
	public FocapdomOut getDatosIniciales() throws AforeException {
		try {
			return repo.getDatosIniciales();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut cargaMasiva(String ic_NOMBRE_ARCHIVO) throws AforeException {
		try {
			return repo.cargaMasiva(ic_NOMBRE_ARCHIVO);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
