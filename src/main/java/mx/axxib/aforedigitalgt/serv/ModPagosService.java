package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModPagosRepo;
import mx.axxib.aforedigitalgt.eml.DatosIniResult;


@Service
public class ModPagosService extends ServiceBase{

	@Autowired
	private ModPagosRepo service;
	
	
	public DatosIniResult getAccionInicial() throws AforeException {
		try {
			return service.getInicioModPagos();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public String refresh(Date fechaproceso,Date fecharetiro,String titlewin ) throws AforeException {
		try {
			return service.getRefresh(fechaproceso, fecharetiro, titlewin);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String generarFondos(Date fechaproceso,String procesosRetiro,String tiposPagos,String tipoFondos,String instituto,String titleWin,String globalCliente  ) throws AforeException {
		try {
			return service.generaFondos(fechaproceso, procesosRetiro, tiposPagos, tipoFondos, instituto, titleWin, globalCliente);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public String generarPagos(Date fechaRetiro,String procesoRetiro,String instituto,String tiposPagos,String titleWin,String globalRetiro) throws AforeException {
		try {
			return service.generaPagos(fechaRetiro, procesoRetiro, instituto, tiposPagos, titleWin, globalRetiro);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
