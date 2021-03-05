package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CerInaLPRepo;
import mx.axxib.aforedigitalgt.eml.LoteOut;
import mx.axxib.aforedigitalgt.eml.ProcesResult;

@Service
public class CerInaLPServ extends ServiceBase{

	@Autowired
	private CerInaLPRepo repo;
	
	public List<LoteOut> getLotes() throws AforeException {		
		try {
			return repo.getLotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ProcesResult generarLayout(Date pFechaEntrada,String pLotes,Integer p_opciones,String p_Ruta,String p_Archivo) throws AforeException {
		try {
			return repo.generarLayout(pFechaEntrada,pLotes,p_opciones,p_Ruta, p_Archivo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
