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
import mx.axxib.aforedigitalgt.eml.VerCheque;
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
	public List<VerCheque> getVerCheque(String nss) throws AforeException {
		try {
		String storedFullName =  Constantes.DETALLE_CHEQUE_PACKAGE.concat(".").concat(Constantes.CONSULTA_VER_DETALLE_CHEQUE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"VerCheque");

		query.registerStoredProcedureParameter("P_NSS", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("P_CUENTA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);

		query.setParameter("P_NSS", nss);

		List<VerCheque> res=query.getResultList();
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<VerSolicitudChequeOut> getVerSolicitudCheque() throws AforeException {
		try {
		String storedFullName =  Constantes.DETALLE_CHEQUE_PACKAGE.concat(".").concat(Constantes.SOLICITUD_VER_DETALLE_CHEQUE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"SolicitudChequeOut");

		query.registerStoredProcedureParameter("P_FECHA_OPERACION", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_DIAGNOSTICO_CUENTA", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_TIPO_PRESTACION", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_NUMERO_SOLIC_RET", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_fecha_solic", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_folio_solic", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_fecha_pension", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_numero_resolucion", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_fecha_resolucion", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_num_empleado", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_rigth_fax", String.class, ParameterMode.OUT);
		
		

		List<VerSolicitudChequeOut> res = query.getResultList();	
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
	@SuppressWarnings("unchecked")
	public List<VerPagoChequeOut> getVerPagosCheque() throws AforeException {
		try {
		String storedFullName =  Constantes.DETALLE_CHEQUE_PACKAGE.concat(".").concat(Constantes.PAGOS_VER_DETALLE_CHEQUE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName,"PagoChequeOut");

		query.registerStoredProcedureParameter("P_IDENTIFICADOR_OPERACION", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_TIPO_SEGURO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_TIPO_PENSION", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_TIPO_PRESTACION", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_FECHA_OPERACION", Date.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_IMPORTE_AUTORIZADO", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_IMPORTE_SUBCTA_RCV", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_SOLICITUD", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("P_diag_Op17", String.class, ParameterMode.OUT);
				

		List<VerPagoChequeOut> res = query.getResultList();	
		return res;
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
