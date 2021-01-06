package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.CargasRetirosParcialesOut;

@Repository
public class CargasRetirosParcialesDAO extends RepoBase{
	
private final EntityManager entityManager;
	
	@Autowired
	public CargasRetirosParcialesDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
		
	@SuppressWarnings("unchecked")
	public CargasRetirosParcialesOut cargaSolicitudArchivo(String ruta) throws AforeException  {
		try {
		String storedFullName =  Constantes.CARGA_RETIRO_PARCIAL_PACKAGE.concat(".").concat(Constantes.CARGA_RETIRO_PARCIAL_CARGA_ARCHIVO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_RUTA_ARCHICO", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_AVANCE", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ERROR", String.class, ParameterMode.OUT);
		
		query.setParameter("P_RUTA_ARCHICO",ruta);
		
		CargasRetirosParcialesOut res= new CargasRetirosParcialesOut ();
		res.setAvance((String) query.getOutputParameterValue("P_AVANCE"));
		res.setError((String) query.getOutputParameterValue("P_ERROR"));
		
		
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String cargaSolicitudInicio() throws AforeException {
		try {
		String storedFullName =  Constantes.CARGA_RETIRO_PARCIAL_PACKAGE.concat(".").concat(Constantes.CARGA_RETIRO_PARCIAL_INICIO_PROCESO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_LINEA", String.class, ParameterMode.OUT);
					
		String res = (String) query.getOutputParameterValue("P_LINEA");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
