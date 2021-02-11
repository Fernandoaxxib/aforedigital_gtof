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
	
	public String generarLayoutProcesar(Date pFechaEntrada,String pLotes,Integer p_opciones)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_LAYOUTPROC_BTN_GENERAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pFechaEntrada", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pLotes", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_opciones", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_avance", String.class, ParameterMode.OUT);

			query.setParameter("pFechaEntrada", pFechaEntrada);
			query.setParameter("pLotes", pLotes);
			query.setParameter("p_opciones", p_opciones);
			
			String resp = (String) query.getOutputParameterValue("p_avance");
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
