package mx.axxib.aforedigitalgt.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ConsultaNSSOut;

@Repository
@Transactional
public class ConsultaRecaudacionRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public ConsultaNSSOut getConsultaNSS(String nss) throws AforeException {
//		PROCEDURE CONSULTA_X_NSS( P_NSS  IN NUMBER,
//                CP_CURSOR OUT SYS_REFCURSOR,
//                P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTA_RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.CONSULTA_RECAUDACION_CONSULTA_NSS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ConsultaNSS");

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_NSS", nss);
			
			ConsultaNSSOut res = new ConsultaNSSOut();
			res.setDatos(query.getResultList());
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String getGeneraReporte(String nss) throws AforeException {
//		PROCEDURE PRC_GENERA_REPORTE (P_Nss  IN  VARCHAR2,
//                P_AVISO OUT VARCHAR2) ; 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTA_RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.CONSULTA_RECAUDACION_GENERA_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_Nss", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_AVISO", String.class, ParameterMode.OUT);
			
			return (String) query.getOutputParameterValue("P_AVISO");
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
