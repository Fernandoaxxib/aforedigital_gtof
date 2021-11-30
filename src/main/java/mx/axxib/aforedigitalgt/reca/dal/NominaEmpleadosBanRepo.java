package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaDispOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Nómina Empleados Grupo Financiero Banorte
//** Interventor Principal: JJSC
//** Fecha Creación: 22/NOV/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class NominaEmpleadosBanRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public RespuestaDispOut getLoteDispersion() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_GET_LOTE_DISPERSION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteDispOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RespuestaDispOut res = new RespuestaDispOut();

			res.setLotes(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RespuestaOut aplicarDispersion(String oc_Opcion) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_APLICAR_DISPERSION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			query.registerStoredProcedureParameter("oc_Opcion", String.class, ParameterMode.IN);		
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RespuestaOut res = new RespuestaOut();

			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public RespuestaDispOut getListaEmpresas() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_GET_LISTA_EMPRESAS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "EmpresaOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RespuestaDispOut res = new RespuestaDispOut();

			res.setListaEmpresas(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public RespuestaDispOut getLotesEmpresa() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_GET_LOTES_EMPRESA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "EmpresaOut");

			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RespuestaDispOut res = new RespuestaDispOut();

			res.setLotesEmpresa(query.getResultList());
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaDispOut aplicarDispersionVolEmp(String oc_Opcion) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_APLICAR_DISPERSION_VOL_EMP);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			query.registerStoredProcedureParameter("oc_Lote", String.class, ParameterMode.IN);		
			query.registerStoredProcedureParameter("oc_IND_ACCION", String.class, ParameterMode.OUT);			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			RespuestaDispOut res = new RespuestaDispOut();

			res.setOc_IND_ACCION((String) query.getOutputParameterValue("oc_IND_ACCION"));
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
