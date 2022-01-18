package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.MontosBonoOut;
import mx.axxib.aforedigitalgt.reca.eml.MontosRecaOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaIssLoteOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaIssSieforeOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Actualiza Saldos y Bono de Pensión
//** Interventor Principal: JJSC
//** Fecha Creación: 10/01/2022
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

	public RespuestaOut ejecutarRecaudacion(String ic_Lote,String id_Fecha_Aplicado,String ic_Cod_Inversion,String ic_CodEmpresa) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_RECA_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Lote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Lote1", String.class, ParameterMode.IN);	
			query.registerStoredProcedureParameter("ic_Fecha_Aplicado", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Cod_Inversion", String.class, ParameterMode.IN);			
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("ic_Lote", ic_Lote);
			query.setParameter("ic_Lote1", "1");
			query.setParameter("ic_Fecha_Aplicado", id_Fecha_Aplicado);
			query.setParameter("ic_Cod_Inversion", ic_Cod_Inversion);			
			
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
	
	public RespuestaOut ejecutarBono(String ic_Lote1, String ic_Fecha_Aplicado1, String ic_Cod_Inversion1) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_BONO_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Lote1", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("ic_Fecha_Aplicado1", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Cod_Inversion1", String.class, ParameterMode.IN);
								
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("ic_Lote1", ic_Lote1);
			query.setParameter("ic_Fecha_Aplicado1", ic_Fecha_Aplicado1);
			query.setParameter("ic_Cod_Inversion1", ic_Cod_Inversion1);
			
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
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteSepaOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RecaIssLoteOut res = new RecaIssLoteOut();
			res.setLotesSepa(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut ejecutarSeparacion(String ic_Lote,String ic_Fecha_Aplicado,String ic_Cod_Inversion ) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_SEPA_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Lote", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("ic_Fecha_Aplicado", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Cod_Inversion", String.class, ParameterMode.IN);									
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("ic_Lote", ic_Lote);
			query.setParameter("ic_Fecha_Aplicado", ic_Fecha_Aplicado);
			query.setParameter("ic_Cod_Inversion", ic_Cod_Inversion);
			
			RespuestaOut res = new RespuestaOut();
		
			res.setOn_Estatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setOc_Mensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
		
	public RespuestaOut getMontosRecaIsss(String ic_lote, Integer ic_Cod_Inversion) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_RECA_LLENA_MONTOS_ISSS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_lote", String.class, ParameterMode.IN);						
			query.registerStoredProcedureParameter("in_cod_Inversion", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_monto_pendiente", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivs", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_monto_pendiente08", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivs08", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_monto_pendiente_C", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivs_C", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_monto_pendiente08_C", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivs08_C", Integer.class, ParameterMode.OUT);		
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("ic_lote", ic_lote);
			query.setParameter("in_cod_Inversion", ic_Cod_Inversion);
			
			RespuestaOut res = new RespuestaOut();
			MontosRecaOut montos= new MontosRecaOut();
			montos.setOn_monto_pendiente((Integer) query.getOutputParameterValue("on_monto_pendiente"));
			montos.setOn_aivs((Integer) query.getOutputParameterValue("on_aivs"));
			montos.setOn_monto_pendiente08((Integer) query.getOutputParameterValue("on_monto_pendiente08"));
			montos.setOn_aivs08((Integer) query.getOutputParameterValue("on_aivs08"));
			montos.setOn_monto_pendiente_C((Integer) query.getOutputParameterValue("on_monto_pendiente_C"));
			montos.setOn_aivs_C((Integer) query.getOutputParameterValue("on_aivs_C"));
			montos.setOn_monto_pendiente08_C((Integer) query.getOutputParameterValue("on_monto_pendiente08_C"));
			montos.setOn_aivs08_C((Integer) query.getOutputParameterValue("on_aivs08_C"));						
			
			res.setMontosRecaIsss(montos);
			res.setOn_Estatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setOc_Mensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut getMontosBonoPen(String ic_lote1, Integer in_cod_inversion1) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_BONO_LLENA_MONTOS_BONO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_lote1", String.class, ParameterMode.IN);						
			query.registerStoredProcedureParameter("in_cod_inversion1", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_udis", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_monto_udis", Integer.class, ParameterMode.OUT);
				
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("ic_lote1", ic_lote1);
			query.setParameter("in_cod_inversion1", in_cod_inversion1);
			
			RespuestaOut res = new RespuestaOut();
			MontosBonoOut montos= new MontosBonoOut();
			
			montos.setOn_udis((Integer) query.getOutputParameterValue("on_udis"));
			montos.setOn_monto_udis((Integer) query.getOutputParameterValue("on_monto_udis"));							
			
			res.setMontosBono(montos);
			res.setOn_Estatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setOc_Mensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaOut getMontosSepa(String ic_lotes, Integer in_cod_inversions) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_PACKAGE)
					.concat(".").concat(Constantes.ACTUALIZA_SALDOS_BONOS_PENSION_SEPA_LLENA_MONTOS_SEPA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_lotes", String.class, ParameterMode.IN);						
			query.registerStoredProcedureParameter("in_cod_inversions", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_monto_pend", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivss", Integer.class, ParameterMode.OUT);			
			query.registerStoredProcedureParameter("on_monto_pend08s", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivs08s", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_monto_pend_Cs", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivs_Cs", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_monto_pend08_Cs", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_aivs08_Cs", Integer.class, ParameterMode.OUT);	
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("ic_lotes", ic_lotes);
			query.setParameter("in_cod_inversions", in_cod_inversions);
			
			RespuestaOut res = new RespuestaOut();
			MontosRecaOut montos= new MontosRecaOut();
			montos.setOn_monto_pendiente((Integer) query.getOutputParameterValue("on_monto_pend"));
			montos.setOn_aivs((Integer) query.getOutputParameterValue("on_aivss"));
			montos.setOn_monto_pendiente08((Integer) query.getOutputParameterValue("on_monto_pend08s"));
			montos.setOn_aivs08((Integer) query.getOutputParameterValue("on_aivs08s"));
			montos.setOn_monto_pendiente_C((Integer) query.getOutputParameterValue("on_monto_pend_Cs"));
			montos.setOn_aivs_C((Integer) query.getOutputParameterValue("on_aivs_Cs"));
			montos.setOn_monto_pendiente08_C((Integer) query.getOutputParameterValue("on_monto_pend08_Cs"));
			montos.setOn_aivs08_C((Integer) query.getOutputParameterValue("on_aivs08_Cs"));						
			
			res.setMontosRecaIsss(montos);
			res.setOn_Estatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setOc_Mensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
