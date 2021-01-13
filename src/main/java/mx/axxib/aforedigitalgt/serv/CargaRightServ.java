package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CargaRightRepo;
import mx.axxib.aforedigitalgt.eml.CargaRightIn;
import mx.axxib.aforedigitalgt.eml.CargaRightOut;

@Service
public class CargaRightServ extends ServiceBase {

	@Autowired
	private CargaRightRepo cargaRepo;
	
	public CargaRightOut getCrucePrevio(CargaRightIn parametros) throws AforeException {
		try {
			return cargaRepo.getCrucePrevio(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public CargaRightOut getCarga(CargaRightIn parametros) throws AforeException {
		try {
			return cargaRepo.getCarga(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
