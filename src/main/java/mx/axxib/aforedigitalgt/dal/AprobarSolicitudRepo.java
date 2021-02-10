package mx.axxib.aforedigitalgt.dal;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.AprobarSolicResult;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;

@Repository
public class AprobarSolicitudRepo extends RepoBase implements Runnable {
	
		private  List<SolicitudOut> selectedSolicitud;
		
		public AprobarSolicitudRepo (List<SolicitudOut> selectedSolicitud){
			this.selectedSolicitud=selectedSolicitud;
		}
		
		
		 public AprobarSolicResult aprobarSolicitud(Integer inNoSolicitud,Integer inTipTransac,String icSubTipTransac) throws AforeException {
			  try {	
				String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.APRO_SOLIC_TIPO_RETIRO_PACKAGE).concat(".")
						.concat(Constantes.APRO_SOLIC_TIPO_RETIRO_APROBAR_SELECCIONADOS);
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

		@Override
		public void run() {
			
				 
		          selectedSolicitud.forEach(p->{
		        	try {
		        		 //AprobarSolicResult res=aprobarSolicitud(p.getNumSolicitud(), Integer.valueOf(p.getTransaccion().substring(0, 1)), p.getSubTransaccion().substring(0,1));
						Thread.sleep(300);
		        		System.out.println("num solicitud-->"+p.getNumSolicitud());
		        	} catch (Exception e) {
						GenericException(e);
					}        	
		        });    
			System.out.println("fin del hilo hijo");
		}

	}
