package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosIcefasOut;


@Repository
@Transactional
public class ConsultarMarcasCuentasRepo extends RepoBase{
	
	private final EntityManager entityManager;

	@Autowired
	public ConsultarMarcasCuentasRepo (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public ConsultarTraspasosIcefasOut getConsultarTraspasos(String codCuenta,String codTipoSaldo,String claveProceso, String estado) throws AforeException {
		try {
		System.out.println("Parametros codCuenta,codTipoSaldo,claveProceso,estado+: "+codCuenta+"|"+codTipoSaldo+"|"+claveProceso+"|"+estado);
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTAR_TRASPASOS_PACKAGE).concat(".").concat(Constantes.CONSULTAR_TRASPASOS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"ConsultarDatosIcefasOut");

		
		query.registerStoredProcedureParameter("P_COD_CUENTA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_COD_TIPSALDO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CLAVE_PROCESO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_ESTADO", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		
		query.setParameter("P_COD_CUENTA", codCuenta);
		query.setParameter("P_COD_TIPSALDO", codTipoSaldo);
		query.setParameter("P_CLAVE_PROCESO", claveProceso);
		query.setParameter("P_ESTADO", estado);
		

		ConsultarTraspasosIcefasOut res=new ConsultarTraspasosIcefasOut();
		Object cursor = query.getOutputParameterValue("CP_CURSOR");
		if (cursor != null) {
			res.setCpCursor(query.getResultList());
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		}
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public ConsultarNombreCuentaIcefasOut getConsultarCurp(String curp) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTAR_TRASPASOS_PACKAGE).concat(".").concat(Constantes.CONSULTAR_TRASPASOS_CURP_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"CpDatosIcefasOut");

		query.registerStoredProcedureParameter("P_CURP", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_cuenta", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_nombre", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.setParameter("P_CURP", curp);
		
		ConsultarNombreCuentaIcefasOut res= new ConsultarNombreCuentaIcefasOut ();
		Object cursor = query.getOutputParameterValue("CP_DATOS");
		System.out.println("Cursor CURP: "+cursor);
		if (cursor != null) {
			res.setCuenta((Integer)query.getOutputParameterValue("P_cuenta"));
			res.setNombre((String) query.getOutputParameterValue("P_nombre"));
			res.setCurp_o_nss((String)query.getOutputParameterValue("P_NSS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		}
		System.out.println("RES CURP: "+res);
		res.setCpDatos(query.getResultList());
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public ConsultarNombreCuentaIcefasOut getConsultarNss(String nss) throws AforeException {
		try {
			System.out.println("PARAMETRO NSS: "+nss);
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTAR_TRASPASOS_PACKAGE).concat(".").concat(Constantes.CONSULTAR_TRASPASOS_NSS_STORED);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"CpDatosIcefasOut");

			query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_cuenta", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_nombre", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_curp", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			query.setParameter("P_NSS", nss);
			
			ConsultarNombreCuentaIcefasOut res= new ConsultarNombreCuentaIcefasOut ();
			Object cursor = query.getOutputParameterValue("CP_DATOS");
			System.out.println("Cursor NSS"+cursor);
			if (cursor != null) {
				res.setCuenta((Integer)query.getOutputParameterValue("P_cuenta"));
				res.setNombre((String) query.getOutputParameterValue("P_nombre"));
				res.setCurp_o_nss((String)query.getOutputParameterValue("P_curp"));
				res.setCpDatos(query.getResultList());
				res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			}
			System.out.println("res NSS"+res);
			
			return res;
			} catch (Exception e) {
				throw GenericException(e);
			}

	}
}
