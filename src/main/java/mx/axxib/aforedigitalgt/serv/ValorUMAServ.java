package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ValorUMARepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteUMAIn;
import mx.axxib.aforedigitalgt.eml.ValorUMA;
import mx.axxib.aforedigitalgt.eml.ValorUMAOut;

@Service
public class ValorUMAServ extends ServiceBase {

	@Autowired
	private ValorUMARepo valorUMARepo;

	public ValorUMAOut getValoresUMA() throws AforeException {
		try {
			return valorUMARepo.getValoresUMA();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut getGeneraReporte(GeneraReporteUMAIn parametros) throws AforeException {
		try {
			return valorUMARepo.getGeneraReporte(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut insertarUMA(ValorUMA parametros) throws AforeException {
		try {
			return valorUMARepo.insertarUMA(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut actualizarUMA(ValorUMA parametros) throws AforeException {
		try {
			return valorUMARepo.actualizarUMA(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut eliminarUMA(ValorUMA parametros) throws AforeException {
		try {
			return valorUMARepo.eliminarUMA(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}

	}
}
