package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.NominaEmpleadosBanRepo;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaDispOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Nómina Empleados Grupo Financiero Banorte
//** Interventor Principal: JJSC
//** Fecha Creación: 22/NOV/2021
//** Última Modificación:
//***********************************************//

@Service
public class NominaEmpleadosBanServ extends ServiceBase {

	@Autowired
	private NominaEmpleadosBanRepo repo;

	public RespuestaDispOut getLoteDispersion() throws AforeException {
		try {
			return repo.getLoteDispersion();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RespuestaDispOut aplicarDispersion(String oc_Opcion,String ic_Lote) throws AforeException {
		try {
			return repo.aplicarDispersion(oc_Opcion, ic_Lote);
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

	public RespuestaDispOut aplicarDispersionVolEmp(String ic_LOTE, String ic_EMPRESA, String ic_LOTE_EMPRESA,
			Integer P_VALOR, String ic_Boton1, String ic_Boton2) throws AforeException {
		try {
			return repo.aplicarDispersionVolEmp(ic_LOTE, ic_EMPRESA, ic_LOTE_EMPRESA, P_VALOR, ic_Boton1, ic_Boton2);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public RespuestaDispOut confirmarDatos(String P_EMPRESA, String P_LOTE_EMPRESA) throws AforeException {
		try {
			return repo.confirmarDatos(P_EMPRESA, P_LOTE_EMPRESA);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
