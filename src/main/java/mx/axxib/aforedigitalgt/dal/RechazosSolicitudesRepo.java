package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.CatRechazosOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteIn;
import mx.axxib.aforedigitalgt.eml.GeneraReporteOut;
import mx.axxib.aforedigitalgt.eml.RechazosOut;

@Repository
@Transactional
public class RechazosSolicitudesRepo extends RepoBase {
	
	public RechazosOut getConsultaRechazos(String parametro, Integer tipo) throws AforeException {
//		 procedure prc_rechazos (P_FOLIO IN NUMBER,
//                 P_RESOLUCION IN VARCHAR2,
//                 P_NUM_SEGURO_SOCIAL IN VARCHAR2,
//                 P_NOMBRE       OUT VARCHAR2,
//                 P_R_FAX        OUT VARCHAR2,
//                 P_TIP_PRESTACION OUT VARCHAR2,
//                 P_DESC_PRESTACION  OUT VARCHAR2,
//                 P_TIPO_PENSION     OUT VARCHAR2,
//                 P_DESC_PENSION      OUT VARCHAR2,
//                 P_FEC_INCLUSION_RECH   OUT DATE,
//                 P_NUM_EMPLEADO   OUT VARCHAR2,
//                 P_NOM_EMPLEADO   OUT VARCHAR2,
//                 P_PLAZA          OUT VARCHAR2,
//                 P_CAUSA_RECHAZO  OUT VARCHAR2,
//                 P_CAUSA_RECHAZO2 OUT VARCHAR2,
//                 P_CAUSA_RECHAZO3 OUT VARCHAR2,
//                 P_DESC_CAUSA     OUT VARCHAR2,
//                 P_DESC_CAUSA2     OUT VARCHAR2,
//                 P_DESC_CAUSA3     OUT VARCHAR2,
//                 P_MENSAJE         OUT VARCHAR2,
//                 P_ESTATUS         OUT NUMBER); 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_PACKAGE)
					.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_CONSULTA_RECHAZOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_FOLIO", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_RESOLUCION", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_NUM_SEGURO_SOCIAL", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_R_FAX", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIP_PRESTACION", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DESC_PRESTACION", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIPO_PENSION", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DESC_PENSION", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FEC_INCLUSION_RECH", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_NUM_EMPLEADO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_NOM_EMPLEADO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_PLAZA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CAUSA_RECHAZO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CAUSA_RECHAZO2", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CAUSA_RECHAZO3", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DESC_CAUSA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DESC_CAUSA2", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DESC_CAUSA3", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			RechazosOut res = new RechazosOut();
			res.setTipo(tipo);
			if(tipo == 1) {
				query.setParameter("P_FOLIO", parametro);
				//res.setFolio(parametro);
			} else if(tipo == 2) {
				query.setParameter("P_RESOLUCION", parametro);
				//res.setResolucion(parametro);
			} else if(tipo == 3) {
				query.setParameter("P_NUM_SEGURO_SOCIAL", parametro);
				//res.setNss(parametro);
			}
			
			
			res.setFolio( (String) query.getOutputParameterValue("P_FOLIO") );
			res.setResolucion( (String) query.getOutputParameterValue("P_RESOLUCION") );
			res.setNss( (String) query.getOutputParameterValue("P_NUM_SEGURO_SOCIAL") );
			
			res.setNombre( (String) query.getOutputParameterValue("P_NOMBRE") );
			res.setFax( (String) query.getOutputParameterValue("P_R_FAX") );
			res.setTipoPrestacion( (String) query.getOutputParameterValue("P_TIP_PRESTACION") );
			res.setDescPrestacion( (String) query.getOutputParameterValue("P_DESC_PRESTACION") );
			res.setTipoPension( (String) query.getOutputParameterValue("P_TIPO_PENSION") );
			res.setDescPension( (String) query.getOutputParameterValue("P_DESC_PENSION") );
			res.setFechaInclusion( (Date) query.getOutputParameterValue("P_FEC_INCLUSION_RECH") );
			res.setNoEmpleado( (String) query.getOutputParameterValue("P_NUM_EMPLEADO") );
			res.setNombreEmpleado( (String) query.getOutputParameterValue("P_NOM_EMPLEADO") );
			res.setPlaza( (String) query.getOutputParameterValue("P_PLAZA") );
			res.setCausaRechazo( (String) query.getOutputParameterValue("P_CAUSA_RECHAZO") );
			res.setCausaRechazo2( (String) query.getOutputParameterValue("P_CAUSA_RECHAZO2") );
			res.setCausaRechazo3( (String) query.getOutputParameterValue("P_CAUSA_RECHAZO3") );
			res.setDescCausa( (String) query.getOutputParameterValue("P_DESC_CAUSA") );
			res.setDescCausa2( (String) query.getOutputParameterValue("P_DESC_CAUSA2") );
			res.setDescCausa3( (String) query.getOutputParameterValue("P_DESC_CAUSA3") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}


	
	@SuppressWarnings("unchecked")
	public CatRechazosOut getCatalogo() throws AforeException {
//		PROCEDURE PRC_CAT_RECHASOS(CP_DATOS OUT SYS_REFCURSOR,
//                P_ESTATUS  OUT NUMBER,
//                  P_MENSAJE   OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_PACKAGE)
					.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_CATALOGO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "CatRechazos");

			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			CatRechazosOut res = new CatRechazosOut();
			Object cursor = query.getOutputParameterValue("CP_DATOS");
			if (cursor != null) {
				res.setRechazos(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut ingresarRechazos(RechazosOut parametros) throws AforeException  {
//	    PROCEDURE PRC_INGRESAR_RECHAZOS(P_NSS IN VARCHAR2,
//                P_RESOLUCION IN NUMBER,
//                P_FOLIO     IN NUMBER,
//                P_CAUSA_RECHAZO  IN NUMBER,
//                P_CAUSA_RECHAZO2 IN NUMBER,
//                P_CAUSA_RECHAZO3 IN NUMBER,
//                P_CAUSA          IN VARCHAR2,
//                P_CAUSA2          IN VARCHAR2,
//                P_CAUSA3          IN VARCHAR2,
//                P_TIPO_PRESTACION  IN NUMBER,
//                P_TIPO_PENSION     IN VARCHAR2,
//                P_MENSAJE          OUT VARCHAR2,
//                P_ESTATUS          OUT NUMBER);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_PACKAGE)
					.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_INGRESAR_RECHAZOS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_RESOLUCION", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FOLIO", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_CAUSA_RECHAZO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CAUSA_RECHAZO2", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CAUSA_RECHAZO3", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CAUSA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CAUSA2", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CAUSA3", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_TIPO_PRESTACION", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIPO_PENSION", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			
			
			query.setParameter("P_FOLIO", parametros.getFolio());
			query.setParameter("P_RESOLUCION", parametros.getResolucion());
			query.setParameter("P_NSS", parametros.getNss());
				
			query.setParameter("P_CAUSA_RECHAZO", parametros.getCausaRechazo());
			query.setParameter("P_CAUSA_RECHAZO2", parametros.getCausaRechazo2());
			query.setParameter("P_CAUSA_RECHAZO3", parametros.getCausaRechazo3());
			
			query.setParameter("P_CAUSA", parametros.getDescCausa());
			query.setParameter("P_CAUSA2", parametros.getDescCausa2());
			query.setParameter("P_CAUSA3", parametros.getDescCausa3());
			
			query.setParameter("P_TIPO_PRESTACION", parametros.getTipoPrestacion());
			query.setParameter("P_TIPO_PENSION", parametros.getTipoPension());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public GeneraReporteOut generaReporte(GeneraReporteIn parametros) throws AforeException {
//		PROCEDURE prc_genera_reporte   (p_fec_ini in date,
//                p_fec_fin  in date,
//                P_NOM_ARCHIVO IN VARCHAR2,
//                P_ESTATUS out varchar2,
//                p_MENSAJE  out	varchar2,
//                p_seguimiento OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_PACKAGE)
					.concat(".").concat(Constantes.RECHAZOS_SOLICITUDES_GENERA_REPORTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_fec_ini", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_fec_fin", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NOM_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_seguimiento", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_MENSAJE", String.class, ParameterMode.OUT);
			

			query.setParameter("p_fec_ini", parametros.getFechaInicial());
			query.setParameter("p_fec_fin", parametros.getFechaFinal());
			query.setParameter("P_NOM_ARCHIVO", parametros.getNombreArchivo());

			GeneraReporteOut res = new GeneraReporteOut();
			res.setSeguimiento( (String) query.getOutputParameterValue("p_seguimiento"));
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("p_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
