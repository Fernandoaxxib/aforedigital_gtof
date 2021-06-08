package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DetCompraOut;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.LoteCOut;
import mx.axxib.aforedigitalgt.eml.TotalesOut;

//***********************************************//
//** Funcionalidad: Repositorio - Reinversiones a básicas parcialidades - Compras
//** Desarrollador: JJSC
//** Fecha de creación: 01/Feb/2021
//** Última modificación:
//***********************************************//
@Repository
@Transactional
public class ReInverModDesComprasRepo extends RepoBase{

	private final EntityManager entityManager;

	@Autowired
	public ReInverModDesComprasRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EjecucionResult comprar(Integer p_Lote,Date p_FechaLote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE)
					.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_BTN_COMPRA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);	
			
			query.registerStoredProcedureParameter("p_Lote", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_FechaLote", Date.class, ParameterMode.IN); 			
			query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
						
			query.setParameter("p_Lote", p_Lote);
			query.setParameter("p_FechaLote", p_FechaLote);
						
			EjecucionResult result = new EjecucionResult();
		   
			result.setOcMensaje((String)query.getOutputParameterValue("p_Message"));			
            result.setOn_Estatus((Integer)query.getOutputParameterValue("on_Estatus"));                       
			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public EjecucionResult generarReporte(String pLote,String pTot_Monto,String pFecLote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE)
					.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_BTN_GENERA_REP_COMPRA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);	
			
			query.registerStoredProcedureParameter("pLote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pTot_Monto", String.class, ParameterMode.IN); 
			query.registerStoredProcedureParameter("pFecLote", String.class, ParameterMode.IN); 
			query.registerStoredProcedureParameter("pMensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
						
			query.setParameter("pLote", pLote);
			query.setParameter("pTot_Monto", pTot_Monto);
			query.setParameter("pFecLote", pFecLote);
						
			EjecucionResult result = new EjecucionResult();
		   
			result.setOcMensaje((String)query.getOutputParameterValue("pMensaje"));			
            result.setOn_Estatus((Integer)query.getOutputParameterValue("on_Estatus"));                       
			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TotalesOut> getTotalCompra(String pLote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE)
					.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_TOTAL_COMPRA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "TotalesOut");

			query.registerStoredProcedureParameter("pLote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			
			List<TotalesOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<LoteCOut> getLotes() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE)
					.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteCOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			
			List<LoteCOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DetCompraOut> getDetalleCompra(String p_Lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE)
					.concat(".").concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_DETALLE_COMPRA);
			
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DetCompraOut");
			query.registerStoredProcedureParameter("p_Lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			
			query.setParameter("p_Lote", p_Lote);
			
			List<DetCompraOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
