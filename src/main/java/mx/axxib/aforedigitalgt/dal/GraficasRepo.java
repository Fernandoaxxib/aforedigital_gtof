package mx.axxib.aforedigitalgt.dal;

import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DatosGraficasDetalleOut;
import mx.axxib.aforedigitalgt.eml.DatosGraficasTotalesOut;
import mx.axxib.aforedigitalgt.eml.GraficasDetalleOut;
import mx.axxib.aforedigitalgt.eml.GraficasTotalesOut;
import mx.axxib.aforedigitalgt.eml.TipoSubTransacOut;
import mx.axxib.aforedigitalgt.eml.TipoTransacOut;


	@Repository
	@Transactional
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
		@SuppressWarnings("unchecked")
		public DatosGraficasTotalesOut getDatosTotales() throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PARCIALIDADES_PACKAGE).concat(".").concat(Constantes.OBTENER_DATOS_GRAFICAS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "GraficasTotalesOut");

			query.registerStoredProcedureParameter("P_PERIODO_FEC", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_TOTALES", void.class, ParameterMode.REF_CURSOR);				
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);			
				
			DatosGraficasTotalesOut resp= new DatosGraficasTotalesOut();
			List<GraficasTotalesOut> output= query.getResultList();	
			resp.setP_PERIODO_FEC((String) query.getOutputParameterValue("P_PERIODO_FEC"));
			resp.setGraficasTotales(output);
			resp.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
			resp.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
			
			return resp;
		  }catch(Exception e) {
			 throw GenericException(e); 
		  }
		}
		
		@SuppressWarnings("unchecked")
		public DatosGraficasDetalleOut getDetalleGraficas() throws AforeException {
			try {	
				String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PARCIALIDADES_PACKAGE).concat(".").concat(Constantes.OBTENER_DETALLE_GRAFICAS);
				StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"GraficasDetalleOut");		

				query.registerStoredProcedureParameter("CP_DETALLE", void.class, ParameterMode.REF_CURSOR);				
				query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
				query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);								
				DatosGraficasDetalleOut resp= new DatosGraficasDetalleOut();
				List<GraficasDetalleOut> output= query.getResultList();					
				resp.setGraficasDetalle(output);
				resp.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
				resp.setP_ESTATUS((Integer) query.getOutputParameterValue("P_ESTATUS"));
				
				return resp;
			  }catch(Exception e) {
				 throw GenericException(e); 
			  }
		}		
}