package mx.axxib.aforedigitalgt.reca.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.DatosHistoricoOut;
import mx.axxib.aforedigitalgt.reca.eml.DatosPunteoOut;
import mx.axxib.aforedigitalgt.reca.eml.InfoPunteoOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Aclaraciones Especiales
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
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

			query.registerStoredProcedureParameter("ic_nss_afil", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("ic_rfc_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_nombre_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_cod_estado_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_tip_solicitud_afil", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("ic_nss_afil", ic_Nss);

			InfoPunteoOut res = new InfoPunteoOut();

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
	public InfoPunteoOut llenarDatosPunteo(String P_NSS, String P_RFC, String P_NOMBRE) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_LLENA_DATOS_PUNTEO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DatosPunteoOut");

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_RFC", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_Aceptado", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_Restantes", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("P_NSS", P_NSS);
			query.setParameter("P_RFC", P_RFC);
			query.setParameter("P_NOMBRE", "");

			InfoPunteoOut res = new InfoPunteoOut();

			res.setIc_Nss((String) query.getOutputParameterValue("P_NSS"));
			res.setIc_rfc_afil((String) query.getOutputParameterValue("P_RFC"));
			res.setIc_nombre_afil((String) query.getOutputParameterValue("P_NOMBRE"));
			res.setIc_Aceptado((String) query.getOutputParameterValue("P_Aceptado"));
			res.setIc_Restantes((String) query.getOutputParameterValue("P_Restantes"));
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));

			if (res.getOn_Estatus() == 1) {
				res.setDatosPunteo(query.getResultList());
			} else {
				List<DatosPunteoOut> datosPunteo = new ArrayList<>();
				res.setDatosPunteo(datosPunteo);
			}

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public InfoPunteoOut actualizarPunteo(String ic_Nss, String ic_Aceptado) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PUNTEO_BTN_ACTUALIZAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_Nss", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Aceptado", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("ic_Nss", ic_Nss);
			query.setParameter("ic_Aceptado", ic_Aceptado);

			InfoPunteoOut res = new InfoPunteoOut();

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

			query.registerStoredProcedureParameter("ic_Nss_Afilh", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("ic_Rfc_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Nombre_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Cod_Estado_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("ic_Tip_Solicitud_Afilh", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("ic_Nss_Afilh", ic_Nss);

			InfoPunteoOut res = new InfoPunteoOut();

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

	@SuppressWarnings("unchecked")
	public InfoPunteoOut llenarDatosHistorico(String P_NSS, String P_RFC, String P_NOMBRE) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_LLENA_DATOS_HIST);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DatosHistoricoOut");

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_RFC", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("P_NSS", P_NSS);
			query.setParameter("P_RFC", P_RFC);
			query.setParameter("P_NOMBRE", "");

			InfoPunteoOut res = new InfoPunteoOut();

			res.setIc_Nss((String) query.getOutputParameterValue("P_NSS"));
			res.setIc_rfc_afil((String) query.getOutputParameterValue("P_RFC"));
			res.setIc_nombre_afil((String) query.getOutputParameterValue("P_NOMBRE"));
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			if (res.getOn_Estatus() == 1) {
				res.setDatosHistorico(query.getResultList());
			} else {
				List<DatosHistoricoOut> datosHistorico = new ArrayList<>();
				res.setDatosHistorico(datosHistorico);
			}

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public InfoPunteoOut actualizarHistorico(String ic_Nss, String ic_Aceptado) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_PACKAGE).concat(".")
					.concat(Constantes.ACLARACIONES_ESPECIALES_HISTORICO_BTN_ACTUALIZAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_Nss", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Aceptado", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("ic_Nss", ic_Nss);
			query.setParameter("ic_Aceptado", ic_Aceptado);

			InfoPunteoOut res = new InfoPunteoOut();

			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}