package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CargasRetirosParcialesDAO;
import mx.axxib.aforedigitalgt.eml.CargasRetirosParcialesOut;


@Service
public class CargasRetirosParcialesServ extends ServiceBase {
	
	@Autowired
	private CargasRetirosParcialesDAO cargasRetirosParciales;
	
	public CargasRetirosParcialesOut cargaSolicitudArchivo(String nombre) throws AforeException {
		
		try {
			return cargasRetirosParciales.cargaSolicitudArchivo(nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public String cargaSolicitudInicio() throws AforeException {
		try {
			return cargasRetirosParciales.cargaSolicitudInicio();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
