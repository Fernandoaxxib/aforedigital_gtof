package mx.axxib.aforedigitalgt.serv;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultarTraspasosIcefasDAO;
import mx.axxib.aforedigitalgt.eml.ConsultarDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosIcefasOut;
import mx.axxib.aforedigitalgt.eml.CpDatosIcefasOut;

@Service
public class ConsultarTraspasosIcefasService extends ServiceBase {
	
	@Autowired
	private ConsultarTraspasosIcefasDAO consultarTraspasos;

	@SuppressWarnings("null")
	public ConsultarTraspasosIcefasOut getConsultarTraspasos(String codCuenta,String codTipoSaldo,String claveProceso, String estado) throws AforeException {
			try {
				//return consultarTraspasos.getConsultarTraspasos(codCuenta,codTipoSaldo,claveProceso,estado);

				Date date = new Date();
				List<ConsultarDatosIcefasOut> lista=new ArrayList<ConsultarDatosIcefasOut>();
				
				ConsultarDatosIcefasOut out= new ConsultarDatosIcefasOut(estado, estado, date, date);
				out.setFEC_INCLUSION(date);
				out.setFEC_MODIFICACION(date);
				out.setINCLUIDO_POR("PRUEBA INCLUIDO");
				out.setMODIFICADO_POR("PRUEBA MODIFICADO");
				
				ConsultarTraspasosIcefasOut datos= new ConsultarTraspasosIcefasOut();
				datos.setCpCursor(lista);
				datos.setMensaje("OK");
				return datos;
			} catch (Exception e) {
				throw GenericException(e);
			}
		}
	
	@SuppressWarnings("null")
	public ConsultarNombreCuentaIcefasOut getConsultarCurp(String curp) throws AforeException {
		try {
			//return consultarTraspasos.getConsultarCurp(curp);
			List<CpDatosIcefasOut> lista= new ArrayList<CpDatosIcefasOut>();
			Date date = new Date();
			CpDatosIcefasOut ice=new CpDatosIcefasOut();
			ice.setCLAVE_PROCESO(111111);	
			ice.setCOD_TIPSALDO("TIPO SALDO");
			ice.setDESC_ESTADO("DESC_PROCESO");
			ice.setDESC_PROCESO("DESC_TIPSALDO");
			ice.setDESC_TIPSALDO("ESTADO");
			ice.setESTADO("DESC_ESTADO");
			ice.setFECHA_FIN(date);
			ice.setFECHA_INICIO(date);
			
			lista.add(ice);
			
			ConsultarNombreCuentaIcefasOut datos= new ConsultarNombreCuentaIcefasOut();
			datos.setCpDatos(lista);
			datos.setCuenta(11111);
			datos.setCurp_o_nss("43814804753");
			datos.setNombre("JOSE LUIS RODRIGUEZ SANCHEZ");
			datos.setMensaje("OK");
			return datos;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	@SuppressWarnings("null")
	public ConsultarNombreCuentaIcefasOut getConsultarNss(String nss) throws AforeException {
		try {
			//return consultarTraspasos.getConsultarNss(nss);
			
			List<CpDatosIcefasOut> lista= new ArrayList<CpDatosIcefasOut>();
			Date date = new Date();
			CpDatosIcefasOut ice=new CpDatosIcefasOut();
			ice.setCLAVE_PROCESO(111111);	
			ice.setCOD_TIPSALDO("TIPO SALDO");
			ice.setDESC_ESTADO("DESC_PROCESO");
			ice.setDESC_PROCESO("DESC_TIPSALDO");
			ice.setDESC_TIPSALDO("ESTADO");
			ice.setESTADO("DESC_ESTADO");
			ice.setFECHA_FIN(date);
			ice.setFECHA_INICIO(date);
			
			lista.add(ice);
			
			ConsultarNombreCuentaIcefasOut datos= new ConsultarNombreCuentaIcefasOut();
			datos.setCpDatos(lista);
			datos.setCuenta(11111);
			datos.setCurp_o_nss("ROSL481011HNLDNS04");
			datos.setNombre("JOSE LUIS RODRIGUEZ SANCHEZ");
			datos.setMensaje("OK");
				
			
			return datos;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
}
