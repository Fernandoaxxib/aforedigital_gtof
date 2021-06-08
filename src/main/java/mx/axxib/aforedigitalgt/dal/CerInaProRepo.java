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
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

//***********************************************//
//** Funcionalidad: Repositorio - Certificación de inactividad - Proceso
//** Desarrollador: JJSC
//** Fecha de creación: 18/Ene/2021
//** Última modificación:
//***********************************************//
@Repository
@Transactional
public class CerInaProRepo extends RepoBase{

	private final EntityManager entityManager;

	@Autowired
	public CerInaProRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EjecucionResult ejecutarProceso(Integer pOpciones,Date pFechaInicial)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PROCESO_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pOpciones", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pFechaInicial", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pAvance", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			
			
			query.setParameter("pOpciones", pOpciones);
			query.setParameter("pFechaInicial", pFechaInicial);

			EjecucionResult resp= new EjecucionResult();			
			resp.setOcAvance((String)query.getOutputParameterValue("pAvance"));
			resp.setOcMensaje((String)query.getOutputParameterValue("oc_Mensaje"));
			resp.setOn_Estatus((Integer)query.getOutputParameterValue("on_Estatus"));
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
