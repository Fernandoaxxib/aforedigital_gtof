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
import mx.axxib.aforedigitalgt.reca.eml.EstatusOut;
import mx.axxib.aforedigitalgt.reca.eml.RefPagoOut;
import mx.axxib.aforedigitalgt.reca.eml.SaldosIndiOut;
import mx.axxib.aforedigitalgt.reca.eml.SaldosSiefOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Reporte Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Feb/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class ReporteConciliadosRepo extends RepoBase {
	
	@SuppressWarnings("unchecked")
	public RefPagoOut refPago(Date fechaI, Date fechaF, Integer estatus) throws AforeException {
//		PROCEDURE PRC_REPORTE_REFPAGO(P_FEC_INI   IN DATE,
//      P_FEC_FIN   IN DATE,
//      P_LIST394     IN number,
//      CP_CONSOLIDADO OUT SYS_REFCURSOR,
//      P_MENSAJE OUT VARCHAR2,
//      P_ESTATUS OUT NUMBER);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_REFPAGO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "RefPago");
			
			query.registerStoredProcedureParameter("P_FEC_INI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LIST394", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("CP_CONSOLIDADO", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FEC_INI", fechaI);
			query.setParameter("P_FEC_FIN", fechaF);
			query.setParameter("P_LIST394", estatus);
			
			RefPagoOut res = new RefPagoOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public BaseOut reintPen(Date fechaI, Date fechaF) throws AforeException {
//		PROCEDURE PRC_REPORTE_SOLPEND(P_FEC_INI IN DATE,
//      P_FEC_FIN IN DATE,
//      P_MENSAJE OUT VARCHAR2,
//      P_ESTATUS OUT NUMBER);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_SOLPEND);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_FEC_INI", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_FIN", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FEC_INI", fechaI);
			query.setParameter("P_FEC_FIN", fechaF);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public SaldosSiefOut saldosSief(Date fecha) throws AforeException {
//		PROCEDURE  PRC_RepSaldosSief (p_Fecha in date,
//      P_CUR_SALDO_SIEF OUT SYS_REFCURSOR,
//      P_MENSAJE out VARCHAR2);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_REPSALDOSSIEF);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SaldosSief");
			
			query.registerStoredProcedureParameter("p_Fecha", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_CUR_SALDO_SIEF", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("p_Fecha", fecha);
			
			SaldosSiefOut res = new SaldosSiefOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public SaldosIndiOut saldosIndi(Date fecha) throws AforeException {
//		PROCEDURE  PRC_RepSaldosIndi (p_Fecha in date,
//      P_CUR_SALDO_INDI OUT SYS_REFCURSOR,
//      P_MENSAJE out VARCHAR2) ;   
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_REPSALDOSINDI);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "SaldosIndi");
			
			query.registerStoredProcedureParameter("p_Fecha", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_CUR_SALDO_INDI", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("p_Fecha", fecha);
			
			SaldosIndiOut res = new SaldosIndiOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public BaseOut reintOp(Date fecha) throws AforeException {
//		PROCEDURE PRC_REPORTE_SOLOPLIQ(P_FECHA IN DATE,
//      P_MENSAJE OUT VARCHAR2,
//       P_ESTATUS OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_SOLOPLIQ);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", fecha);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public BaseOut cargarArchivo(String directorio, String archivo, Date fecha) throws AforeException {
//		PROCEDURE PRC_CARGAR_ARCHIVO(P_directorio IN VARCHAR2,
//  P_ARCHIVO        IN VARCHAR2,
//  P_FEC_NOTIF IN DATE,
//  P_MENSAJE OUT VARCHAR2,
//  P_ESTATUS OUT NUMBER
//  );
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_CARGAR_ARCHIVO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_directorio", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_NOTIF", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_directorio", directorio);
			query.setParameter("P_ARCHIVO", archivo);
			query.setParameter("P_FEC_NOTIF", fecha);
			
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public EstatusOut estatus() throws AforeException {
//		PROCEDURE PRC_ESTATUS(CP_ESTATUS OUT SYS_REFCURSOR,
//                P_MENSAJE  OUT VARCHAR2,
//                P_ESTATUS  OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.REPORTE_CONCILIADOS_ESTATUS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "Estatus");
			
			query.registerStoredProcedureParameter("CP_ESTATUS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

		
			EstatusOut res = new EstatusOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
