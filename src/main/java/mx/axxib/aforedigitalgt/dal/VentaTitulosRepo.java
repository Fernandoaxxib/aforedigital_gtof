package mx.axxib.aforedigitalgt.dal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import mx.axxib.aforedigitalgt.eml.ObtieneMontoCorteOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosOut;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorCTIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorIn;

@Repository
@Transactional
public class VentaTitulosRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public VentaTitulosRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<ObtieneMontoTotalOut> getObtieneMontoTotal(ObtieneMontoTotalIn parametros) throws AforeException {
		try {
			
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_TOTAL);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMontoTotalOut");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHAI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTO_TOTAL", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_FECHA", parametros.getFechaFinal());
			query.setParameter("P_FECHAI", parametros.getFechaInicial());

			List<ObtieneMontoTotalOut> res = new ArrayList<ObtieneMontoTotalOut>();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTO_TOTAL");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtenerTipoRetiroOut> getObtenerTipoRetiro(ObtenerTipoRetiroIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTENER_TIPO_RETIRO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtenerTipoRetiroOut");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIPO_RETIRO", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_FECHA", parametros.getFecha());

			List<ObtenerTipoRetiroOut> res = new ArrayList<ObtenerTipoRetiroOut>();
			Object cursor = query.getOutputParameterValue("P_TIPO_RETIRO");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtieneMontoRetiroOut> getObtieneMontoRetiro(ObtieneMontoRetiroIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_RETIRO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMontoRetiroOut");

			query.registerStoredProcedureParameter("P_TIP_RETIRO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHAI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIP_TRANSACCION", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SUBTIP_TRANSAC", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MON_RETIRO", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_TIP_RETIRO", parametros.getTipoRetiro());
			query.setParameter("P_FECHA", parametros.getFechaFinal());
			query.setParameter("P_FECHAI", parametros.getFechaInicial());
			query.setParameter("P_TIP_TRANSACCION", parametros.getTipoTransaccion());
			query.setParameter("P_SUBTIP_TRANSAC", parametros.getSubTipoTransaccion());

			List<ObtieneMontoRetiroOut> res = new ArrayList<ObtieneMontoRetiroOut>();
			Object cursor = query.getOutputParameterValue("P_CUR_MON_RETIRO");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtieneMontoCorteOut> getObtieneMontoCorte(ObtieneMontoCorteIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_CORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMontoCorteOut");

			query.registerStoredProcedureParameter("P_LOTE_CORTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTO_CORTE", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_LOTE_CORTE", parametros.getLoteCorte());
			query.setParameter("P_FECHA", parametros.getFecha());

			List<ObtieneMontoCorteOut> res = new ArrayList<ObtieneMontoCorteOut>();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTO_CORTE");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtenerLoteTraspasosOut> getObtenerLoteTraspasos(ObtenerLoteTraspasosIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTENER_LOTE_TRASPA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtenerLoteTraspasosOut");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE_TRASPA", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_FECHA", parametros.getFecha());

			List<ObtenerLoteTraspasosOut> res = new ArrayList<ObtenerLoteTraspasosOut>();
			Object cursor = query.getOutputParameterValue("P_LOTE_TRASPA");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtieneMontoTraspasosOut> getObtieneMontoTraspasos(ObtieneMontoTraspasosIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_TRASPASOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtieneMontoTraspasosOut");

			query.registerStoredProcedureParameter("P_LOTE_TRASP", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTRASPASOS", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_LOTE_TRASP", parametros.getLoteTraspaso());
			query.setParameter("P_FECHA", parametros.getFecha());

			List<ObtieneMontoTraspasosOut> res = new ArrayList<ObtieneMontoTraspasosOut>();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTRASPASOS");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtenerRgDevExcesOut> getObtenerRgDevExces(ObtenerRgDevExcesIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTENER_RG_DEV_EXCES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ObtenerRgDevExcesOut");

			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_DEV_EXCES", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_FECHA", parametros.getFecha());

			List<ObtenerRgDevExcesOut> res = new ArrayList<ObtenerRgDevExcesOut>();
			Object cursor = query.getOutputParameterValue("P_DEV_EXCES");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObtieneMontoDevOut> getObtieneMontoDev(ObtieneMontoDevIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VENTA_TITULOS_PACKAGE)
					.concat(".").concat(Constantes.VENTA_TITULOS_OBTIENE_MONTO_DEV);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ObtieneMontoDevOut");

			query.registerStoredProcedureParameter("P_LOTE_REV", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CUR_MONTO_DEV", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_LOTE_REV", parametros.getLoteRev());
			query.setParameter("P_FECHA", parametros.getFecha());

			List<ObtieneMontoDevOut> res = new ArrayList<ObtieneMontoDevOut>();
			Object cursor = query.getOutputParameterValue("P_CUR_MONTO_DEV");
			if (cursor != null) {
				res = query.getResultList();
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ventaTitulosMonitor(VentaTitulosMonitorIn parametros) throws AforeException {
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

	public void ventaTitulosMonitorCT(VentaTitulosMonitorCTIn parametros) throws AforeException {
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
			query.registerStoredProcedureParameter("P_ESTATUS", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", BigDecimal.class, ParameterMode.OUT);

			query.setParameter("P_IND_CUOTA_REND", parametros.getIndCuotaRend());
			query.setParameter("P_LOTE_CORTE", parametros.getLoteCorte());
			query.setParameter("P_USUARIO", parametros.getUsuario());
			query.setParameter("P_SIAFORE", parametros.getSiafore());
			query.setParameter("P_RETIRO_AFORE_MND", parametros.getRetiroAforeMnd());
			query.setParameter("P_VALOR_CUOTA", parametros.getValorCuota());

			query.execute();
			

		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
