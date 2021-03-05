package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.SaldosImssIssteRepo;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoImssIssteOut;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoNegativoOut;

@Service
public class SaldosImssIssteServ extends ServiceBase {

	@Autowired
	private SaldosImssIssteRepo SaldosImssIsste;
	
	public ConsultaSaldoImssIssteOut ejecutarImssCarga(String ruta, String nombre) throws AforeException {
		try {
			return SaldosImssIsste.ejecutarImssCarga(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ConsultaSaldoImssIssteOut ejecutarImssReporte(String ruta, String nombre) throws AforeException {
		try {
			return SaldosImssIsste.ejecutarImssReporte(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ConsultaSaldoImssIssteOut ejecutarIssteCarga(String ruta, String nombre) throws AforeException {
		try {
			return SaldosImssIsste.ejecutarIssteCarga(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ConsultaSaldoImssIssteOut ejecutarIssteReporte(String ruta, String nombre) throws AforeException {
		try {
			return SaldosImssIsste.ejecutarIssteReporte(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ConsultaSaldoImssIssteOut ejecutarReporteNegativo(String ruta, String nombre, Date fechaMovimiento) throws AforeException {
		try {
			return SaldosImssIsste.ejecutarReporteNegativo(ruta,nombre,fechaMovimiento);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
