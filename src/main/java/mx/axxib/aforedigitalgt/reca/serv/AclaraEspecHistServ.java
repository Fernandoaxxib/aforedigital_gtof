package mx.axxib.aforedigitalgt.reca.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.reca.dal.AclaracionesEspecialesRepo;
import mx.axxib.aforedigitalgt.reca.eml.InfoPunteoOut;
import mx.axxib.aforedigitalgt.serv.ServiceBase;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Servicio de Aclaraciones Especiales - Histórico
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:
//***********************************************//

@Service
public class AclaraEspecHistServ  extends ServiceBase{

	@Autowired
	private AclaracionesEspecialesRepo repo;

		public InfoPunteoOut cargarInfoHist(String ic_Nss) throws AforeException {
			try {				
				return repo.cargarInfoHist(ic_Nss);
			} catch (Exception e) {
				throw GenericException(e);
			}
		}
		
		public InfoPunteoOut llenarDatosHistorico(String P_NSS,String P_RFC, String P_NOMBRE) throws AforeException {
			try {											
				return repo.llenarDatosHistorico(P_NSS, P_RFC, P_NOMBRE);
			} catch (Exception e) {
				throw GenericException(e);
			}
		}	
		
		public InfoPunteoOut actualizarHistorico(String ic_Nss, String ic_Aceptado) throws AforeException {
			try {				
				return repo.actualizarHistorico(ic_Nss, ic_Aceptado);
			} catch (Exception e) {
				throw GenericException(e);
			}
		}
		
}
