package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;

@Repository
public class CerInaProRepo extends RepoBase{

	private final EntityManager entityManager;

	@Autowired
	public CerInaProRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public String ejecutarProceso(Integer pOpciones,Date pFechaInicial)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PROCESO_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pOpciones", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pFechaInicial", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pAvance", String.class, ParameterMode.OUT);

			query.setParameter("pOpciones", pOpciones);
			query.setParameter("pFechaInicial", pFechaInicial);

			String resp = (String) query.getOutputParameterValue("pAvance");
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
