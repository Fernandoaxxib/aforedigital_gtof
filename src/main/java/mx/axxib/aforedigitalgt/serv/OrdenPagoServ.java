package mx.axxib.aforedigitalgt.serv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.OrdenPagoRepo;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.OrdenPagoFechasOut;
import mx.axxib.aforedigitalgt.eml.TiposReportes;

@Service
public class OrdenPagoServ extends ServiceBase {
	
	@Autowired
	private OrdenPagoRepo  ordenPagoRepo;
	
	public OrdenPagoFechasOut cargaFechas() throws AforeException {
		try {
			return ordenPagoRepo.cargaFechas();
		} catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	public BaseOut enviarImpresora(OrdenPagoFechasOut parametro, Integer boxImpresora) throws AforeException {
		try {
			return ordenPagoRepo.impresoraReporte(parametro,boxImpresora);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public BaseOut generarArchivo(OrdenPagoFechasOut parametro, Integer boxGenerar) throws AforeException {
		try {
			return ordenPagoRepo.impresoraReporte(parametro,boxGenerar);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public TiposReportes creaTipoReporte(String tipoReporte ) throws AforeException {
		try {
			return ordenPagoRepo.creaTipoReporte(tipoReporte);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String generaNombre(OrdenPagoFechasOut parametro ) throws AforeException{
		try {
			return ordenPagoRepo.generaNombre(parametro);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public List<String> inicializarA() throws AforeException{
		try {


			 List<String> listaTipoReporte = new ArrayList<String>();
			 listaTipoReporte.add("A");
			 listaTipoReporte.add("B");
			 listaTipoReporte.add("C");
			 listaTipoReporte.add("D");
			 listaTipoReporte.add("E");
			return listaTipoReporte;
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
