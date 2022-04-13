package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.FocapdomRepo;
import mx.axxib.aforedigitalgt.reca.eml.FocapdomOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Repositorio FOCAPDOM Carga
//** Interventor Principal: JJSC
//** Fecha Creación: 24/03/2022
//** Última Modificación:
//***********************************************//
@Service
public class FocapdomCapturaServ extends ServiceBase {

	@Autowired
	private FocapdomRepo repo;

	public FocapdomOut getDatosIniciales() throws AforeException {
		try {
			return repo.getDatosIniciales();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut cargaMasiva(String ic_NOMBRE_ARCHIVO) throws AforeException {
		try {
			return repo.cargaMasiva(ic_NOMBRE_ARCHIVO);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getDatosOrigAporta() throws AforeException {
		try {
			return repo.getDatosOrigAporta();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getPeriodicidad(String ic_CVE_ORIG_APORTA) throws AforeException {
		try {
			return repo.getPeriodicidad(ic_CVE_ORIG_APORTA);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getBeneficiario() throws AforeException {
		try {
			return repo.getBeneficiario();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getOrigenCaptura() throws AforeException {
		try {
			return repo.getOrigenCaptura();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getOrigenRecursos() throws AforeException {
		try {
			return repo.getOrigenRecursos();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getTipoMovimiento() throws AforeException {
		try {
			return repo.getTipoMovimiento();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getTipoIncremento() throws AforeException {
		try {
			return repo.getTipoIncremento();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getTipoPeriodo() throws AforeException {
		try {
			return repo.getTipoPeriodo();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getCompaniaCelular() throws AforeException {
		try {
			return repo.getCompaniaCelular();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getTipoPersona() throws AforeException {
		try {
			return repo.getTipoPersona();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public FocapdomOut getTipoIdentificacion() throws AforeException {
		try {
			return repo.getTipoIdentificacion();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public FocapdomOut getBancos() throws AforeException {
		try {
			return repo.getBancos();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public FocapdomOut getTipoCuenta() throws AforeException {
		try {
			return repo.getTipoCuenta();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public FocapdomOut getAsesor() throws AforeException {
		try {
			return repo.getAsesor();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}