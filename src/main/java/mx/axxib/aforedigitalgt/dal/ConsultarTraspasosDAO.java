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
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosOut;
import mx.axxib.aforedigitalgt.eml.VerCheque;

@Repository
public class ConsultarTraspasosDAO extends RepoBase{
	
	private final EntityManager entityManager;

	@Autowired
	public ConsultarTraspasosDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultarTraspasosOut> getConsultarTraspasos() throws AforeException {
		try {
		String storedFullName =  Constantes.CONSULTAR_TRASPASOS_PACKAGE.concat(".").concat(Constantes.CONSULTAR_TRASPASOS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultarTraspasosOut");

		
		query.registerStoredProcedureParameter("P_CLAVE_PROCESO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_DESC_PROCESO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_COD_TIPSALDO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_DESCRIPCION", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTADO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_DESC_ESTADO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FECHA_FIN", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_INCLUIDO_POR", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_MODIFICADO_POR", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FEC_INCLUSION", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FEC_MODIFICACION", Date.class, ParameterMode.OUT);

		List<ConsultarTraspasosOut> res=query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public ConsultarNombreCuentaOut getConsultarCurp() throws AforeException {
		try {
		String storedFullName =  Constantes.CONSULTAR_TRASPASOS_PACKAGE.concat(".").concat(Constantes.CONSULTAR_TRASPASOS_CURP_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultarTraspasosOut");

		
		query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
		
		ConsultarNombreCuentaOut res= new ConsultarNombreCuentaOut ();
		res.setCuenta((String) query.getOutputParameterValue("P_CUENTA"));
		res.setNombre((String) query.getOutputParameterValue("P_NOMBRE"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public ConsultarNombreCuentaOut getConsultarNss() throws AforeException {
		try {
		String storedFullName =  Constantes.CONSULTAR_TRASPASOS_PACKAGE.concat(".").concat(Constantes.CONSULTAR_TRASPASOS_NSS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultarTraspasosOut");

		
		query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
		
		ConsultarNombreCuentaOut res= new ConsultarNombreCuentaOut ();
		res.setCuenta((String) query.getOutputParameterValue("P_CUENTA"));
		res.setNombre((String) query.getOutputParameterValue("P_NOMBRE"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
