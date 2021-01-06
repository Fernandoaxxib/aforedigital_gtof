package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.OrdenPagoArchivoOut;
import mx.axxib.aforedigitalgt.eml.OrdenPagoFechasOut;

@Repository
public class OrdenPagoDAO extends RepoBase{
	
private final EntityManager entityManager;
	
	@Autowired
	public OrdenPagoDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public OrdenPagoFechasOut cargaFechas() throws AforeException {
		try {
		String storedFullName =  Constantes.ORDEN_PAGO_PACKAGE.concat(".").concat(Constantes.ORDEN_PAGO_OBTIENE_FECHA_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FECHA_FINAL", Date.class, ParameterMode.OUT);
		
		
		
		OrdenPagoFechasOut res= new OrdenPagoFechasOut ();
		res.setFechaInicio((Date) query.getOutputParameterValue("P_FECHA_INICIO"));
		res.setFechaFin((Date) query.getOutputParameterValue("P_FECHA_FINAL"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public String obtenerNombre(Date fechaInicio, Date fechaFin,Integer registroReporte) throws AforeException {
		try {
		String storedFullName =  Constantes.ORDEN_PAGO_PACKAGE.concat(".").concat(Constantes.ORDEN_PAGO_REPORTE_ANTERIOR_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		
		query.registerStoredProcedureParameter("P_FECHA_INICIO", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FECHA_FINAL", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_REG_REPORTE", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.OUT);
			
		query.setParameter("P_FECHA_INICIO", fechaInicio);
		query.setParameter("P_FECHA_FINAL", fechaFin);
		query.setParameter("P_REG_REPORTE", registroReporte);
		
		String res = (String) query.getOutputParameterValue("P_NOMBRE_ARCHIVO");
				return res;
	} catch (Exception e) {
		throw GenericException(e);
	}
	}
	
	@SuppressWarnings("unchecked")
	public OrdenPagoArchivoOut creaReporte(String tipoReporte ) throws AforeException {
		try {
		String storedFullName =  Constantes.ORDEN_PAGO_PACKAGE.concat(".").concat(Constantes.ORDEN_PAGO_CREA_REPORTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		
		query.registerStoredProcedureParameter("TIPO_REPORTE", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("NOMBRE_ARCHIVO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_MENSAJE", Date.class, ParameterMode.OUT);
		
				
		query.setParameter("TIPO_REPORTE", tipoReporte);
		
		
		OrdenPagoArchivoOut res= new OrdenPagoArchivoOut ();
		res.setNombreArchivo((String) query.getOutputParameterValue("NOMBRE_ARCHIVO"));
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String generaReporte(Date fechaInicio, Date fechaFin ) throws AforeException {
		try {
		String storedFullName =  Constantes.ORDEN_PAGO_PACKAGE.concat(".").concat(Constantes.ORDEN_PAGO_GENERA_REPORTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		
		query.registerStoredProcedureParameter("P_FECHA_INICIO", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FECHA_FINAL", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.OUT);
				
		query.setParameter("P_FECHA_INICIO", fechaInicio);
		query.setParameter("P_FECHA_FINAL", fechaFin);
		
		String res = (String) query.getOutputParameterValue("P_NOMBRE_ARCHIVO");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
