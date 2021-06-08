package mx.axxib.aforedigitalgt.serv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.ModDesParcReportesRepo;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;

//***********************************************//
//** Funcionalidad: Servicio - Desempleo parcialidades - Reportes
//** Desarrollador: JJSC
//** Fecha de creación: 10/Dic/2020
//** Última modificación:
//***********************************************//
@Service
public class ModDesParcReportesServ extends ServiceBase {

	@Autowired
	private ModDesParcReportesRepo desParcRepo;
	
	public EjecucionResult procesarReporte(Integer p_OpcionReporte, Date pd_fechaInicial,String oc_Ruta,String oc_NomArchivo) throws AforeException {
		try {
			return desParcRepo.procesarReporte(p_OpcionReporte, pd_fechaInicial,oc_Ruta,oc_NomArchivo);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
