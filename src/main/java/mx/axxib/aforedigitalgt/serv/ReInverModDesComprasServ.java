package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReInverModDesComprasRepo;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.TotalesOut;


@Service
public class ReInverModDesComprasServ extends ServiceBase {

	@Autowired
	private ReInverModDesComprasRepo repo;
	
	
	public EjecucionResult comprar(Integer p_Lote,Date p_FechaLote) throws AforeException {
		try {
			return repo.comprar(p_Lote, p_FechaLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public EjecucionResult generarReporte(String pLote,String pTot_Monto,String pFecLote) throws AforeException {
		try {
			return repo.generarReporte(pLote, pTot_Monto, pFecLote);
		}catch(Exception e) {
			throw GenericException(e);
		}
		
	}
	
	public List<TotalesOut> getTotalCompra(String pLote) throws AforeException {
		try {
			return repo.getTotalCompra(pLote);
		}catch(Exception e) {
			throw GenericException(e);
		}
		
	}
}
