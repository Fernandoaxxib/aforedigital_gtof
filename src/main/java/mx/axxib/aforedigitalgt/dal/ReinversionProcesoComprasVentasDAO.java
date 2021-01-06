package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ReinversionProcesoOut;


@Repository
public class ReinversionProcesoComprasVentasDAO extends RepoBase{
	
private final EntityManager entityManager;
	
	@Autowired
	public ReinversionProcesoComprasVentasDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReinversionProcesoOut> getEjecutarProceso(Integer operacion, Integer codSistema,Integer codAgencia,String codEmpresa,Date fechaIni,Date fechaFin,Date fechaLote) throws AforeException {
		try {
		String storedFullName =  Constantes.REINVERSION_PROCESO_COMPRAS_VENTAS_PACKAGE.concat(".").concat(Constantes.REINVERSION_COMPRAS_PROCESO_BOTON_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ReinversionProcesoOut");
		
		query.registerStoredProcedureParameter("p_Operacion", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodSistema", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodAgencia", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodEmpresa", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_FechaInicial", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_FechaFinal", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Fecha_Lote", Date.class, ParameterMode.IN);
		
		
		query.registerStoredProcedureParameter("p_Cuentas", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Avance", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Fecha_Trans", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Ruta_Arch_Trans", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Arch_Sal_Trans", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
		
		query.setParameter("p_Operacion", operacion);
		query.setParameter("p_CodSistema", codSistema);
		query.setParameter("p_CodAgencia", codAgencia);
		query.setParameter("p_CodEmpresa", codEmpresa);
		query.setParameter("p_FechaInicial", fechaIni);
		query.setParameter("p_FechaFinal", fechaFin);
		query.setParameter("p_Fecha_Lote", fechaLote);
		
		
		List<ReinversionProcesoOut> res = query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String crearReporteCompras(String idLote) throws AforeException {
		try {
		String storedFullName =  Constantes.REINVERSION_PROCESO_COMPRAS_VENTAS_PACKAGE.concat(".").concat(Constantes.REINVERSION_COMPRAS_BOTON_GENERA_REPORTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_ID_LOTE", Date.class, ParameterMode.IN);
		
		
		query.setParameter("p_ID_LOTE",idLote);
				
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String procesoCompras(String idLote, Date fechaLote) throws AforeException {
		try {
		String storedFullName =  Constantes.REINVERSION_PROCESO_COMPRAS_VENTAS_PACKAGE.concat(".").concat(Constantes.REINVERSION_COMPRAS_BOTON_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Lote", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_FechaLote", Date.class, ParameterMode.IN);
		
		
		query.setParameter("p_Lote",idLote);
		query.setParameter("p_FechaLote",fechaLote);
			
		String res = (String) query.getOutputParameterValue("p_Message");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String detalleCompra(String idLote) throws AforeException {
		try {
		String storedFullName =  Constantes.REINVERSION_PROCESO_COMPRAS_VENTAS_PACKAGE.concat(".").concat(Constantes.REINVERSION_COMPRAS_COMPRA_LOTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Lote", Date.class, ParameterMode.IN);
			
		query.setParameter("p_Lote",idLote);
		
			
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public String crearReporteVentas(String idLote) throws AforeException {
		try {
		String storedFullName =  Constantes.REINVERSION_PROCESO_COMPRAS_VENTAS_PACKAGE.concat(".").concat(Constantes.REINVERSION_COMPRAS_VENTAS_BOTON_GENERA_REPORTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_ID_LOTE", Date.class, ParameterMode.IN);
		
		
		query.setParameter("p_ID_LOTE",idLote);
				
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String procesoVentas(String idLote, Date fechaLote) throws AforeException {
		try {
		String storedFullName =  Constantes.REINVERSION_PROCESO_COMPRAS_VENTAS_PACKAGE.concat(".").concat(Constantes.REINVERSION_COMPRAS_VENTAS_BOTON_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Lote", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_FechaLote", Date.class, ParameterMode.IN);
		
		
		query.setParameter("p_Lote",idLote);
		query.setParameter("p_FechaLote",fechaLote);
			
		String res = (String) query.getOutputParameterValue("p_Message");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String detalleVentas(String idLote) throws AforeException {
		try {
		String storedFullName =  Constantes.REINVERSION_PROCESO_COMPRAS_VENTAS_PACKAGE.concat(".").concat(Constantes.REINVERSION_COMPRAS_VENTAS_LOTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_Lote", Date.class, ParameterMode.IN);
			
		query.setParameter("p_Lote",idLote);
		
			
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
