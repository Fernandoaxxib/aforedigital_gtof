package mx.axxib.aforedigitalgt.serv;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModPagosRepo;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

//***********************************************//
//** Funcionalidad: Servicio - Liquidación de fondos y tipos de pagos
//** Desarrollador: JJSC
//** Fecha de creación: 23/Dic/2020
//** Última modificación:
//***********************************************//
@Service
public class ModPagosServ extends ServiceBase{

	@Autowired
	private ModPagosRepo service;
		
	public EjecucionResult refresh(String ic_BotonContinuar,Date id_Fecha_Proceso,Date id_Fecha_Retiro) throws AforeException {
		try {
			return service.getRefresh(ic_BotonContinuar,id_Fecha_Proceso,id_Fecha_Retiro);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public EjecucionResult generarFondos(Date id_FechaProceso,String ic_ProcesosRetiro,String ic_TiposPagos ,String ic_TipoFondos ,String ic_Instituto ) throws AforeException {
		try {
			return service.generaFondos( id_FechaProceso, ic_ProcesosRetiro, ic_TiposPagos , ic_TipoFondos , ic_Instituto);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	public EjecucionResult generarPagos(Date id_FechaRetiro,String ic_ProcesoRetiro,String ic_Instituto,String ic_TiposPagos) throws AforeException {
		try {
			return service.generaPagos( id_FechaRetiro, ic_ProcesoRetiro, ic_Instituto, ic_TiposPagos);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}	
	
}
