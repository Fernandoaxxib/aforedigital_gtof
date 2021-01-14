package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ExportarIn;
import mx.axxib.aforedigitalgt.eml.ExportarOut;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;

@Repository
public class NotificacionPagosRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public LlenaInfoOut llenaInfo(Date fecha) throws AforeException {
		try {
			
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_LLENA_INFO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LlenaInfo");

			query.registerStoredProcedureParameter("P_FECHA_CONSULTA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TOT_TITULOS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_PESOS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			
			query.setParameter("P_FECHA_CONSULTA", fecha);
			
			LlenaInfoOut res = new LlenaInfoOut();
			Object cursor = query.getOutputParameterValue("CP_DATOS");
			if (cursor != null) {
				res.setDatos(query.getResultList());
				res.setTotTitulos((Integer)query.getOutputParameterValue("P_TOT_TITULOS"));
				res.setTotPesos((Integer)query.getOutputParameterValue("P_TOT_PESOS"));
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String enviaFecha(Date fecha) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_ENVIA_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_TXT_FECHA", Date.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_TXT_FECHA", fecha);
			
			return (String)query.getOutputParameterValue("P_MENSAJE");  
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ExportarOut exportar(ExportarIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_EXPORTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_TXT_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ARCHIVO", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ERROR_FECHA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ERROR", String.class, ParameterMode.OUT);
			
			query.setParameter("P_TXT_FECHA", parametros.getFecha());
			query.setParameter("P_ARCHIVO", parametros.getArchivo());
			
			ExportarOut res = new ExportarOut();
			res.setMensaje((String)query.getOutputParameterValue("P_MENSAJE"));
			res.setErrorFecha((String)query.getOutputParameterValue("P_ERROR_FECHA"));
			res.setError((String)query.getOutputParameterValue("P_ERROR"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
