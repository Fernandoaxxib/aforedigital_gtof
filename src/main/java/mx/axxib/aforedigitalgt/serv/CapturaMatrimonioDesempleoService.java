package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CapturaMatrimonioDesempleoDAO;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialCuentaOut;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialNSSOut;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialNoResolucionOut;
import mx.axxib.aforedigitalgt.eml.CapturaRetiroParcialSaldoInversionOut;

@Service
public class CapturaMatrimonioDesempleoService  extends ServiceBase {
	
	@Autowired
	CapturaMatrimonioDesempleoDAO capturaMatrimonioDesempleoDAO;
	
	public CapturaRetiroParcialNSSOut obtenerNSS(Integer nss,Integer noSolicitud,String codEmpresa,Integer tipoPrestacion, Integer noResolucion) throws AforeException {
		try {
			return	capturaMatrimonioDesempleoDAO.obtenerNSS(nss, noSolicitud, codEmpresa, tipoPrestacion, noResolucion);	
	} catch (Exception e) {
		throw GenericException(e);
	}
		
	}
	
	public CapturaRetiroParcialNoResolucionOut obtenerNoResolucion(String codEmpresa, Integer codCuenta,String tipoPrestacion,Integer noResolucion) throws AforeException {
		try {
			return capturaMatrimonioDesempleoDAO.obtenerNoResolucion(codEmpresa, codCuenta, tipoPrestacion, noResolucion);	
	} catch (Exception e) {
		throw GenericException(e);
	}
		
	}
	
	public void ejecutarSiefore(String codCuenta, String codEmpresa,String codProducto,String codSistema,String tipoTransaccion,String tipoPrestacion, Date fechaSistema) throws AforeException {
		try {
			capturaMatrimonioDesempleoDAO.ejecutarSiefore(codCuenta, codEmpresa, codProducto, codSistema, tipoTransaccion, tipoPrestacion, fechaSistema);	
	} catch (Exception e) {
		throw GenericException(e);
	}
		
	}
	
	public CapturaRetiroParcialCuentaOut obtenerCuenta (String tipPagoHab,Integer tipoPago,Integer cuenta,String curp,Integer nss,String esperaProc,String rechazaActualizar,String cuentaAlta)throws AforeException {
		try {
			return capturaMatrimonioDesempleoDAO.obtenerCuenta(tipPagoHab, tipoPago, cuenta, curp, nss, esperaProc, rechazaActualizar, cuentaAlta);	
	} catch (Exception e) {
		throw GenericException(e);
	}
		
	}
	
	public CapturaRetiroParcialSaldoInversionOut obtenerSaldoInversion(String codEmpresa,String codCuenta,String codTipoSaldo,String codInversion) throws AforeException {
		try {
			return capturaMatrimonioDesempleoDAO.obtenerSaldoInversion(codEmpresa, codCuenta, codTipoSaldo, codInversion) ;
	} catch (Exception e) {
		throw GenericException(e);
	}
		
	}
		
	
}
