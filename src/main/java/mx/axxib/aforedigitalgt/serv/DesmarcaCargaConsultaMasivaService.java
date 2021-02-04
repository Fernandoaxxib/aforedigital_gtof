package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.DesmarcaCargaConsultaMasivaRepo;
import mx.axxib.aforedigitalgt.eml.DesmarcaCargaMasivaClaveOut;

@Service
public class DesmarcaCargaConsultaMasivaService extends ServiceBase {
	
	@Autowired
	private DesmarcaCargaConsultaMasivaRepo desmarcaCargaConsultaMasivaRepo;
	
	public String ejecutarArchivoCarga(String ruta, String nombre) throws AforeException {
		try {
			return desmarcaCargaConsultaMasivaRepo.ejecutarArchivoCarga(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String reversaArchivoCarga() throws AforeException {
		try {
			return desmarcaCargaConsultaMasivaRepo.reversaArchivoCarga();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String desmarcaMasivaCuenta() throws AforeException {
		try {
			return desmarcaCargaConsultaMasivaRepo.desmarcaMasivaCuenta();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public DesmarcaCargaMasivaClaveOut desmarcaObtenerProceso (String proceso,String claveProceso, String propClave) throws AforeException {
		try {
			return desmarcaCargaConsultaMasivaRepo.desmarcaObtenerProceso (proceso,claveProceso,propClave);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String desmarcaIndividualCuenta(String nss, String curp, String claveProceso) throws AforeException {
		try {
			return desmarcaCargaConsultaMasivaRepo.desmarcaIndividualCuenta(nss,curp,claveProceso);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public String consultaMarcas(String claveProceso,String descripcionProceso) throws AforeException {
		try {
			return desmarcaCargaConsultaMasivaRepo.consultaMarcas(claveProceso,descripcionProceso);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public String consultaMarcasArchivo(String ruta, String nombre) throws AforeException {
		try {
			return desmarcaCargaConsultaMasivaRepo.consultaMarcasArchivo(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
