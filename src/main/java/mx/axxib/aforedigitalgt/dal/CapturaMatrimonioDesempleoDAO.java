package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialCuentaOut;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialNSSOut;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialNoResolucionOut;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialSaldoInversionOut;

@Repository
public class CapturaMatrimonioDesempleoDAO extends RepoBase {
	
	private final EntityManager entityManager;
	
	@Autowired
	public  CapturaMatrimonioDesempleoDAO (final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public CapturaRetiroParcialNSSOut obtenerNSS(Integer nss, Integer noSolicitud,String codEmpresa,Integer tipoPrestacion, Integer noResolucion) throws AforeException{
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_PACKAGE).concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_NSS_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "CapturaRetiroParcialNSSOut");
		
		query.registerStoredProcedureParameter("p_Nss", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_NumSolicitud", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodEmpresa", String.class, ParameterMode.IN);	
		query.registerStoredProcedureParameter("p_TipoPrestacion", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_NumResolucion", Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodCuenta", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Nombre_Afil", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_CURP", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Rfc_Afil", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Telefono", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_NumeroSS", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Porcentaje", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_TipoPago", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Origen", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
		
		query.setParameter("p_Nss", nss);
		query.setParameter("p_NumSolicitud", noSolicitud);
		query.setParameter("p_CodEmpresa", codEmpresa);
		query.setParameter("p_TipoPrestacion", tipoPrestacion);
		query.setParameter("p_NumResolucion", noResolucion);
		
		CapturaRetiroParcialNSSOut res=new CapturaRetiroParcialNSSOut();
		res.setP_CodCuenta((Integer) query.getOutputParameterValue("p_CodCuenta"));
		res.setP_Nombre_Afil((String) query.getOutputParameterValue("p_Nombre_Afil"));
		res.setP_CURP((String) query.getOutputParameterValue("p_CURP"));
		res.setP_Rfc_Afil((String) query.getOutputParameterValue("p_Rfc_Afil"));
		res.setP_Telefono((String) query.getOutputParameterValue("p_Telefono"));
		res.setP_NumeroSS((Integer) query.getOutputParameterValue("p_NumeroSS"));
		res.setP_Porcentaje((Integer) query.getOutputParameterValue("p_Porcentaje"));
		res.setP_TipoPago((String) query.getOutputParameterValue("p_TipoPago"));
		res.setP_Origen((String) query.getOutputParameterValue("p_Origen"));
		res.setP_Message((String) query.getOutputParameterValue("p_Message"));
		
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public CapturaRetiroParcialNoResolucionOut obtenerNoResolucion(String codEmpresa, Integer codCuenta,String tipoPrestacion,Integer noResolucion) throws AforeException{
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_PACKAGE).concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_NO_RESOLUCION_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "CapturaRetiroParcialNoResolucionOut");
		
