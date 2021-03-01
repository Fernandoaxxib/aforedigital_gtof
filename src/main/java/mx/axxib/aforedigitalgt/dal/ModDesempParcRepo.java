package mx.axxib.aforedigitalgt.dal;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.CancelarSolicitudIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesOut;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudIn;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudOut;
import mx.axxib.aforedigitalgt.eml.ListaCancelacionOut;
import mx.axxib.aforedigitalgt.eml.MarcasIn;
import mx.axxib.aforedigitalgt.eml.MarcasOut;
import mx.axxib.aforedigitalgt.eml.RetparDetaIn;
import mx.axxib.aforedigitalgt.eml.RetparDetaOut;

@Repository
@Transactional
public class ModDesempParcRepo extends RepoBase {

    private EntityManager entityManager = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

	public DatosSolicitudOut getDatosSolicitud(DatosSolicitudIn parametros) throws AforeException {
//		 PROCEDURE  PRC_DATOS_SOLICITUD(P_NSS IN VARCHAR2,
//                 P_NUM_SOLICITUD    OUT VARCHAR2, 
//                 P_CURP  OUT VARCHAR2 ,  
//                 P_RFC    OUT VARCHAR2, 
//                 P_PRIMER_APELLIDO   OUT VARCHAR2 ,
//                 P_SEGUNDO_APELLIDO  OUT VARCHAR2 ,
//                 P_NOMBRE           OUT  VARCHAR2  ,
//                 P_MON_TOTAL_PRESTACION OUT  VARCHAR2 ,
//                 P_SBC_TIPO_A     OUT VARCHAR2 ,
//                 P_COD_EMPRESA      OUT VARCHAR2,
//                 P_CLASIFICACION_PAGO    OUT  VARCHAR2,
//                 P_ESTATUS_SOLICITUD  OUT VARCHAR2,
//                 P_NUM_RESOLUCION    OUT VARCHAR2  ,
//                 P_FONDO             OUT  VARCHAR2,
//                 P_COD_CUENTA        OUT VARCHAR2 ,
//                 P_FECHA_ACCION        OUT DATE,
//                 P_FONDO_DESCRIPCION  OUT VARCHAR2,
//                 P_PAGO_DESC   OUT VARCHAR2,
//                 P_REMANENTE  OUT VARCHAR2,
//                  P_MENSAJE    OUT VARCHAR2,
//                 P_ESTATUS    OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_DATOS_SOLICITUD);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUM_SOLICITUD", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CURP", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_RFC", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_PRIMER_APELLIDO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_SEGUNDO_APELLIDO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MON_TOTAL_PRESTACION", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_SBC_TIPO_A", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CLASIFICACION_PAGO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS_SOLICITUD", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_NUM_RESOLUCION", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FONDO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FECHA_ACCION", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FONDO_DESCRIPCION", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_PAGO_DESC", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_REMANENTE", BigDecimal.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_NSS", parametros.getNss());

			DatosSolicitudOut res = new DatosSolicitudOut();
			res.setNoSolicitud((String) query.getOutputParameterValue("P_NUM_SOLICITUD"));
			res.setCurp((String) query.getOutputParameterValue("P_CURP"));
			res.setRfc((String) query.getOutputParameterValue("P_RFC"));
			res.setPrimerApellido((String) query.getOutputParameterValue("P_PRIMER_APELLIDO"));
			res.setSegundoApellido((String) query.getOutputParameterValue("P_SEGUNDO_APELLIDO"));
			res.setNombre((String) query.getOutputParameterValue("P_NOMBRE"));
			res.setMonTotalPrestacion((BigDecimal) query.getOutputParameterValue("P_MON_TOTAL_PRESTACION"));
			res.setSbcTipoA((Integer) query.getOutputParameterValue("P_SBC_TIPO_A"));
			res.setCodEmpresa((String) query.getOutputParameterValue("P_COD_EMPRESA"));
			res.setClasificacionPago((String) query.getOutputParameterValue("P_CLASIFICACION_PAGO"));
			res.setEstatusSolicitud((String) query.getOutputParameterValue("P_ESTATUS_SOLICITUD"));
			res.setNumResolucion((String) query.getOutputParameterValue("P_NUM_RESOLUCION"));
			res.setFondo((String) query.getOutputParameterValue("P_FONDO"));
			res.setCodCuenta((String) query.getOutputParameterValue("P_COD_CUENTA"));
			res.setFechaAccion((Date) query.getOutputParameterValue("P_FECHA_ACCION"));
			res.setFondoDescripcion((String) query.getOutputParameterValue("P_FONDO_DESCRIPCION"));
			res.setPagoDescripcion((String) query.getOutputParameterValue("P_PAGO_DESC"));
			res.setRemanente((BigDecimal) query.getOutputParameterValue("P_REMANENTE"));
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public CargaParcialidadesOut getCargaParcialidades(CargaParcialidadesIn parametros) throws AforeException {
//		 PROCEDURE PRC_CARGA_PARCIALIDADES(p_num_solicitud  IN VARCHAR2,              
//                 p_cod_empresa   IN NUMBER,             
//                 CP_PARCIALIDADES  OUT SYS_REFCURSOR,
//                 P_MENSAJE       OUT VARCHAR2,
//                 P_ESTATUS       OUT NUMBER);
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_CARGA_PARCIALIDADES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"CargaParcialidades");

			query.registerStoredProcedureParameter("p_num_solicitud", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_cod_empresa", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_PARCIALIDADES", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("p_num_solicitud", parametros.getNoSolicitud());
			query.setParameter("p_cod_empresa", parametros.getCodEmpresa());


			CargaParcialidadesOut res = new CargaParcialidadesOut();
			Object cursor = query.getOutputParameterValue("CP_PARCIALIDADES");
			if (cursor != null) {
				res.setParcialidades(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public RetparDetaOut getRetpar_Deta(RetparDetaIn parametros) throws AforeException {
//		 PROCEDURE  PRC_RETPAR_DETA ( P_NUM_SOLICITUD    IN VARCHAR2,
//                 P_NUMERO_PAGO      IN VARCHAR2,
//                 CP_ADMINISTRATIVOS OUT SYS_REFCURSOR,
//                 P_MENSAJE          OUT VARCHAR2,
//                 P_ESTATUS          OUT NUMBER);  
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_RETPAR_DETA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "RetparDeta");

			query.registerStoredProcedureParameter("P_NUM_SOLICITUD", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUMERO_PAGO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_ADMINISTRATIVOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_NUM_SOLICITUD", parametros.getNoSolicitud());
			query.setParameter("P_NUMERO_PAGO", parametros.getNoPago());

			RetparDetaOut res = new RetparDetaOut();
			Object cursor = query.getOutputParameterValue("CP_ADMINISTRATIVOS");
			if (cursor != null) {
				res.setAdministrativos(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public MarcasOut getMarcas(MarcasIn parametros) throws AforeException {
//		 PROCEDURE   PRC_MARCAS(P_COD_CUENTA     IN  VARCHAR2, 
//                 CP_DATOS  OUT SYS_REFCURSOR,
//                 P_MENSAJE  OUT VARCHAR2,
//                 P_ESTATUS  OUT NUMBER);  
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_MARCAS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "Marcas");

			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_COD_CUENTA", parametros.getCodCuenta());

			MarcasOut res = new MarcasOut();
			Object cursor = query.getOutputParameterValue("CP_DATOS");
			if (cursor != null) {
				res.setDatos(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ListaCancelacionOut getListaCancelacion() throws AforeException {
//		 PROCEDURE PRC_LISTA_CANCELACION(CP_CURSOR OUT SYS_REFCURSOR,
//                 P_MENSAJE OUT VARCHAR2,
//                 P_ESTATUS OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_LISTA_CANCELACION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ListaCancelacion");

			query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			ListaCancelacionOut res = new ListaCancelacionOut();
			Object cursor = query.getOutputParameterValue("CP_CURSOR");
			if (cursor != null) {
				res.setDatos(query.getResultList());
				res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
				res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			}
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut cancelarSolicitud(CancelarSolicitudIn parametros) throws AforeException {
//		  PROCEDURE PRC_CANCELAR_SOLICITUD(P_NSS IN VARCHAR2,
//                  P_NUM_SOLICITUD IN VARCHAR2,
//                  P_CVE_PROCESO_OPE IN NUMBER,
//                  P_MESSAGE OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_CANCELAR_SOLICITUD);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUM_SOLICITUD", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CVE_PROCESO_OPE", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_NSS", parametros.getNss());
			query.setParameter("P_NUM_SOLICITUD", parametros.getNoSolicitud());
			query.setParameter("P_CVE_PROCESO_OPE", parametros.getCveProcesoOpe());

			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
