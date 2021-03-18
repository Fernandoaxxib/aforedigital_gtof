package mx.axxib.aforedigitalgt.serv;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReInverModDesProcesoRepo;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

@Service
public class ReInverModDesProcesoServ extends ServiceBase {

	@Autowired
	private ReInverModDesProcesoRepo repo;
	
	public BigDecimal getValorCuentas()throws AforeException {	
		try {
			return repo.getValorCuentas();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public EjecucionResult procesoEjecutar(Integer p_Operacion, Date p_FechaInicial, Date p_FechaFinal,Date p_Fecha_Lote) throws AforeException {
		try {
			return repo.procesoEjecutar(p_Operacion, p_FechaInicial, p_FechaFinal, p_Fecha_Lote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}



	