package mx.axxib.aforedigitalgt.reca.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.ConsultaBonoPensionRepo;
import mx.axxib.aforedigitalgt.reca.eml.BonoOut;
import mx.axxib.aforedigitalgt.reca.eml.FechaCorteOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Consulta bono pensión
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2022
//** Última Modificación:
//***********************************************//

@Service
public class ConsultaBonoPensionServ extends ServiceBase {

	@Autowired
	private ConsultaBonoPensionRepo repo;
	
	public BonoOut consultaBono(String curp) throws AforeException {
		try {
			return repo.consultaBono(curp);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public FechaCorteOut fechaCorte(Date fecha, Integer codCuenta) throws AforeException {
		try {
			return repo.fechaCorte(fecha, codCuenta);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

}
