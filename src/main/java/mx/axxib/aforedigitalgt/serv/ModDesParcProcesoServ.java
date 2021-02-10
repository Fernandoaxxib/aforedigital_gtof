package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModDesParcProcesoRepo;
import mx.axxib.aforedigitalgt.eml.DiagnosticoResult;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.RegisSinSalarioOut;

 

@Service
public class ModDesParcProcesoServ extends ServiceBase {
	@Autowired
	private ModDesParcProcesoRepo desParcRepo;
	
	
	public EjecucionResult ejecutar(Date pdFechaCarga, Integer opciones) throws AforeException {
		try {
			return desParcRepo.ejecutar(pdFechaCarga, opciones);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public DiagnosticoResult getRegistrosXProcesar(Date pdFecha)  throws AforeException {
		try {
			return desParcRepo.getRegistrosXProcesar(pdFecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public void guardarDetSal(RegisSinSalarioOut registro) throws AforeException{
		try {
			 desParcRepo.guardarDetSal(registro);
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	public List<RegisSinSalarioOut> getRegSinSalario()  throws AforeException {
		try {
			return desParcRepo.getRegSinSalario();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
