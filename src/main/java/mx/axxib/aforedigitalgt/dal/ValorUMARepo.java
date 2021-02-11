package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.GeneraReporteUMAIn;
import mx.axxib.aforedigitalgt.eml.ValorUMAOut;

@Repository
@Transactional
public class ValorUMARepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public ValorUMAOut getValorUMA(String usuario) throws AforeException {
//		PROCEDURE PRC_CAT_UMA (P_USUARIO IN VARCHAR2,
//                CP_CURSOR OUT SYS_REFCURSOR,
//                P_MENSAJE      OUT VARCHAR2);
	
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VALORES_UMA_PACKAGE)
					.concat(".").concat(Constantes.VALORES_UMA_VALORES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ValorUMA");

			query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_USUARIO", usuario);
			
			ValorUMAOut res = new ValorUMAOut();
			Object cursor = query.getOutputParameterValue("CP_CURSOR");
			if (cursor != null) {
				res.setValores(query.getResultList());
				res.setMensaje((String)query.getOutputParameterValue("P_MENSAJE"));
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String getGeneraReporte(GeneraReporteUMAIn parametros) throws AforeException {
//		PROCEDURE PRC_GENERA_REPORTE(P_RUTA IN VARCHAR2,
//                P_FECHA_INI IN DATE,
//                P_FECHA_FIN IN DATE,
//                P_MENSAJE OUT VARCHAR2
//                );
		try {
			
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.VALORES_UMA_PACKAGE)
					.concat(".").concat(Constantes.VALORES_UMA_GENERAR_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_RUTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_INI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_RUTA", parametros.getRuta());
			query.setParameter("P_FECHA_INI", parametros.getFechaInicial());
			query.setParameter("P_FECHA_FIN", parametros.getFechaFinal());

			String res = (String) query.getOutputParameterValue("P_MENSAJE");
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
