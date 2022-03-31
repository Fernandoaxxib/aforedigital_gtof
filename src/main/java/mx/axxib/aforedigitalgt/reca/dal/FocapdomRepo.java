package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.DatosInicialesFocapdom;
import mx.axxib.aforedigitalgt.reca.eml.FocapdomOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio FOCAPDOM
//** Interventor Principal: JJSC
//** Fecha Creación: 24/03/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class FocapdomRepo extends RepoBase {
	
	public FocapdomOut getDatosIniciales() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.FOCAPDOM_PACKAGE)
					.concat(".").concat(Constantes.FOCAPDOM_DATOS_INICIALES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_COD_EMPRESA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIPO_INCREMENTO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COMTEL_CVE_COMPANIA", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS_INCRE", Integer.class, ParameterMode.OUT);			
			query.registerStoredProcedureParameter("P_CVE_EST_SOL", Integer.class, ParameterMode.OUT);		
			query.registerStoredProcedureParameter("P_SOLICITUD", String.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_CVE_EST_REG", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DESC_EST_REG", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FEC_CAPTURA", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_ORIG_SOL", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_TIPO_PERSONA", Integer.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_DUMMY_TIPO_PERSONA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_TIPO_IDENT", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DUMMY_TIPO_IDENTIFICACION", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_ORIG_APORTA", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DUMMY_ORIGEN_APORTA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_TIPO_PERIODO", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DUMMY_PERIODO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_BANCO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DUMMY_BANCO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_TIPO_CTA", String.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_DUMMY_TIPO_CUENTA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_TIPO_APORTA", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_TIPO_SIEFORE", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CREADO_POR", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FEC_CREACION", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MODIF_POR", String.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_FEC_MODIF", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ORIG_MONTO_OTROS", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ID_REFERENCIA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_TIPO_MOV", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DUMMY_TIP_MOV", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DUMMY_COMP_CELULAR", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_BENEF_FISCAL", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_DUMMY_BENEFICIARIO_FISCAL", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_CVE_ORIG_CAPTURA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FECHA_COBRO", String.class, ParameterMode.OUT);
			
			FocapdomOut resp= new FocapdomOut();
			DatosInicialesFocapdom dato = new DatosInicialesFocapdom();

			dato.setCod_empresa((String) query.getOutputParameterValue("P_COD_EMPRESA"));
			dato.setTipo_incremento((String) query.getOutputParameterValue("P_TIPO_INCREMENTO"));
			dato.setComtel_cve_compania((Integer) query.getOutputParameterValue("P_COMTEL_CVE_COMPANIA"));
			dato.setEstatus_incre((Integer) query.getOutputParameterValue("P_ESTATUS_INCRE"));
			dato.setCve_est_sol((Integer) query.getOutputParameterValue("P_CVE_EST_SOL"));
			dato.setSolicitud((String) query.getOutputParameterValue("P_SOLICITUD"));
						
			dato.setCve_est_reg((String) query.getOutputParameterValue("P_CVE_EST_REG"));
			dato.setDesc_est_reg((String) query.getOutputParameterValue("P_DESC_EST_REG"));
			dato.setFec_captura((Date) query.getOutputParameterValue("P_FEC_CAPTURA"));
			dato.setCve_orig_aporta((Integer) query.getOutputParameterValue("P_CVE_ORIG_SOL"));
			dato.setCve_tipo_persona((Integer) query.getOutputParameterValue("P_CVE_TIPO_PERSONA"));
						
			dato.setDummy_tipo_persona((String) query.getOutputParameterValue("P_DUMMY_TIPO_PERSONA"));
			dato.setCve_tipo_ident((Integer) query.getOutputParameterValue("P_CVE_TIPO_IDENT"));
			dato.setDummy_tipo_identificacion((String) query.getOutputParameterValue("P_DUMMY_TIPO_IDENTIFICACION"));
			dato.setCve_orig_aporta((Integer) query.getOutputParameterValue("P_CVE_ORIG_APORTA"));
			dato.setDummy_origen_aporta((String) query.getOutputParameterValue("P_DUMMY_ORIGEN_APORTA"));
			dato.setCve_tipo_periodo((Integer) query.getOutputParameterValue("P_CVE_TIPO_PERIODO"));
			dato.setDummy_periodo((String) query.getOutputParameterValue("P_DUMMY_PERIODO"));
			dato.setCve_banco((String) query.getOutputParameterValue("P_CVE_BANCO"));
			dato.setDummy_banco((String) query.getOutputParameterValue("P_DUMMY_BANCO"));
			dato.setCve_tipo_cta((String) query.getOutputParameterValue("P_CVE_TIPO_CTA"));			
						
			dato.setDummy_tipo_cuenta((String) query.getOutputParameterValue("P_DUMMY_TIPO_CUENTA"));
			dato.setCve_tipo_aporta((Integer) query.getOutputParameterValue("P_CVE_TIPO_APORTA"));
			dato.setCve_tipo_siefore((Integer) query.getOutputParameterValue("P_CVE_TIPO_SIEFORE"));
			dato.setCreado_por((String) query.getOutputParameterValue("P_CREADO_POR"));
			dato.setFec_creacion((Date) query.getOutputParameterValue("P_FEC_CREACION"));
			dato.setModif_por((String) query.getOutputParameterValue("P_MODIF_POR"));
						
			dato.setFec_modif((Date) query.getOutputParameterValue("P_FEC_MODIF"));
			dato.setOrig_monto_otros((String) query.getOutputParameterValue("P_ORIG_MONTO_OTROS"));
			dato.setId_referencia((String) query.getOutputParameterValue("P_ID_REFERENCIA"));
			dato.setTipo_mov((String) query.getOutputParameterValue("P_TIPO_MOV"));
			dato.setDummy_tip_mov((String) query.getOutputParameterValue("P_DUMMY_TIP_MOV"));
			dato.setDummy_comp_celular((String) query.getOutputParameterValue("P_DUMMY_COMP_CELULAR"));
			dato.setCve_benef_fiscal((Integer) query.getOutputParameterValue("P_CVE_BENEF_FISCAL"));
			dato.setDummy_beneficiario_fiscal((String) query.getOutputParameterValue("P_DUMMY_BENEFICIARIO_FISCAL"));
			dato.setCve_orig_captura((String) query.getOutputParameterValue("P_CVE_ORIG_CAPTURA"));
			dato.setFecha_cobro((String) query.getOutputParameterValue("P_FECHA_COBRO"));			

			resp.setDatosIniciales(dato);
			return resp;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut cargaMasiva(String ic_NOMBRE_ARCHIVO) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.FOCAPDOM_PACKAGE)
					.concat(".").concat(Constantes.FOCAPDOM_CARGA_MASIVA_BTN_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("ic_NOMBRE_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("oc_SALIDA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_NOMBRE_ARCHIVO", ic_NOMBRE_ARCHIVO);

			FocapdomOut res = new FocapdomOut();
			res.setSalida((String) query.getOutputParameterValue("oc_SALIDA"));
			res.setEstatus((Integer) query.getOutputParameterValue("on_Estatus"));
			res.setMensaje((String) query.getOutputParameterValue("oc_Mensaje"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
