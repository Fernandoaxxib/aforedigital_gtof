package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.RepParcialesRepo;
import mx.axxib.aforedigitalgt.dal.RepoBase;

@Service
public class RepParcialesServ extends RepoBase{

	@Autowired
	private RepParcialesRepo repo;
	
	public String generarReporte(Date p_fechaInicio,Date p_fechaFinal,String p_Ruta,String p_Archivo) throws AforeException {
		try {
			return repo.generarReporte(p_fechaInicio, p_fechaFinal, p_Ruta, p_Archivo);
		}catch(Exception e) {
			throw GenericException(e);
		}		
	}
}
	
	

