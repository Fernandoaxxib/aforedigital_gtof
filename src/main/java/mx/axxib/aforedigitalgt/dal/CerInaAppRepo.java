package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ProcesResult;

//***********************************************//
//** Funcionalidad: Repositorio - Certificación de inactividad - AppMovil
//** Desarrollador: JJSC
//** Fecha de creación: 18/Ene/2021
//** Última modificación:
//***********************************************//
@Repository
@Transactional
public class CerInaAppRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public CerInaAppRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ProcesResult ejecutar(String p_Ruta,String p_Archivo)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_APPMOVIL_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);
			
			query.setParameter("p_Ruta", p_Ruta);
			query.setParameter("p_Archivo", p_Archivo);

			ProcesResult resp = new ProcesResult();
			resp.setP_Ruta((String) query.getOutputParameterValue("p_Ruta"));	
			resp.setP_Archivo((String) query.getOutputParameterValue("p_Archivo"));
			resp.setP_Message((String) query.getOutputParameterValue("oc_Mensaje"));
			resp.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			resp.setOc_Avance((String) query.getOutputParameterValue("p_message"));
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ProcesResult generarArchivo(Date p_fecha)throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_PACKAGE)
					.concat(".").concat(Constantes.PRO_CERT_INACTIVIDAD_APPMOVIL_BTN_GENARCH);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_fecha", Date.class, ParameterMode.IN);		
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_mesage", String.class, ParameterMode.OUT);
			
			query.setParameter("p_fecha", p_fecha);

			ProcesResult resp = new ProcesResult();
			resp.setP_Message((String) query.getOutputParameterValue("oc_Mensaje"));
			resp.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			resp.setOc_Avance((String) query.getOutputParameterValue("p_mesage"));			
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
