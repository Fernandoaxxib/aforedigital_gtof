package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
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

	public ProcesResult generarLayout(Integer in_Opciones,Date p_Fecha,String p_Lote,String p_Ruta,String p_Archivo) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_LAYOUT_BTN_GENERAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("in_Opciones", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Fecha", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Lote", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("oc_Avance", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			
			query.setParameter("in_Opciones", in_Opciones);
			query.setParameter("p_Fecha", p_Fecha);
			query.setParameter("p_Lote", p_Lote);
			query.setParameter("p_Ruta", p_Ruta);
			query.setParameter("p_Archivo", p_Archivo);
			

			ProcesResult result = new ProcesResult();

			result.setP_Ruta((String) query.getOutputParameterValue("p_Ruta"));
			result.setOc_Avance((String) query.getOutputParameterValue("p_Archivo"));
			result.setOc_Avance((String) query.getOutputParameterValue("oc_Avance"));
            result.setOn_Estatus((Integer)query.getOutputParameterValue("on_Estatus"));
			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
