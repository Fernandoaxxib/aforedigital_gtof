package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.AportacionesVoluntariasOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Aportaciones Voluntarias 
//** Interventor Principal: EAG
//** Fecha Creación: 06/Abril/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class AportacionesVoluntariasRepo extends RepoBase {
	

	public AportacionesVoluntariasOut consultaAportacionesVoluntarias(Integer sucursal) throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.APORTACIONES_VOLUNTARIAS_PACKAGE).concat(".")
					.concat(Constantes.APORTACIONES_VOLUNTARIAS_APORTACIONES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("sucursal", Integer.class, ParameterMode.IN);
	
			query.registerStoredProcedureParameter("P_NOM_SUCURSAL", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_APORTA_VOL", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("sucursal", sucursal);
		
			
			AportacionesVoluntariasOut res = new AportacionesVoluntariasOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			res.setNombreSucursal( (String) query.getOutputParameterValue("P_NOM_SUCURSAL") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public AportacionesVoluntariasOut consultaAportacionesVoluntariasIndependientes(Integer sucursal) throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.APORTACIONES_VOLUNTARIAS_PACKAGE).concat(".")
					.concat(Constantes.APORTACIONES_VOLUNTARIAS_INDEPENDIENTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("sucursal", Integer.class, ParameterMode.IN);
	
			query.registerStoredProcedureParameter("P_NOM_SUCURSAL", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_APORTA_VOL", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("sucursal", sucursal);
		
			
			AportacionesVoluntariasOut res = new AportacionesVoluntariasOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			res.setNombreSucursal( (String) query.getOutputParameterValue("P_NOM_SUCURSAL") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
