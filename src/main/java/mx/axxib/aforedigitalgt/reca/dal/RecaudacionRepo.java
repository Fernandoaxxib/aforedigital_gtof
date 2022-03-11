package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionOut;

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

			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			res.setListRecaudacion(query.getResultList());
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public RecaudacionOut actualizarSaldos(String ic_id_operacion, Date id_fecha_transf,Integer in_secuencia_lote, BigDecimal in_TOT_COMPRAR, Date ic_FECHA_43BIS) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.RECAUDACION_BTN_ACTUALIZAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_id_operacion", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_fecha_transf", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_secuencia_lote", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_TOT_COMPRAR", BigDecimal.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("in_PART_COMP", Integer.class, ParameterMode.OUT);			
			query.registerStoredProcedureParameter("ic_FECHA_43BIS", Date.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_id_operacion", ic_id_operacion);
			query.setParameter("id_fecha_transf", id_fecha_transf);
			query.setParameter("in_secuencia_lote", in_secuencia_lote);
			query.setParameter("in_TOT_COMPRAR", in_TOT_COMPRAR);
			query.setParameter("ic_FECHA_43BIS", ic_FECHA_43BIS);

			RecaudacionOut res = new RecaudacionOut();
		    
			res.setTotComprar((BigDecimal) query.getOutputParameterValue("in_TOT_COMPRAR"));
			res.setPartComprar((Integer) query.getOutputParameterValue("in_PART_COMP"));
			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public RecaudacionOut obtenerMontoTotal(String ic_id_operacion, Date id_fecha_transf,Integer in_secuencia_lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECAUDACION_PACKAGE)
					.concat(".").concat(Constantes.RECAUDACION_OBTIENE_TOTAL);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_id_operacion", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_fecha_transf", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_secuencia_lote", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_TOT_COMP", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("in_PART_COMP", Integer.class, ParameterMode.OUT);				
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_id_operacion", ic_id_operacion);
			query.setParameter("id_fecha_transf", id_fecha_transf);
			query.setParameter("in_secuencia_lote", in_secuencia_lote);
			
			RecaudacionOut res = new RecaudacionOut();
		    
			res.setTotComprar((BigDecimal) query.getOutputParameterValue("in_TOT_COMP"));
			res.setPartComprar((Integer) query.getOutputParameterValue("in_PART_COMP"));
			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
