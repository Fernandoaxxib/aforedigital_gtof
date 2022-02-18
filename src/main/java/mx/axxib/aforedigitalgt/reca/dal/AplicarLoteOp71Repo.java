package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.RespAplicarOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de aplicar lote intereses en tránsito Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 16/02/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class AplicarLoteOp71Repo extends RepoBase {

	public RespAplicarOut ejecutar(String P_lote_recauda, Date P_fecha_mov, String P_OPCION) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APLICAR_LOTE_OP71_PACKAGE)
					.concat(".").concat(Constantes.APLICAR_LOTE_OP71_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_lote_recauda", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_fecha_mov", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_OPCION", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);

			query.setParameter("P_lote_recauda", P_lote_recauda);
			query.setParameter("P_fecha_mov", P_fecha_mov);
			query.setParameter("P_OPCION", P_OPCION);

			RespAplicarOut res = new RespAplicarOut();

			res.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public RespAplicarOut getLotes() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.APLICAR_LOTE_OP71_PACKAGE).concat(".")
					.concat(Constantes.APLICAR_LOTE_OP71_GET_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteIssOut");

			query.registerStoredProcedureParameter("CP_LOTE", void.class, ParameterMode.REF_CURSOR);
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

}
