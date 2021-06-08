package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ReporteOut;

//***********************************************//
//** Funcionalidad: Repositorio - Reporte de parciales CONSAR
//** Desarrollador: JJSC
//** Fecha de creación: 15/Feb/2021
//** Última modificación:
//***********************************************//
@Repository
@Transactional
public class RepParcialesRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public RepParcialesRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ReporteOut generarReporte(Date p_fechaInicio, Date p_fechaFinal, String p_Ruta, String p_Archivo)
			throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_PARCIALES_CONSAR_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_PARCIALES_CONSAR_BTN_GENERA_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_fechaInicio", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_fechaFinal", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("p_fechaInicio", p_fechaInicio);
			query.setParameter("p_fechaFinal", p_fechaFinal);
			query.setParameter("p_Ruta", p_Ruta);
			query.setParameter("p_Archivo", p_Archivo);

			ReporteOut resp = new ReporteOut();
			resp.setOc_Mensaje((String) query.getOutputParameterValue("p_message"));
			resp.setOn_Estatus((Integer)query.getOutputParameterValue("on_Estatus"));	

			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
