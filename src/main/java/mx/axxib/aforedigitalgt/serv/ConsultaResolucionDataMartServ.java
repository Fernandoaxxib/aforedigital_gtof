package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultaResolucionDataMartRepo;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionDataMartOut;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionesNombreOut;

@Service
public class ConsultaResolucionDataMartServ extends ServiceBase {

	@Autowired
	private ConsultaResolucionDataMartRepo consultaResolucionDataMartRepo;
	
	public ConsultaResolucionesNombreOut getCuentaNombre(Long nss) throws AforeException {
		try {
		return consultaResolucionDataMartRepo.getCuentaNombre(nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
