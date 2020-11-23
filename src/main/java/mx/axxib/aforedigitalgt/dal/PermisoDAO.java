package mx.axxib.aforedigitalgt.dal;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.eml.PermisoResult;

@Repository
public class PermisoDAO {

	private final EntityManager entityManager;

	@Autowired
	public PermisoDAO(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public PermisoResult getPermisosUsuario(String usuario) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PKG_ASISTE.SP_OBTIENE_PERMISOS_USUARIO",
				"Permiso");

		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_DATOS", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

		query.setParameter("P_USUARIO", usuario);

		PermisoResult res = new PermisoResult();
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setDatos(query.getResultList());	
		return res;
	}
}
