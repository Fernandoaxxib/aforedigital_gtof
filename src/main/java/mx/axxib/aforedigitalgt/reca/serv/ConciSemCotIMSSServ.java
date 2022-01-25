package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.ConciSemCotIMSSRepo;
import mx.axxib.aforedigitalgt.reca.eml.DatosConciOut;
import mx.axxib.aforedigitalgt.reca.eml.SemanasOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Conciliación Semanas de Cotización IMSS
//** Interventor Principal: JSAS
//** Fecha Creación: 31/Ene/2022
//** Última Modificación:
//***********************************************//

@Service
public class ConciSemCotIMSSServ extends ServiceBase {

	@Autowired
	private ConciSemCotIMSSRepo repo;
	
	public SemanasOut buscar(Date fechaI, Date fechaF, String nss) throws AforeException {
		try {
			return repo.buscar(fechaI, fechaF, nss);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public DatosConciOut datos(Integer noMov, Date fechaMov) throws AforeException {
		try {
			return repo.datos(noMov, fechaMov);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
