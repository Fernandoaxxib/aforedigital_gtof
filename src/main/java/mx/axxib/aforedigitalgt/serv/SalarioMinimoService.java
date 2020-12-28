package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.SalarioMinimoDAO;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;

@Service
public class SalarioMinimoService extends ServiceBase {
	
	@Autowired
	private SalarioMinimoDAO dao;
	
	public SalarioMinimoOut getSalarioMinimo(Integer usuario,Date fecha) throws AforeException {
		try {
			return dao.getSalarioMinimo(usuario,fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
