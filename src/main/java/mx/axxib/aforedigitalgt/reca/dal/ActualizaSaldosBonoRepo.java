package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;

import mx.axxib.aforedigitalgt.reca.eml.RecaIssLoteOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaIssSieforeOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Actualiza Saldos y Bono de Pensión
//** Interventor Principal: JJSC
//** Fecha Creación: 18/NOV/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class ActualizaSaldosBonoRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public RecaIssLoteOut getIssLote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE).concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_RECA_GET_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteIssOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaIssLoteOut res = new RecaIssLoteOut();
			res.setLotes(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public RecaIssSieforeOut getIssSiefore() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE).concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_RECA_GET_SIEFORE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "IssSieforeOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaIssSieforeOut res = new RecaIssSieforeOut();
			res.setListaIssSiefore(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RespuestaOut ejecutarRecaudacion() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_RECA_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Lote1", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_Fecha_Aplicado", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Cod_Inversion", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_CodEmpresa", String.class, ParameterMode.IN);						
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			RespuestaOut res = new RespuestaOut();
		
			res.setOn_Estatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setOc_Mensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public RecaIssLoteOut getBonoLote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE).concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_BONO_GET_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteIssOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaIssLoteOut res = new RecaIssLoteOut();
			res.setLotes(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public RecaIssSieforeOut getBonoSiefore() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE).concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_BONO_GET_SIEFORE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "IssSieforeOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaIssSieforeOut res = new RecaIssSieforeOut();
			res.setListaIssSiefore(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut ejecutarBono() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_BONO_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Lote1", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("id_Fecha_Aplicado1", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Cod_Inversion1", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_CodEmpresa", String.class, ParameterMode.IN);						
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			RespuestaOut res = new RespuestaOut();
		
			res.setOn_Estatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setOc_Mensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public RecaIssLoteOut getSeparacionLote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE).concat(".")
					.concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_SEPA_GET_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteIssOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaIssLoteOut res = new RecaIssLoteOut();
			res.setLotes(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut ejecutarSeparacion() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_SEPA_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Lote", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("id_Fecha_Aplicado", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Cod_Inversion", String.class, ParameterMode.IN);									
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			RespuestaOut res = new RespuestaOut();
		
			res.setOn_Estatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setOc_Mensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
