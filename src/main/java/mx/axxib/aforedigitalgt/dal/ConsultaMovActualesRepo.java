package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.LlenaDetalleOut;
import mx.axxib.aforedigitalgt.eml.LlenaMovimientosOut;
import mx.axxib.aforedigitalgt.eml.ObtenerCodCuentaOut;

@Repository
@Transactional
public class ConsultaMovActualesRepo extends RepoBase {

	public ObtenerCodCuentaOut getCodCuenta(String nssCurp, boolean isCurp) throws AforeException {
//	    PROCEDURE PRC_OBT_COD_CUENTA(P_NSS  IN VARCHAR2,
//                P_CURP  IN VARCHAR2,
//                P_NOMBRE OUT VARCHAR2,
//                P_COD_CUENTA OUT VARCHAR2,
//                P_COD_ESTADO IN OUT VARCHAR2,
//                P_COD_CLIENTE OUT VARCHAR2,
//                P_FECHA_CERTIF OUT DATE, 
//                P_TIP_SOLICITUD OUT VARCHAR2,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOVIMIENTOS_ACTUALES_PACKAGE)
					.concat(".").concat(Constantes.MOVIMIENTOS_ACTUALES_OBTIENE_CODCUENTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CURP", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_ESTADO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_CLIENTE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FECHA_CERTIF", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIP_SOLICITUD", String.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			if(isCurp) {
				query.setParameter("P_CURP", nssCurp);
			} else {
				query.setParameter("P_NSS", nssCurp);
			}
			
			ObtenerCodCuentaOut res = new ObtenerCodCuentaOut();
			res.setNombre( (String) query.getOutputParameterValue("P_NOMBRE") );
			res.setCodCuenta( (String) query.getOutputParameterValue("P_COD_CUENTA") );
			res.setCodEmpresa( (String) query.getOutputParameterValue("P_COD_EMPRESA") );
			res.setCodEstado( (String) query.getOutputParameterValue("P_COD_ESTADO") );
			res.setCodCliente( (String) query.getOutputParameterValue("P_COD_CLIENTE") );
			res.setFechaCert( (Date) query.getOutputParameterValue("P_FECHA_CERTIF") );
			res.setTipoSolic( (String) query.getOutputParameterValue("P_TIP_SOLICITUD") );
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public LlenaMovimientosOut getLlenaMovimientos(String codCuenta, String codEmpresa, Date fInicial, Date fFinal) throws AforeException {
//	    PROCEDURE PRC_LLENA_MOVIMIENTOS(P_COD_EMPRESA IN VARCHAR2,
//                P_COD_CUENTA IN VARCHAR2,
//                P_FECHA_INICIAL IN DATE,
//                P_FECHA_FIN IN DATE,
//                P_CUR_MOVI_GENERAL OUT SYS_REFCURSOR,
//                P_CUR_MOVI_HISTORICO OUT SYS_REFCURSOR,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOVIMIENTOS_ACTUALES_PACKAGE)
					.concat(".").concat(Constantes.MOVIMIENTOS_ACTUALES_LLENA_MOVIMIENTOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LlenaMovimiento");

			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_INICIAL", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_FIN", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_CUR_MOVI_GENERAL", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_CUR_MOVI_HISTORICO", void.class, ParameterMode.REF_CURSOR);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_COD_EMPRESA", codEmpresa);
			query.setParameter("P_COD_CUENTA", codCuenta);
			query.setParameter("P_FECHA_INICIAL", fInicial);
			query.setParameter("P_FECHA_FIN", fFinal);

			LlenaMovimientosOut res = new LlenaMovimientosOut();
			Object cursor = query.getOutputParameterValue("P_CUR_MOVI_GENERAL");
			if (cursor != null) {
				res.setMovimientos(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public LlenaDetalleOut getLlenaDetalle(String codEmpresa, Date fecMov, Integer noMov) throws AforeException {
//	    PROCEDURE PRC_LLENA_DETALLE (P_COD_EMPRESA IN VARCHAR2,
//                P_FEC_MOVIMTO IN DATE,
//                P_NUM_MIVIMTO IN NUMBER,
//                P_CUR_DETA_HIST OUT SYS_REFCURSOR,
//                P_CUR_DETA     OUT SYS_REFCURSOR,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOVIMIENTOS_ACTUALES_PACKAGE)
					.concat(".").concat(Constantes.MOVIMIENTOS_ACTUALES_LLENA_DETALLE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LlenaDetalle");

			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_MOVIMTO", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUM_MIVIMTO", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_CUR_DETA", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_CUR_DETA_HIST", void.class, ParameterMode.REF_CURSOR);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_COD_EMPRESA", codEmpresa);
			query.setParameter("P_FEC_MOVIMTO", fecMov);
			query.setParameter("P_NUM_MIVIMTO", noMov);

			LlenaDetalleOut res = new LlenaDetalleOut();
			Object cursor = query.getOutputParameterValue("P_CUR_DETA");
			if (cursor != null) {
				res.setDetalle(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
