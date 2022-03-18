package mx.axxib.aforedigitalgt.reca.dal;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.BonoOut;
import mx.axxib.aforedigitalgt.reca.eml.FechaCorteOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Consulta bono pensión
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class ConsultaBonoPensionRepo extends RepoBase {
 
	public BonoOut consultaBono(String curp) throws AforeException {
//		 PROCEDURE PRC_CONSULTA_BONO_DE_PENSION(P_CURP IN VARCHAR2,
//      P_EDAD OUT NUMBER,
//      P_NOMBRE OUT VARCHAR2,
//      P_PATERNO OUT VARCHAR2,
//      P_MATERNO OUT VARCHAR2,
//      P_BONO_INI OUT NUMBER,
//      P_MONTO_EN_PESOS OUT NUMBER,
//      P_FEC_ACREDITACION OUT DATE,
//      P_FEC_VENCIMIENTO  OUT DATE,
//      P_VALOR_UDI       OUT NUMBER,
//      P_AJUSTE_BONO      OUT NUMBER,
//      P_MONT_PESOS    OUT NUMBER,
//      P_FEC_AJUSTE       OUT DATE,
//      P_FEC_AJUS_VENCIMIENTO OUT DATE,
//      P_VALOR_AUDI_AJUSTE   OUT NUMBER,
//      P_BONO_TOTAL   OUT NUMBER,
//      P_COD_CUENTA   OUT NUMBER,
//      P_MENSAJE  OUT VARCHAR2,
//      P_ESTATUS  OUT NUMBER); 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CONSULTA_BONO_PACKAGE).concat(".")
					.concat(Constantes.CONSULTA_BONO_PENSION);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_CURP", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_EDAD", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_PATERNO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MATERNO", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_BONO_INI", BigDecimal.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_MONTO_EN_PESOS", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FEC_ACREDITACION", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FEC_VENCIMIENTO", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_VALOR_UDI", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_AJUSTE_BONO", BigDecimal.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_MONT_PESOS", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FEC_AJUSTE", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_FEC_AJUS_VENCIMIENTO", Date.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_VALOR_AUDI_AJUSTE", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_BONO_TOTAL", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COD_CUENTA", Integer.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_CURP", curp);
			
			BonoOut res = new BonoOut();
			res.setEdad( (Integer) query.getOutputParameterValue("P_EDAD") );
			res.setNombre( (String) query.getOutputParameterValue("P_NOMBRE") );
			res.setAPaterno( (String) query.getOutputParameterValue("P_PATERNO") );
			res.setAMaterno( (String) query.getOutputParameterValue("P_MATERNO") );
			res.setBonoIni( (BigDecimal) query.getOutputParameterValue("P_BONO_INI") );
			
			res.setMontoPesos( (BigDecimal) query.getOutputParameterValue("P_MONTO_EN_PESOS") );
			res.setFecAcredit( (Date) query.getOutputParameterValue("P_FEC_ACREDITACION") );
			res.setFecVenc( (Date) query.getOutputParameterValue("P_FEC_VENCIMIENTO") );
			res.setValorUDI( (BigDecimal) query.getOutputParameterValue("P_VALOR_UDI") );
			res.setAjusteBono( (BigDecimal) query.getOutputParameterValue("P_AJUSTE_BONO") );
			
			res.setMontoPesos2( (BigDecimal) query.getOutputParameterValue("P_MONT_PESOS") );
			res.setFecAjuste( (Date) query.getOutputParameterValue("P_FEC_AJUSTE") );
			res.setFecAjusteVenc( (Date) query.getOutputParameterValue("P_FEC_AJUS_VENCIMIENTO") );
			res.setValorAudi( (BigDecimal) query.getOutputParameterValue("P_VALOR_AUDI_AJUSTE") );
			res.setBonoTotal( (BigDecimal) query.getOutputParameterValue("P_BONO_TOTAL") );
			res.setCodCuenta( (Integer) query.getOutputParameterValue("P_COD_CUENTA") );
			
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public FechaCorteOut fechaCorte(Date fecha, Integer codCuenta) throws AforeException {
		//PROCEDURE PRC_FECHA_CORTE (P_FEC_CORTE IN DATE,
	//   P_COD_CUENTA IN NUMBER,
	//   P_VALOR_UDI_DIA OUT NUMBER,
	//   P_PAGO_ANT_UDIS  OUT NUMBER,
	//   P_COMP_VAL_NOM_UDIS OUT NUMBER,
	//   P_VAL_PESOS_PAG_ANT  OUT NUMBER,
	//   P_VAL_PESOS_COM_VALOR_NOM OUT NUMBER,
	//   P_MENSAJE  OUT VARCHAR2,
	//   P_ESTATUS  OUT NUMBER);   
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CONSULTA_BONO_PACKAGE).concat(".")
					.concat(Constantes.CONSULTA_BONO_FECHA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_FEC_CORTE", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_COD_CUENTA", Integer.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_VALOR_UDI_DIA", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_PAGO_ANT_UDIS", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_COMP_VAL_NOM_UDIS", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_VAL_PESOS_PAG_ANT", BigDecimal.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_VAL_PESOS_COM_VALOR_NOM", BigDecimal.class, ParameterMode.OUT);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FEC_CORTE", fecha);
			query.setParameter("P_COD_CUENTA", codCuenta);
			
			FechaCorteOut res = new FechaCorteOut();
			res.setValorUDI( (BigDecimal) query.getOutputParameterValue("P_VALOR_UDI_DIA") );
			res.setPagoAnt( (BigDecimal) query.getOutputParameterValue("P_PAGO_ANT_UDIS") );
			res.setCompVal( (BigDecimal) query.getOutputParameterValue("P_COMP_VAL_NOM_UDIS") );
			res.setValorPesos( (BigDecimal) query.getOutputParameterValue("P_VAL_PESOS_PAG_ANT") );
			res.setValorPesosCom( (BigDecimal) query.getOutputParameterValue("P_VAL_PESOS_COM_VALOR_NOM") );
						
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
		
}
