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
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;

@Repository
public class SalarioMinimoDAO extends RepoBase {

	
private final EntityManager entityManager;
	
	@Autowired
	public SalarioMinimoDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public SalarioMinimoOut getSalarioMinimo(Integer usuario, Date fecha) throws AforeException {
		
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.SALARIO_MINIMO_PACKAGE).concat(".").concat(Constantes.SALARIO_MINIMO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SalarioMinimoOut");
		
		query.registerStoredProcedureParameter("p_usuario", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_fecha_calendario", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("cp_cursor", void.class, ParameterMode.REF_CURSOR);	
		query.registerStoredProcedureParameter("CP_FECHA_MAX", void.class, ParameterMode.REF_CURSOR);	
		
		query.setParameter("p_usuario", usuario);
		query.setParameter("p_fecha_calendario", fecha);
		
		SalarioMinimoOut res = new SalarioMinimoOut(); 
		res.setMontoDiario((Integer) query.getOutputParameterValue("MONTO_DIARIO"));	
		res.setUserId((Integer) query.getOutputParameterValue("USR_ID"));
		res.setFechaUltimo((Date) query.getOutputParameterValue("FCH_ULT_ACT") );
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
