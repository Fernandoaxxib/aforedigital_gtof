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

	
}
