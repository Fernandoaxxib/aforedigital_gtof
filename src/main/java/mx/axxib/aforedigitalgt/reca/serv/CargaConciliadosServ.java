package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.CargaConciliadosRepo;
import mx.axxib.aforedigitalgt.reca.eml.CargaOut;
import mx.axxib.aforedigitalgt.reca.eml.ConciliarOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Carga Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 21/Feb/2022
//** Última Modificación:
//***********************************************//

@Service
public class CargaConciliadosServ extends ServiceBase {

	@Autowired
	private CargaConciliadosRepo repo;
	
	public CargaOut cargaArchivo(String directorio, String archivo, String tipo) throws AforeException {
		try {
			return repo.cargaArchivo(directorio, archivo, tipo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ConciliarOut conciliarArchivo(String tipo, String lote) throws AforeException {
		try {
			return repo.conciliarArchivo(tipo, lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