		query.registerStoredProcedureParameter("p_CodEmpresa", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodCuenta", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_TipoPrestacion", String.class, ParameterMode.IN);	
		query.registerStoredProcedureParameter("p_NumResolucion", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("g_global", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
		
		
		query.setParameter("p_CodEmpresa", codEmpresa);
		query.setParameter("p_CodCuenta", codCuenta);
		query.setParameter("p_TipoPrestacion", tipoPrestacion);
		query.setParameter("p_NumResolucion", noResolucion);
		
		CapturaRetiroParcialNoResolucionOut res=new CapturaRetiroParcialNoResolucionOut();
		res.setG_global((String) query.getOutputParameterValue("g_global"));
		res.setP_Message((String) query.getOutputParameterValue("p_Message") );
				
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void ejecutarSiefore(String codCuenta, String codEmpresa,String codProducto,String codSistema,String tipoTransaccion,String tipoPrestacion, Date fechaSistema) throws AforeException{
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_PACKAGE).concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_SIEFORE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
	
		query.registerStoredProcedureParameter("p_CodCuenta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodEmpresa", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodProducto", String.class, ParameterMode.IN);	
		query.registerStoredProcedureParameter("p_CodSistemas", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_TipTransac_Ret", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_TipoPrestacion", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_FecSistema", Date.class, ParameterMode.IN);	
		
		query.setParameter("p_CodCuenta", codCuenta);
		query.setParameter("p_CodEmpresa", codEmpresa);
		query.setParameter("p_CodProducto", codProducto);
		query.setParameter("p_CodSistemas", codSistema);
		query.setParameter("p_TipTransac_Ret", tipoTransaccion);
		query.setParameter("p_TipoPrestacion", tipoPrestacion);
		query.setParameter("p_FecSistema", fechaSistema);
		
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public CapturaRetiroParcialCuentaOut obtenerCuenta (String tipPagoHab,Integer tipoPago,Integer cuenta,String curp,Integer nss,String esperaProc,String rechazaActualizar,String cuentaAlta) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_PACKAGE).concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_CUENTA_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
	
		query.registerStoredProcedureParameter("p_TipoPago_Hab", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_TipoPago", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Cuenta", Integer.class, ParameterMode.IN);	
		query.registerStoredProcedureParameter("p_Curp", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Nss", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Espera_ProcCont", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_Rechazada_Actualizar", String.class, ParameterMode.IN);	
		query.registerStoredProcedureParameter("p_CuentaDarAlta", String.class, ParameterMode.IN);	

		query.registerStoredProcedureParameter("p_TipoPago", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Cuenta", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Banco", String.class, ParameterMode.OUT);	
		query.registerStoredProcedureParameter("p_Nombre_De", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Rfc", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Telefono", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);	
	
		query.setParameter("p_TipoPago_Hab", tipPagoHab);
		query.setParameter("p_TipoPago", tipoPago);
		query.setParameter("p_Cuenta", cuenta);
		query.setParameter("p_Curp", curp);
		query.setParameter("p_Nss", nss);
		query.setParameter("p_Espera_ProcCont", esperaProc);
		query.setParameter("p_Rechazada_Actualizar", rechazaActualizar);
		query.setParameter("p_CuentaDarAlta", cuentaAlta);
				
		CapturaRetiroParcialCuentaOut res =new CapturaRetiroParcialCuentaOut();
		res.setP_TipoPago((Integer) query.getOutputParameterValue("p_TipoPago"));
		res.setP_Cuenta((Integer) query.getOutputParameterValue("p_Cuenta"));
		res.setP_Banco((String) query.getOutputParameterValue("p_Banco"));
		res.setP_Nombre_De((String) query.getOutputParameterValue("p_Nombre_De"));
		res.setP_Rfc((String) query.getOutputParameterValue("p_Rfc"));
		res.setP_Telefono((String) query.getOutputParameterValue("p_Telefono"));
		res.setP_Message((String) query.getOutputParameterValue("p_Message"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	
	public CapturaRetiroParcialSaldoInversionOut obtenerSaldoInversion(String codEmpresa,String codCuenta,String codTipoSaldo,String codInversion) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_PACKAGE).concat(".").concat(Constantes.CAPTURA_RETIRO_PARCIAL_INVERSION_SALDO_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
	
		query.registerStoredProcedureParameter("p_CodEmpresa", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodCuenta", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_CodTipo_Saldo", String.class, ParameterMode.IN);	
		query.registerStoredProcedureParameter("p_CodInversion", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_MonPrin_Saldo", Integer.class, ParameterMode.OUT);	
		query.registerStoredProcedureParameter("p_Desc_Siefore", String.class, ParameterMode.OUT);
				
		query.setParameter("p_CodEmpresa", codEmpresa);
		query.setParameter("p_CodCuenta", codCuenta);
		query.setParameter("p_CodTipo_Saldo", codTipoSaldo);
		query.setParameter("p_CodInversion", codInversion);
		
		CapturaRetiroParcialSaldoInversionOut res= new CapturaRetiroParcialSaldoInversionOut();
		res.setP_MonPrin_Saldo((Integer) query.getOutputParameterValue("p_MonPrin_Saldo"));
		res.setP_Desc_Siefore((String) query.getOutputParameterValue("p_Desc_Siefore"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}	
	}
}
