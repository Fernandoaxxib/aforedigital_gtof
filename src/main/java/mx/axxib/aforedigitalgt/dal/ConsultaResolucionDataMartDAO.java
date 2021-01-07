package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ConsultaResolucionDataMartOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaOut;

@Repository
public class ConsultaResolucionDataMartDAO extends RepoBase {
	
private final EntityManager entityManager;
	
	@Autowired
	public ConsultaResolucionDataMartDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public ConsultarNombreCuentaOut getCuentaNombre(String nss) throws AforeException {
		try {
		String storedFullName =  Constantes.CONSULTA_RESOLUCION_DATA_MART_PACKAGE.concat(".").concat(Constantes.CONSULTA_RESOLUCION_DATA_NSS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
		
		query.setParameter("P_NSS",nss);
		
		
		//List<ConsultarNombreCuentaOut> res=query.getResultList();
		ConsultarNombreCuentaOut res= new ConsultarNombreCuentaOut ();
		res.setCuenta((String) query.getOutputParameterValue("P_CUENTA"));
		res.setNombre((String) query.getOutputParameterValue("P_NOMBRE"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public List<ConsultaResolucionDataMartOut> getConsultarTraspasos() throws AforeException {
		try {
			String storedFullName =  Constantes.CONSULTA_RESOLUCION_DATA_MART_PACKAGE.concat(".").concat(Constantes.CONSULTA_RESOLUCION_DATA_MART_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultaResolucionDataMartOut");

		query.registerStoredProcedureParameter("P_NSS_TRABAJADOR", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_CURP", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NOMBRE_DATAMART", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_NOMBRE_AFORE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_PATERNO_AFORE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_MATERNO_AFORE", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_DERECHO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_DESC_DERECHO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_SEC_PENSION", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_TIPO_MOVIMTO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_REGIMEN", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_TIPO_RETIRO", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_TIPO_SEGURO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_TIPO_PENSION", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_TIPO_PRESTACION", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_ART_NEGATIVA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FRACC_NEGATIVA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NUM_CONSIDERANDO", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_NUM_RESOLUCION", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FEC_INI_PENSION", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FEC_EMI_RESOLUCION", Date.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_PORCENTAJE_VALUACION", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_SEMANAS_COTIZADAS", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_DIAG_REGISTRO", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_SBC_TIPO_A", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_SBC_TIPO_B", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_PAGO_COMP", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_MONTO_RETIRO_ORG", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_SALDO_ANT", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_TIPO_AP_CS", String.class, ParameterMode.OUT);
		
		
		List<ConsultaResolucionDataMartOut> res=query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
		
}
