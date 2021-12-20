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
import mx.axxib.aforedigitalgt.reca.eml.LotesOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaProcesoEjecutarIn;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Recaudacion IMSS Proceso
//** Interventor Principal: JSAS
//** Fecha Creación: 13/Dic/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class RecaudacionIMSSProcesoRepo extends RepoBase {

	@SuppressWarnings("unchecked")
	public LotesOut lotes() throws AforeException {
//		PROCEDURE PRC_LOTE(SL_QUERY  OUT CURSOR_QUERY,
//                on_Estatus OUT NUMBER,
//                oc_Mensaje OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_IMSS_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_IMSS_PROCESO_LOTE);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "Lotes");
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			LotesOut res = new LotesOut();
			res.setLotes(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut procesoEjecutar(RecaProcesoEjecutarIn in) throws AforeException {
//		PROCEDURE PRC_PROCESO_BOTON_EJECUTAR(ic_Opcionp IN OUT VARCHAR2,
//                ic_IdOperp IN OUT VARCHAR2,
//                id_FecLotep IN OUT VARCHAR2,
//                ic_SecLotep IN OUT VARCHAR2,
//                id_Fechap IN OUT VARCHAR2,
//                ic_Archivop IN OUT VARCHAR2,
//                ic_Directoriop IN OUT VARCHAR2,
//                oc_Avancep OUT VARCHAR2,
//                on_Estatus OUT NUMBER,
//                oc_Mensaje OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_IMSS_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_IMSS_PROCESO_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_Opcionp", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_IdOperp", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_FecLotep", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_SecLotep", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_Fechap", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Archivop", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_Directoriop", String.class, ParameterMode.IN);
			
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_Opcionp", in.getOpcion());
			query.setParameter("ic_IdOperp", in.getIdOperacion());
			query.setParameter("id_FecLotep", in.getFechaLote());
			query.setParameter("ic_SecLotep", in.getSecLote());
			query.setParameter("id_Fechap", in.getFecha());
			query.setParameter("ic_Archivop", in.getArchivo());
			query.setParameter("ic_Directoriop", in.getDirectorio());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
