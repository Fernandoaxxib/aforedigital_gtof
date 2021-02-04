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
import mx.axxib.aforedigitalgt.eml.ObtieneJobsOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;

@Repository
public class SalarioMinimoRepo extends RepoBase {

	
private final EntityManager entityManager;
	
	@Autowired
	public SalarioMinimoRepo (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public SalarioMinOut getSalarioMinimo(String usuario) throws AforeException {
		
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SalarioMinimoOut");
		
		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);	
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);	
		
		query.setParameter("P_USUARIO", usuario);
		
		
		SalarioMinOut res = new SalarioMinOut(); 
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setListSalarioMin(query.getResultList());
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
	public String save(String usuario , Date calendario, Double monto) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_INSERT_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FCH_CALENDARIO", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MONTO_DIARIO", Double.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);		
		
		query.setParameter("P_USUARIO", usuario);
		query.setParameter("P_FCH_CALENDARIO", calendario);
		query.setParameter("P_MONTO_DIARIO", monto);
		
		String res = (String) query.getOutputParameterValue("P_MENSAJE");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String update(String usuario, String cdZona , Date calendario, Double monto) throws AforeException {
		try {
			 System.out.println("DAO ");
			System.out.println("Datos del dao Usuario: "+usuario);
	        System.out.println("Datos del dao Zona: "+cdZona);
	        System.out.println("Datos del dao  Fecha Calendario: "+calendario);
	        System.out.println("Datos del dao  Monto: "+monto);
	        
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_UPDATE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
		    
		query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CDG_ZONA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_FCH_CALENDARIO", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MONTO_DIARIO", Double.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);		
		
		query.setParameter("P_USUARIO", usuario);
		query.setParameter("P_CDG_ZONA", cdZona);
		query.setParameter("P_FCH_CALENDARIO", calendario);
		query.setParameter("P_MONTO_DIARIO", monto);
		
		String res = (String) query.getOutputParameterValue("P_MENSAJE");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
