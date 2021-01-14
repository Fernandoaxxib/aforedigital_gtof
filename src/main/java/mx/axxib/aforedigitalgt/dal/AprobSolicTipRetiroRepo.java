package mx.axxib.aforedigitalgt.dal;

import java.util.List;

import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.AprobarSolicResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;

@Repository
public class AprobSolicTipRetiroRepo extends RepoBase {
	
	
	@SuppressWarnings("unchecked")
	public List<SolicitudOut> getSolicitudes() throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_RECUP_SOLIC_PEND);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SolicitudOut");		

		query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);		
		List<SolicitudOut> res = query.getResultList();
		return res;
	  }catch(Exception e) {
		 throw GenericException(e); 
	  }
	}
	
    public AprobarSolicResult aprobarSolicitud(Integer inNoSolicitud,Integer inTipTransac,String icSubTipTransac) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_APROBAR_SELECCIONADOS);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("in_NoSolicitud", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("in_TipTransac", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("ic_SubTipTransac", String.class, ParameterMode.IN);		   	       		
		query.registerStoredProcedureParameter("oc_IndAccion", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("oc_GlobalError", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("oc_GlobalExito", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("oc_GlobalSistProc", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("oc_GlobalAbrevProc", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("oc_NombreAplicacion", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);						
			
				
		query.setParameter("in_NoSolicitud", inNoSolicitud).setParameter("in_TipTransac", inTipTransac).setParameter("ic_SubTipTransac", icSubTipTransac);		
		
		AprobarSolicResult result= new AprobarSolicResult();
		
		result.setOcIndAccion((String)query.getOutputParameterValue("oc_IndAccion"));
		result.setOcGlobalError((String)query.getOutputParameterValue("oc_GlobalError"));
		result.setOcGlobalExito((String)query.getOutputParameterValue("oc_GlobalExito"));
		result.setOcGlobalSistProc((String)query.getOutputParameterValue("oc_GlobalSistProc"));
		result.setOcGlobalAbrevProc((String)query.getOutputParameterValue("oc_GlobalAbrevProc"));
		result.setOcNombreAplicacion((String)query.getOutputParameterValue("oc_NombreAplicacion"));
		result.setOcMensaje((String)query.getOutputParameterValue("oc_Mensaje"));
		return result;		
	  }catch(Exception e) {
			 throw GenericException(e); 
	  }	
	}
	
	public Integer getIdProceso(Integer pnNoSolicitud ,Integer pTipTransac,String pSubTipTransac) throws AforeException {
	 try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_OBTIENE_ID_SESION);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("pnNoSolicitud", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pTipTransac", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pSubTipTransac", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pSesionid", Integer.class, ParameterMode.OUT);
				
		query.setParameter("pnNoSolicitud", pnNoSolicitud).setParameter("pTipTransac", pTipTransac).setParameter("pSubTipTransac", pSubTipTransac);
		Integer idProceso = (Integer) query.getOutputParameterValue("pSesionid");
		return idProceso;		
	 }catch(Exception e) {
		 throw GenericException(e); 
	 }	
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcesoOut> getProceso(Integer idSesion) throws AforeException {
	 try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_MONITOR_SESION);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ProcesoOut");

		query.registerStoredProcedureParameter("vSESION_ID", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
		
		query.setParameter("vSESION_ID", idSesion);
		List<ProcesoOut> res = query.getResultList();
		return res;
	 }catch(Exception e) {
		throw  GenericException(e);
	 }	
	}
}
