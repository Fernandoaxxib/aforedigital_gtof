package mx.axxib.aforedigitalgt.dal;

import java.util.Date;


import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DatosIniResult;

@Repository
public class ModPagosRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public ModPagosRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	public DatosIniResult getInicioModPagos() throws AforeException{
		
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.MOD_PAGOS_INICIO_MODULO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pFECHA_PROCESO", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pFECHA_RETIRO", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pMensaje", String.class, ParameterMode.OUT);

			DatosIniResult datosOut = new DatosIniResult();
			datosOut.setPfechaproceso((Date) query.getOutputParameterValue("pFECHA_PROCESO"));
			datosOut.setPfecharetiro((Date) query.getOutputParameterValue("pFECHA_RETIRO"));
			datosOut.setPmensaje((String) query.getOutputParameterValue("pMensaje"));
			return datosOut;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	
	public String getRefresh(Date fechaproceso,Date fecharetiro,String titlewin ) throws AforeException{
		
		try {
		String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_PAGOS_PACKAGE)
				.concat(".").concat(Constantes.MOD_PAGOS_REFRESH_PAGO);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("pd_fechaproceso", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pd_FechaRetiro", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pc_TitleWin", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pmensaje", String.class, ParameterMode.OUT);
		
		query.setParameter("pd_fechaproceso", fechaproceso);
		query.setParameter("pd_FechaRetiro", fecharetiro);
		query.setParameter("pc_TitleWin", titlewin);
		
		String result =(String) query.getOutputParameterValue("pmensaje");		
		return result;
	 }catch (Exception e) {
			throw GenericException(e);
	 }
	}
	
	public String generaFondos(Date fechaproceso,String procesosRetiro,String tiposPagos,String tipoFondos,String instituto,String titleWin,String globalCliente ) throws AforeException{
	  try {
		String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_PAGOS_PACKAGE)
				.concat(".").concat(Constantes.MOD_PAGOS_INTERFACE_FONDOS);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("pd_FechaProceso", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Procesos_Retiro", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Tipos_Pagos", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Tipo_Fondos", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Instituto", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pc_TitleWin", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pg_GlobalCliente", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pg_GlobalCliente", String.class, ParameterMode.OUT);
		
		query.setParameter("pd_FechaProceso", fechaproceso);
		query.setParameter("p_Procesos_Retiro", procesosRetiro);
		query.setParameter("p_Tipos_Pagos", tiposPagos);
		query.setParameter("p_Tipo_Fondos", tipoFondos);
		query.setParameter("p_Instituto", instituto);
		query.setParameter("pc_TitleWin", titleWin);
		query.setParameter("pg_GlobalCliente", globalCliente);
		
		
		String result =(String) query.getOutputParameterValue("pg_GlobalCliente");		
		return result;
	  }catch (Exception e) {
			throw GenericException(e);
	  }
	}
	
	public String generaPagos(Date fechaRetiro,String procesoRetiro,String instituto,String tiposPagos,String titleWin,String globalRetiro ) throws AforeException{
	  try{	
		String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_PAGOS_PACKAGE)
				.concat(".").concat(Constantes.MOD_PAGOS_INTERFACE_FONDOS);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("pd_fechaRetiro", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pc_ProcesoRetiro", String.class, ParameterMode.IN);		
		query.registerStoredProcedureParameter("p_Instituto", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Tipos_Pagos", String.class, ParameterMode.IN);				
		query.registerStoredProcedureParameter("pc_TitleWin", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pg_GlobalRetiro", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("pmensaje", String.class, ParameterMode.OUT);
		
		query.setParameter("pd_fechaRetiro", fechaRetiro);
		query.setParameter("pc_ProcesoRetiro", procesoRetiro);
		query.setParameter("p_Instituto", instituto);
		query.setParameter("p_Tipos_Pagos", tiposPagos);
		query.setParameter("pc_TitleWin", titleWin);
		query.setParameter("pg_GlobalRetiro", globalRetiro);
		
		
		String result =(String) query.getOutputParameterValue("pmensaje");		
		return result;
	  }catch (Exception e) {
	  	throw GenericException(e);
	  }  
	}
}
