package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ReporteSolcChequeRetiroIn;
import mx.axxib.aforedigitalgt.eml.SubtransaccionesOut;
import mx.axxib.aforedigitalgt.eml.TransaccionesOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de reporte de solicitudes de retiro
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2021
//** Última Modificación:
//***********************************************//
@Repository
@Transactional
public class ReporteSolicRetiroRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public TransaccionesOut getTransacciones() throws AforeException {
//	    PROCEDURE PRC_SELEC_TIP_TRANSAC_RET(P_CUR_TRANSAC_RET OUT SYS_REFCURSOR,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_SOLICITUD_RETIRO_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_SOLICITUD_RETIRO_TIPO_TRANSACCION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "Transacciones");

			query.registerStoredProcedureParameter("P_CUR_TRANSAC_RET", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			TransaccionesOut res = new TransaccionesOut();
			Object cursor = query.getOutputParameterValue("P_CUR_TRANSAC_RET");
			if (cursor != null) {
				res.setTransacciones(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public SubtransaccionesOut getSubtransacciones(String idTransaccion) throws AforeException {
//	    PROCEDURE PRC_SELEC_SUB_TRANSAC_RET(P_TIP_TRANSAC_RET IN NUMBER,
//                P_CUR_SUB_TRANSAC_RET OUT SYS_REFCURSOR,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE  OUT  VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_SOLICITUD_RETIRO_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_SOLICITUD_RETIRO_SUBTIPO_TRANSACCION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "Subtransacciones");

			query.registerStoredProcedureParameter("P_TIP_TRANSAC_RET", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_SUB_TRANSAC_RET", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_TIP_TRANSAC_RET", idTransaccion);
			
			SubtransaccionesOut res = new SubtransaccionesOut();
			Object cursor = query.getOutputParameterValue("P_CUR_SUB_TRANSAC_RET");
			if (cursor != null) {
				res.setSubtransacciones(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut reporteSolcChequeRetiro(ReporteSolcChequeRetiroIn parametros) throws AforeException {
//	    PROCEDURE PRC_REP_SOL_CHEQ_RETIRO_P (P_TIP_TRANSAC IN NUMBER,  
//                P_SUBTIP_TRANSAC IN VARCHAR2,
//                P_FCH_INI IN DATE,
//                P_FCH_FIN IN DATE,
//                P_NO_SOLIC_INI IN NUMBER,
//                P_NO_SOLIC_FIN IN NUMBER,
//                P_ESTADO IN VARCHAR2,
//                P_ESTATUS OUT NUMBER,
//                P_mensaje OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_SOLICITUD_RETIRO_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_SOLICITUD_RETIRO_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_TIP_TRANSAC", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SUBTIP_TRANSAC", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FCH_INI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FCH_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NO_SOLIC_INI", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NO_SOLIC_FIN", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTADO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_mensaje", String.class, ParameterMode.OUT);

			query.setParameter("P_TIP_TRANSAC", parametros.getIdTransaccion());
			query.setParameter("P_SUBTIP_TRANSAC", parametros.getIdSubtransaccion());
			query.setParameter("P_FCH_INI", parametros.getFechaInicial());
			query.setParameter("P_FCH_FIN", parametros.getFechaFinal());
			query.setParameter("P_NO_SOLIC_INI", parametros.getNoSolicInicial());
			query.setParameter("P_NO_SOLIC_FIN", parametros.getNoSolicFinal());
			query.setParameter("P_ESTADO", parametros.getEstado());

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
