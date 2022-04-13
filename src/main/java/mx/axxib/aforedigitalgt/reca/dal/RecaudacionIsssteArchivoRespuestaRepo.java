package mx.axxib.aforedigitalgt.reca.dal;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteArchivoRespuesta;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteArchivoRespuestaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Recaudacion ISSSTE
//** Interventor Principal: EAG
//** Fecha Creación: 04/Abril/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class RecaudacionIsssteArchivoRespuestaRepo extends RepoBase {
	
	
	@SuppressWarnings("unchecked")
	public RecaudacionIsssteArchivoRespuestaOut lote() throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_ARCHIVO_RESPUESTA_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_ARCHIVO_RESPUESTA_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_LOTE", void.class, ParameterMode.REF_CURSOR);
			
			
			RecaudacionIsssteArchivoRespuestaOut res = new RecaudacionIsssteArchivoRespuestaOut();
			
			List<RecaudacionIsssteArchivoRespuesta> lista = query.getResultList();
			res.setListaDatos(lista);
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RecaudacionIsssteArchivoRespuestaOut iniciaProceso(String lote) throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_ARCHIVO_RESPUESTA_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_ARCHIVO_RESPUESTA_INICIA_PROCESO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_LOTE", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ARCHIVO_SALIDA", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_SEGUIMIENTO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);			
			
			query.setParameter("P_LOTE", lote);
			
			RecaudacionIsssteArchivoRespuestaOut res = new RecaudacionIsssteArchivoRespuestaOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			res.setSeguimiento( (String) query.getOutputParameterValue("P_SEGUIMIENTO") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
