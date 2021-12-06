package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.AplicarCuentaIn;
import mx.axxib.aforedigitalgt.reca.eml.ValidaCuentaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Centro Patronal Empresarial
//** Interventor Principal: JSAS
//** Fecha Creación: 23/Nov/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class CentroPatronalEmpresarialRepo extends RepoBase {

	public ValidaCuentaOut validaCuenta(String codCuenta, String lote, String monto) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CENTRO_PATRONAL_PACKAGE).concat(".")
					.concat(Constantes.CENTRO_PATRONAL_VALIDA_CUENTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("oc_CodCuenta", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_MonDispersion", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("oc_DescCuenta", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("oc_CodCuenta", codCuenta);
			query.setParameter("oc_Lote", lote);
			query.setParameter("oc_MonDispersion", monto);

			ValidaCuentaOut res = new ValidaCuentaOut();
			res.setDescCuenta( (String) query.getOutputParameterValue("oc_DescCuenta") );
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut loteDispersion() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CENTRO_PATRONAL_PACKAGE).concat(".")
					.concat(Constantes.CENTRO_PATRONAL_LOTE);
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
	
	
	public BaseOut aplicar(AplicarCuentaIn in) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CENTRO_PATRONAL_PACKAGE).concat(".")
					.concat(Constantes.CENTRO_PATRONAL_APLICAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			
			query.registerStoredProcedureParameter("oc_CodCuenta", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("oc_Lote", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("oc_IndAccion", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("oc_Accion", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("oc_SUBTRANSAC_APORTERET", String.class, ParameterMode.INOUT);

			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("oc_CodCuenta", in.getCodCuenta());
			query.setParameter("oc_Lote", in.getLote());
			query.setParameter("oc_IndAccion", in.getIndAccion());
			query.setParameter("oc_Accion", in.getAccion());
			query.setParameter("oc_SUBTRANSAC_APORTERET", in.getSubTrans());
			

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	

	
}
