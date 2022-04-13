package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.FovulredRepo;
import mx.axxib.aforedigitalgt.reca.eml.FovulredOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio FOVULRED
//** Interventor Principal: JJSC
//** Fecha Creación: 13/04/2022
//** Última Modificación:
//***********************************************//

@Service
public class FovulredServ extends ServiceBase{

	@Autowired
	private FovulredRepo repo;
	
	public FovulredOut getRedesComerciales(Integer P_OPCION, String P_NOMBRE_ARCHIVO, String P_RUTA,
			Date P_FECHA_REVERSA, Integer P_SECUENCIA, Date P_FEC_GEN_MOV) throws AforeException {
		try {
			return repo.getRedesComerciales(P_OPCION, P_NOMBRE_ARCHIVO, P_RUTA, P_FECHA_REVERSA, P_SECUENCIA, P_FEC_GEN_MOV);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FovulredOut reversar(Integer P_OPCION, String P_NOMBRE_ARCHIVO, String P_RUTA, Date P_FECHA_REVERSA,
			Integer P_SECUENCIA, Date P_FEC_GEN_MOV) throws AforeException {
		try {			
			return repo.reversar(P_OPCION, P_NOMBRE_ARCHIVO, P_RUTA, P_FECHA_REVERSA, P_SECUENCIA, P_FEC_GEN_MOV);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FovulredOut getReportesRedesComerciales(Integer P_OPCION, Date P_FEC_INICIO, Date P_FEC_FIN,
			String P_LOTE_REP, String P_NOM_ARCHIVO) throws AforeException {
		try {
			return repo.getReportesRedesComerciales(P_OPCION, P_FEC_INICIO, P_FEC_FIN, P_LOTE_REP, P_NOM_ARCHIVO);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FovulredOut getLotes(Integer P_OPCION, String P_NOMBRE_ARCHIVO, String P_RUTA, Date P_FECHA_REVERSA,
			Integer P_SECUENCIA, Date P_FEC_GEN_MOV) throws AforeException {
		try {			
			return repo.getLotes(P_OPCION, P_NOMBRE_ARCHIVO, P_RUTA, P_FECHA_REVERSA, P_SECUENCIA, P_FEC_GEN_MOV);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public FovulredOut consultarPendientes(String P_NSS_CURP,Integer P_OPCION_REC) throws AforeException {
		try {			
			return repo.consultarPendientes(P_NSS_CURP, P_OPCION_REC);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}