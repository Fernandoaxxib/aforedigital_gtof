package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.GeneracionLotesInteresesRepo;
import mx.axxib.aforedigitalgt.reca.eml.InteresesTranOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de generación de lotes intereses 
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:
//***********************************************//

@Service
public class GeneracionLotesInteresesServ  extends ServiceBase{

	@Autowired
	private GeneracionLotesInteresesRepo repo;
	
	
	public InteresesTranOut getFechas() throws AforeException {
		try {			
			return repo.getFechas();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public InteresesTranOut getLotes() throws AforeException {
		try {			
			return repo.getLotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public InteresesTranOut getLotesRev() throws AforeException {
		try {
			return repo.getLotesRev();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public InteresesTranOut generarLotes(Integer P_TIPO_ACCION, Date P_FEC_OPERACION, Integer P_LOTE, String P_ARCHIVO,
			String P_RUTA, Integer P_LOTE_REVERSA) throws AforeException {
		try {			
			return repo.generarLotes(P_TIPO_ACCION, P_FEC_OPERACION, P_LOTE, P_ARCHIVO, P_RUTA, P_LOTE_REVERSA);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
