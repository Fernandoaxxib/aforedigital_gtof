package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.OrdenPagoFechasOut;
import mx.axxib.aforedigitalgt.eml.TiposReportes;

@Repository
public class OrdenPagoRepo extends RepoBase{
	
private final EntityManager entityManager;
	
	@Autowired
	public OrdenPagoRepo (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public OrdenPagoFechasOut cargaFechas() throws AforeException {
		try {
		
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ORDEN_PAGO_PACKAGE).concat(".").concat(Constantes.ORDEN_PAGO_OBTIENE_FECHA_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FECHA_FINAL", Date.class, ParameterMode.OUT);
		
		
		
		OrdenPagoFechasOut res= new OrdenPagoFechasOut ();
		res.setFechaInicio((Date) query.getOutputParameterValue("P_FECHA_INICIO"));
		res.setFechaFin((Date) query.getOutputParameterValue("P_FECHA_FINAL"));
		System.out.println("IMPRIMIR EL VALOR DE res: "+res);
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public String impresoraReporte(OrdenPagoFechasOut parametro, Integer registroReporte) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ORDEN_PAGO_PACKAGE).concat(".").concat(Constantes.ORDEN_PAGO_REPORTE_ANTERIOR_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		
		query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_REG_REPORTE", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
		query.setParameter("P_FECHA_INICIO", parametro.getFechaInicio());
		query.setParameter("P_REG_REPORTE", registroReporte);
		
		String res = (String) query.getOutputParameterValue("P_MENSAJE");
		System.out.println("VALOR DE PRC_GENERA_REPORTE_ANT"+res);
		return res;
	} catch (Exception e) {
		throw GenericException(e);
	}
	}
	
	@SuppressWarnings("unchecked")
	public TiposReportes creaTipoReporte(String tipoReporte ) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ORDEN_PAGO_PACKAGE).concat(".").concat(Constantes.ORDEN_PAGO_CREA_REPORTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		
		query.registerStoredProcedureParameter("P_TIPO_REPORTE", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		
				
		query.setParameter("P_TIPO_REPORTE", tipoReporte);
		
		
		TiposReportes res= new TiposReportes ();
		res.setP_NOMBRE_ARCHIVO((String) query.getOutputParameterValue("P_NOMBRE_ARCHIVO"));
		res.setP_MENSAJE((String) query.getOutputParameterValue("P_MENSAJE"));
		System.out.println("IMPRIMIR PAQUETE PRC_REPORTE TiposReportes EL VALOR DE res: "+res);
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String generaNombre(OrdenPagoFechasOut parametro ) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.ORDEN_PAGO_PACKAGE).concat(".").concat(Constantes.ORDEN_PAGO_GENERA_REPORTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		
		query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FECHA_FINAL", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.OUT);
				
		query.setParameter("P_FECHA_INICIO", parametro.getFechaInicio());
		query.setParameter("P_FECHA_FINAL",parametro.getFechaFin());
		
		String res = (String) query.getOutputParameterValue("P_NOMBRE_ARCHIVO");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
