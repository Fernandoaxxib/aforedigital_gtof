package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.ReporteConciliadosRepo;
import mx.axxib.aforedigitalgt.reca.eml.EstatusOut;
import mx.axxib.aforedigitalgt.reca.eml.RefPagoOut;
import mx.axxib.aforedigitalgt.reca.eml.SaldosIndiOut;
import mx.axxib.aforedigitalgt.reca.eml.SaldosSiefOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Reporte Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Feb/2022
//** Última Modificación:
//***********************************************//

@Service
public class ReporteConciliadosServ extends ServiceBase {

	@Autowired
	private ReporteConciliadosRepo repo;
	
	public RefPagoOut refPago(Date fechaI, Date fechaF, Integer estatus) throws AforeException {
		try {
			return repo.refPago(fechaI, fechaF, estatus);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut reintPen(Date fechaI, Date fechaF) throws AforeException {
		try {
			return repo.reintPen(fechaI, fechaF);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public SaldosSiefOut saldosSief(Date fecha) throws AforeException {
		try {
			return repo.saldosSief(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public SaldosIndiOut saldosIndi(Date fecha) throws AforeException {
		try {
			return repo.saldosIndi(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut reintOp(Date fecha) throws AforeException {
		try {
			return repo.reintOp(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut cargarArchivo(String directorio, String archivo, Date fecha) throws AforeException {
		try {
			return repo.cargarArchivo(directorio, archivo, fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public EstatusOut estatus() throws AforeException {
		try {
			return repo.estatus();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
