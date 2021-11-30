package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.NominaEmpleadosBanRepo;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaDispOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Nómina Empleados Grupo Financiero Banorte
//** Interventor Principal: JJSC
//** Fecha Creación: 22/NOV/2021
//** Última Modificación:
//***********************************************//

@Service
public class NominaEmpleadosBanServ extends ServiceBase{
	
	@Autowired
	private NominaEmpleadosBanRepo repo;

	public RespuestaDispOut getLoteDispersion() throws AforeException {
		try {			
			return repo.getLoteDispersion();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RespuestaOut aplicarDispersion(String oc_Opcion) throws AforeException {
		try {			
			return repo.aplicarDispersion(oc_Opcion);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaDispOut getListaEmpresas() throws AforeException {
		try {
			return repo.getListaEmpresas();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	

	public RespuestaDispOut getLotesEmpresa() throws AforeException {
		try {
			return repo.getLotesEmpresa();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RespuestaDispOut aplicarDispersionVolEmp(String oc_Opcion) throws AforeException {
		try {
			return repo.aplicarDispersionVolEmp(oc_Opcion);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
