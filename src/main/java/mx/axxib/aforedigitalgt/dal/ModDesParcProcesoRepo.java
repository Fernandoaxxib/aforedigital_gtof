package mx.axxib.aforedigitalgt.dal;


import java.util.Date;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.DiagnosticoResult;
import mx.axxib.aforedigitalgt.eml.RegisSinSalarioOut;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

//***********************************************//
//** Funcionalidad: Repositorio - Desempleo parcialidades - Proceso masivo (primer pago)
//** Desarrollador: JJSC
//** Fecha de creación: 10/Dic/2020
//** Última modificación:
//***********************************************//
@Repository
@Transactional
public class ModDesParcProcesoRepo extends RepoBase {

	public DiagnosticoResult getRegistrosXProcesar(Date id_dFecha) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PROCESO_FEC_INI);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("id_dFecha", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("on_Parcialidades", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_UnaExhibicion", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_SinSalario", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Totales", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Pendiente", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("id_dFecha", id_dFecha);

			DiagnosticoResult result = new DiagnosticoResult();

			result.setParcialidades((Integer) query.getOutputParameterValue("on_Parcialidades"));
			result.setUnaExhibicion((Integer) query.getOutputParameterValue("on_UnaExhibicion"));
			result.setSinSalario((Integer) query.getOutputParameterValue("on_SinSalario"));
			result.setTotales((Integer) query.getOutputParameterValue("on_Totales"));
			result.setPendientes((Integer) query.getOutputParameterValue("on_Pendiente"));
			result.setOn_Estatus((Integer)query.getOutputParameterValue("on_Estatus"));
			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<RegisSinSalarioOut> getRegSinSalario() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_DET_SAL);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "RegisSinSalarioOut");
			
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);

			List<RegisSinSalarioOut> result = query.getResultList();

			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public EjecucionResult ejecutar(Date id_FechaCarga, Integer in_Opciones) throws AforeException {
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_EJECUTAR_PROCESO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("id_FechaCarga", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_Opciones", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Avance", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			

			query.setParameter("id_FechaCarga", id_FechaCarga);
			query.setParameter("in_Opciones", in_Opciones);

			EjecucionResult result = new EjecucionResult();

			result.setOcMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			result.setOcAvance((String) query.getOutputParameterValue("oc_Avance"));
            result.setOn_Estatus((Integer)query.getOutputParameterValue("on_Estatus"));
			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public Integer guardarDetSal(RegisSinSalarioOut registro) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARCF_GUARDAR_DET_SAL);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			
			query.registerStoredProcedureParameter("on_NumSolicitud", Integer.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("on_sbc_tipo_a", Double.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);		
			query.setParameter("on_NumSolicitud", registro.getOnNumSolicitud());			
			query.setParameter("on_sbc_tipo_a", registro.getOnSbcTipoA());	
			
			Integer result= (Integer) query.getOutputParameterValue("on_Estatus");

			return result;

		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
