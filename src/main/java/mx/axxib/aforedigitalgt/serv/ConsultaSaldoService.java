package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultaSaldoClienteDAO;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoCliente;

@Service
public class ConsultaSaldoService extends ServiceBase {
	
	@Autowired
	private ConsultaSaldoClienteDAO consultaSaldo;
	
	public List <ConsultaSaldoCliente> getConsultaNombre() throws AforeException {
		
		try {
			return	consultaSaldo.getConsultaNombre();
		} catch (Exception e) {
			throw GenericException(e);
		}
		
		}
	public List <ConsultaSaldoCliente> getConsultaId() throws AforeException {
		
		try {
			return	consultaSaldo.getConsultaID();
		} catch (Exception e) {
			throw GenericException(e);
		}
		
		}
}
