package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

@Repository
public class ModPagosRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public ModPagosRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public String getRefresh(String ic_BotonContinuar,Date id_Fecha_Proceso,Date id_Fecha_Retiro) throws AforeException {

		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.MOD_PAGOS_BOTON_REFRESH);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_BotonContinuar", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_Fecha_Proceso", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_Fecha_Retiro", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_BotonContinuar", ic_BotonContinuar);
			query.setParameter("id_Fecha_Proceso", id_Fecha_Proceso);
			query.setParameter("id_Fecha_Retiro", id_Fecha_Retiro);

			String result = (String) query.getOutputParameterValue("oc_Mensaje");
			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public EjecucionResult generaFondos(Date id_FechaProceso,String ic_ProcesosRetiro,String ic_TiposPagos ,String ic_TipoFondos ,String ic_Instituto) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.MOD_PAGOS_INTERFACE_FONDOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("id_FechaProceso", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_ProcesosRetiro", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_TiposPagos", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_TipoFondos", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Instituto", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Msg", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("id_FechaProceso", id_FechaProceso);
			query.setParameter("ic_ProcesosRetiro", ic_ProcesosRetiro);
			query.setParameter("ic_TiposPagos", ic_TiposPagos);
			query.setParameter("ic_TipoFondos", ic_TipoFondos);
			query.setParameter("ic_Instituto", ic_Instituto);
			
			EjecucionResult result= new EjecucionResult();
			
			result.setOcMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			result.setOcAvance((String) query.getOutputParameterValue("oc_Msg"));
		
			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public void generaPagos(Date id_FechaRetiro,String ic_ProcesoRetiro,String ic_Instituto,String ic_TiposPagos) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_PAGOS_PACKAGE)
					.concat(".").concat(Constantes.MOD_PAGOS_INTERFACE_PAGOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("id_FechaRetiro", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_ProcesoRetiro", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Instituto", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_TiposPagos", String.class, ParameterMode.IN);
			

			query.setParameter("id_FechaRetiro", id_FechaRetiro);
			query.setParameter("ic_ProcesoRetiro", ic_ProcesoRetiro);
			query.setParameter("ic_Instituto", ic_Instituto);
			query.setParameter("ic_TiposPagos", ic_TiposPagos);
						
			
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
