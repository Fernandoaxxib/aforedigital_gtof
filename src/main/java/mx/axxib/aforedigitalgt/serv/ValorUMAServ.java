package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ValorUMARepo;
import mx.axxib.aforedigitalgt.eml.GeneraReporteUMAIn;
import mx.axxib.aforedigitalgt.eml.ValorUMAOut;

@Service
public class ValorUMAServ extends ServiceBase {

	@Autowired
	private ValorUMARepo valorUMARepo;
	
	public ValorUMAOut getValorUMA(String usuario) throws AforeException {
		try {
			return valorUMARepo.getValorUMA(usuario);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public String getGeneraReporte(GeneraReporteUMAIn parametros) throws AforeException {
		try {
			return valorUMARepo.getGeneraReporte(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
