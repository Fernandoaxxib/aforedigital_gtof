package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CerInaProRepo;

public class CerInaProService extends ServiceBase {

	@Autowired
	private CerInaProRepo repo;
	
	public String ejecutarProceso(Integer pOpciones,Date pFechaInicial)throws AforeException {
		try {
			return repo.ejecutarProceso(pOpciones, pFechaInicial);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
}
