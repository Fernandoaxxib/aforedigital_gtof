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
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Recaudacion IMSS Reportes
//** Interventor Principal: JSAS
//** Fecha Creación: 13/Dic/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class RecaudacionIMSSReportesRepo extends RepoBase {

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
			
			query.registerStoredProcedureParameter("SL_QUERY", void.class, ParameterMode.REF_CURSOR);
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
	
	public BaseOut reporteEjecutar(RecaProcesoEjecutarIn in) throws AforeException {
//		PROCEDURE PRC_REPORTES_BOTON_EJECUTAR(ic_OpcionR IN VARCHAR2,
//		                                      ic_IdOperR IN VARCHAR2,
//		                                      id_FecLoteR IN DATE,
//		                                      ic_SecLoteR IN VARCHAR2,
//		                                      on_Estatus OUT NUMBER,
//		                                      oc_Mensaje OUT VARCHAR2);
		
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_IMSS_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_IMSS_REPORTE_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_OpcionR", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_IdOperR", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("id_FecLoteR", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_SecLoteR", String.class, ParameterMode.IN);
		
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_OpcionR", in.getOpcion());
			query.setParameter("ic_IdOperR", in.getIdOperacion());
			query.setParameter("id_FecLoteR", DateUtil.getDateToString(in.getFechaLote(),"yyyyMMdd"));
			query.setParameter("ic_SecLoteR", in.getSecLote());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
