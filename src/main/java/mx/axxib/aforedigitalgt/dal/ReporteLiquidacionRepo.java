package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.LiqEjecutaReporteIn;
import mx.axxib.aforedigitalgt.eml.LiqObtieneParametrosOut;
import mx.axxib.aforedigitalgt.eml.LiqObtieneSieforeOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de reporte de liquidación
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Ene/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class ReporteLiquidacionRepo extends RepoBase {

	public LiqObtieneParametrosOut getObtieneParametros() throws AforeException {
//	    PROCEDURE PRC_OBTIENE_PARAMETROS (  P_ID_LOTE OUT VARCHAR2,
//                P_TIP_TRANSAC_TOTAL OUT VARCHAR2,
//                P_TIP_TRANSAC_PARCIAL OUT VARCHAR2,
//                P_FECHA_SISTEMA OUT DATE,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_LIQUIDACION_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_LIQUIDACION_OBTIENE_PARAMETROS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_ID_LOTE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIP_TRANSAC_TOTAL", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIP_TRANSAC_PARCIAL", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FECHA_SISTEMA", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			LiqObtieneParametrosOut res = new LiqObtieneParametrosOut();
			res.setIdLote( (String) query.getOutputParameterValue("P_ID_LOTE") );
			res.setFecha( (Date) query.getOutputParameterValue("P_FECHA_SISTEMA") );
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public LiqObtieneSieforeOut getObtieneSiefore() throws AforeException {
//	    PROCEDURE PRC_OBTIENE_SIEFORE(P_CUR_SIEFORE OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_LIQUIDACION_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_LIQUIDACION_OBTIENE_SIEFORE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiqObtieneSiefore");

			query.registerStoredProcedureParameter("P_CUR_SIEFORE", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			LiqObtieneSieforeOut res = new LiqObtieneSieforeOut();
			Object cursor = query.getOutputParameterValue("P_CUR_SIEFORE");
			if (cursor != null) {
				res.setSiefore(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ejecutaReporte(LiqEjecutaReporteIn parametros) throws AforeException {
//		   PROCEDURE PRC_EJECUTA_REPORTE(P_TIPO_REPORTE IN VARCHAR2,
//                   P_SIEFORE IN VARCHAR2,
//                   P_ID_LOTE IN VARCHAR2,
//                   P_TIPO_RETIRO IN VARCHAR2,
//                   P_ESTADO IN VARCHAR2,
//                   P_USUARIO IN VARCHAR2,
//                   P_DESC_SIEFORE IN VARCHAR2,
//                   P_FECHA_SISTEMA IN DATE,
//                   P_MENSAJE OUT VARCHAR2,
//                   P_ESTATUS OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_LIQUIDACION_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_LIQUIDACION_EJECUTA_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_TIPO_REPORTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SIEFORE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIPO_RETIRO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTADO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_DESC_SIEFORE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_SISTEMA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_TIPO_REPORTE", parametros.getTipoReporte());
			query.setParameter("P_SIEFORE", parametros.getSiefore());
			query.setParameter("P_ID_LOTE", parametros.getIdLote());
			query.setParameter("P_TIPO_RETIRO", parametros.getTipoRetiro());
			query.setParameter("P_ESTADO", parametros.getEstado());
			query.setParameter("P_USUARIO", parametros.getUsuario());
			query.setParameter("P_DESC_SIEFORE", parametros.getDescripcion());
			query.setParameter("P_FECHA_SISTEMA", parametros.getFecha());

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
