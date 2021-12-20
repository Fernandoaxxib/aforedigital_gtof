package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenConsultaOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenOut;
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

	@SuppressWarnings("unchecked")
	public LiquidarRenOut lote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarRenLotes");
			
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			LiquidarRenOut res = new LiquidarRenOut();
			res.setLotes(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public LiquidarRenConsultaOut consulta(LiquidarRendimientoIn in) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_CONSULTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarRenConsulta");
			
			query.registerStoredProcedureParameter("oc_ID_LOTE", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("oc_ID_LOTE", in.getIdLote()); //No está definido en el stored un parametro de entrada pero es necesario para buscar por lote
			
			LiquidarRenConsultaOut res = new LiquidarRenConsultaOut();
			res.setMontos(query.getResultList());
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
