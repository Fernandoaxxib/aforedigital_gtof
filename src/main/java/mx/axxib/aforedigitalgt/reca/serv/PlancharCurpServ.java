package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.PlancharCurpRepo;
import mx.axxib.aforedigitalgt.reca.eml.PlancharCurpOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Planchar Curp
//** Interventor Principal: JJSC
//** Fecha Creación: 14/03/2022
//** Última Modificación:
//***********************************************//
@Service
public class PlancharCurpServ extends ServiceBase {

	@Autowired
	private PlancharCurpRepo repo;

	public PlancharCurpOut getLotes() throws AforeException {
		try {
			return repo.getLotes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public PlancharCurpOut getDatosTrabajadores(String idLote) throws AforeException {
		try {
			return repo.getDatosTrabajadores(idLote);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}

	public PlancharCurpOut procesar(String P_SELECCION, String P_LOTE_CARGA, String P_curp, String P_nss_trabajador,
			String P_RESUL_OPERA) throws AforeException {
		try {
			return repo.procesar(P_SELECCION, P_LOTE_CARGA, P_curp, P_nss_trabajador, P_RESUL_OPERA);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}