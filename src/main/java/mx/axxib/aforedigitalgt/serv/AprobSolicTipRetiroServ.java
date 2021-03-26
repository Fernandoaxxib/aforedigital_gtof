package mx.axxib.aforedigitalgt.serv;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.AprobSolicTipRetiroRepo;
import mx.axxib.aforedigitalgt.eml.AprobarSolicResult;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;

@Service
public class AprobSolicTipRetiroServ extends ServiceBase {

	@Autowired
	private AprobSolicTipRetiroRepo aprobacionRepo;
	
	public List<SolicitudOut> getListSolicitudes() throws AforeException {
		try {
			return aprobacionRepo.getSolicitudes();
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
	public AprobarSolicResult aprobarSolicitud(Integer inNoSolicitud,Integer inTipTransac,String icSubTipTransac) throws AforeException {
		try {
			return aprobacionRepo.aprobarSolicitud(inNoSolicitud, inTipTransac, icSubTipTransac);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	
}
