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
import mx.axxib.aforedigitalgt.eml.LoteOP84Out;
import mx.axxib.aforedigitalgt.eml.RegOP84Out;

@Repository
public class RetParImssOP84Repo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public RetParImssOP84Repo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public String cargarArchivoOP84(String p_Path, String p_Nombre_Archivo) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE)
					.concat(".").concat(Constantes.RET_PARIMSS_OP84_CARGA_ARCH_OP84);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Path", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Nombre_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);

			query.setParameter("p_Path", p_Path);
			query.setParameter("p_Nombre_Archivo", p_Nombre_Archivo);

			String resp = (String) query.getOutputParameterValue("p_Message");
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<LoteOP84Out> getLotesOP84() throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE).concat(".").concat(Constantes.RET_PARIMSS_OP84_BTN_LOTES_OP84);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteOP84Out");		

		query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);		
		List<LoteOP84Out> res = query.getResultList();
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	
	@SuppressWarnings("unchecked")
	public List<RegOP84Out> getConsultaOP84(Date p_FechaInicial,Date p_FechaFinal,String p_Lote) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE).concat(".").concat(Constantes.RET_PARIMSS_OP84_CONSULTA_OP84);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "RegOP84Out");		

		query.registerStoredProcedureParameter("p_FechaInicial", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_FechaFinal", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Lote", String.class, ParameterMode.IN);		
		query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);	
		query.registerStoredProcedureParameter("p_Path", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
		
		query.setParameter("p_FechaInicial", p_FechaInicial);
		query.setParameter("p_FechaFinal", p_FechaFinal);
		query.setParameter("p_Lote", p_Lote);
		
		List<RegOP84Out> res = query.getResultList();
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	public String generarReporteOP84(String p_Path,String p_Nombre_Archivo,String p_Lote,Date p_Fecha_Inicial,Date p_Fecha_Final,String p_Bloque) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RET_PARIMSS_OP84_PACKAGE)
					.concat(".").concat(Constantes.RET_PARIMSS_OP84_GENEREA_REPOP84);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Path", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Nombre_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Fecha_Inicial", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Fecha_Final", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Bloque", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);

			query.setParameter("p_Path", p_Path);
			query.setParameter("p_Nombre_Archivo", p_Nombre_Archivo);
			query.setParameter("p_Lote", p_Lote);
			query.setParameter("p_Fecha_Inicial", p_Fecha_Inicial);
			query.setParameter("p_Fecha_Final", p_Fecha_Final);
			query.setParameter("p_Bloque", p_Bloque);

			String resp = (String) query.getOutputParameterValue("p_Message");
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
