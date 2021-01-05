package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModDesParcReportesRepo;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

@Service
public class ModDesParcReportesService extends ServiceBase {

	@Autowired
	private ModDesParcReportesRepo desParcRepo;
	
	public EjecucionResult procesarReporte(Integer p_OpcionReporte, Date pd_fechaInicial) throws AforeException {
		try {
			return desParcRepo.procesarReporte(p_OpcionReporte, pd_fechaInicial);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
