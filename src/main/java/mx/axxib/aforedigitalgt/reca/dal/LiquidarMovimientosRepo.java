package mx.axxib.aforedigitalgt.reca.dal;

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
	public LiquidarRenOut lote() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarRenLotes");
			
//			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
//			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			LiquidarRenOut res = new LiquidarRenOut();
			res.setLotes(query.getResultList());
//			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
//			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
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
			query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", Integer.class, ParameterMode.OUT);

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
	public LiquidarBusquedaOut buscarLote(String lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_BUSCAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LiquidarLote");

			query.registerStoredProcedureParameter("P_IDE_LOTE", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", Integer.class, ParameterMode.OUT);

			query.setParameter("P_IDE_LOTE", lote);

			LiquidarBusquedaOut res = new LiquidarBusquedaOut();
			res.setLotes(query.getResultList());
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
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_PACKAGE).concat(".")
					.concat(Constantes.LIQUIDAR_MOVIMIENTOS_LIQUIDAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("PID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("PUSUARIO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("PCOD_EMPRESA", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", Integer.class, ParameterMode.OUT);

			query.setParameter("PID_LOTE", lote);
			query.setParameter("PUSUARIO", usuario);
			query.setParameter("PCOD_EMPRESA", "1"); //BD dice que debe ponerse fijo el valor 1

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}

