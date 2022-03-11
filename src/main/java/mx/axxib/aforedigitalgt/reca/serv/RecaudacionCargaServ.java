package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionRepo;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;


//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudación - Carga
//** Interventor Principal: JJSC
//** Fecha Creación: 09/03/2022
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionCargaServ extends ServiceBase{
	
	@Autowired
	private RecaudacionRepo repo;
	
	public RecaudacionOut cargar(String ic_archivo_sal) throws AforeException {
		try {
			return repo.cargar(ic_archivo_sal);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RecaudacionOut getListaCarga() throws AforeException {
		try {
			return repo.getListaCarga();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RecaudacionOut aplicar(String ic_id_lote) throws AforeException {
		try {
			return repo.aplicar(ic_id_lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
