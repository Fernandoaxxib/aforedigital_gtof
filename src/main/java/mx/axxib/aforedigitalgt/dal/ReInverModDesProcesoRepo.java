
package mx.axxib.aforedigitalgt.dal;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

//***********************************************//
//** Funcionalidad: Repositorio - Reinversiones a básicas parcialidades - Proceso
//** Desarrollador: JJSC
//** Fecha de creación: 01/Feb/2021
//** Última modificación:
//***********************************************//
@Repository
@Transactional
public class ReInverModDesProcesoRepo extends RepoBase {

	private final EntityManager entityManager;

	@Autowired
	public ReInverModDesProcesoRepo(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public BigDecimal getValorCuentas() throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE).concat(".")
					.concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_VALOR_CUENTAS);

			BigDecimal value = (BigDecimal) entityManager.createNativeQuery("SELECT " + storedFullName + " FROM DUAL")
					.getSingleResult();
			return value;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public EjecucionResult procesoEjecutar(Integer p_Operacion, Date p_FechaInicial, Date p_FechaFinal,
			Date p_Fecha_Lote) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PACKAGE).concat(".")
					.concat(Constantes.REINVER_BASICAS_MODULO_DESEMPLEO_PROCESO_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("p_Operacion", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_FechaInicial", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_FechaFinal", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Fecha_Lote", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_Cuentas", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_Avance", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_Fecha_Trans", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_Ruta_Arch_Trans", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_Arch_Sal_Trans", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);

			query.setParameter("p_Operacion", p_Operacion);
			query.setParameter("p_FechaInicial", p_FechaInicial);
			query.setParameter("p_FechaFinal", p_FechaFinal);
			query.setParameter("p_Fecha_Lote", p_Fecha_Lote);

			EjecucionResult result = new EjecucionResult();

			result.setOcAvance((String) query.getOutputParameterValue("p_Avance"));
			result.setOcMensaje((String) query.getOutputParameterValue("p_Message"));
			result.setOn_Estatus((Integer) query.getOutputParameterValue("on_Estatus"));

			return result;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
