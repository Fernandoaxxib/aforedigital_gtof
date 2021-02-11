package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.LiqEjecutaReporteIn;
import mx.axxib.aforedigitalgt.eml.LiqObtieneParametrosOut;
import mx.axxib.aforedigitalgt.eml.LiqObtieneSieforeOut;

@Repository
@Transactional
public class ReporteLiquidacionRepo extends RepoBase {

	public LiqObtieneParametrosOut getObtieneParametros() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_LIQUIDACION_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_LIQUIDACION_OBTIENE_PARAMETROS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_ID_LOTE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIP_TRANSAC_TOTAL", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIP_TRANSAC_PARCIAL", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FECHA_SISTEMA", Date.class, ParameterMode.OUT);
			
			LiqObtieneParametrosOut res = new LiqObtieneParametrosOut();
			res.setIdLote( (String) query.getOutputParameterValue("P_ID_LOTE") );
			res.setFecha( (Date) query.getOutputParameterValue("P_FECHA_SISTEMA") );
			
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<LiqObtieneSieforeOut> getObtieneSiefore() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_LIQUIDACION_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_LIQUIDACION_OBTIENE_SIEFORE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiqObtieneSieforeOut");

			query.registerStoredProcedureParameter("P_CUR_SIEFORE", void.class, ParameterMode.REF_CURSOR);
			List<LiqObtieneSieforeOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String ejecutaReporte(LiqEjecutaReporteIn parametros) throws AforeException {
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
			query.registerStoredProcedureParameter("V_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_TIPO_REPORTE", parametros.getTipoReporte());
			query.setParameter("P_SIEFORE", parametros.getSiefore());
			query.setParameter("P_ID_LOTE", parametros.getIdLote());
			query.setParameter("P_TIPO_RETIRO", parametros.getTipoRetiro());
			query.setParameter("P_ESTADO", parametros.getEstado());
			query.setParameter("P_USUARIO", parametros.getUsuario());
			query.setParameter("P_DESC_SIEFORE", parametros.getDescripcion());
			query.setParameter("P_FECHA_SISTEMA", parametros.getFecha());

			String res = (String) query.getOutputParameterValue("V_MENSAJE");
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
