package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.dal.CalculoContabilidadRepo;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Cálculo Contabilidad
//** Interventor Principal: JSAS
//** Fecha Creación: 8/Mar/2022
//** Última Modificación:
//***********************************************//

@Service
public class CalculoContabilidadServ extends ServiceBase {

	@Autowired
	private CalculoContabilidadRepo repo;
	
	public BaseOut calculoDiario(Date fecha) throws AforeException {
		try {
			return repo.calculoDiario(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut archivoContabilidad(Date fecha) throws AforeException {
		try {
			return repo.archivoContabilidad(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut calculoBono(Date fecha) throws AforeException {
		try {
			return repo.calculoBono(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut generacionAuto(Date fecha) throws AforeException {
		try {
			return repo.generacionAuto(fecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
