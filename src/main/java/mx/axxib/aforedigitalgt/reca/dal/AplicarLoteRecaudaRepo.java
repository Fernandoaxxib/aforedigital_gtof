package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.RespAplicarOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Aplicar lote recauda Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 14/02/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class AplicarLoteRecaudaRepo extends RepoBase {

	public RespAplicarOut ejecutar(String P_lote_recauda, Integer P_fecha_mov, String P_lote_bono, String P_opciones)
			throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.APLICAR_LOTE_RECAUDA_PACKAGE).concat(".")
					.concat(Constantes.APLICAR_LOTE_RECAUDA_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_lote_recauda", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_fecha_mov", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_lote_bono", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_opciones", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			query.setParameter("P_lote_recauda", P_lote_recauda);
			query.setParameter("P_fecha_mov", P_fecha_mov);
			query.setParameter("P_lote_bono", P_lote_bono);
			query.setParameter("P_opciones", P_opciones);

			RespAplicarOut res = new RespAplicarOut();

			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public RespAplicarOut getLotesBono() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.APLICAR_LOTE_RECAUDA_PACKAGE).concat(".")
					.concat(Constantes.APLICAR_LOTE_RECAUDA_LOTE_BONO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteIssOut");

			query.registerStoredProcedureParameter("CP_BONO", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			RespAplicarOut res = new RespAplicarOut();
			res.setLotesBono(query.getResultList());
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public RespAplicarOut getLotesReca() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.APLICAR_LOTE_RECAUDA_PACKAGE).concat(".")
					.concat(Constantes.APLICAR_LOTE_RECAUDA_LOTE_RECA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteIssOut");

			query.registerStoredProcedureParameter("CP_RECAUDACION", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			RespAplicarOut res = new RespAplicarOut();
			res.setLotesReca(query.getResultList());
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
