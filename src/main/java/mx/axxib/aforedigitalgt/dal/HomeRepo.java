package mx.axxib.aforedigitalgt.dal;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DatosAccesoOut;
import mx.axxib.aforedigitalgt.eml.InfoUsuarioOut;

@Repository
@Transactional
public class HomeRepo extends RepoBase {

	private final EntityManager entityManager;
	
	@Autowired
	public HomeRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public DatosAccesoOut validarAcceso(String P_CUENTA) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DATOS_USUARIO_PACKAGE)
					.concat(".").concat(Constantes.DATOS_USUARIO_VALIDA_ACCESO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("P_ID_USUARIO", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_GPO_SEG_SITIO", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_CUENTA", P_CUENTA);
			
			DatosAccesoOut res = new DatosAccesoOut();
			res.setP_CUENTA(P_CUENTA);
			res.setP_ID_USUARIO((Integer) query.getOutputParameterValue("P_ID_USUARIO"));
			res.setP_GPO_SEG_SITIO((Integer) query.getOutputParameterValue("P_GPO_SEG_SITIO"));
			res.setP_MENSAJE( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public InfoUsuarioOut getObtenerDatosUsuario(Integer P_ID_USUARIO) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DATOS_USUARIO_PACKAGE)
					.concat(".").concat(Constantes.DATOS_USUARIO_OBTIENE_DATOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"DatosUsuarioOut");

			query.registerStoredProcedureParameter("P_ID_USUARIO", Integer.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("CP_LIST_USR", void.class, ParameterMode.REF_CURSOR);			
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_ID_USUARIO", P_ID_USUARIO);
			
			InfoUsuarioOut res = new InfoUsuarioOut();			
			res.setDatosUsuario(query.getResultList());
		    res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
