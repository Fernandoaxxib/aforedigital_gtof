package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CerInaAppRepo;

@Service
public class CerInaAppService extends ServiceBase{

	@Autowired
	private CerInaAppRepo repo;
	
	public String ejecutar(String p_Ruta,String p_Archivo)throws AforeException {
		try {
			return repo.ejecutar(p_Ruta, p_Archivo);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
	
	public String generarArchivo(Date p_fecha)throws AforeException {
		try {
			return repo.generarArchivo(p_fecha);
		}catch(Exception e) {
			throw GenericException(e);
		}	
	}
}
