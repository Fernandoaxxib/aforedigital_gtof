package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModDesempParcRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.CancelarSolicitudIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesOut;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudIn;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudOut;
import mx.axxib.aforedigitalgt.eml.ListaCancelacionOut;
import mx.axxib.aforedigitalgt.eml.MarcasIn;
import mx.axxib.aforedigitalgt.eml.MarcasOut;
import mx.axxib.aforedigitalgt.eml.RetparDetaIn;
import mx.axxib.aforedigitalgt.eml.RetparDetaOut;

@Service
public class ModDesempParcServ extends ServiceBase {

	@Autowired
	private ModDesempParcRepo modDesempParcRepo;
	
	public DatosSolicitudOut getDatosSolicitud(DatosSolicitudIn parametros) throws AforeException {
		try {
			return modDesempParcRepo.getDatosSolicitud(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public CargaParcialidadesOut getCargaParcialidades(CargaParcialidadesIn parametros) throws AforeException {
		try {
			return modDesempParcRepo.getCargaParcialidades(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RetparDetaOut getRetpar_Deta(RetparDetaIn parametros) throws AforeException {
		try {
			return modDesempParcRepo.getRetpar_Deta(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public MarcasOut getMarcas(MarcasIn parametros) throws AforeException {
		try {
			return modDesempParcRepo.getMarcas(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ListaCancelacionOut getListaCancelacion() throws AforeException {
		try {
			return modDesempParcRepo.getListaCancelacion();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut cancelarSolicitud(CancelarSolicitudIn parametros) throws AforeException {
		try {
			return modDesempParcRepo.cancelarSolicitud(parametros);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut cancelacionMasiva(String ruta, String archivo) throws AforeException {
		try {
			return modDesempParcRepo.cancelacionMasiva(ruta, archivo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
