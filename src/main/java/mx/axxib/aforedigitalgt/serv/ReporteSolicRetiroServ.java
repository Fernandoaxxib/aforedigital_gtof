package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReporteSolicRetiroRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ReporteSolcChequeRetiroIn;
import mx.axxib.aforedigitalgt.eml.SubtransaccionesOut;
import mx.axxib.aforedigitalgt.eml.TransaccionesOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de reporte de solicitudes de retiro
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2021
//** Última Modificación:
//***********************************************//
@Service
public class ReporteSolicRetiroServ extends ServiceBase {

	@Autowired
	private ReporteSolicRetiroRepo reporte;
	
	public TransaccionesOut getTransacciones() throws AforeException {
		try {
			return reporte.getTransacciones();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public SubtransaccionesOut getSubtransacciones(String idTransaccion) throws AforeException {
		try {
			return reporte.getSubtransacciones(idTransaccion);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut reporteSolcChequeRetiro(ReporteSolcChequeRetiroIn parametros) throws AforeException {
		try {
			return reporte.reporteSolcChequeRetiro(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
