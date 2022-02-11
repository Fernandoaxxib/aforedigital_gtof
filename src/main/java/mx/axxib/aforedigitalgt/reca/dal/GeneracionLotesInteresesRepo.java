package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.InteresesTranOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de generación de lotes de intereses transito Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 07/02/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class GeneracionLotesInteresesRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public InteresesTranOut getFechas() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_PACKAGE).concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "FechaOut");

			query.registerStoredProcedureParameter("CP_FECHA", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			InteresesTranOut res = new InteresesTranOut();

			res.setListFechas(query.getResultList());
			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public InteresesTranOut getLotes() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_PACKAGE).concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteCargaOut");

			query.registerStoredProcedureParameter("CP_LOTE", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			InteresesTranOut res = new InteresesTranOut();

			res.setListLotes(query.getResultList());
			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public InteresesTranOut getLotesRev() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_PACKAGE).concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_LOTE_REV);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteRevOut");

			query.registerStoredProcedureParameter("CP_LOTE_RV", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			InteresesTranOut res = new InteresesTranOut();

			res.setListatLotesRev(query.getResultList());
			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public InteresesTranOut generarLotes(Integer P_TIPO_ACCION, Date P_FEC_OPERACION, String P_LOTE, String P_ARCHIVO,
			String P_RUTA, String P_LOTE_REVERSA) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_PACKAGE).concat(".")
					.concat(Constantes.GENERACION_LOTES_INTERESES_TRANSITO_GENERACION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_TIPO_ACCION", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_OPERACION", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_RUTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE_REVERSA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			query.setParameter("P_TIPO_ACCION", P_TIPO_ACCION);
			query.setParameter("P_FEC_OPERACION", P_FEC_OPERACION);
			query.setParameter("P_LOTE", P_LOTE);
			query.setParameter("P_ARCHIVO", P_ARCHIVO);
			query.setParameter("P_RUTA", P_RUTA);
			query.setParameter("P_LOTE_REVERSA", P_LOTE_REVERSA);

			InteresesTranOut res = new InteresesTranOut();

			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
