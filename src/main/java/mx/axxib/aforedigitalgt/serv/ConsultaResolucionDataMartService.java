package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultaResolucionDataMartDAO;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionDataMartOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaOut;

@Service
public class ConsultaResolucionDataMartService extends ServiceBase {
	
	@Autowired
	private ConsultaResolucionDataMartDAO consultaResolucionDataMartDAO;
	
	
	public ConsultarNombreCuentaOut getCuentaNombre(String nss) throws AforeException {
	try {
		return consultaResolucionDataMartDAO.getCuentaNombre(nss);
	} catch (Exception e) {
		throw GenericException(e);
	}
	}
	
	public List<ConsultaResolucionDataMartOut> getConsultarTraspasos() throws AforeException {
		try {
			return consultaResolucionDataMartDAO.getConsultarTraspasos();
		} catch (Exception e) {
			throw GenericException(e);
		}
		}	
}
