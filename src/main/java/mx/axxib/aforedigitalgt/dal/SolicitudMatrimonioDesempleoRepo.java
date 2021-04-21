package mx.axxib.aforedigitalgt.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosIcefasOut;
import mx.axxib.aforedigitalgt.eml.FopagosListOut;
import mx.axxib.aforedigitalgt.eml.VerChequeOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;

@Repository
public class SolicitudMatrimonioDesempleoRepo extends RepoBase {
	
	private final EntityManager entityManager;

	@Autowired
	public SolicitudMatrimonioDesempleoRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public VerChequeOut getVerCheque(String nss) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_CHEQUE_PACKAGE).concat(".").concat(Constantes.CONSULTA_VER_DETALLE_CHEQUE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

		query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);

		query.setParameter("P_NSS", nss);

		VerChequeOut res=new VerChequeOut();
		res.setCuenta((String) query.getOutputParameterValue("P_CUENTA"));
		res.setNombre((String) query.getOutputParameterValue("P_NOMBRE"));
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		res.setStatus((String) query.getOutputParameterValue("P_ESTATUS"));
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public VerSolicitudChequeOut getVerSolicitudCheque(String cuenta) throws AforeException {
		try {
		String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_CHEQUE_PACKAGE).concat(".").concat(Constantes.SOLICITUD_VER_DETALLE_CHEQUE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"VerSolicitudChequeListOut");

		query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);	
		query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);	
		query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);
		query.setParameter("P_CUENTA", cuenta);
		
		VerSolicitudChequeOut res=new VerSolicitudChequeOut();
		Object cursor = query.getOutputParameterValue("CP_CURSOR");
		if (cursor != null) {		
		res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
		
		res.setVerSolicitudChequeListOut(query.getResultList());
		}
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	@SuppressWarnings("unchecked")
	public VerPagoChequeOut getVerPagosCheque(String cuenta) throws AforeException {
		try {
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.DETALLE_CHEQUE_PACKAGE).concat(".").concat(Constantes.PAGOS_VER_DETALLE_CHEQUE_STORED);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"VerPagoChequeListOut");

			query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_CURSOR", void.class, ParameterMode.REF_CURSOR);	
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("P_ESTATUS", String.class, ParameterMode.OUT);	
			
			query.setParameter("P_CUENTA", cuenta);
			
			VerPagoChequeOut res=new VerPagoChequeOut();
			Object cursor = query.getOutputParameterValue("CP_CURSOR");
			if (cursor != null) {		
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));
			res.setStatus((String) query.getOutputParameterValue("P_ESTATUS"));
			res.setVerPagoChequeListOut(query.getResultList());
			}
			return res;
			} catch (Exception e) {
				throw GenericException(e);
			}

		}
	
	@SuppressWarnings("unchecked")
	//public List<FopagosListOut> getFopagos(Integer numSolicitud,String nss,String cuenta,String nombre) throws AforeException {
	public FopagosListOut getFopagos(Long numSolicitud,String nss,String cuenta,String nombre) throws AforeException {	
		try {
			String storedFullName =  Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CONSULTA_FOPAGOS_PACKAGE).concat(".").concat(Constantes.CONSULTA_FOPAGOS_NSS_STORED);
			//StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"FopagosListOut");
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("pNumero_SolicRet", Long.class, ParameterMode.IN);
			//query.registerStoredProcedureParameter("pNss", String.class, ParameterMode.IN);	
			query.registerStoredProcedureParameter("pCuenta", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("pNombre", String.class, ParameterMode.INOUT);
			query.registerStoredProcedureParameter("pNoPoliza_Pag", Integer.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pReferencia", String.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pFechaMov_Pag", Date.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pFecReinv_Pag", Date.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pTelefono_Pag", String.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pBenef_Re", String.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pPorcent_Re", Integer.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pEstatus_Re", String.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pPlaza_Re", String.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pMonBruto_Re", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("pMontoIsr_Re", Integer.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pMontoNeto_Re", Integer.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("pMensaje", String.class, ParameterMode.OUT);	
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			
			
			query.setParameter("pNumero_SolicRet", numSolicitud).setParameter("pCuenta", cuenta).setParameter("pNombre", nombre);	
			
//			List <FopagosListOut> res=query.getResultList();
			FopagosListOut res=new FopagosListOut();
			res.setPNombre((String) query.getOutputParameterValue("pNombre"));
			res.setPCuenta((String) query.getOutputParameterValue("pCuenta"));
			res.setPNoPoliza_Pag((Integer) query.getOutputParameterValue("pNoPoliza_Pag"));
			res.setPReferencia((String) query.getOutputParameterValue("pReferencia"));
			res.setPFechaMov_Pag((Date) query.getOutputParameterValue("pFechaMov_Pag"));
			res.setPFecReinv_Pag((Date) query.getOutputParameterValue("pFecReinv_Pag"));
			res.setPTelefono_Pag((String) query.getOutputParameterValue("pTelefono_Pag"));
			res.setPBenef_Re((String) query.getOutputParameterValue("pBenef_Re"));
			res.setPPorcent_Re((Integer) query.getOutputParameterValue("pPorcent_Re"));
			res.setPEstatus_Re((String) query.getOutputParameterValue("pEstatus_Re"));
			res.setPPlaza_Re((String) query.getOutputParameterValue("pPlaza_Re"));
			res.setPMonBruto_Re((Integer) query.getOutputParameterValue("pMonBruto_Re"));
			res.setPMontoIsr_Re((Integer) query.getOutputParameterValue("pMontoIsr_Re"));
			res.setPMontoNeto_Re((Integer) query.getOutputParameterValue("pMontoNeto_Re"));
			res.setPMensaje((String) query.getOutputParameterValue("pMensaje"));
			res.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));
			
			return res;
			} catch (Exception e) {
				throw GenericException(e);
			}

		
	}
	
}
