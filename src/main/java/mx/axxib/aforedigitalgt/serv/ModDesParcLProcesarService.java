package mx.axxib.aforedigitalgt.serv;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModDesParcLProcesarRepo;

import mx.axxib.aforedigitalgt.eml.ProcesResult;

import mx.axxib.aforedigitalgt.eml.LoteOut;


@Service
public class ModDesParcLProcesarService extends ServiceBase {

	@Autowired
	private ModDesParcLProcesarRepo desParcRepo;
	
	public List<LoteOut> getLotes() throws AforeException {		
		try {
			return desParcRepo.getLotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ProcesResult generarLayout(Integer pn_Opciones) throws AforeException {
		try {
			return desParcRepo.generarLayout(pn_Opciones);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
