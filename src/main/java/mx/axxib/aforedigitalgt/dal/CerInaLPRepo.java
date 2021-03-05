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
import mx.axxib.aforedigitalgt.eml.LoteOut;
import mx.axxib.aforedigitalgt.eml.ProcesResult;

@Repository
@Transactional
public class CerInaLPRepo extends RepoBase{

	private final EntityManager entityManager;

	@Autowired
	public CerInaLPRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<LoteOut> getLotes() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_BTN_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			List<LoteOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ProcesResult generarLayout(Date pFechaEntrada,String pLotes,Integer p_opciones,String p_Ruta,String p_Archivo)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_LAYOUTPROC_BTN_GENERAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pFechaEntrada", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pLotes", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_opciones", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_avance", String.class, ParameterMode.OUT);
			
			query.setParameter("pFechaEntrada", pFechaEntrada);
			query.setParameter("pLotes", pLotes);
			query.setParameter("p_opciones", p_opciones);
			query.setParameter("p_Ruta", p_Ruta);
			query.setParameter("p_Archivo", p_Archivo);
			
			ProcesResult resp = new ProcesResult();
			resp.setP_Ruta((String) query.getOutputParameterValue("p_Ruta"));
			resp.setP_Archivo((String) query.getOutputParameterValue("p_Archivo"));
			resp.setP_Message((String) query.getOutputParameterValue("oc_Mensaje"));
		    resp.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));			
			resp.setOc_Avance((String) query.getOutputParameterValue("p_avance"));		
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
