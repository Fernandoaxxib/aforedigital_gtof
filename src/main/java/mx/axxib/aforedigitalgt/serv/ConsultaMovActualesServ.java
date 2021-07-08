package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ConsultaMovActualesRepo;
import mx.axxib.aforedigitalgt.eml.LlenaDetalleOut;
import mx.axxib.aforedigitalgt.eml.LlenaMovimientosOut;
import mx.axxib.aforedigitalgt.eml.ObtenerCodCuentaOut;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de movimientos actuales
//** Interventor Principal: JSAS
//** Fecha Creación: 26/Feb/2021
//** Última Modificación:
//***********************************************//
@Service
public class ConsultaMovActualesServ extends ServiceBase {

	@Autowired
	private ConsultaMovActualesRepo consulta;
	
	public ObtenerCodCuentaOut getCodCuenta(String nssCurp, boolean isCurp) throws AforeException {
		try {
			return consulta.getCodCuenta(nssCurp, isCurp);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	
	public LlenaMovimientosOut getLlenaMovimientos(String codCuenta, String codEmpresa, Date fInicial, Date fFinal) throws AforeException {
		try {
			return consulta.getLlenaMovimientos(codCuenta, codEmpresa, fInicial, fFinal);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public LlenaDetalleOut getLlenaDetalle(String codEmpresa, Date fecMov, Integer noMov) throws AforeException {
		try {
			return consulta.getLlenaDetalle(codEmpresa, fecMov, noMov);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
