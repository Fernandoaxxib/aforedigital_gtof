package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.SalarioMinimoRepo;
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoMensaje;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;

@Service
public class SalarioMinimoServ extends ServiceBase {
	
	@Autowired
	private SalarioMinimoRepo dao;
	
	public SalarioMinOut getSalarioMinimo() throws AforeException {
		try {
			return dao.getSalarioMinimo();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public List<SalarioMinimoOut> getSalarioMinimo2(String usuario) throws AforeException {
		try {
			return dao.getSalarioMinimo2(usuario);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public SalarioMinimoMensaje save(String usuario, String zona, Date calendario, Double monto) throws AforeException {
		try {
			return dao.save(usuario,zona,calendario,monto);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public SalarioMinimoMensaje update(SalarioMinimoOut seleccionado)  throws AforeException {
		try {
			return dao.update(seleccionado);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public SalarioMinimoMensaje delete( SalarioMinimoOut seleccionado)  throws AforeException {
		try {
			return dao.delete(seleccionado);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
