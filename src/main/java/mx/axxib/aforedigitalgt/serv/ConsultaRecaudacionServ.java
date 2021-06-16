package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultaRecaudacionRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ConsultaNSSOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudación
//** Interventor Principal: JSAS
//** Fecha Creación: 22/Feb/2021
//** Última Modificación:
//***********************************************//
@Service
public class ConsultaRecaudacionServ extends ServiceBase {

	@Autowired
	private ConsultaRecaudacionRepo consultaRepo;
	
	public ConsultaNSSOut getConsultaNSS(String nss) throws AforeException {
		try {
			return consultaRepo.getConsultaNSS(nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public BaseOut getGeneraReporte(String nss) throws AforeException {
		try {
			return consultaRepo.getGeneraReporte(nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
