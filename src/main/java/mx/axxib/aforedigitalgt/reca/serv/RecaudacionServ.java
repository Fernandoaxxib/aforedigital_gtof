package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.RecaudacionOut;
import mx.axxib.aforedigitalgt.reca.dal.RecaudacionRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Recaudación
//** Interventor Principal: JJSC
//** Fecha Creación: 07/03/2022
//** Última Modificación:
//***********************************************//

public class RecaudacionServ extends ServiceBase{

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
	public RecaudacionOut getListaLote() throws AforeException {
		try {
			return repo.getListaLote();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public RecaudacionOut comprar(String ic_id_operacion, Integer in_TOT_COMPRAR, Integer on_partic_comp,
			String ic_id_lote, Date ic_FECHA_43BIS) throws AforeException {
		try {			
			return repo.comprar(ic_id_operacion, in_TOT_COMPRAR, on_partic_comp, ic_id_lote, ic_FECHA_43BIS);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
