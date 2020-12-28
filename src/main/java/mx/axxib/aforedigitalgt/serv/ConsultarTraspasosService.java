package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultarTraspasosDAO;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosOut;

@Service
public class ConsultarTraspasosService extends ServiceBase {
	
	@Autowired
	private ConsultarTraspasosDAO consultarTraspasos;
	
	public List<ConsultarTraspasosOut> getConsultarTraspasos() throws AforeException {
			try {
				return consultarTraspasos.getConsultarTraspasos();
			} catch (Exception e) {
				throw GenericException(e);
			}
		}
	
	public List<ConsultarNombreCuentaOut> getConsultarCurp() throws AforeException {
		try {
			return consultarTraspasos.getConsultarCurp();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ConsultarNombreCuentaOut> getConsultarNss() throws AforeException {
		try {
			return consultarTraspasos.getConsultarNss();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
}
