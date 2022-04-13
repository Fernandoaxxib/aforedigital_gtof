package mx.axxib.aforedigitalgt.reca.dal;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteCargaArchivo;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteCargaArchivoOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Recaudacion ISSSTE
//** Interventor Principal: EAG
//** Fecha Creación: 04/Abril/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class RecaudacionIsssteCargaArchivoRepo extends RepoBase {
	

	public BaseOut cargarArchivo(String arch_entrada, String dir_archivo, String tipo) throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_CARGA_ARCHIVO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_arch_entrada", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_DIRARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIPO", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_arch_entrada", arch_entrada);
			query.setParameter("P_DIRARCHIVO", dir_archivo);
			query.setParameter("P_TIPO", tipo);
			
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public BaseOut reversa(String lote_reversa) throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_REVERSA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_LOTE_REVERSA", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_LOTE_REVERSA", lote_reversa);
			
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public RecaudacionIsssteCargaArchivoOut lReversa() throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_ISSSTE_L_REVERSA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_RESERVA", void.class, ParameterMode.REF_CURSOR);
			
			
			RecaudacionIsssteCargaArchivoOut res = new RecaudacionIsssteCargaArchivoOut();
			
			List<RecaudacionIsssteCargaArchivo> lista = query.getResultList();
			res.setListaDatos(lista);
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
