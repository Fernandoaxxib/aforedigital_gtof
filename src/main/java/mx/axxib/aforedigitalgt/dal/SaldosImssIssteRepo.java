package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoImssIssteOut;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoNegativoOut;

@Repository
@Transactional
public class SaldosImssIssteRepo extends RepoBase{
	
private final EntityManager entityManager;
	
	@Autowired
	public SaldosImssIssteRepo (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSaldoImssIssteOut> ejecutarImssCarga(String ruta, String nombre) throws AforeException {
		try {
		String storedFullName =  Constantes.SALDOS_CONSULTAR_IMSS_ISSTE_PACKAGE.concat(".").concat(Constantes.SALDOS_CARGA_IMSS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultaSaldoImssIssteOut");

		query.registerStoredProcedureParameter("p_RutaCrgNss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_ArchCrgNss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Estatus", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_vMensaje", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);
		
		query.setParameter("p_RutaCrgNss", ruta);
		query.setParameter("p_ArchCrgNss", nombre);
		
		List<ConsultaSaldoImssIssteOut> res = query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSaldoImssIssteOut> ejecutarImssReporte(String ruta, String nombre) throws AforeException {
		try {
		String storedFullName =  Constantes.SALDOS_CONSULTAR_IMSS_ISSTE_PACKAGE.concat(".").concat(Constantes.SALDOS_CONSULTAR_IMSS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultaSaldoImssIssteOut");

		query.registerStoredProcedureParameter("p_RutaRepSldImss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_ArchRepSldImss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Estatus", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_vMensaje", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);
		
		query.setParameter("p_RutaRepSldImss", ruta);
		query.setParameter("p_ArchRepSldImss", nombre);
		
		List<ConsultaSaldoImssIssteOut> res = query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSaldoImssIssteOut> ejecutarIssteCarga(String ruta, String nombre) throws AforeException {
		try {
		String storedFullName =  Constantes.SALDOS_CONSULTAR_IMSS_ISSTE_PACKAGE.concat(".").concat(Constantes.SALDOS_CARGA_ISSTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultaSaldoImssIssteOut");

		query.registerStoredProcedureParameter("p_RutaCrgCURPIss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_ArchCrgCURPIss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Estatus", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_vMensaje", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);
		
		query.setParameter("p_RutaCrgCURPIss", ruta);
		query.setParameter("p_ArchCrgCURPIss", nombre);
		
		List<ConsultaSaldoImssIssteOut> res = query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSaldoImssIssteOut> ejecutarIssteReporte(String ruta, String nombre) throws AforeException {
		try {
		String storedFullName =  Constantes.SALDOS_CONSULTAR_IMSS_ISSTE_PACKAGE.concat(".").concat(Constantes.SALDOS_CONSULTAR_ISSTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultaSaldoImssIssteOut");

		query.registerStoredProcedureParameter("p_RutaRepSldIss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_ArchRepSldIss", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Estatus", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_vMensaje", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);
		
		query.setParameter("p_RutaRepSldIss", ruta);
		query.setParameter("p_ArchRepSldIss", nombre);
		
		List<ConsultaSaldoImssIssteOut> res = query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSaldoNegativoOut> ejecutarReporteNegativo(String ruta, String nombre,Date fechaMovimiento) throws AforeException {
		try {
		String storedFullName =  Constantes.SALDOS_CONSULTAR_IMSS_ISSTE_PACKAGE.concat(".").concat(Constantes.SALDOS_CONSULTAR_ISSTE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultaSaldoNegativoOut");

		query.registerStoredProcedureParameter("p_RutaVol", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_ArchVol", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_FecMov", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Mensaje", String.class, ParameterMode.OUT);
		
		query.setParameter("p_RutaVol", ruta);
		query.setParameter("p_ArchVol", nombre);
		query.setParameter("p_FecMov", fechaMovimiento);
		
		List<ConsultaSaldoNegativoOut> res = query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	

}
