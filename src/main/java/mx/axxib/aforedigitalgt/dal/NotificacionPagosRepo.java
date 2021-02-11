package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ExportarIn;
import mx.axxib.aforedigitalgt.eml.ExportarOut;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;

@Repository
@Transactional
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
//		PROCEDURE PRC_NOTIFICA_INFO(P_FECHA     IN OUT   DATE,
//                P_AVISO     OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_ENVIA_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_AVISO", String.class, ParameterMode.OUT);
			
			query.setParameter("P_FECHA", fecha);
			
			return (String)query.getOutputParameterValue("P_AVISO");  
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ExportarOut exportar(ExportarIn parametros) throws AforeException {
//		 PROCEDURE PRC_EXPORTA_INFO (P_FEC_DIA IN DATE ,
//                 P_ARCH    IN VARCHAR2 ,
//                 P_ERROR_DATA OUT VARCHAR2,
//                 P_MENSAJE OUT VARCHAR2,
//                 P_ERROR_PRINCIPAL  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_EXPORTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_FEC_DIA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ARCH", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ERROR_DATA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ERROR_PRINCIPAL", String.class, ParameterMode.OUT);
			
			query.setParameter("P_FEC_DIA", parametros.getFecha());
			query.setParameter("P_ARCH", parametros.getArchivo());
			
			ExportarOut res = new ExportarOut();
			res.setMensaje((String)query.getOutputParameterValue("P_MENSAJE"));
			res.setErrorFecha((String)query.getOutputParameterValue("P_ERROR_DATA"));
			res.setError((String)query.getOutputParameterValue("P_ERROR_PRINCIPAL"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
