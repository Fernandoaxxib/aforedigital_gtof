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
import mx.axxib.aforedigitalgt.eml.ObtieneJobs;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitor;
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoMensaje;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;

@Repository
@Transactional
public class SalarioMinimoRepo extends RepoBase {

	
private final EntityManager entityManager;
	
	@Autowired
	public SalarioMinimoRepo (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public SalarioMinOut getSalarioMinimo() throws AforeException {
		
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SalarioMinimoOut");
		
		query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);	
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);	
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
		
		
		
		
		SalarioMinOut res = new SalarioMinOut(); 
		Object cursor = query.getOutputParameterValue("CP_CURSOR");
		
		if (cursor != null) {
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setListSalarioMin(query.getResultList());
		res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
		}
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SalarioMinimoOut> getSalarioMinimo2(String usuario) throws AforeException {
		
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SalarioMinimoOut");
		
		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);	
		//query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);	
		
		query.setParameter("P_USUARIO", usuario);
		
		List<SalarioMinimoOut> res = query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public SalarioMinimoMensaje save(SalarioMinimoOut parametros) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_INSERT_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CDG_ZONA", String.class, ParameterMode.IN);		
		query.registerStoredProcedureParameter("P_FCH_CALENDARIO", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MONTO_DIARIO", Double.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
		
		query.setParameter("P_USUARIO", parametros.getUserId());
		query.setParameter("P_CDG_ZONA", parametros.getCdZona());
		query.setParameter("P_FCH_CALENDARIO", parametros.getFechaCalendario());
		query.setParameter("P_MONTO_DIARIO", parametros.getMontoDiario());
		
		SalarioMinimoMensaje res=new SalarioMinimoMensaje();
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public SalarioMinimoMensaje update(SalarioMinimoOut seleccionado) throws AforeException {
		try {
			
	        
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_UPDATE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		    
		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CDG_ZONA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FCH_CALENDARIO", Date.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter("P_FCH_NUEVA", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MONTO_DIARIO", Double.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
		
		query.setParameter("P_USUARIO", seleccionado.getUserId());
		query.setParameter("P_CDG_ZONA", seleccionado.getCdZona());
		query.setParameter("P_FCH_CALENDARIO",seleccionado.getFechaCalendario());
//		query.setParameter("P_FCH_NUEVA", seleccionado.getFechaUltimo());
		query.setParameter("P_MONTO_DIARIO",seleccionado.getMontoDiario());
		
		SalarioMinimoMensaje res=new SalarioMinimoMensaje();
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public SalarioMinimoMensaje delete( SalarioMinimoOut seleccionado) throws AforeException {
		try {	       
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_DELETE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		    
		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);		
		query.registerStoredProcedureParameter("P_FCH_CALENDARIO", Date.class, ParameterMode.IN);	
		query.registerStoredProcedureParameter("P_CDG_ZONA",String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);		
		query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
		
		query.setParameter("P_USUARIO", seleccionado.getUserId());
		query.setParameter("P_FCH_CALENDARIO", seleccionado.getFechaCalendario());
		query.setParameter("P_CDG_ZONA", seleccionado.getCdZona());
		
		SalarioMinimoMensaje res=new SalarioMinimoMensaje();
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
