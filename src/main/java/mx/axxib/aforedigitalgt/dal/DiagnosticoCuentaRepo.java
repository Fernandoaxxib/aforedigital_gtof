package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.DesbloqueaCuentasIn;
import mx.axxib.aforedigitalgt.eml.GeneraArchivoIn;
import mx.axxib.aforedigitalgt.eml.ObtieneCodCuentaOut;
import mx.axxib.aforedigitalgt.eml.ObtieneTipoProcesoOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de diagnóstico de la cuenta
//** Interventor Principal: JSAS
//** Fecha Creación: 12/Feb/2021
//** Última Modificación:
//***********************************************//
@Repository
@Transactional
public class DiagnosticoCuentaRepo extends RepoBase {

	public ObtieneCodCuentaOut getObtieneCodCuenta(String parametro, boolean isCurp) throws AforeException {
//	    PROCEDURE PRC_OBTIENE_COD_CUENTA(P_NSS  IN VARCHAR2,
//                P_CURP IN VARCHAR2,
//                P_NOMBRE OUT VARCHAR2,
//                P_COD_CUENTA OUT VARCHAR2,
//                P_ESTATUS  OUT  NUMBER,
//                P_MENSAJE OUT VARCHAR2);
                  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_PACKAGE)
					.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_OBTIENE_CODCUENTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CURP", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			if(isCurp) {
				query.setParameter("P_CURP", parametro);
				query.setParameter("P_NSS", null);
			} else {
				query.setParameter("P_NSS", parametro);
				query.setParameter("P_CURP", null);
			}
			
			ObtieneCodCuentaOut res = new ObtieneCodCuentaOut();
			res.setNombre((String)query.getOutputParameterValue("P_NOMBRE"));
			res.setCodCuenta((String)query.getOutputParameterValue("P_COD_CUENTA"));
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ObtieneTipoProcesoOut getObtieneTipoProceso() throws AforeException {
//	    PROCEDURE PRC_OBTIENE_TIPO_PROCESO(P_CUR_TIP_PROCESO OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_PACKAGE)
					.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_OBTIENE_TIPOPROCESO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ObtieneTipoProceso");

			query.registerStoredProcedureParameter("P_CUR_TIP_PROCESO", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			ObtieneTipoProcesoOut res = new ObtieneTipoProcesoOut();
			Object cursor = query.getOutputParameterValue("P_CUR_TIP_PROCESO");
			if (cursor != null) {
				res.setTipos( query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	public BaseOut generaArchivo(GeneraArchivoIn parametros) throws AforeException {
//	    PROCEDURE PRC_GENERA_ARCHIVO (P_CLAVE_PROCESO IN NUMBER,
//                P_FECHA_INICIO IN DATE,
//                P_FECHA_FIN   IN DATE,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);                 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_PACKAGE)
					.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_GENERA_ARCHIVO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_CLAVE_PROCESO", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_CLAVE_PROCESO", parametros.getClave());
			query.setParameter("P_FECHA_INICIO", parametros.getFechaInicio());
			query.setParameter("P_FECHA_FIN", parametros.getFechaFin());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut desbloqueaCuentas(DesbloqueaCuentasIn parametros) throws AforeException {
//	    PROCEDURE PRC_DESBLOQUEA_CTAS (P_COD_CUENTA  IN VARCHAR2,
//                P_CLAVE_PROCESO IN NUMBER,
//                P_USUARIO  IN VARCHAR2,
//                P_FECHA_INICIO IN DATE,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);         
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_PACKAGE)
					.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_DESBLOQUEA_CUENTAS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CLAVE_PROCESO", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_COD_CUENTA", parametros.getCodCuenta());
			query.setParameter("P_CLAVE_PROCESO", parametros.getClave());
			query.setParameter("P_USUARIO", parametros.getUsuario());
			query.setParameter("P_FECHA_INICIO", parametros.getFechaInicio());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut bloqueaCuentas(DesbloqueaCuentasIn parametros) throws AforeException {
//	    PROCEDURE PRC_BLOQUEA_CUENTAS(P_COD_CUENTA  IN VARCHAR2,
//                P_CLAVE_PROCESO IN NUMBER,
//                P_USUARIO  IN VARCHAR2,
//                P_FECHA_INICIO IN DATE,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);          
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_PACKAGE)
					.concat(".").concat(Constantes.DIAGNOSTICO_CUENTA_BLOQUEA_CUENTAS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CLAVE_PROCESO", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_COD_CUENTA", parametros.getCodCuenta());
			query.setParameter("P_CLAVE_PROCESO", parametros.getClave());
			query.setParameter("P_USUARIO", parametros.getUsuario());
			query.setParameter("P_FECHA_INICIO", parametros.getFechaInicio());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
