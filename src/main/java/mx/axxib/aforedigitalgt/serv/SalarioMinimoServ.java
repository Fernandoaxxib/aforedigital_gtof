package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.SalarioMinimoRepo;
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;

@Service
public class SalarioMinimoServ extends ServiceBase {
	
	@Autowired
	private SalarioMinimoRepo dao;
	
	public SalarioMinOut getSalarioMinimo(String usuario) throws AforeException {
		try {
			return dao.getSalarioMinimo(usuario);
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
	
	public String save(String usuario, Date calendario, Double monto) throws AforeException {
		try {
			return dao.save(usuario,calendario,monto);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String update(String usuario, String cdZona , Date calendario, Double monto)  throws AforeException {
		try {
			return dao.update(usuario,cdZona,calendario,monto);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
