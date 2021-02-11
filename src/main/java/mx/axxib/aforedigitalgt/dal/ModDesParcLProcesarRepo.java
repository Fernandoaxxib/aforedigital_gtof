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
public class ModDesParcLProcesarRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public List<LoteOut> getLotes() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_LAYOUT_BTN_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			List<LoteOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ProcesResult generarLayout(Integer pn_Opciones) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_LAYOUT_BTN_GENERAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("in_Opciones", Integer.class, ParameterMode.IN);

			query.registerStoredProcedureParameter("od_Fecha", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Lote", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Avance", String.class, ParameterMode.OUT);

			query.setParameter("in_Opciones", pn_Opciones);

			ProcesResult result = new ProcesResult();

			result.setPdFecha((Date) query.getOutputParameterValue("od_Fecha"));
			result.setPcLote((String) query.getOutputParameterValue("oc_Lote"));
			result.setPcAvance((String) query.getOutputParameterValue("oc_Avance"));

			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
