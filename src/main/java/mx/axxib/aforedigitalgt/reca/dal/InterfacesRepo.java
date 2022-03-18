package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.InterInversionOut;
import mx.axxib.aforedigitalgt.reca.eml.InterLoteOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Interfaces
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Mar/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class InterfacesRepo extends RepoBase {
	
	@SuppressWarnings("unchecked")
	public InterLoteOut lote() throws AforeException {
		//PROCEDURE PRC_LOTE(CP_LOTE OUT SYS_REFCURSOR,
//      P_MENSAJE OUT VARCHAR2,
//      P_ESTATUS OUT NUMBER) ;
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.INTERFACES_PACKAGE).concat(".")
					.concat(Constantes.INTERFACES_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "InterLote");
			
			query.registerStoredProcedureParameter("CP_LOTE", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			InterLoteOut res = new InterLoteOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public InterInversionOut inversion() throws AforeException {
		//PROCEDURE PRC_INVERSION(CP_INVERSION OUT SYS_REFCURSOR,
//		                 P_MENSAJE OUT VARCHAR2,
//		                 P_ESTATUS OUT NUMBER) ;
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.INTERFACES_PACKAGE).concat(".")
					.concat(Constantes.INTERFACES_INVERSION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "InterInversion");
			
			query.registerStoredProcedureParameter("CP_INVERSION", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			InterInversionOut res = new InterInversionOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public BaseOut interfaces(String tipo, String lote, String claveInversion, Integer codInversion, Date fecha) throws AforeException {
		//PROCEDURE PRC_INTERFACES(P_TIPO_BONO IN VARCHAR2,
//      P_ID_LOTE   IN NUMBER,
//      P_DESC_INVERSION IN VARCHAR2,
//      P_COD_INVERSION  IN NUMBER,
//      P_FECHA_APLICADO IN DATE,
//      P_MENSAJE        OUT VARCHAR2,
//      P_ESTATUS        OUT NUMBER); 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.INTERFACES_PACKAGE).concat(".")
					.concat(Constantes.INTERFACES_INTERFACES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_TIPO_BONO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_DESC_INVERSION", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_COD_INVERSION", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_APLICADO", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_TIPO_BONO", tipo);
			query.setParameter("P_ID_LOTE", lote);
			query.setParameter("P_DESC_INVERSION", claveInversion);
			query.setParameter("P_COD_INVERSION", codInversion);
			query.setParameter("P_FECHA_APLICADO", fecha);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
		
}
