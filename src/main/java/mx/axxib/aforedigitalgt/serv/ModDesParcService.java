package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModDesParcProcesoRepo;
import mx.axxib.aforedigitalgt.eml.DiagnosticoResult;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.eml.RegSinSalarioOut;
import mx.axxib.aforedigitalgt.eml.ReporteResult;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;

@Service
public class ModDesParcService extends ServiceBase {

	@Autowired
	private ModDesParcProcesoRepo desParcRepo;
	
	public DiagnosticoResult getDiagnosticarSolic(Date pdFecha)  throws AforeException {
		try {
			return desParcRepo.getDiagnosticarSolic(pdFecha);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RegSinSalarioOut getRegSinSalario(Date pdFechaCarga)  throws AforeException {
		try {
			return desParcRepo.getRegSinSalario(pdFechaCarga);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public RegSinSalarioOut ejecutar(Date pdFechaCarga, Integer opciones) throws AforeException {
		try {
			return desParcRepo.ejecutar(pdFechaCarga, opciones);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ReporteResult procesarReporte(Integer p_OpcionReporte, Date pd_fechaInicial) throws AforeException {
		try {
			return desParcRepo.procesarReporte(p_OpcionReporte, pd_fechaInicial);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public ProcesResult procesarLayout(Integer pn_Opciones) throws AforeException {
		try {
			return desParcRepo.procesarLayout(pn_Opciones);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
