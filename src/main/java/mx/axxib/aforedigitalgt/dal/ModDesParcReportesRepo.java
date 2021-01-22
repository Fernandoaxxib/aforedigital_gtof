package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

@Repository
public class ModDesParcReportesRepo extends RepoBase {


	public EjecucionResult procesarReporte(Integer p_OpcionReporte, Date pd_fechaInicial) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE).concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PROCESAR_REPORTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("in_OpcionReporte", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_FechaInicial", Date.class, ParameterMode.IN);					   	       		
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Avance", String.class, ParameterMode.OUT);		
					
			query.setParameter("in_OpcionReporte", p_OpcionReporte);		
			query.setParameter("id_FechaInicial", pd_fechaInicial);	
			
			EjecucionResult result= new EjecucionResult();
			
			result.setOcMensaje((String)query.getOutputParameterValue("oc_Mensaje"));
			result.setOcAvance((String)query.getOutputParameterValue("oc_Avance"));
			
			return result;		
		  }catch(Exception e) {
				 throw GenericException(e); 
		  }	
		}	
}
