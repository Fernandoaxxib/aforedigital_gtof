package mx.axxib.aforedigitalgt.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.TipoSubTransacOut;
import mx.axxib.aforedigitalgt.eml.TipoTransacOut;


	@Repository
	public class GraficasRepo extends RepoBase {

		
		@SuppressWarnings("unchecked")
		public List<TipoTransacOut> getTipoTransacciones(String vFECHA_I,String vFECHA_F) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".").concat(Constantes.APRO_SOLIC_PRC_TIPO_TRANSAC);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "TipoTransacOut");		

			query.registerStoredProcedureParameter("vFECHA_I", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("vFECHA_F", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);	
			
			query.setParameter("vFECHA_I", vFECHA_I);
			query.setParameter("vFECHA_F", vFECHA_F);
			
			List<TipoTransacOut> res = query.getResultList();
			return res;
		  }catch(Exception e) {
			 throw GenericException(e); 
		  }
		}

		@SuppressWarnings("unchecked")
		public List<TipoSubTransacOut> getTipoSubTransac(String vFECHA_I,String vFECHA_F, Integer vTIP_TRANSAC) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".").concat(Constantes.APRO_SOLIC_PRC_TIPO_SUBTRAN);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "TipoSubTransacOut");		

			query.registerStoredProcedureParameter("vFECHA_I", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("vFECHA_F", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("vTIP_TRANSAC", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);	
			
			query.setParameter("vFECHA_I", vFECHA_I);
			query.setParameter("vFECHA_F", vFECHA_F);
			query.setParameter("vTIP_TRANSAC", vTIP_TRANSAC);
			
			List<TipoSubTransacOut> res = query.getResultList();
			return res;
		  }catch(Exception e) {
			 throw GenericException(e); 
		  }
		}
}
