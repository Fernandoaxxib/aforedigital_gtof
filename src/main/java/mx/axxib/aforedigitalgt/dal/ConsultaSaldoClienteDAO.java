package mx.axxib.aforedigitalgt.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoCliente;

@Repository
public class ConsultaSaldoClienteDAO {

	private final EntityManager entityManager;
	
	@Autowired
	public ConsultaSaldoClienteDAO(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaSaldoCliente> getConsultaNombre(){
		
		String storedFullName =  Constantes.DETALLE_SALDO_PACKAGE.concat(".").concat(Constantes.CONSULTA_VER_SALDO_NOMBRE_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ConsultaSaldo");
		
		query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
		
		List<ConsultaSaldoCliente> res = query.getResultList();
		return res;
	}
	@SuppressWarnings("unchecked")
	public List<ConsultaSaldoCliente> getConsultaID(){
		
		String storedFullName =  Constantes.DETALLE_SALDO_PACKAGE.concat(".").concat(Constantes.CONSULTA_VER_SALDO_ID_STORED);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ConsultaSaldo");
		
		query.registerStoredProcedureParameter("CP_DATOS", void.class, ParameterMode.REF_CURSOR);
		
		List<ConsultaSaldoCliente> res = query.getResultList();
		return res;
	}
}
