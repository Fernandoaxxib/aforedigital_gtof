package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.RetParImssOP84Repo;
import mx.axxib.aforedigitalgt.eml.LoteOP84Out;
import mx.axxib.aforedigitalgt.eml.ProcesResult;

//***********************************************//
//** Funcionalidad: Servicio - Retiros parciales IMSS OP84
//** Desarrollador: JJSC
//** Fecha de creación: 05/Ene/2021
//** Última modificación:
//***********************************************//
@Service
public class RetParImssOP84Serv extends ServiceBase{

	@Autowired
	private RetParImssOP84Repo repositorio;
	
	public ProcesResult cargarArchivoOP84(String p_Path, String p_Nombre_Archivo) throws AforeException {
		try {
			return repositorio.cargarArchivoOP84(p_Path, p_Nombre_Archivo);
		} catch (Exception e) {
			throw GenericException(e);
		}		
	}
	public List<LoteOP84Out> getLotesOP84() throws AforeException {
		try {
			return repositorio.getLotesOP84();
		} catch (Exception e) {
			throw GenericException(e);
		}				
	}
	public ProcesResult getConsultaOP84(Date p_FechaInicial,Date p_FechaFinal,String p_Lote) throws AforeException {
		try {
			return repositorio.getConsultaOP84(p_FechaInicial, p_FechaFinal, p_Lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public ProcesResult generarReporteOP84(String p_Path,String p_Nombre_Archivo,String p_Lote,Date p_Fecha_Inicial,Date p_Fecha_Final) throws AforeException {
		try {
			return repositorio.generarReporteOP84(p_Path, p_Nombre_Archivo, p_Lote, p_Fecha_Inicial, p_Fecha_Final);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
