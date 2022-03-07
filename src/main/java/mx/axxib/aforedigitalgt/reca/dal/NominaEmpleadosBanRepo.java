package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaDispOut;

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

	public RespuestaDispOut aplicarDispersion(String oc_Opcion,String ic_Lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_APLICAR_DISPERSION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			query.registerStoredProcedureParameter("oc_Opcion", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Lote", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("oc_Opcion", oc_Opcion);
			query.setParameter("ic_Lote", ic_Lote);
			
			RespuestaDispOut res = new RespuestaDispOut();
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
	public RespuestaDispOut aplicarDispersionVolEmp(String ic_LOTE, String ic_EMPRESA,String ic_LOTE_EMPRESA,Integer P_VALOR,String ic_Boton1,String ic_Boton2) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_APLICAR_DISPERSION_VOL_EMP);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			query.registerStoredProcedureParameter("ic_LOTE", String.class, ParameterMode.INOUT);		
			query.registerStoredProcedureParameter("ic_EMPRESA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_LOTE_EMPRESA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_VALOR", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Boton1", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Boton2", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_LOTE", ic_LOTE);
			query.setParameter("ic_EMPRESA", ic_EMPRESA);
			query.setParameter("ic_LOTE_EMPRESA", ic_LOTE_EMPRESA);
			query.setParameter("P_VALOR", P_VALOR);
			query.setParameter("ic_Boton1", ic_Boton1);
			query.setParameter("ic_Boton2", ic_Boton2);
			
			RespuestaDispOut res = new RespuestaDispOut();

			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public RespuestaDispOut confirmarDatos(String P_EMPRESA,String  P_LOTE_EMPRESA) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_PACKAGE).concat(".")
					.concat(Constantes.NOMINA_EMPLEADOS_BANORTE_CONFIRMA_DATOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			query.registerStoredProcedureParameter("P_EMPRESA", String.class, ParameterMode.IN);		
			query.registerStoredProcedureParameter("P_LOTE_EMPRESA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_VALOR", Integer.class, ParameterMode.OUT);			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("P_EMPRESA", P_EMPRESA);
			query.setParameter("P_LOTE_EMPRESA", P_LOTE_EMPRESA);

			RespuestaDispOut res = new RespuestaDispOut();
			res.setP_VALOR((Integer) query.getOutputParameterValue("P_VALOR"));
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setOc_Mensaje((String) query.getOutputParameterValue("oc_Mensaje"));
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
