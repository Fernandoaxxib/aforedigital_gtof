package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;


@Repository
public class RepParcialesRepo extends RepoBase{

	public String generarReporte(Date p_fechaInicio,Date p_fechaFinal,String p_Ruta,String p_Archivo) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.REPORTE_PARCIALES_CONSAR_PACKAGE)
					.concat(".").concat(Constantes.REPORTE_PARCIALES_CONSAR_BTN_GENERA_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_fechaInicio", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_fechaFinal", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Ruta", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Archivo", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);
			
			query.setParameter("p_fechaInicio",p_fechaInicio);
			query.setParameter("p_fechaFinal",p_fechaFinal);
			query.setParameter("p_Ruta",p_Ruta);
			query.setParameter("p_Archivo",p_Archivo);
			
			String resp=(String)query.getOutputParameterValue("p_message");
			
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
