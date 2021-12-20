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
import mx.axxib.aforedigitalgt.reca.eml.RecaRechazoEjecutarIn;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionPatronalOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio de Recaudacion IMSS Rechazo
//** Interventor Principal: JSAS
//** Fecha Creación: 20/Dic/2021
//** Última Modificación:
//***********************************************//

@Repository
@Transactional
public class RecaudacionIMSSRechazoRepo extends RepoBase {
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
	
	@SuppressWarnings("unchecked")
	public RecaudacionPatronalOut patronal(String idOperacion, Date fechaLote, String secLote) throws AforeException {
//		PROCEDURE PRC_RECA_PATRONAL(ic_IdOper IN VARCHAR2,
//                ic_FecLote IN VARCHAR2,
//                ic_SecLote IN VARCHAR2,
//                SL_QUERY  OUT CURSOR_QUERY,                          
//                on_Estatus OUT NUMBER,
//                oc_Mensaje OUT VARCHAR2);
		
//	     SELECT SRV.identificador_operacion   
//         ,SRV.tipo_registro           
//         ,SRV.identificador_servicio      
//         ,SRV.consecutivo              
//         ,SRV.numero_nss_trabajador
//         ,SRV.curp                    
//         ,SRV.clave_motivo_rechazo     
//         ,(SRV.pago_retiro + SRV.actualizacion_retiro + SRV.pago_cv + SRV.actualiza_cesantia_vejez) RCV
//         ,SRV.aportaciones_voluntarias
//         ,SRV.aportaciones_complementarias
//         ,CAT.descripcion_recha AS MOTIVO_RECHAZO1
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_IMSS_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_IMSS_RECHAZO_PATRONAL);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName, "RecaudacionPatronal");
			
			query.registerStoredProcedureParameter("ic_IdOper", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_FecLote", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_SecLote", String.class, ParameterMode.IN);
		
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);

			query.setParameter("ic_IdOper", idOperacion);
			query.setParameter("ic_FecLote", fechaLote);
			query.setParameter("ic_SecLote", secLote);
			
			RecaudacionPatronalOut res = new RecaudacionPatronalOut();
			res.setRechazos(query.getResultList());
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut rechazoEjecutar(RecaRechazoEjecutarIn in) throws AforeException {
//		PROCEDURE PRC_RECHRECA_BOTON_PROC_SELEC(ic_TODO IN VARCHAR2,
//                ic_IdOper IN VARCHAR2,
//                ic_FecchaTrans IN VARCHAR2,
//                ic_SecLote IN VARCHAR2,
//                in_TipoReg IN NUMBER,    
//                ic_IdServ IN VARCHAR2,   
//                in_Cons IN NUMBER,       
//                ic_NumNssTrab IN VARCHAR2,
//                oc_SelectDetalle IN VARCHAR2,
//                on_Estatus OUT NUMBER,
//                oc_Mensaje OUT VARCHAR2);
		try {
			String storedFullName = Constantes.USUARIO_PENSION.concat(".")
					.concat(Constantes.RECAUDACION_IMSS_PACKAGE).concat(".")
					.concat(Constantes.RECAUDACION_IMSS_REPORTE_EJECUTAR);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedFullName);
			
			query.registerStoredProcedureParameter("ic_IdOper", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_FecchaTrans", Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_SecLote", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_TipoReg", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_IdServ", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("in_Cons", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ic_NumNssTrab", String.class, ParameterMode.IN);
		
			query.registerStoredProcedureParameter("on_Estatus", Integer.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("oc_Mensaje", String.class, ParameterMode.OUT);
			
			query.setParameter("ic_IdOper", in.getIdOperacion());
			query.setParameter("ic_FecchaTrans", in.getFechaLote());
			query.setParameter("ic_SecLote", in.getSecLote());
			query.setParameter("in_TipoReg", in.getTipoRegistro());
			query.setParameter("ic_IdServ", in.getIdServicio());
			query.setParameter("in_Cons", in.getConsecutivo());
			query.setParameter("ic_NumNssTrab", in.getNss());
			
			BaseOut res = new BaseOut();
			res.setEstatus( (Integer) query.getOutputParameterValue("on_Estatus") );
			res.setMensaje( (String) query.getOutputParameterValue("oc_Mensaje") );
					
			return res;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
