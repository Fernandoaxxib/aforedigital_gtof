package mx.axxib.aforedigitalgt.dal;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ExportarIn;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Repository
@Transactional
public class NotificacionPagosRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public LlenaInfoOut llenaInfo(Date fecha) throws AforeException {
//		 PROCEDURE PRC_LLENA_INFO (P_FECHA_CONSULTA         IN DATE,
//                 P_TOT_TITULOS            OUT NUMBER,
//                 P_TOT_PESOS              OUT NUMBER,
//                 CP_DATOS                 OUT SYS_REFCURSOR,
//                 P_MENSAJE                OUT VARCHAR2,
//                 P_ESTATUS                OUT NUMBER);
		try {
			
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_LLENA_INFO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LlenaInfo");

			query.registerStoredProcedureParameter("P_FECHA_CONSULTA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TOT_TITULOS", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_PESOS", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			query.setParameter("P_FECHA_CONSULTA", fecha);
			
			LlenaInfoOut res = new LlenaInfoOut();
			Object cursor = query.getOutputParameterValue("CP_DATOS");
			if (cursor != null) {
				res.setDatos(query.getResultList());
				res.setTotTitulos((BigDecimal)query.getOutputParameterValue("P_TOT_TITULOS"));
				res.setTotPesos((BigDecimal)query.getOutputParameterValue("P_TOT_PESOS"));
				res.setMensaje((String)query.getOutputParameterValue("P_MENSAJE"));
				res.setEstatus((Integer)query.getOutputParameterValue("P_ESTATUS"));
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public BaseOut enviaFecha(Date fecha) throws AforeException {
//		PROCEDURE PRC_ENVIA_FECHA(P_TXT_FECHA IN OUT VARCHAR2,
//                P_MENSAJE   OUT VARCHAR2,
//                P_ESTATUS   OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_ENVIA_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_TXT_FECHA", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			query.setParameter("P_TXT_FECHA", DateUtil.getDateToString(fecha, "dd/MM/yyyy"));
			
			BaseOut res = new BaseOut();  
			res.setMensaje((String)query.getOutputParameterValue("P_MENSAJE"));
			res.setEstatus((Integer)query.getOutputParameterValue("P_ESTATUS"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut exportar(ExportarIn parametros) throws AforeException {
//		PROCEDURE PRC_EXPORTAR (P_TXT_FECHA IN DATE,
//                P_ARCHIVO   IN VARCHAR2,
//                P_MENSAJE   OUT VARCHAR2,
//                P_ESTATUS    OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.NOTIFICACION_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.NOTIFICACION_PAGOS_EXPORTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_TXT_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			query.setParameter("P_TXT_FECHA", parametros.getFecha());
			query.setParameter("P_ARCHIVO", parametros.getArchivo());
			
			BaseOut res = new BaseOut();  
			res.setMensaje((String)query.getOutputParameterValue("P_MENSAJE"));
			res.setEstatus((Integer)query.getOutputParameterValue("P_ESTATUS"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
