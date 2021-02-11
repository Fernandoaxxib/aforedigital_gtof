package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ResultOut;

@Repository
@Transactional
public class CerInaRepRepo extends RepoBase{

	private final EntityManager entityManager;

	@Autowired
	public CerInaRepRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ResultOut procesarReporte(Date p_Fecha_Inicial,Date p_Fecha_Final,String p_Opcion_Reporte) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_REPORTES_BTN_PROCESAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Fecha_Inicial", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Fecha_Final", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Opcion_Reporte", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_Avance", String.class, ParameterMode.OUT);

			query.setParameter("p_Fecha_Inicial", p_Fecha_Inicial);
			query.setParameter("p_Fecha_Final", p_Fecha_Final);
			query.setParameter("p_Opcion_Reporte", p_Opcion_Reporte);
			
			ResultOut r= new ResultOut();
            r.setP_Message((String) query.getOutputParameterValue("p_Message"));
            r.setP_Avance((String) query.getOutputParameterValue("p_Avance"));
			
			return r;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
