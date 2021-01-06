package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;

@Repository
public class ReporteParcialDAO extends RepoBase {
	
private final EntityManager entityManager;
	
	@Autowired
	public ReporteParcialDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public String crearReporteParcial(Date fechaIni,Date fechaFin, String ruta, String nombre) throws AforeException {
		try {
		String storedFullName =  Constantes.REPORTE_PARCIAL_PACKAGE.concat(".").concat(Constantes.REPORTE_PARCIAL_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("p_fechaInicio", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_fechaFinal", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);		
		
		query.setParameter("p_fechaInicio",fechaIni);
		query.setParameter("p_fechaFinal",fechaFin);
		query.setParameter("p_Ruta", ruta);
		query.setParameter("p_Archivo", nombre);
		
		String res = (String) query.getOutputParameterValue("p_Mensaje");
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
