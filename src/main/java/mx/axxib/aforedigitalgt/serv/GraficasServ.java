package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.GraficasRepo;
import mx.axxib.aforedigitalgt.eml.DatosGraficasDetalleOut;
import mx.axxib.aforedigitalgt.eml.DatosGraficasTotalesOut;
import mx.axxib.aforedigitalgt.eml.TipoSubTransacOut;
import mx.axxib.aforedigitalgt.eml.TipoTransacOut;

@Service
public class GraficasServ extends ServiceBase {

	@Autowired
	private GraficasRepo graficasRepo;

	public List<TipoTransacOut> getTipoTransacciones(String vFECHA_I, String vFECHA_F) throws AforeException {
		try {
			return graficasRepo.getTipoTransacciones(vFECHA_I, vFECHA_F);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<TipoSubTransacOut> getTipoSubTransac(String vFECHA_I, String vFECHA_F, Integer vTIP_TRANSAC)
			throws AforeException {
		try {
			return graficasRepo.getTipoSubTransac(vFECHA_I, vFECHA_F, vTIP_TRANSAC);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public DatosGraficasTotalesOut getDatosTotales() throws AforeException {
		try {
			return graficasRepo.getDatosTotales();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public DatosGraficasDetalleOut getDetalleGraficas() throws AforeException {
		try {
			return graficasRepo.getDetalleGraficas();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}