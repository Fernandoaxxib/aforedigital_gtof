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
import mx.axxib.aforedigitalgt.eml.GeneraReporteUMAIn;
import mx.axxib.aforedigitalgt.eml.ValorUMA;
import mx.axxib.aforedigitalgt.eml.ValorUMAOut;

@Repository
@Transactional
public class ValorUMARepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public ValorUMAOut getValoresUMA() throws AforeException {
//		PROCEDURE PRC_CAT_UMA (
//                CP_CURSOR OUT SYS_REFCURSOR,
//                P_ESTATUS    OUT NUMBER,
//                P_MENSAJE      OUT VARCHAR2);

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VALORES_UMA_PACKAGE)
					.concat(".").concat(Constantes.VALORES_UMA_VALORES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ValorUMA");

			query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			ValorUMAOut res = new ValorUMAOut();
			Object cursor = query.getOutputParameterValue("CP_CURSOR");
			if (cursor != null) {
				res.setValores(query.getResultList());
				res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
				res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut getGeneraReporte(GeneraReporteUMAIn parametros) throws AforeException {
//		PROCEDURE PRC_GENERA_REPORTE(P_RUTA IN VARCHAR2,
//                P_FECHA_INI IN DATE,
//                P_FECHA_FIN IN DATE,
//                 P_ESTATUS  OUT NUMBER,
//                P_MENSAJE OUT VARCHAR2
//                );
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VALORES_UMA_PACKAGE)
					.concat(".").concat(Constantes.VALORES_UMA_GENERAR_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_RUTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_INI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_RUTA", parametros.getRuta());
			query.setParameter("P_FECHA_INI", parametros.getFechaInicial());
			query.setParameter("P_FECHA_FIN", parametros.getFechaFinal());

			BaseOut res = new BaseOut();
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}

	public BaseOut insertarUMA(ValorUMA parametros) throws AforeException {
//		PROCEDURE  PRC_SALARIO_UMA_INSERT(P_USUARIO IN VARCHAR2,
//                P_FCH_UMA IN DATE,
//                P_MONTO_DIARIO IN  NUMBER,
//                P_ESTATUS   OUT NUMBER,
//                P_MENSAJE  OUT VARCHAR2); 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VALORES_UMA_PACKAGE)
					.concat(".").concat(Constantes.VALORES_UMA_INSERTAR_UMA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FCH_UMA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MONTO_DIARIO", BigDecimal.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_USUARIO", parametros.getUser());
			query.setParameter("P_FCH_UMA", parametros.getFecha());
			query.setParameter("P_MONTO_DIARIO", parametros.getMonto());

			BaseOut res = new BaseOut();
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut actualizarUMA(ValorUMA parametros) throws AforeException {
//		PROCEDURE  PRC_SALARIO_UMA_UPD(P_USUARIO IN VARCHAR2,
//                P_FCH_UMA IN DATE,
//                P_FCH_NUEVA IN DATE,
//                P_MONTO_DIARIO  IN  NUMBER,
//                P_ESTATUS   OUT NUMBER,
//                P_MENSAJE  OUT VARCHAR2);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VALORES_UMA_PACKAGE)
					.concat(".").concat(Constantes.VALORES_UMA_ACTUALIZAR_UMA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FCH_UMA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FCH_NUEVA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MONTO_DIARIO", BigDecimal.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_USUARIO", parametros.getUser());
			query.setParameter("P_FCH_UMA", parametros.getFecha());
			query.setParameter("P_FCH_NUEVA", parametros.getFechaNueva());
			query.setParameter("P_MONTO_DIARIO", parametros.getMonto());

			BaseOut res = new BaseOut();
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut eliminarUMA(ValorUMA parametros) throws AforeException {
//		 PROCEDURE   PRC_DELETE_UMA  ( P_FCH_UMA IN DATE,
//                 P_USUARIO IN VARCHAR2,
//                 P_ESTATUS   OUT NUMBER,
//                 P_MENSAJE  OUT VARCHAR2);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VALORES_UMA_PACKAGE)
					.concat(".").concat(Constantes.VALORES_UMA_ELIMINAR_UMA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FCH_UMA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_USUARIO", parametros.getUser());
			query.setParameter("P_FCH_UMA", parametros.getFecha());

			BaseOut res = new BaseOut();
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
