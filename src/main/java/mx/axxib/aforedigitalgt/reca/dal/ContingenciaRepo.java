package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.SellosOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Contingencia
//** Interventor Principal: JSAS
//** Fecha Creación: 27/Ene/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class ContingenciaRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public SellosOut sellos(Integer folio) throws AforeException {
//		PROCEDURE PRC_SELLOS(P_FOLIOSOL IN NUMBER,
//                CP_SELLOS OUT SYS_REFCURSOR,
//                P_MENSAJE OUT VARCHAR2,
//                P_ESTATUS OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CONTINGENCIA_PACKAGE).concat(".")
					.concat(Constantes.CONTINGENCIA_SELLOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "BuscarSellos");
			
			query.registerStoredProcedureParameter("P_FOLIOSOL", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("CP_SELLOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FOLIOSOL", folio);
			
			SellosOut res = new SellosOut();
			res.setSellos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut asignar(String sello, String folioSol) throws AforeException {
//		PROCEDURE PRC_BTN_ASIGNAR(P_SELLOS IN VARCHAR2,
//                P_FOLIOSOL IN VARCHAR2,
//                P_MENSAJE OUT VARCHAR2,
//                P_ESTATUS OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CONTINGENCIA_PACKAGE).concat(".")
					.concat(Constantes.CONTINGENCIA_EXPEDIENTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_SELLOS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FOLIOSOL", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_SELLOS", sello);
			query.setParameter("P_FOLIOSOL", folioSol);
						
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut expediente(String seleccion, String noSolicitud) throws AforeException {
//		PROCEDURE PRC_ENVIAR_EXPEDIENTE(P_SELEC_MASIVA_INDI IN VARCHAR2,
//                P_NUM_SOL IN VARCHAR2,
//                P_MENSAJE OUT VARCHAR2,
//                P_ESTATUS OUT NUMBER); 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CONTINGENCIA_PACKAGE).concat(".")
					.concat(Constantes.CONTINGENCIA_EXPEDIENTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_SELEC_MASIVA_INDI", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUM_SOL", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_SELEC_MASIVA_INDI", seleccion);
			query.setParameter("P_NUM_SOL", noSolicitud);
						
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
