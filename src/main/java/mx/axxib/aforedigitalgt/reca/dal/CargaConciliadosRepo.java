package mx.axxib.aforedigitalgt.reca.dal;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.Constantes;
import mx.axxib.aforedigitalgt.dal.RepoBase;
import mx.axxib.aforedigitalgt.reca.eml.CargaOut;
import mx.axxib.aforedigitalgt.reca.eml.ConciliarOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Carga Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 21/Feb/2022
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class CargaConciliadosRepo extends RepoBase {

	public CargaOut cargaArchivo(String directorio, String archivo, String tipo) throws AforeException {
//		PROCEDURE PRC_CARGA_ARCHIVO(P_RUTA       IN VARCHAR2,
//                P_ARCHIVO    IN VARCHAR2,
//                P_TIPO_CARGA IN VARCHAR2, 
//                P_LOTE       OUT VARCHAR2,
//                P_MENSAJE   OUT VARCHAR2,
//                P_ESTATUS   OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CARGA_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.CARGA_CONCILIADOS_CARGA_ARCHIVO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("P_RUTA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_ARCHIVO", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_TIPO_CARGA", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("P_LOTE", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_RUTA", directorio);
			query.setParameter("P_ARCHIVO", archivo);
			query.setParameter("P_TIPO_CARGA", tipo);
			
			CargaOut res = new CargaOut();
			res.setLote( (String) query.getOutputParameterValue("P_LOTE") );
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ConciliarOut conciliarArchivo(String tipo, String lote) throws AforeException {
//		PROCEDURE PRC_CONCILIAR_ARCHIVO (P_TIPO_CARGA IN VARCHAR2,
//                P_LOTE IN VARCHAR2,
//                CP_FALTANTES  OUT SYS_REFCURSOR,
//                P_MENSAJE OUT VARCHAR2,
//                P_ESTATUS  OUT NUMBER);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.CARGA_CONCILIADOS_PACKAGE).concat(".")
					.concat(Constantes.CARGA_CONCILIADOS_CONCILIAR_ARCHIVO);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "ConciliarArchivo");
			
			query.registerStoredProcedureParameter("P_TIPO_CARGA", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("P_LOTE", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("CP_FALTANTES", void.class, ParameterMode.REF_CURSOR);
			query.registerStoredProcedureParameter("P_ESTATUS", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);

			query.setParameter("P_TIPO_CARGA", tipo);
			query.setParameter("P_LOTE", lote);
						
			ConciliarOut res = new ConciliarOut();
			res.setDatos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("P_ESTATUS") );
			res.setMensaje( (String) query.getOutputParameterValue("P_MENSAJE") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
