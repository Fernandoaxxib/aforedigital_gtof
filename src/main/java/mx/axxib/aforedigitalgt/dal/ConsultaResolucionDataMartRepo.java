package mx.axxib.aforedigitalgt.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionDataMartOut;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionesNombreOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;


@Repository
@Transactional
public class ConsultaResolucionDataMartRepo extends RepoBase {
	
	
	@SuppressWarnings("unchecked")
	public ConsultaResolucionesNombreOut getCuentaNombre(Long nss) throws AforeException {
		try {
	
		String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTA_RESOLUCION_DATA_MART_PACKAGE).concat(".").concat(Constantes.CONSULTA_RESOLUCION_DATA_NSS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ConsultaResolucionDataMartOut");

		query.registerStoredProcedureParameter("P_NSS", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CUENTA", Long.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_NSS_TRABAJADOR", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_CURP", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_NOMBRE_DATAMART", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_NOMBRE_AFORE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_PATERNO_AFORE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_MATERNO_AFORE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_derecho", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_desc_derecho", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("CP_CURP_DATO",  void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);
		
		query.setParameter("P_NSS",nss);
		

		ConsultaResolucionesNombreOut res= new ConsultaResolucionesNombreOut ();
		Object cursor = query.getOutputParameterValue("CP_CURP_DATO");
		if (cursor != null) {
		res.setP_CUENTA((Long) query.getOutputParameterValue("P_CUENTA"));
		res.setP_NSS_TRABAJADOR((String) query.getOutputParameterValue("p_NSS_TRABAJADOR"));
		res.setP_NOMBRE((String) query.getOutputParameterValue("P_NOMBRE"));
		res.setP_CURP((String) query.getOutputParameterValue("p_CURP"));		
		res.setP_NOMBRE_DATAMART((String) query.getOutputParameterValue("p_NOMBRE_DATAMART"));
		res.setP_NOMBRE_AFORE((String) query.getOutputParameterValue("p_NOMBRE_AFORE"));
		res.setP_PATERNO_AFORE((String) query.getOutputParameterValue("p_PATERNO_AFORE"));
		res.setP_MATERNO_AFORE((String) query.getOutputParameterValue("p_MATERNO_AFORE"));
		res.setP_derecho((String) query.getOutputParameterValue("P_derecho"));
		res.setP_desc_derecho((String) query.getOutputParameterValue("P_desc_derecho"));
		res.setCursor(query.getResultList());
		res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setP_ESTATUS((String) query.getOutputParameterValue("P_ESTATUS"));
		}
		 
		
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	

}
