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

@Repository
@Transactional
public class CerInaAppRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public CerInaAppRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public String ejecutar(String p_Ruta,String p_Archivo)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_APPMOVIL_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);

			query.setParameter("p_Ruta", p_Ruta);
			query.setParameter("p_Archivo", p_Archivo);

			String resp = (String) query.getOutputParameterValue("p_message");
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String generarArchivo(Date p_fecha)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_APPMOVIL_BTN_GENARCH);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_fecha", Date.class, ParameterMode.IN);		
			query.registerStoredProcedureParameter("p_mesage", String.class, ParameterMode.OUT);

			query.setParameter("p_fecha", p_fecha);

			String resp = (String) query.getOutputParameterValue("p_mesage");
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
