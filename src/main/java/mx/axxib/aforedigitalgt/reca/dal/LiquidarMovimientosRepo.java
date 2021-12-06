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
	public LiquidarBusquedaOut buscar(Date fecha, String lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_BUSCAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarLotes");

			query.registerStoredProcedureParameter("id_FECLOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_IDE_LOTE", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", Integer.class, ParameterMode.OUT);

			query.setParameter("id_FECLOTE", fecha);
			query.setParameter("P_IDE_LOTE", lote);

			LiquidarBusquedaOut res = new LiquidarBusquedaOut();
			res.setLotes(query.getResultList());
			
			res.setAccion0( (BigDecimal) query.getOutputParameterValue("P_tot_acciones") );
			res.setAccion1( (BigDecimal) query.getOutputParameterValue("P_tot_acciones1") );
			res.setAccion2( (BigDecimal) query.getOutputParameterValue("P_tot_acciones2") );
			res.setAccion3( (BigDecimal) query.getOutputParameterValue("P_tot_acciones3") );
			res.setAccion4( (BigDecimal) query.getOutputParameterValue("P_tot_acciones4") );
			res.setMonto0( (BigDecimal) query.getOutputParameterValue("P_tot_monto") );
			res.setMonto1( (BigDecimal) query.getOutputParameterValue("P_tot_monto1") );
			res.setMonto2( (BigDecimal) query.getOutputParameterValue("P_tot_monto2") );
			res.setMonto3( (BigDecimal) query.getOutputParameterValue("P_tot_monto3") );
			res.setMonto4( (BigDecimal) query.getOutputParameterValue("P_tot_monto4") );
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public BaseOut liquidar(String lote, String usuario) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.PAQUETE_GENERICO_RECAUDACION).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_LIQUIDAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("PID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("PUSUARIO", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", Integer.class, ParameterMode.OUT);

			query.setParameter("PID_LOTE", lote);
			query.setParameter("PUSUARIO", usuario);

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
