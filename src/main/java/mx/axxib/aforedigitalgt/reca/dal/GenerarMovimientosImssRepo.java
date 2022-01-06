package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.MovimientoImssOut;
import mx.axxib.aforedigitalgt.reca.eml.MovimientosOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Generación de Movimientos Semanas de Cotizaciónn IMSS
//** Interventor Principal: JJSC
//** Fecha Creación: 16/NOV/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class GenerarMovimientosImssRepo  extends RepoBase{

	
	@SuppressWarnings("unchecked")
	public MovimientosOut getDetalle(Date PFEC_INI, Date PFEC_FIN) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.GENERACION_MOVIMIENTOS_COTIZACION_IMSS_PACKAGE)
					.concat(".").concat(Constantes.GENERACION_MOVIMIENTOS_COTIZACION_IMSS_GET_DETALLE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "MovimientoImssOut");
			
			query.registerStoredProcedureParameter("PFEC_INI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("PFEC_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_DETA", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("PFEC_INI", PFEC_INI);
			query.setParameter("PFEC_FIN", PFEC_FIN);
			
			MovimientosOut res = new MovimientosOut();
			List<MovimientoImssOut> listaMovimientos =query.getResultList();
			res.setListaMovimientos(listaMovimientos);
			res.setP_ESTATUS( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setP_MENSAJE( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public MovimientosOut generarMovimientos(Date PFEC_INI,Date PFEC_FIN,Integer pcant) throws AforeException{
      try {
    	  String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.GENERACION_MOVIMIENTOS_COTIZACION_IMSS_PACKAGE)
					.concat(".").concat(Constantes.GENERACION_MOVIMIENTOS_COTIZACION_IMSS_GENERA_MOVIMIENTOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("PFEC_INI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("PFEC_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pcant", Integer.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("PFEC_INI", PFEC_INI);
			query.setParameter("PFEC_FIN", PFEC_FIN);
			query.setParameter("pcant", pcant);
			
			MovimientosOut res = new MovimientosOut();
			res.setP_ESTATUS( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setP_MENSAJE( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
      }catch(Exception e) {
    	  throw GenericException(e);
      }				
	}
	
	public MovimientosOut procesar(Integer P_PROCESAR,String P_nss) throws AforeException{
	      try {
	    	  String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.GENERACION_MOVIMIENTOS_COTIZACION_IMSS_PACKAGE)
						.concat(".").concat(Constantes.GENERACION_MOVIMIENTOS_COTIZACION_IMSS_PROCESAR);
				StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
				
				query.registerStoredProcedureParameter("P_PROCESAR", Integer.class, ParameterMode.IN);
				query.registerStoredProcedureParameter("P_nss", String.class, ParameterMode.IN);
				query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
				query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
				
				query.setParameter("P_PROCESAR", P_PROCESAR);
				query.setParameter("P_nss", P_nss);
				
				MovimientosOut res = new MovimientosOut();
				res.setP_ESTATUS( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setP_MENSAJE( (String) query.getOutputParameterValue("P_MENSAJE") );
				return res;
	      }catch(Exception e) {
	    	  throw GenericException(e);
	      }				
		}
}