package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReInverModDesVentasRepo;
import mx.axxib.aforedigitalgt.eml.DetCompraOut;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.LoteCOut;
import mx.axxib.aforedigitalgt.eml.TotalesOut;

//***********************************************//
//** Funcionalidad: Servicio - Reinversiones a básicas parcialidades - Ventas
//** Desarrollador: JJSC
//** Fecha de creación: 01/Feb/2021
//** Última modificación:
//***********************************************//
@Service
public class ReInverModDesVentasServ extends ServiceBase {
	
	@Autowired 
    private ReInverModDesVentasRepo repo;
	
	public EjecucionResult vender(Integer p_Lote,Date p_FechaLote) throws AforeException {
		try {
			return repo.vender(p_Lote, p_FechaLote);
		} catch (Exception e) {
			throw GenericException(e);
		}		
	}
	
	public EjecucionResult generarReporte(String pLoteV,String pTot_MontoV,String pFecLoteV) throws AforeException {
		try {
			return repo.generarReporte(pLoteV, pTot_MontoV, pFecLoteV);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public List<TotalesOut> getTotalVenta(String pLoteV) throws AforeException {
		try {
			return repo.getTotalVenta(pLoteV);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public List<LoteCOut> getLotes(String pLote) throws AforeException {
		try {
			return repo.getLotes(pLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public List<DetCompraOut> getDetalleVenta(String p_Lote) throws AforeException {
		try {
			return repo.getDetalleVenta(p_Lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
