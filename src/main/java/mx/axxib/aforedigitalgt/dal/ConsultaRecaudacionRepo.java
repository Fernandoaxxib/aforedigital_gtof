package mx.axxib.aforedigitalgt.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ConsultaNSSOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Recaudación
//** Interventor Principal: JSAS
//** Fecha Creación: 22/Feb/2021
//** Última Modificación:
//***********************************************//
@Repository
@Transactional
public class ConsultaRecaudacionRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public ConsultaNSSOut getConsultaNSS(String nss) throws AforeException {
//		PROCEDURE CONSULTA_X_NSS( P_NSS  IN NUMBER,
//                CP_CURSOR OUT SYS_REFCURSOR,
//                P_MENSAJE  OUT VARCHAR2,
//                P_ESTATUS  OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTA_RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.CONSULTA_RECAUDACION_CONSULTA_NSS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ConsultaNSS");

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_NSS", nss);
			
			ConsultaNSSOut res = new ConsultaNSSOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut getGeneraReporte(String nss) throws AforeException {
//		PROCEDURE PRC_GENERA_REPORTE (P_Nss  IN  VARCHAR2,
//                P_MENSAJE OUT VARCHAR2,
//                P_ESTATUS  OUT NUMBER) ; 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTA_RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.CONSULTA_RECAUDACION_GENERA_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_Nss", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_Nss", nss);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
