package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReInverModDesComprasRepo;
import mx.axxib.aforedigitalgt.eml.DetCompraOut;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.LoteCOut;
import mx.axxib.aforedigitalgt.eml.TotalesOut;

//***********************************************//
//** Funcionalidad: Servicio - Reinversiones a básicas parcialidades - Compras
//** Desarrollador: JJSC
//** Fecha de creación: 01/Feb/2021
//** Última modificación:
//***********************************************//
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
	public List<LoteCOut> getLotes() throws AforeException {
		try {
			return repo.getLotes();
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
	public List<DetCompraOut> getDetalleCompra(String p_Lote) throws AforeException {
		try {
			return repo.getDetalleCompra(p_Lote);
		}catch(Exception e) {
			throw GenericException(e);
		}
	}
}
