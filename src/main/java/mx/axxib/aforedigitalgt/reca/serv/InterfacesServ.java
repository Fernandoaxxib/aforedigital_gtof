package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.InterfacesRepo;
import mx.axxib.aforedigitalgt.reca.eml.InterInversionOut;
import mx.axxib.aforedigitalgt.reca.eml.InterLoteOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Interfaces
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Mar/2022
//** Última Modificación:
//***********************************************//

@Service
public class InterfacesServ extends ServiceBase {

	@Autowired
	private InterfacesRepo repo;
	
	public InterLoteOut lote() throws AforeException {
		try {
			return repo.lote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public InterInversionOut inversion() throws AforeException {
		try {
			return repo.inversion();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut interfaces(String tipo, String lote, String claveInversion, Integer codInversion, Date fecha) throws AforeException {
		try {
			return repo.interfaces(tipo, lote, claveInversion, codInversion, fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
