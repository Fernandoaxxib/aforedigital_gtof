package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.LoteOP85Out;
import mx.axxib.aforedigitalgt.eml.ProcesResult;

//***********************************************//
//** Funcionalidad: Repositorio - Retiros parciales IMSS OP85-OP86
//** Desarrollador: JJSC
//** Fecha de creación: 05/Ene/2021
//** Última modificación:
//***********************************************//
@Repository
@Transactional
public class RetParImssOP8586Repo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public RetParImssOP8586Repo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ProcesResult cargarArchivoOP85(String p_Path, String p_Nombre_Archivo) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE)
					.concat(".").concat(Constantes.RET_PARIMSS_OP84_GENERA_ARCH_OP85);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Path", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Nombre_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.setParameter("p_Path", p_Path);
			query.setParameter("p_Nombre_Archivo", p_Nombre_Archivo);

			ProcesResult resp = new ProcesResult();
			resp.setP_Message((String) query.getOutputParameterValue("oc_Mensaje"));		
		    resp.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public ProcesResult cargarArchivoOP86(String p_Path, String p_Nombre_Archivo) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE)
					.concat(".").concat(Constantes.RET_PARIMSS_OP84_CARGA_ARCH_OP86);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Path", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Nombre_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			
			
			query.setParameter("p_Path", p_Path);
			query.setParameter("p_Nombre_Archivo", p_Nombre_Archivo);

			ProcesResult resp =  new ProcesResult();
		    resp.setP_Message((String) query.getOutputParameterValue("oc_Mensaje"));			
		    resp.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
		    
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<LoteOP85Out> getLotesOP85() throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE).concat(".").concat(Constantes.RET_PARIMSS_OP84_BTN_LOTES_OP85);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteOP85Out");		

		query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);		
		List<LoteOP85Out> res = query.getResultList();
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	
	public ProcesResult generarReporteOP86(String p_Path,String p_Nombre_Archivo,String p_Lote,Date p_Fecha_Inicial,Date p_Fecha_Final) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE)
					.concat(".").concat(Constantes.RET_PARIMSS_OP84_GENEREA_REPOP86);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Path", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Nombre_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Fecha_Inicial", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Fecha_Final", Date.class, ParameterMode.IN);		
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.setParameter("p_Path", p_Path);
			query.setParameter("p_Nombre_Archivo", p_Nombre_Archivo);
			query.setParameter("p_Lote", p_Lote);
			query.setParameter("p_Fecha_Inicial", p_Fecha_Inicial);
			query.setParameter("p_Fecha_Final", p_Fecha_Final);
			ProcesResult resp = new ProcesResult();
		    resp.setP_Message((String) query.getOutputParameterValue("oc_Mensaje"));			
			resp.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
