package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.InfoPunteoOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Aclaraciones Especiales
//** Interventor Principal: JJSC
//** Fecha Creación: 17/NOV/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class AclaracionesEspecialesRepo extends RepoBase {

	public RespuestaOut cargarArchivo(String ic_Archivo, String ic_Ruta) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_BTN_CARGAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Ruta", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("ic_Archivo", ic_Archivo);
			query.setParameter("ic_Ruta", ic_Ruta);

			RespuestaOut res = new RespuestaOut();
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut generarPunteoAutomatico() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_BTN_PUNTEO_AUT);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			RespuestaOut res = new RespuestaOut();
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public InfoPunteoOut cargarInfoPunteo(String ic_Nss) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_CARGA_INFO_PUNTEO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Nss", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("ic_Aceptado", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Restantes", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Casos", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_nss_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_rfc_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_nombre_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_cod_estado_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_tip_solicitud_afil", String.class, ParameterMode.OUT);			
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
						
			query.setParameter("ic_Nss", ic_Nss);

			InfoPunteoOut res = new InfoPunteoOut();
			
			res.setIc_Aceptado((String) query.getOutputParameterValue("ic_Aceptado"));
			res.setIc_Restantes((String) query.getOutputParameterValue("ic_Restantes"));
			res.setIc_Casos((String) query.getOutputParameterValue("ic_Casos"));
			res.setIc_nss_afil((String) query.getOutputParameterValue("ic_nss_afil"));
			res.setIc_rfc_afil((String) query.getOutputParameterValue("ic_rfc_afil"));
			res.setIc_nombre_afil((String) query.getOutputParameterValue("ic_nombre_afil"));
			res.setIc_cod_estado_afil((String) query.getOutputParameterValue("ic_cod_estado_afil"));
			res.setIc_tip_solicitud_afil((String) query.getOutputParameterValue("ic_tip_solicitud_afil"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));			
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public InfoPunteoOut llenarDatosPunteo(String ic_Nss) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_LLENA_DATOS_PUNTEO);			
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DatosPunteoOut");
			
			query.registerStoredProcedureParameter("ic_Nss", String.class, ParameterMode.IN);		
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			
			InfoPunteoOut res = new InfoPunteoOut();
			
			res.setDatosPunteo(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setOc_Mensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut actualizarPunteo() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PUNTEO_BTN_ACTUALIZAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_Nss", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Aceptado", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			RespuestaOut res = new RespuestaOut();
			
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public InfoPunteoOut cargarInfoHist(String ic_Nss) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_CARGA_INFO_HIST);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_Nss", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("ic_Nss_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Rfc_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Nombre_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Cod_Estado_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Tip_Solicitud_Afilh", String.class, ParameterMode.OUT);
						
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("ic_Nss", ic_Nss);
			
			InfoPunteoOut res = new InfoPunteoOut();
			
			res.setIc_Nss((String) query.getOutputParameterValue("ic_Nss"));
			res.setIc_nss_afil((String) query.getOutputParameterValue("ic_Nss_Afilh"));
			res.setIc_rfc_afil((String) query.getOutputParameterValue("ic_Rfc_Afilh"));
			res.setIc_nombre_afil((String) query.getOutputParameterValue("ic_Nombre_Afilh"));
			res.setIc_cod_estado_afil((String) query.getOutputParameterValue("ic_Cod_Estado_Afilh"));
			res.setIc_tip_solicitud_afil((String) query.getOutputParameterValue("ic_Tip_Solicitud_Afilh"));
			
			
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}