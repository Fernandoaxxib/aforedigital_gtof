package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReporteLiquidacionRepo;
import mx.axxib.aforedigitalgt.eml.LiqEjecutaReporteIn;
import mx.axxib.aforedigitalgt.eml.LiqObtieneParametrosOut;
import mx.axxib.aforedigitalgt.eml.LiqObtieneSieforeOut;

@Service
public class ReporteLiquidacionServ extends ServiceBase {

	@Autowired
	private ReporteLiquidacionRepo liquidacionRepo;

	public LiqObtieneParametrosOut getObtieneParametros() throws AforeException {
		try {
			return liquidacionRepo.getObtieneParametros();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<LiqObtieneSieforeOut> getObtieneSiefore() throws AforeException {
		try {
			return liquidacionRepo.getObtieneSiefore();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String ejecutarReporte(LiqEjecutaReporteIn parametros) throws AforeException {
		try {
			return liquidacionRepo.ejecutaReporte(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
