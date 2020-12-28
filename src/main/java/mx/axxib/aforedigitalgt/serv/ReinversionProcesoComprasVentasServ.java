package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ReinversionProcesoComprasVentasDAO;
import mx.axxib.aforedigitalgt.eml.ReinversionProcesoOut;

@Service
public class ReinversionProcesoComprasVentasServ extends ServiceBase{
	
	@Autowired
	private ReinversionProcesoComprasVentasDAO reinversionProcesoComprasVentas;
	
	public List<ReinversionProcesoOut> getEjecutarProceso(Integer operacion, Integer codSistema,Integer codAgencia,String codEmpresa,Date fechaIni,Date fechaFin,Date fechaLote) throws AforeException {
		try {
			return reinversionProcesoComprasVentas.getEjecutarProceso(operacion,codSistema,codAgencia,codEmpresa,fechaIni,fechaFin,fechaLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	
	}
	
	public String crearReporteCompras(String idLote) throws AforeException {	
		try {
			return reinversionProcesoComprasVentas.crearReporteCompras(idLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String procesoCompras(String idLote, Date fechaLote) throws AforeException {
		try {
			return reinversionProcesoComprasVentas.procesoCompras(idLote,fechaLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String detalleCompra(String idLote) throws AforeException  {
		try {
			return reinversionProcesoComprasVentas.detalleCompra(idLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String crearReporteVentas(String idLote) throws AforeException  {
		try {
			return reinversionProcesoComprasVentas.crearReporteVentas(idLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String procesoVentas(String idLote, Date fechaLote) throws AforeException  {
		try {
			return reinversionProcesoComprasVentas.procesoVentas(idLote,fechaLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public String detalleVentas(String idLote) throws AforeException  {
		try {
			return reinversionProcesoComprasVentas.detalleVentas(idLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
