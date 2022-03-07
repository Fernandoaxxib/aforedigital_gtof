package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.CompraTitulosRepo;
import mx.axxib.aforedigitalgt.reca.eml.CompraTitMontoOut;
import mx.axxib.aforedigitalgt.reca.eml.EmpresasOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Compra de Titulos
//** Interventor Principal: JSAS
//** Fecha Creación: 1/Mar/2022
//** Última Modificación:
//***********************************************//

@Service
public class CompraTitulosServ extends ServiceBase {

	@Autowired
	private CompraTitulosRepo repo;

	public EmpresasOut empresas() throws AforeException {
		try {
			return repo.empresas();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public CompraTitMontoOut montoFecha(Date fecha, String empresa) throws AforeException {
		try {
			return repo.montoFecha(fecha, empresa);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public CompraTitMontoOut montoLote(String lote, String empresa) throws AforeException {
		try {
			return repo.montoLote(lote, empresa);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut ejecuta(String lote, String empresa) throws AforeException {
		try {
			return repo.ejecuta(lote, empresa);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public BaseOut compraTitulos(Date fecha, String empresa, String generaVenta) throws AforeException {
		try {
			return repo.compraTitulos(fecha, empresa, generaVenta);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
