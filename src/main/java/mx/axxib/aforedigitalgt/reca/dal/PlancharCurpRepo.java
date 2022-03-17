package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.PlancharCurpOut;


//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Planchar Curp
//** Interventor Principal: JJSC
//** Fecha Creación: 11/03/2022
//** Última Modificación:
//***********************************************//
@Repository
@Transactional
public class PlancharCurpRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public PlancharCurpOut getLotes() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PLANCHAR_CURP_PACKAGE)
					.concat(".").concat(Constantes.PLANCHAR_CURP_GET_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteRecaudacionIssste");

			query.registerStoredProcedureParameter("CP_LOTE", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			PlancharCurpOut res = new PlancharCurpOut();

			res.setListLotes(query.getResultList());
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public PlancharCurpOut getDatosTrabajadores(String idLote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PLANCHAR_CURP_PACKAGE)
					.concat(".").concat(Constantes.PLANCHAR_CURP_GET_DATOS_TRABAJADOR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "DatosTrabajador");
			
			query.registerStoredProcedureParameter("P_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			query.setParameter("P_ID_LOTE", idLote);
			
			PlancharCurpOut res = new PlancharCurpOut();

			res.setListDatosTrabajadores(query.getResultList());
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public PlancharCurpOut procesar(String P_SELECCION, String P_LOTE_CARGA, String P_curp, String P_nss_trabajador,String P_RESUL_OPERA ) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.PLANCHAR_CURP_PACKAGE)
					.concat(".").concat(Constantes.PLANCHAR_CURP_PROCESAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_SELECCION", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE_CARGA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_curp", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_nss_trabajador", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_RESUL_OPERA", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			query.setParameter("P_SELECCION", P_SELECCION);
			query.setParameter("P_LOTE_CARGA", P_LOTE_CARGA);
			query.setParameter("P_curp", P_curp);
			query.setParameter("P_nss_trabajador", P_nss_trabajador);
			query.setParameter("P_RESUL_OPERA", P_RESUL_OPERA);
			
			PlancharCurpOut res = new PlancharCurpOut();

			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
