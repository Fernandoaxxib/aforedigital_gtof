package mx.axxib.aforedigitalgt.dal;

import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.AprobarSolicResult;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;
//***********************************************//
//** Funcionalidad: Repositorio - Aprobación de solicitudes por tipo de retiro
//** Desarrollador: JJSC
//** Fecha de creación: 19/Nov/2020
//** Última modificación:
//***********************************************//

@Repository
@Transactional
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
	
    @SuppressWarnings("unchecked")
	public AprobarSolicResult aprobarSolicitud(Integer inNoSolicitud,Integer inTipTransac,String icSubTipTransac) throws AforeException {
	  try {	
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".")
				.concat(Constantes.APRO_SOLIC_TIPO_RETIRO_APROBAR_SELECCIONADOS);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ProcesMonitorOut");

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
		query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);	
		
							
		query.setParameter("in_NoSolicitud", inNoSolicitud).setParameter("in_TipTransac", inTipTransac).setParameter("ic_SubTipTransac", icSubTipTransac);		
		
		AprobarSolicResult result= new AprobarSolicResult();
		
		result.setOcIndAccion((String)query.getOutputParameterValue("oc_IndAccion"));
		result.setOcGlobalError((String)query.getOutputParameterValue("oc_GlobalError"));
		result.setOcGlobalExito((String)query.getOutputParameterValue("oc_GlobalExito"));
		result.setOcGlobalSistProc((String)query.getOutputParameterValue("oc_GlobalSistProc"));
		result.setOcGlobalAbrevProc((String)query.getOutputParameterValue("oc_GlobalAbrevProc"));
		result.setOcNombreAplicacion((String)query.getOutputParameterValue("oc_NombreAplicacion"));
		result.setOcMensaje((String)query.getOutputParameterValue("oc_Mensaje"));
		result.setOn_estatus((Integer)query.getOutputParameterValue("on_Estatus"));		
		return result;		
	  }catch(Exception e) {
			 throw GenericException(e); 
	  }	
	}
	
}
