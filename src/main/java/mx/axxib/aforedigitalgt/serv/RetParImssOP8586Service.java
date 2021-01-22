package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.RetParImssOP8586Repo;
import mx.axxib.aforedigitalgt.eml.LoteOP85Out;

@Service
public class RetParImssOP8586Service extends ServiceBase{

	@Autowired
	private RetParImssOP8586Repo repositorio;
	
	
	
	public String cargarArchivoOP85(String p_Path, String p_Nombre_Archivo) throws AforeException {
		try {
			return repositorio.cargarArchivoOP85(p_Path, p_Nombre_Archivo);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
	
	public String cargarArchivoOP86(String p_Path, String p_Nombre_Archivo) throws AforeException {
		try {
			return repositorio.cargarArchivoOP86(p_Path, p_Nombre_Archivo);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
	
	public List<LoteOP85Out> getLotesOP85() throws AforeException {
		try {
			return repositorio.getLotesOP85();
		}catch(Exception e) {
			throw GenericException(e);
		}		
	}
	
	public String generarReporteOP86(String p_Path,String p_Nombre_Archivo,String p_Lote,Date p_Fecha_Inicial,Date p_Fecha_Final) throws AforeException {
		try {
			return repositorio.generarReporteOP86(p_Path, p_Nombre_Archivo, p_Lote, p_Fecha_Inicial, p_Fecha_Final);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
}
