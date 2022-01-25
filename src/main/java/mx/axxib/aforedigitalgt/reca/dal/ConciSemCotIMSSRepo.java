package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.DatosConciOut;
import mx.axxib.aforedigitalgt.reca.eml.SemanasOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Conciliación Semanas de Cotización IMSS
//** Interventor Principal: JSAS
//** Fecha Creación: 31/Ene/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class ConciSemCotIMSSRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public SemanasOut buscar(Date fechaI, Date fechaF, String nss) throws AforeException {
//		 PROCEDURE PRC_BUSCAR(p_fec_ini   IN DATE,
//                 p_fec_fin   IN DATE, 
//                 p_nss          IN OUT VARCHAR2,
//                 CP_DATOS OUT SYS_REFCURSOR,
//                 P_ESTATUS  OUT NUMBER,
//                 P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CONCILIACION_SEMACOTI_IMSS_PACKAGE).concat(".")
					.concat(Constantes.CONCILIACION_SEMACOTI_IMSS_BUSCAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "BuscarSemanas");
			
			query.registerStoredProcedureParameter("p_fec_ini", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_fec_fin", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_nss", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("p_fec_ini", fechaI);
			query.setParameter("p_fec_fin", fechaF);
			query.setParameter("p_nss", nss);
			
			SemanasOut res = new SemanasOut();
			res.setSemanas(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public DatosConciOut datos(Integer noMov, Date fechaMov) throws AforeException {
//PROCEDURE PRC_DATOS(p_num_mov   IN NUMBER,
//                    p_fec_mov   IN DATE,
//                    CP_DATOS OUT SYS_REFCURSOR,
//                    P_ESTATUS  OUT NUMBER,
//                    P_MENSAJE  OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CONCILIACION_SEMACOTI_IMSS_PACKAGE).concat(".")
					.concat(Constantes.CONCILIACION_SEMACOTI_IMSS_DATOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "BuscarSemanasDetalle");
			
			query.registerStoredProcedureParameter("p_num_mov", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_fec_mov", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("p_num_mov", noMov);
			query.setParameter("p_fec_mov", fechaMov);
						
			DatosConciOut res = new DatosConciOut();
			res.setDetalle(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
