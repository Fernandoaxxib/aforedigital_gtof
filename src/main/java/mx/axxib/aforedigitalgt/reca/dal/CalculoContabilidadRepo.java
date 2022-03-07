package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Cálculo Contabilidad
//** Interventor Principal: JSAS
//** Fecha Creación: 8/Mar/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class CalculoContabilidadRepo extends RepoBase {
	
	
	public BaseOut calculoDiario(Date fecha) throws AforeException {
//		 PROCEDURE PRC_CAL_DIA_BONO_PENSION(P_FECHA IN DATE,
//                 P_MENSAJE OUT VARCHAR2,
//                 P_ESTATUS OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_PACKAGE).concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_DIA_BONO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", fecha);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut archivoContabilidad(Date fecha) throws AforeException {
//		 PROCEDURE PRC_ARCHIVO_DE_CONTABILIDAD(P_FECHA IN DATE,
//                 P_MENSAJE OUT VARCHAR2,
//                 P_ESTATUS OUT NUMBER); 
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_PACKAGE).concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_ARCHIVO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", fecha);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut calculoBono(Date fecha) throws AforeException {
//		  PROCEDURE PRC_REP_CALC_BONO_PENSION(P_FECHA IN DATE,
//        P_MENSAJE OUT VARCHAR2,
//        P_ESTATUS OUT NUMBER);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_PACKAGE).concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_REP_CAL);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", fecha);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public BaseOut generacionAuto(Date fecha) throws AforeException {
//PROCEDURE PRC_GENERACION_AUTOM_BONO_PENSION(P_FECHA IN DATE,
//            P_MENSAJE OUT VARCHAR2,
//            P_ESTATUS OUT NUMBER);  
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_PACKAGE).concat(".")
					.concat(Constantes.CALCULO_CONTABILIDAD_GENERA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_FECHA", fecha);
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
