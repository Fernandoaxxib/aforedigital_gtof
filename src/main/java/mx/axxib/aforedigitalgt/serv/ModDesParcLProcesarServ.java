package mx.axxib.aforedigitalgt.serv;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModDesParcLProcesarRepo;

import mx.axxib.aforedigitalgt.eml.ProcesResult;

import mx.axxib.aforedigitalgt.eml.LoteOut;


@Service
public class ModDesParcLProcesarServ extends ServiceBase {

	@Autowired
	private ModDesParcLProcesarRepo desParcRepo;
	
	public List<LoteOut> getLotes() throws AforeException {		
		try {
			return desParcRepo.getLotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ProcesResult generarLayout(Integer in_Opciones,Date p_Fecha,String p_Lote,String p_Ruta,String p_Archivo) throws AforeException {
		try {
			return desParcRepo.generarLayout(in_Opciones, p_Fecha, p_Lote, p_Ruta, p_Archivo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
