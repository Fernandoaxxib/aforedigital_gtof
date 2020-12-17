package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.CargaMasivaDAO;

@Service
public class CargaMasivaService extends ServiceBase {
	
	@Autowired
	private CargaMasivaDAO cargaMasiva;
	
	public String ejecutarArchivoCarga(String ruta, String nombre) throws AforeException {
		try {
			return cargaMasiva.ejecutarArchivoCarga(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String reversaArchivoCarga() throws AforeException {
		try {
			return cargaMasiva.reversaArchivoCarga();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String desmarcaMasivaCuenta() throws AforeException {
		try {
			return cargaMasiva.desmarcaMasivaCuenta();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String desmarcaIndividualCuenta(String nss, String curp, String claveProceso) throws AforeException {
		try {
			return cargaMasiva.desmarcaIndividualCuenta(nss,curp,claveProceso);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public String consultaMarcas(String claveProceso,String descripcionProceso) throws AforeException {
		try {
			return cargaMasiva.consultaMarcas(claveProceso,descripcionProceso);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public String consultaMarcasArchivo(String ruta, String nombre) throws AforeException {
		try {
			return cargaMasiva.consultaMarcasArchivo(ruta,nombre);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
}
