package mx.axxib.aforedigitalgt.dal;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosIn;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosOut;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesIn;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesOut;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoCorteIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorCTIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorIn;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de venta de títulos
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Dic/2020
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class VentaTitulosRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public VentaTitulosRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public ObtieneMontoOut getObtieneMontoTotal(ObtieneMontoTotalIn parametros) throws AforeException {
//	    PROCEDURE PRC_RET_OBTIENE_MONTO_TOTAL (P_FECHA IN DATE,
//                P_FECHAI IN DATE,
//                P_CUR_MONTO_TOTAL OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);    
		try {
			
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_TOTAL);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMonto");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHAI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTO_TOTAL", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", parametros.getFechaFinal());
			query.setParameter("P_FECHAI", parametros.getFechaInicial());

			ObtieneMontoOut res = new ObtieneMontoOut();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTO_TOTAL");
			if (cursor != null) {
				res.setMonto(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtenerTipoRetiroOut getObtenerTipoRetiro(ObtenerTipoRetiroIn parametros) throws AforeException {
//	    PROCEDURE PRC_RET_OBTENER_TIPO_RETIRO(P_FECHA IN DATE,
//                P_TIPO_RETIRO OUT SYS_REFCURSOR,
//                P_ESTATUS  OUT NUMBER,
//                P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTENER_TIPO_RETIRO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtenerTipoRetiro");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIPO_RETIRO", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", parametros.getFecha());

			ObtenerTipoRetiroOut res = new ObtenerTipoRetiroOut();
			Object cursor = query.getOutputParameterValue("P_TIPO_RETIRO");
			if (cursor != null) {
				res.setRetiro( query.getResultList() );
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtieneMontoOut getObtieneMontoRetiro(ObtieneMontoRetiroIn parametros) throws AforeException {
//	    PROCEDURE PRC_RET_OBTIENE_MONTO_RETIRO( P_TIP_RETIRO IN VARCHAR2,
//                P_FECHA IN DATE,
//                P_FECHAI IN DATE,
//                P_TIP_TRANSACCION IN NUMBER,
//                P_SUBTIP_TRANSAC IN VARCHAR2,
//                P_CUR_MON_RETIRO OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_RETIRO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMonto");

			query.registerStoredProcedureParameter("P_TIP_RETIRO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHAI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIP_TRANSACCION", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SUBTIP_TRANSAC", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MON_RETIRO", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_TIP_RETIRO", parametros.getTipoRetiro());
			query.setParameter("P_FECHA", parametros.getFechaFinal());
			query.setParameter("P_FECHAI", parametros.getFechaInicial());
			query.setParameter("P_TIP_TRANSACCION", parametros.getTipoTransaccion());
			query.setParameter("P_SUBTIP_TRANSAC", parametros.getSubTipoTransaccion());

			ObtieneMontoOut res = new ObtieneMontoOut();
			Object cursor = query.getOutputParameterValue("P_CUR_MON_RETIRO");
			if (cursor != null) {
				res.setMonto(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtieneMontoOut getObtieneMontoCorte(ObtieneMontoCorteIn parametros) throws AforeException {
//        PROCEDURE PRC_RET_OBTIENE_MONTO_CORTE(P_LOTE_CORTE IN VARCHAR2,
//                P_FECHA IN DATE,
//                P_CUR_MONTO_CORTE OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_CORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMonto");

			query.registerStoredProcedureParameter("P_LOTE_CORTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTO_CORTE", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_LOTE_CORTE", parametros.getLoteCorte());
			query.setParameter("P_FECHA", parametros.getFecha());

			ObtieneMontoOut res = new ObtieneMontoOut();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTO_CORTE");
			if (cursor != null) {
				res.setMonto(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtenerLoteTraspasosOut getObtenerLoteTraspasos(ObtenerLoteTraspasosIn parametros) throws AforeException {
//	    PROCEDURE PRC_RET_OBTENER_LOTE_TRASPA(P_FECHA IN DATE,
//                P_LOTE_TRASPA OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTENER_LOTE_TRASPA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtenerLoteTraspasos");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE_TRASPA", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", parametros.getFecha());

			ObtenerLoteTraspasosOut res = new ObtenerLoteTraspasosOut();
			Object cursor = query.getOutputParameterValue("P_LOTE_TRASPA");
			if (cursor != null) {
				res.setTraspaso(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtieneMontoOut getObtieneMontoTraspasos(ObtieneMontoTraspasosIn parametros) throws AforeException {
//	    PROCEDURE PRC_OBTIENE_MONTO_TRASPASOS(P_LOTE_TRASP IN VARCHAR2,
//                P_FECHA IN DATE,
//                P_CUR_MONTRASPASOS OUT SYS_REFCURSOR);    FALTA
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_TRASPASOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMonto");

			query.registerStoredProcedureParameter("P_LOTE_TRASP", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTRASPASOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_LOTE_TRASP", parametros.getLoteTraspaso());
			query.setParameter("P_FECHA", parametros.getFecha());

			ObtieneMontoOut res = new ObtieneMontoOut();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTRASPASOS");
			if (cursor != null) {
				res.setMonto(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtenerRgDevExcesOut getObtenerRgDevExces(ObtenerRgDevExcesIn parametros) throws AforeException {
//	    PROCEDURE PRC_RET_OBTENER_RG_DEV_EXCES(P_FECHA IN DATE,
//                P_DEV_EXCES OUT SYS_REFCURSOR,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTENER_RG_DEV_EXCES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtenerRgDevExces");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_DEV_EXCES", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", parametros.getFecha());

			ObtenerRgDevExcesOut res = new ObtenerRgDevExcesOut();
			Object cursor = query.getOutputParameterValue("P_DEV_EXCES");
			if (cursor != null) {
				res.setRgDevExces(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ObtieneMontoOut getObtieneMontoDev(ObtieneMontoDevIn parametros) throws AforeException {
//	    PROCEDURE PRC_RET_OBTIENE_MONTO_DEV (P_LOTE_REV IN VARCHAR2,
//                P_FECHA IN DATE,
//                P_CUR_MONTO_DEV OUT SYS_REFCURSOR); FALTA
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_DEV);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ObtieneMonto");

			query.registerStoredProcedureParameter("P_LOTE_REV", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTO_DEV", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_LOTE_REV", parametros.getLoteRev());
			query.setParameter("P_FECHA", parametros.getFecha());

			ObtieneMontoOut res = new ObtieneMontoOut();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTO_DEV");
			if (cursor != null) {
				res.setMonto(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ventaTitulosMonitor(VentaTitulosMonitorIn parametros) throws AforeException {
//	    PROCEDURE PRC_RET_VENTA_TITULOS_MONITOR(P_SELECCION IN VARCHAR,
//                P_IND_CUOTA_REND IN VARCHAR,
//                P_TRANSACMOV  IN NUMBER,
//                P_SUBTRANSMOV IN VARCHAR2,
//                P_IDLOTE IN VARCHAR2,
//                P_LOTE_REV IN VARCHAR2,
//                P_FECHAI IN DATE,
//                P_FECHA  IN DATE,
//                P_USUARIO IN VARCHAR2,
//                P_SIAFORE IN VARCHAR2,
//                P_RETIRO_AFORE_MND IN NUMBER,
//                P_VALOR_CUOTA IN NUMBER,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_VENTA_TITULOS_MONITOR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			query.registerStoredProcedureParameter("P_SELECCION", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_IND_CUOTA_REND", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TRANSACMOV", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SUBTRANSMOV", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_IDLOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE_REV", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHAI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SIAFORE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_RETIRO_AFORE_MND", BigDecimal.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_VALOR_CUOTA", BigDecimal.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_SELECCION", parametros.getSeleccion());
			query.setParameter("P_IND_CUOTA_REND", parametros.getIndCuotaRend());
			query.setParameter("P_TRANSACMOV", parametros.getTransacMov());
			query.setParameter("P_SUBTRANSMOV", parametros.getSubtransMov());
			query.setParameter("P_IDLOTE", parametros.getIdLote());
			query.setParameter("P_LOTE_REV", parametros.getLoteRev());
			query.setParameter("P_FECHAI", parametros.getFechaI());
			query.setParameter("P_FECHA", parametros.getFecha());
			query.setParameter("P_USUARIO", parametros.getUsuario());
			query.setParameter("P_SIAFORE", parametros.getSiafore());
			query.setParameter("P_RETIRO_AFORE_MND", parametros.getRetiroAforeMnd());
			query.setParameter("P_VALOR_CUOTA", parametros.getValorCuota());

//			query.execute();
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
			
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ventaTitulosMonitorCT(VentaTitulosMonitorCTIn parametros) throws AforeException {
//	    PROCEDURE PRC_VENTA_TITULOS_MONITOR_CT (P_IND_CUOTA_REND IN VARCHAR,
//                P_LOTE_CORTE IN VARCHAR2,
//                P_USUARIO IN VARCHAR2,
//                P_SIAFORE IN VARCHAR2,
//                P_RETIRO_AFORE_MND IN NUMBER,
//                P_VALOR_CUOTA IN NUMBER,
//                P_ESTATUS OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_VENTA_TITULOS_MONITOR_CT);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_IND_CUOTA_REND", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE_CORTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SIAFORE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_RETIRO_AFORE_MND", BigDecimal.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_VALOR_CUOTA", BigDecimal.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_IND_CUOTA_REND", parametros.getIndCuotaRend());
			query.setParameter("P_LOTE_CORTE", parametros.getLoteCorte());
			query.setParameter("P_USUARIO", parametros.getUsuario());
			query.setParameter("P_SIAFORE", parametros.getSiafore());
			query.setParameter("P_RETIRO_AFORE_MND", parametros.getRetiroAforeMnd());
			query.setParameter("P_VALOR_CUOTA", parametros.getValorCuota());

//			query.execute();
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
