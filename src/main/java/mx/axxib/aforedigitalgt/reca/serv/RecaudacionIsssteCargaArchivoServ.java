package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionIsssteCargaArchivoRepo;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteCargaArchivoOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudacion ISSSTE
//** Interventor Principal: EAG
//** Fecha Creación: 04/Abril/2022
//** Última Modificación:
//***********************************************//

@Service
public class RecaudacionIsssteCargaArchivoServ extends ServiceBase {

	@Autowired
	private RecaudacionIsssteCargaArchivoRepo repo;
	

	public BaseOut cargarArchivo(String arch_entrada, String dir_archivo, String tipo) throws AforeException {
		try {
			return repo.cargarArchivo(arch_entrada, dir_archivo, tipo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut reversa(String lote_reversa) throws AforeException {
		try {
			return repo.reversa(lote_reversa);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RecaudacionIsssteCargaArchivoOut lReversa() throws AforeException {
		try {
			return repo.lReversa();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
