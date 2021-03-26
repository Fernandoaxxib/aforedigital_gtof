package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultaMovHistoricosRepo;
import mx.axxib.aforedigitalgt.eml.LlenaDetalleHOut;
import mx.axxib.aforedigitalgt.eml.LlenaMovimientosHOut;
import mx.axxib.aforedigitalgt.eml.ObtenerCodCuentaHOut;

@Service
public class ConsultaMovHistoricosServ extends ServiceBase {

	@Autowired
	private ConsultaMovHistoricosRepo consulta;
	
	public ObtenerCodCuentaHOut getCodCuenta(String nssCurp, boolean isCurp) throws AforeException {
		try {
			return consulta.getCodCuenta(nssCurp, isCurp);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public LlenaMovimientosHOut getLlenaMovimientos(String codCuenta, Date fInicial, Date fFinal) throws AforeException {
		try {
			return consulta.getLlenaMovimientos(codCuenta, fInicial, fFinal);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public LlenaDetalleHOut getLlenaDetalle(String codEmpresa, Date fecMov, Integer noMov) throws AforeException {
		try {
			return consulta.getLlenaDetalle(codEmpresa, fecMov, noMov);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
