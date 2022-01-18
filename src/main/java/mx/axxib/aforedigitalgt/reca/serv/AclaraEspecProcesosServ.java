package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.AclaracionesEspecialesRepo;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Aclaraciones Especiales - Procesos
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:
//***********************************************//
@Service
public class AclaraEspecProcesosServ extends ServiceBase {
	
	@Autowired
	private AclaracionesEspecialesRepo repo;

	public RespuestaOut cargarArchivo(String ic_Archivo, String ic_Ruta) throws AforeException {
		try {
			return repo.cargarArchivo(ic_Archivo, ic_Ruta);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut generarPunteoAutomatico() throws AforeException {
		try {
			return repo.generarPunteoAutomatico();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
