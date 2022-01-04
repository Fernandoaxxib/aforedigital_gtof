package mx.axxib.aforedigitalgt.reca.dal;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarBusquedaOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Liquidar Movimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Nov/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class LiquidarMovimientosRepo extends RepoBase {
	
	@SuppressWarnings("unchecked")
	public LiquidarRenOut lote(Date fecha) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarRenLotes");
			
			
			query.registerStoredProcedureParameter("FEC_LOTE", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_LOTES", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("FEC_LOTE", fecha);

			LiquidarRenOut res = new LiquidarRenOut();
			res.setLotes(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public LiquidarBusquedaOut buscarFecha(Date fecha) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarLote");

			query.registerStoredProcedureParameter("P_FECLOTE", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECLOTE", fecha);

			LiquidarBusquedaOut res = new LiquidarBusquedaOut();
			res.setLotes(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public LiquidarBusquedaOut buscarLote(String lote, Date fecha) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_BUSCAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarLote");
			
			
			query.registerStoredProcedureParameter("id_FECLOTE", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_IDE_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_TOT_ACCIONES", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_ACCIONES1", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_ACCIONES2", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_ACCIONES3", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_ACCIONES4", BigDecimal.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_TOT_MONTO", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_MONTO1", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_MONTO2", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_MONTO3", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TOT_MONTO4", BigDecimal.class, ParameterMode.OUT);
			
			query.setParameter("id_FECLOTE", fecha);
			query.setParameter("P_IDE_LOTE", lote);

			LiquidarBusquedaOut res = new LiquidarBusquedaOut();
			res.setLotes(query.getResultList());
			res.setAcciones( (BigDecimal) query.getOutputParameterValue("P_TOT_ACCIONES") );
			res.setAcciones1( (BigDecimal) query.getOutputParameterValue("P_TOT_ACCIONES1") );
			res.setAcciones2( (BigDecimal) query.getOutputParameterValue("P_TOT_ACCIONES2") );
			res.setAcciones3( (BigDecimal) query.getOutputParameterValue("P_TOT_ACCIONES3") );
			res.setAcciones4( (BigDecimal) query.getOutputParameterValue("P_TOT_ACCIONES4") );
			
			res.setMonto( (BigDecimal) query.getOutputParameterValue("P_TOT_MONTO") );
			res.setMonto1( (BigDecimal) query.getOutputParameterValue("P_TOT_MONTO1") );
			res.setMonto2( (BigDecimal) query.getOutputParameterValue("P_TOT_MONTO2") );
			res.setMonto3( (BigDecimal) query.getOutputParameterValue("P_TOT_MONTO3") );
			res.setMonto4( (BigDecimal) query.getOutputParameterValue("P_TOT_MONTO4") );
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public BaseOut liquidar(String lote, Date fecha) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_LIQUIDAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			
			query.registerStoredProcedureParameter("id_FECLOTE", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_IDE_LOTE", String.class, ParameterMode.IN);
			
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("id_FECLOTE", fecha);
			query.setParameter("P_IDE_LOTE", lote);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}

