package mx.axxib.aforedigitalgt.reca.dal;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.FovulredOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio FOVULRED
//** Interventor Principal: JJSC
//** Fecha Creación: 13/04/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class FovulredRepo extends RepoBase {

	public FovulredOut getRedesComerciales(Integer P_OPCION, String P_NOMBRE_ARCHIVO, String P_RUTA,
			Date P_FECHA_REVERSA, Integer P_SECUENCIA, Date P_FEC_GEN_MOV) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.FOVULRED_PACKAGE)
					.concat(".").concat(Constantes.FOVULRED_REDES_COMERCIALES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_OPCION", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NOMBRE_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_RUTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FECHA_REVERSA", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_SECUENCIA", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_GEN_MOV", Date.class, ParameterMode.IN);

			query.registerStoredProcedureParameter("P_AVANCE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_OPCION", P_OPCION);
			query.setParameter("P_NOMBRE_ARCHIVO", P_NOMBRE_ARCHIVO);
			query.setParameter("P_RUTA", P_RUTA);
			query.setParameter("P_FECHA_REVERSA", P_FECHA_REVERSA);
			query.setParameter("P_SECUENCIA", P_SECUENCIA);
			query.setParameter("P_FEC_GEN_MOV", P_FEC_GEN_MOV);

			FovulredOut res = new FovulredOut();
			res.setAvance((String) query.getOutputParameterValue("P_AVANCE"));
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public FovulredOut reversar(Integer P_OPCION, String P_NOMBRE_ARCHIVO, String P_RUTA, Date P_FECHA_REVERSA,
			Integer P_SECUENCIA, Date P_FEC_GEN_MOV) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.FOVULRED_PACKAGE)
					.concat(".").concat(Constantes.FOVULRED_REVERSA);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "FechaReversa");

			query.registerStoredProcedureParameter("CP_REVERSA1", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			FovulredOut res = new FovulredOut();
			res.setListFechas(query.getResultList());
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FovulredOut getReportesRedesComerciales(Integer P_OPCION, Date P_FEC_INICIO, Date P_FEC_FIN,
			String P_LOTE_REP, String P_NOM_ARCHIVO) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.FOVULRED_PACKAGE)
					.concat(".").concat(Constantes.FOVULRED_REPORTES_REDES_COMERCIALES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);

			query.registerStoredProcedureParameter("P_OPCION", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_INICIO", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_FEC_FIN", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE_REP", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_NOM_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_AVANCE_REPORT", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			FovulredOut res = new FovulredOut();
			res.setAvance((String) query.getOutputParameterValue("P_AVANCE_REPORT"));
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public FovulredOut getLotes(Integer P_OPCION, String P_NOMBRE_ARCHIVO, String P_RUTA, Date P_FECHA_REVERSA,
			Integer P_SECUENCIA, Date P_FEC_GEN_MOV) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.FOVULRED_PACKAGE)
					.concat(".").concat(Constantes.FOVULRED_LOTES);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "LoteReporte");

			query.registerStoredProcedureParameter("CP_LOTES", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			FovulredOut res = new FovulredOut();
			res.setListLotes(query.getResultList());
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public FovulredOut consultarPendientes(String P_NSS_CURP,Integer P_OPCION_REC) throws AforeException {
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".").concat(Constantes.FOVULRED_PACKAGE)
					.concat(".").concat(Constantes.FOVULRED_PENDIENTES2);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "RedComercial");

			query.registerStoredProcedureParameter("P_NSS_CURP", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_OPCION_REC", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("CP_PENDIENTES", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			FovulredOut res = new FovulredOut();
			res.setListRedesComerciales(query.getResultList());
			res.setEstatus((Integer) query.getOutputParameterValue("P_ESTATUS"));
			res.setMensaje((String) query.getOutputParameterValue("P_MENSAJE"));

			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
