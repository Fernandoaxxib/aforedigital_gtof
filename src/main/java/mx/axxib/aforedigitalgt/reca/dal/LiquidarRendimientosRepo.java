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
			
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
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
	public LiquidarRenConsultaOut consulta(String lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_CONSULTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarRenConsulta");
			
			query.registerStoredProcedureParameter("oc_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("OC_SEGUIMIENTO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("OC_CURSOR", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("oc_ID_LOTE", lote); 
			
			LiquidarRenConsultaOut res = new LiquidarRenConsultaOut();
			res.setMontos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		

	}
	
	public BaseOut liquidar(String lote, String tipo) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_RENDIMIENTOS_LIQUIDAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("oc_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_TIPO", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("oc_ID_LOTE", lote);
			query.setParameter("oc_TIPO",tipo);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
