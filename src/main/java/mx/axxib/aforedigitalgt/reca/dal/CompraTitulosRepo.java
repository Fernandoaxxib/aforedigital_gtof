package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.CompraTitMontoOut;
import mx.axxib.aforedigitalgt.reca.eml.EmpresasOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Compra de Titulos
//** Interventor Principal: JSAS
//** Fecha Creación: 1/Mar/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class CompraTitulosRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public EmpresasOut empresas() throws AforeException {
//		PROCEDURE PRC_NOM_EMPRESA(CP_EMPRESA OUT SYS_REFCURSOR,
	//  P_MENSAJE   OUT VARCHAR2,
	//  P_ESTATUS   OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.COMPRA_TITULOS_PACKAGE).concat(".")
					.concat(Constantes.COMPRA_TITULOS_EMPRESAS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "Empresas");
			
			query.registerStoredProcedureParameter("CP_EMPRESA", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			EmpresasOut res = new EmpresasOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public CompraTitMontoOut montoFecha(Date fecha, String empresa) throws AforeException {
//		PROCEDURE  PRC_OBTIENE_MONTO_TOTAL (P_Fecha       IN DATE,
//      P_COD_EMPRESA IN VARCHAR2,
//      CP_CUR_TOTAL  OUT SYS_REFCURSOR,
//      P_MENSAJE     OUT VARCHAR2,
//      P_ESTATUS     OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.COMPRA_TITULOS_PACKAGE).concat(".")
					.concat(Constantes.COMPRA_TITULOS_MONTO_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "CompraTitMonto");
			
			query.registerStoredProcedureParameter("P_Fecha", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.IN);

			query.registerStoredProcedureParameter("CP_CUR_TOTAL", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_Fecha", fecha);
			query.setParameter("P_COD_EMPRESA", empresa);
			
			CompraTitMontoOut res = new CompraTitMontoOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public CompraTitMontoOut montoLote(String lote, String empresa) throws AforeException {
//		PROCEDURE PRC_OBTIENE_MONTO_TOTAL_LOTE (P_ID_LOTE     IN VARCHAR2,
//      P_COD_EMPRESA IN VARCHAR2,
//      CP_CUR_TOTAL  OUT SYS_REFCURSOR,
//      P_MENSAJE     OUT varchar2,
//      P_ESTATUS     OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.COMPRA_TITULOS_PACKAGE).concat(".")
					.concat(Constantes.COMPRA_TITULOS_MONTO_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "CompraTitMonto");
			
			query.registerStoredProcedureParameter("P_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.IN);

			query.registerStoredProcedureParameter("CP_CUR_TOTAL", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_ID_LOTE", lote);
			query.setParameter("P_COD_EMPRESA", empresa);
			
			CompraTitMontoOut res = new CompraTitMontoOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ejecuta(String lote, String empresa) throws AforeException {
//		PROCEDURE PRC_EJECUTA (
//      P_id_lote             IN VARCHAR2,
//      P_MENSAJE             OUT VARCHAR2,
//      P_ESTATUS             OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.COMPRA_TITULOS_PACKAGE).concat(".")
					.concat(Constantes.COMPRA_TITULOS_EJECUTA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_ID_LOTE", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.IN);

			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_ID_LOTE", lote);
			query.setParameter("P_COD_EMPRESA", empresa);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut compraTitulos(Date fecha, String empresa, String generaVenta) throws AforeException {
//		PROCEDURE PRC_compra_titulos_ct (
//      P_fCalendario IN DATE,
//      P_GENERAR_VENTA IN VARCHAR2,
//      P_MENSAJE  OUT VARCHAR2,
//      P_ESTATUS  OUT NUMBER);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.COMPRA_TITULOS_PACKAGE).concat(".")
					.concat(Constantes.COMPRA_TITULOS_COMPRA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_fCalendario", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_GENERAR_VENTA", String.class, ParameterMode.IN);

			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_fCalendario", fecha);
			query.setParameter("P_COD_EMPRESA", empresa);
			query.setParameter("P_GENERAR_VENTA", generaVenta);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}	
}
