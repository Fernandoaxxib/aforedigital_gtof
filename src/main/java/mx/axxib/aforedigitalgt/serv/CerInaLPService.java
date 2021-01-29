package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CerInaLPRepo;
import mx.axxib.aforedigitalgt.eml.LoteOut;

@Service
public class CerInaLPService extends ServiceBase{

	@Autowired
	private CerInaLPRepo repo;
	
	public List<LoteOut> getLotes() throws AforeException {
		try {
			return repo.getLotes();
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
	
	public String generarLayoutProcesar(Date pFechaEntrada,String pLotes,Integer p_opciones)throws AforeException {
		try {
			
			return repo.generarLayoutProcesar(pFechaEntrada, pLotes, p_opciones);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
	
}
