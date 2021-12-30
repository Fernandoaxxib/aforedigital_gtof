package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarLoteOp71Out;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Liquidar LoteOP71
//** Interventor Principal: JJSC
//** Fecha Creación: 29/Dic/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class LiquidarLoteOp71Repo extends RepoBase {

	@SuppressWarnings("unchecked")
	public LiquidarLoteOp71Out getLote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.LIQUIDAR_LOTEOP71_PACKAGE)
					.concat(".").concat(Constantes.LIQUIDAR_LOTEOP71_GET_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteOp71Out");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			LiquidarLoteOp71Out res = new LiquidarLoteOp71Out();
			res.setListaLotes(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public LiquidarLoteOp71Out getSiefore() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.LIQUIDAR_LOTEOP71_PACKAGE)
					.concat(".").concat(Constantes.LIQUIDAR_LOTEOP71_GET_SIEFORE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SieforeOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			LiquidarLoteOp71Out res = new LiquidarLoteOp71Out();
			res.setListaSiefore(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public LiquidarLoteOp71Out getSectores() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.LIQUIDAR_LOTEOP71_PACKAGE)
					.concat(".").concat(Constantes.LIQUIDAR_LOTEOP71_GET_SECTORES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SectorOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			LiquidarLoteOp71Out res = new LiquidarLoteOp71Out();
			res.setListaSectores(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public LiquidarLoteOp71Out generarInterface(String ic_identif_operacion, String ic_fecha_transferencia,
			String ic_secuencia_lote, String id_fec_aplicado, String ic_descripcion1, String ic_cod_inversion)
			throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_LOTEOP71_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_LOTEOP71_BTN_GENERAR_INTERFACE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_identif_operacion", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_fecha_transferencia", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_secuencia_lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_fec_aplicado", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_descripcion1", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_cod_inversion", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_identif_operacion", ic_identif_operacion);
			query.setParameter("ic_fecha_transferencia", ic_fecha_transferencia);
			query.setParameter("ic_secuencia_lote", ic_secuencia_lote);
			query.setParameter("id_fec_aplicado", id_fec_aplicado);
			query.setParameter("ic_descripcion1", ic_descripcion1);
			query.setParameter("ic_cod_inversion", ic_cod_inversion);

			LiquidarLoteOp71Out res = new LiquidarLoteOp71Out();
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public LiquidarLoteOp71Out liquidar(String ic_identif_operacion,
            String ic_fecha_transferencia,String ic_secuencia_lote,
            Double in_monto_liquidado,
            Double in_importes_aceptados,
            String ic_siefore,
            String ic_agrupacion)
			throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_LOTEOP71_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_LOTEOP71_BTN_LIQUIDAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_identif_operacion", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_fecha_transferencia", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_secuencia_lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_monto_liquidado", Double.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_importes_aceptados", Double.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_siefore", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_agrupacion", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_identif_operacion", ic_identif_operacion);
			query.setParameter("ic_fecha_transferencia", ic_fecha_transferencia);
			query.setParameter("ic_secuencia_lote", ic_secuencia_lote);
			query.setParameter("in_monto_liquidado", in_monto_liquidado);
			query.setParameter("in_importes_aceptados", in_importes_aceptados);
			query.setParameter("ic_siefore", ic_siefore);
			query.setParameter("ic_agrupacion", ic_agrupacion);

			LiquidarLoteOp71Out res = new LiquidarLoteOp71Out();
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
