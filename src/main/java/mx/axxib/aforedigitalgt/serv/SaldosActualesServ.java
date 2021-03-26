package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.SaldosActualesRepo;
import mx.axxib.aforedigitalgt.eml.ResultadoSaldosOut;

@Service
public class SaldosActualesServ extends ServiceBase{
	
	@Autowired
	private SaldosActualesRepo repositorio;
	
	public ResultadoSaldosOut getSaldosBloque(String P_CUENTA,String P_COD_EMPRESA,Date P_FECHA,String P_FECHA_FILTRO,String P_SELEC_VIVIENDA) throws AforeException {
		try {
			return repositorio.getSaldosBloque(P_CUENTA, P_COD_EMPRESA, P_FECHA, P_FECHA_FILTRO, P_SELEC_VIVIENDA);
		} catch (Exception e) {
			throw GenericException(e);
		}		
	}
	
	public ResultadoSaldosOut getDatosXNombre(String P_NOMBRE) throws AforeException {
		try {
			return repositorio.getDatosXNombre(P_NOMBRE);
		} catch (Exception e) {
			throw GenericException(e);
		}	
	}
	
	public ResultadoSaldosOut getDatosXNSS(String P_NSS) throws AforeException {
		try {
			return repositorio.getDatosXNSS(P_NSS);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ResultadoSaldosOut getCargaSaldos(String P_COD_PRODUCTO,String P_COD_CUENTA) throws AforeException {
		try {
			return repositorio.getCargaSaldos(P_COD_PRODUCTO, P_COD_CUENTA);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ResultadoSaldosOut getCargaNuevoNivel(String P_COD_SUBPRODUCTO,String p_cod_cuenta) throws AforeException {
		try {
			return repositorio.getCargaNuevoNivel(P_COD_SUBPRODUCTO, p_cod_cuenta);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
