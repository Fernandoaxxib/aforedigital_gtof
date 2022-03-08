package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.RecaudacionOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Recaudación
//** Interventor Principal: JJSC
//** Fecha Creación: 07/03/2022
//** Última Modificación:
//***********************************************//
@Repository
@Transactional
public class RecaudacionRepo extends RepoBase {

	public RecaudacionOut cargar(String ic_archivo_sal) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.RECAUDACION_BTN_CARGAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_archivo_sal", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_archivo_sal", ic_archivo_sal);

			RecaudacionOut res = new RecaudacionOut();

			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public RecaudacionOut getListaCarga() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.RECAUDACION_LISTA_CARGA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteDesmarcar");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaudacionOut res = new RecaudacionOut();

			res.setListDesmarcar(query.getResultList());
			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RecaudacionOut aplicar(String ic_id_lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.RECAUDACION_BTN_APLICAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_id_lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_id_lote", ic_id_lote);

			RecaudacionOut res = new RecaudacionOut();

			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public RecaudacionOut getListaLote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.RECAUDACION_LISTA_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "Recaudacion");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaudacionOut res = new RecaudacionOut();

			res.setListRecaudacion(query.getResultList());
			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RecaudacionOut comprar(String ic_id_operacion, Integer in_TOT_COMPRAR, Integer on_partic_comp,
			String ic_id_lote, Date ic_FECHA_43BIS) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.RECAUDACION_BTN_COMPRAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_id_operacion", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_TOT_COMPRAR", Integer.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("on_partic_comp", Integer.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("ic_id_lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_FECHA_43BIS", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_id_lote", ic_id_lote);

			RecaudacionOut res = new RecaudacionOut();

			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
