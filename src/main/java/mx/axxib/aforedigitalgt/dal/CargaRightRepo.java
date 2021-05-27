package mx.axxib.aforedigitalgt.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.eml.CargaRightIn;
import mx.axxib.aforedigitalgt.eml.CargaRightOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de carga right fax
//** Interventor Principal: JSAS
//** Fecha Creación: 25/Ene/2021
//** Última Modificación:
//***********************************************//
@Repository
@Transactional
public class CargaRightRepo extends RepoBase {


	public CargaRightOut getCrucePrevio(CargaRightIn parametros) throws AforeException {
//	    PROCEDURE PRC_CREUCE_PREVIO (P_NOMBRE_ARCHIVO IN VARCHAR2,
//                P_FECHA       IN DATE,
//                P_LINEA       OUT VARCHAR2,
//                P_ESTATUS     OUT NUMBER,
//                P_MENSAJE    OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CARGA_RIGHT_PACKAGE)
					.concat(".").concat(Constantes.CARGA_RIGHT_OBTIENE_CRUCE_PREVIO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LINEA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			
			query.setParameter("P_NOMBRE_ARCHIVO", parametros.getNombreArchivo());
			query.setParameter("P_FECHA", parametros.getFecha());

			CargaRightOut res = new CargaRightOut();
			res.setLinea( (String) query.getOutputParameterValue("P_LINEA") );
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public CargaRightOut getCarga(CargaRightIn parametros) throws AforeException {
//	    PROCEDURE PRC_CARGA (P_NOMBRE_ARCHIVO IN VARCHAR2,
//                P_FECHA     IN DATE,
//                P_LINEA     OUT VARCHAR2,
//                P_ESTATUS   OUT NUMBER,
//                P_MENSAJE   OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.CARGA_RIGHT_PACKAGE)
					.concat(".").concat(Constantes.CARGA_RIGHT_OBTIENE_CARGA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LINEA", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
			
			
			query.setParameter("P_NOMBRE_ARCHIVO", parametros.getNombreArchivo());
			query.setParameter("P_FECHA", parametros.getFecha());

			CargaRightOut res = new CargaRightOut();
			res.setLinea( (String) query.getOutputParameterValue("P_LINEA") );
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
}
