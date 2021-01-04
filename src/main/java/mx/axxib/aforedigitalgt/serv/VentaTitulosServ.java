package mx.axxib.aforedigitalgt.serv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.VentaTitulosRepo;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosIn;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosOut;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesIn;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesOut;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonto;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoCorteIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoCorteOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosOut;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorCTIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorIn;

@Service
public class VentaTitulosServ extends ServiceBase {

	@Autowired
	private VentaTitulosRepo titulosRepo;

	public List<ObtieneMonto> getObtieneMontoTotal(ObtieneMontoTotalIn parametros) throws AforeException {
		try {
			List<ObtieneMontoTotalOut> sp = titulosRepo.getObtieneMontoTotal(parametros);
			List<ObtieneMonto> montos = new ArrayList<ObtieneMonto>();
			for(ObtieneMontoTotalOut mSP : sp) {
				ObtieneMonto m = new ObtieneMonto();
				m.setIndCuotaRend(mSP.getIndCuotaRend());
				m.setRetiroAforeMND(mSP.getRetiroAforeMND());
				m.setRetiroAforeTIT(mSP.getRetiroAforeTIT());
				m.setSiefore(mSP.getSiefore());
				m.setTotCuotas(mSP.getTotCuotas());
				m.setTotGeneralCuotas(mSP.getTotGeneralCuotas());
				m.setTotVender(mSP.getTotVender());
				m.setValCuota(mSP.getValCuota());
				montos.add(m);
			}
			return montos;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ObtenerTipoRetiroOut> getObtenerTipoRetiro(ObtenerTipoRetiroIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtenerTipoRetiro(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ObtieneMonto> getObtieneMontoRetiro(ObtieneMontoRetiroIn parametros) throws AforeException {
		try {
			List<ObtieneMontoRetiroOut> sp = titulosRepo.getObtieneMontoRetiro(parametros);
			List<ObtieneMonto> montos = new ArrayList<ObtieneMonto>();
			for(ObtieneMontoRetiroOut mSP : sp) {
				ObtieneMonto m = new ObtieneMonto();
				m.setIndCuotaRend(mSP.getIndCuotaRend());
				m.setRetiroAforeMND(mSP.getRetiroAforeMND());
				m.setRetiroAforeTIT(mSP.getRetiroAforeTIT());
				m.setSiefore(mSP.getSiefore());
				m.setTotCuotas(mSP.getTotCuotas());
				m.setTotGeneralCuotas(mSP.getTotGeneralCuotas());
				m.setTotVender(mSP.getTotVender());
				m.setValCuota(mSP.getValCuota());
				montos.add(m);
			}
			return montos;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ObtieneMonto> getObtieneMontoCorte(ObtieneMontoCorteIn parametros) throws AforeException {
		try {
			List<ObtieneMontoCorteOut> sp = titulosRepo.getObtieneMontoCorte(parametros);
			List<ObtieneMonto> montos = new ArrayList<ObtieneMonto>();
			for(ObtieneMontoCorteOut mSP : sp) {
				ObtieneMonto m = new ObtieneMonto();
				m.setIndCuotaRend(mSP.getIndCuotaRend());
				m.setRetiroAforeMND(mSP.getRetiroAforeMND());
				m.setRetiroAforeTIT(mSP.getRetiroAforeTIT());
				m.setSiefore(mSP.getSiefore());
				m.setTotCuotas(mSP.getTotCuotas());
				m.setTotGeneralCuotas(mSP.getTotGeneralCuotas());
				m.setTotVender(mSP.getTotVender());
				m.setValCuota(mSP.getValCuota());
				montos.add(m);
			}
			return montos;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ObtenerLoteTraspasosOut> getObtenerLoteTraspasos(ObtenerLoteTraspasosIn parametros)
			throws AforeException {
		try {
			return titulosRepo.getObtenerLoteTraspasos(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ObtieneMonto> getObtieneMontoTraspasos(ObtieneMontoTraspasosIn parametros)
			throws AforeException {
		try {
			List<ObtieneMontoTraspasosOut> sp = titulosRepo.getObtieneMontoTraspasos(parametros);
			List<ObtieneMonto> montos = new ArrayList<ObtieneMonto>();
			for(ObtieneMontoTraspasosOut mSP : sp) {
				ObtieneMonto m = new ObtieneMonto();
				m.setIndCuotaRend(mSP.getIndCuotaRend());
				m.setRetiroAforeMND(mSP.getRetiroAforeMND());
				m.setRetiroAforeTIT(mSP.getRetiroAforeTIT());
				m.setSiefore(mSP.getSiefore());
				m.setTotCuotas(mSP.getTotCuotas());
				m.setTotGeneralCuotas(mSP.getTotGeneralCuotas());
				m.setTotVender(mSP.getTotVender());
				m.setValCuota(mSP.getValCuota());
				montos.add(m);
			}
			return montos;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ObtenerRgDevExcesOut> getObtenerRgDevExces(ObtenerRgDevExcesIn parametros) throws AforeException {
		try {
			return titulosRepo.getObtenerRgDevExces(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public List<ObtieneMonto> getObtieneMontoDev(ObtieneMontoDevIn parametros) throws AforeException {
		try {
			List<ObtieneMontoDevOut> sp = titulosRepo.getObtieneMontoDev(parametros);
			List<ObtieneMonto> montos = new ArrayList<ObtieneMonto>();
			for(ObtieneMontoDevOut mSP : sp) {
				ObtieneMonto m = new ObtieneMonto();
				m.setIndCuotaRend(mSP.getIndCuotaRend());
				m.setRetiroAforeMND(mSP.getRetiroAforeMND());
				m.setRetiroAforeTIT(mSP.getRetiroAforeTIT());
				m.setSiefore(mSP.getSiefore());
				m.setTotCuotas(mSP.getTotCuotas());
				m.setTotGeneralCuotas(mSP.getTotGeneralCuotas());
				m.setTotVender(mSP.getTotVender());
				m.setValCuota(mSP.getValCuota());
				montos.add(m);
			}
			return montos;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public void ventaTitulosMonitor(VentaTitulosMonitorIn parametros) throws AforeException {
		try {
			titulosRepo.ventaTitulosMonitor(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public void ventaTitulosMonitorCT(VentaTitulosMonitorCTIn parametros) throws AforeException {
		try {
			titulosRepo.ventaTitulosMonitorCT(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
