package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.VentaTitulosRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosIn;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosOut;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesIn;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesOut;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoCorteIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorCTIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorIn;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de venta de títulos
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Dic/2020
//** Última Modificación:
//***********************************************//

@Service
public class VentaTitulosServ extends ServiceBase {

	@Autowired
	private VentaTitulosRepo titulosRepo;

	public ObtieneMontoOut getObtieneMontoTotal(ObtieneMontoTotalIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtieneMontoTotal(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtenerTipoRetiroOut getObtenerTipoRetiro(ObtenerTipoRetiroIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtenerTipoRetiro(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtieneMontoOut getObtieneMontoRetiro(ObtieneMontoRetiroIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtieneMontoRetiro(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtieneMontoOut getObtieneMontoCorte(ObtieneMontoCorteIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtieneMontoCorte(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtenerLoteTraspasosOut getObtenerLoteTraspasos(ObtenerLoteTraspasosIn parametros)
			throws AforeException {
		try {
			return titulosRepo.getObtenerLoteTraspasos(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtieneMontoOut getObtieneMontoTraspasos(ObtieneMontoTraspasosIn parametros)
			throws AforeException {
		try {
			return titulosRepo.getObtieneMontoTraspasos(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtenerRgDevExcesOut getObtenerRgDevExces(ObtenerRgDevExcesIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtenerRgDevExces(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public ObtieneMontoOut getObtieneMontoDev(ObtieneMontoDevIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtieneMontoDev(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ventaTitulosMonitor(VentaTitulosMonitorIn parametros) throws AforeException {
		try {
			return titulosRepo.ventaTitulosMonitor(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ventaTitulosMonitorCT(VentaTitulosMonitorCTIn parametros) throws AforeException {
		try {
			return titulosRepo.ventaTitulosMonitorCT(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
