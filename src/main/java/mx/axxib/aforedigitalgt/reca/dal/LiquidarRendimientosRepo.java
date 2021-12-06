package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRendimientoIn;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 30/Nov/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class LiquidarRendimientosRepo extends RepoBase {

	public BaseOut lote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut consulta() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_CONSULTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut liquidar(LiquidarRendimientoIn in) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_LIQUIDAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			
			query.registerStoredProcedureParameter("oc_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("on_TOTAL", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_TRAN_DISREND", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_SUBTRAN_DISREND", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("on_TRAN_COMPTIT", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_TIPO", String.class, ParameterMode.IN);

			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("oc_ID_LOTE", in.getIdLote());
			query.setParameter("on_TOTAL", in.getTotal());
			query.setParameter("oc_TRAN_DISREND", in.getTransaccion());
			query.setParameter("oc_SUBTRAN_DISREND", in.getSubTrans());
			query.setParameter("on_TRAN_COMPTIT", in.getTransComp());
			query.setParameter("oc_TIPO", in.getTipo());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
