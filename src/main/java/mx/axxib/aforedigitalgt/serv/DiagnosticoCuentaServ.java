package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.DiagnosticoCuentaRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.DesbloqueaCuentasIn;
import mx.axxib.aforedigitalgt.eml.GeneraArchivoIn;
import mx.axxib.aforedigitalgt.eml.ObtieneCodCuentaOut;
import mx.axxib.aforedigitalgt.eml.ObtieneTipoProcesoOut;

@Service
public class DiagnosticoCuentaServ extends ServiceBase {

	@Autowired
	private DiagnosticoCuentaRepo diagnosticoCuentaRepo;
	
	public ObtieneCodCuentaOut getObtieneCodCuenta(String parametro, boolean isCurp) throws AforeException {
		try {
			return diagnosticoCuentaRepo.getObtieneCodCuenta(parametro, isCurp);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtieneTipoProcesoOut getObtieneTipoProceso() throws AforeException {
		try {
			return diagnosticoCuentaRepo.getObtieneTipoProceso();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut generaArchivo(GeneraArchivoIn parametros) throws AforeException {
		try {
			return diagnosticoCuentaRepo.generaArchivo(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut desbloqueaCuentas(DesbloqueaCuentasIn parametros) throws AforeException {
		try {
			return diagnosticoCuentaRepo.desbloqueaCuentas(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut bloqueaCuentas(DesbloqueaCuentasIn parametros) throws AforeException {
		try {
			return diagnosticoCuentaRepo.bloqueaCuentas(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
