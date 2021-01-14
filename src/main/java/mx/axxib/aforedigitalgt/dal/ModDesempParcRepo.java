package mx.axxib.aforedigitalgt.dal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
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
public class ModDesempParcRepo extends RepoBase {

    private EntityManager entityManager = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

	public DatosSolicitudOut getDatosSolicitud(DatosSolicitudIn parametros) throws AforeException {
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

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CargaParcialidadesOut> getCargaParcialidades(CargaParcialidadesIn parametros) throws AforeException {
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_CARGA_PARCIALIDADES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"CargaParcialidadesOut");

			query.registerStoredProcedureParameter("p_num_solicitud", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_cod_empresa", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_PARCIALIDADES", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("p_num_solicitud", parametros.getNoSolicitud());
			query.setParameter("p_cod_empresa", parametros.getCodEmpresa());

			List<CargaParcialidadesOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<RetparDetaOut> getRetpar_Deta(RetparDetaIn parametros) throws AforeException {
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_RETPAR_DETA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "RetparDetaOut");

			query.registerStoredProcedureParameter("P_NUM_SOLICITUD", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUMERO_PAGO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_ADMINISTRATIVOS", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_NUM_SOLICITUD", parametros.getNoSolicitud());
			query.setParameter("P_NUMERO_PAGO", parametros.getNoPago());

			List<RetparDetaOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<MarcasOut> getMarcas(MarcasIn parametros) throws AforeException {
		try {

			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_MARCAS);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "MarcasOut");

			query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);

			query.setParameter("P_COD_CUENTA", parametros.getCodCuenta());

			List<MarcasOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ListaCancelacionOut> getListaCancelacion() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_LISTA_CANCELACION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"ListaCancelacionOut");

			query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);

			List<ListaCancelacionOut> res = query.getResultList();
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String cancelarSolicitud(CancelarSolicitudIn parametros) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_PACKAGE)
					.concat(".").concat(Constantes.MOD_DESEMPLEO_PARC_CANCELAR_SOLICITUD);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,
					"CancelarSolicitudOut");

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NUM_SOLICITUD", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_CVE_PROCESO_OPE", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_MESSAGE", String.class, ParameterMode.OUT);

			query.setParameter("P_NSS", parametros.getNss());
			query.setParameter("P_NUM_SOLICITUD", parametros.getNoSolicitud());
			query.setParameter("P_CVE_PROCESO_OPE", parametros.getCveProcesoOpe());

			String res = (String) query.getOutputParameterValue("P_MESSAGE");
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
