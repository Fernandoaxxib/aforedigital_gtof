package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DiagnosticoResult;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.eml.RegSinSalarioOut;
import mx.axxib.aforedigitalgt.eml.ReporteResult;

@Repository
public class ModDesParcProcesoRepo extends RepoBase {
	
	private final EntityManager entityManager;

	@Autowired
	public ModDesParcProcesoRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public DiagnosticoResult getDiagnosticarSolic(Date pdFecha) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE).concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_SOL_PARC_EXH_SINSALARIO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pd_Fecha", Date.class, ParameterMode.IN);
					   	       		
			query.registerStoredProcedureParameter("pn_Parcialidades", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pn_UnaExhibicion", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pn_Sin_Salario", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pn_Totales", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pn_Pendiente", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_Avance", String.class, ParameterMode.OUT);											
					
			query.setParameter("pd_Fecha", pdFecha);		
			
			DiagnosticoResult result= new DiagnosticoResult();
			
			result.setParcialidades((Integer)query.getOutputParameterValue("pn_Parcialidades"));
			result.setUnaExhibicion((Integer)query.getOutputParameterValue("pn_UnaExhibicion"));
			result.setSinSalario((Integer)query.getOutputParameterValue("pn_Sin_Salario"));
			result.setTotales((Integer)query.getOutputParameterValue("pn_Totales"));
			result.setPendientes((Integer)query.getOutputParameterValue("pn_Pendiente"));
			result.setSinSalario((Integer)query.getOutputParameterValue("pc_Avance"));
			
			return result;		
		  }catch(Exception e) {
				 throw GenericException(e); 
		  }	
		}

	public RegSinSalarioOut getRegSinSalario(Date pdFechaCarga) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE).concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_MENSUALIDADES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pd_fecha_carga", Date.class, ParameterMode.IN);
					   	       		
			query.registerStoredProcedureParameter("pn_NumSOlicitud", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pn_Nss", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_Curp", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_PrimerApellido", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_SegundoApellido", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_Nombre", String.class, ParameterMode.OUT);											
					
			query.setParameter("pd_fecha_carga", pdFechaCarga);		
			
			RegSinSalarioOut result= new RegSinSalarioOut();
			
			//pendiente la salida, aun no está bien definida
			
			
			return result;		
		  }catch(Exception e) {
				 throw GenericException(e); 
		  }	
		}
	
	public RegSinSalarioOut ejecutar(Date pdFechaCarga, Integer opciones) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE).concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_EJECUTAR_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pd_fecha_carga", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pn_Opciones", Date.class, ParameterMode.IN);
					   	       		
			query.registerStoredProcedureParameter("pc_message", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_avance", Integer.class, ParameterMode.OUT);
														
					
			query.setParameter("pd_fecha_carga", pdFechaCarga);		
			query.setParameter("pd_fecha_carga", opciones);	
			
			RegSinSalarioOut result= new RegSinSalarioOut();
			
			//pendiente la salida, aún no está bien definida
			
			return result;		
		  }catch(Exception e) {
				 throw GenericException(e); 
		  }	
		}
	public ReporteResult procesarReporte(Integer p_OpcionReporte, Date pd_fechaInicial) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE).concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PROCESAR_REPORTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_OpcionReporte", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pd_fechaInicial", Date.class, ParameterMode.IN);
					   	       		
			query.registerStoredProcedureParameter("pc_message", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_avance", Integer.class, ParameterMode.OUT);
														
					
			query.setParameter("p_OpcionReporte", p_OpcionReporte);		
			query.setParameter("pd_fechaInicial", pd_fechaInicial);	
			
			ReporteResult result= new ReporteResult();
			
			result.setPcMessage((String)query.getOutputParameterValue("pc_message"));
			result.setPcAvance((String)query.getOutputParameterValue("pc_avance"));
			
			return result;		
		  }catch(Exception e) {
				 throw GenericException(e); 
		  }	
		}
	
	public ProcesResult procesarLayout(Integer pn_Opciones) throws AforeException {
		  try {	
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE).concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_LAYOUT_PROCESAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pn_Opciones", Integer.class, ParameterMode.IN);			
					   	       		
			query.registerStoredProcedureParameter("pd_fecha", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_Lote", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pc_Avance", String.class, ParameterMode.OUT);											
					
			query.setParameter("pn_Opciones", pn_Opciones);				
			
			ProcesResult result= new ProcesResult();
			
			result.setPdFecha((Date)query.getOutputParameterValue("pd_fecha"));
			result.setPcLote((String)query.getOutputParameterValue("pc_Lote"));
			result.setPcAvance((String)query.getOutputParameterValue("pc_avance"));
			
			return result;		
		  }catch(Exception e) {
				 throw GenericException(e); 
		  }	
		}
}
