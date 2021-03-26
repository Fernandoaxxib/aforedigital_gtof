package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.LlenaDetalleHOut;
import mx.axxib.aforedigitalgt.eml.LlenaMovimientosHOut;
import mx.axxib.aforedigitalgt.eml.ObtenerCodCuentaHOut;

@Repository
@Transactional
public class ConsultaMovHistoricosRepo extends RepoBase {

	public ObtenerCodCuentaHOut getCodCuenta(String nssCurp, boolean isCurp) throws AforeException {
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
		
//		PROCEDURE PRC_OBTIENE_DATOS_X_NSS(P_NSS  IN VARCHAR2,
//                P_CURP  IN VARCHAR2,
//                P_NOMBRE OUT VARCHAR2,
//                P_COD_CUENTA OUT VARCHAR2,
//                P_COD_ESTADO IN OUT VARCHAR2,
//                P_COD_CLIENTE OUT VARCHAR2,
//                P_FECHA_CERTIF OUT DATE, 
//                P_TIP_SOLICITUD OUT VARCHAR2,
//                P_COD_EMPRESA OUT VARCHAR2,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOVIMIENTOS_HISTORICOS_PACKAGE)
					.concat(".").concat(Constantes.MOVIMIENTOS_HISTORICOS_OBTIENE_CODCUENTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CURP", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_ESTADO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_CLIENTE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FECHA_CERTIF", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIP_SOLICITUD", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			if(isCurp) {
				query.setParameter("P_CURP", nssCurp);
			} else {
				query.setParameter("P_NSS", nssCurp);
			}
			
			ObtenerCodCuentaHOut res = new ObtenerCodCuentaHOut();
			res.setNombre( (String) query.getOutputParameterValue("P_NOMBRE") );
			res.setCodCuenta( (String) query.getOutputParameterValue("P_COD_CUENTA") );
			res.setCodEstado( (String) query.getOutputParameterValue("P_COD_ESTADO") );
			res.setCodCliente( (String) query.getOutputParameterValue("P_COD_CLIENTE") );
			res.setFechaCert( (Date) query.getOutputParameterValue("P_FECHA_CERTIF") );
			res.setTipoSolic( (String) query.getOutputParameterValue("P_TIP_SOLICITUD") );
			res.setCodEmpresa( (String) query.getOutputParameterValue("P_COD_EMPRESA") );
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public LlenaMovimientosHOut getLlenaMovimientos(String codCuenta, Date fInicial, Date fFinal) throws AforeException {
//		PROCEDURE PRC_MOVIMIENTO_HISTORICO(P_COD_CUENTA IN VARCHAR2,
//                P_FECHA_INCIO IN DATE,
//                P_FECHA_FIN   IN DATE,
//                CP_HISTORICOS OUT SYS_REFCURSOR,
//                P_MENSAJE     OUT VARCHAR2,
//                P_ESTATUS     OUT  NUMBER);   
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOVIMIENTOS_HISTORICOS_PACKAGE)
					.concat(".").concat(Constantes.MOVIMIENTOS_HISTORICOS_LLENA_MOVIMIENTOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LlenaMovimientoH");

			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_INCIO", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_FIN", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("CP_HISTORICOS", void.class, ParameterMode.REF_CURSOR);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_COD_CUENTA", codCuenta);
			query.setParameter("P_FECHA_INCIO", fInicial);
			query.setParameter("P_FECHA_FIN", fFinal);

			LlenaMovimientosHOut res = new LlenaMovimientosHOut();
			Object cursor = query.getOutputParameterValue("CP_HISTORICOS");
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
	public LlenaDetalleHOut getLlenaDetalle(String codCuenta, Date fecMov, Integer noMov) throws AforeException {
//		PROCEDURE PRC_DES_PRODUCT(P_COD_CUENTA IN VARCHAR2,
//                P_NUM_MOVIMIENTO IN NUMBER,
//                P_FEC_MOVIMIENTO  IN DATE,
//                CP_HIST_DET     OUT SYS_REFCURSOR,
//                P_ESTATUS         OUT NUMBER,
//                P_MENSAJE       OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOVIMIENTOS_HISTORICOS_PACKAGE)
					.concat(".").concat(Constantes.MOVIMIENTOS_HISTORICOS_LLENA_DETALLE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LlenaDetalleH");

			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_MOVIMIENTO", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUM_MOVIMIENTO", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("CP_HIST_DET", void.class, ParameterMode.REF_CURSOR);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_COD_CUENTA", codCuenta);
			query.setParameter("P_FEC_MOVIMTO", fecMov);
			query.setParameter("P_NUM_MIVIMTO", noMov);

			LlenaDetalleHOut res = new LlenaDetalleHOut();
			Object cursor = query.getOutputParameterValue("CP_HIST_DET");
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
