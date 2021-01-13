package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.CargaRightIn;
import mx.axxib.aforedigitalgt.eml.CargaRightOut;

@Repository
public class CargaRightRepo extends RepoBase {


	private final EntityManager entityManager;

	@Autowired
	public CargaRightRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public CargaRightOut getCrucePrevio(CargaRightIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CARGA_RIGHT_PACKAGE)
					.concat(".").concat(Constantes.CARGA_RIGHT_OBTIENE_CRUCE_PREVIO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LINEA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			
			query.setParameter("P_NOMBRE_ARCHIVO", parametros.getNombreArchivo());
			query.setParameter("P_FECHA", parametros.getFecha());

			CargaRightOut res = new CargaRightOut();
			res.setLinea( (String) query.getOutputParameterValue("P_LINEA") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public CargaRightOut getCarga(CargaRightIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CARGA_RIGHT_PACKAGE)
					.concat(".").concat(Constantes.CARGA_RIGHT_OBTIENE_CARGA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LINEA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			
			query.setParameter("P_NOMBRE_ARCHIVO", parametros.getNombreArchivo());
			query.setParameter("P_FECHA", parametros.getFecha());

			CargaRightOut res = new CargaRightOut();
			res.setLinea( (String) query.getOutputParameterValue("P_LINEA") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
}
