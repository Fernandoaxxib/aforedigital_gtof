package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.SolicitudMatrimonioDesempleoRepo;
import mx.axxib.aforedigitalgt.eml.VerCheque;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;

@Service
public class SolicitudMatrimonioDesempleoServ extends ServiceBase{
	
	@Autowired
	private SolicitudMatrimonioDesempleoRepo dao;
	
	public List<VerCheque> getVerCheque(String nss) throws AforeException  {
		
		try {
			return dao.getVerCheque(nss);
		}catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	public List<VerSolicitudChequeOut> getVerSolicitudCheque() throws AforeException  {
		
		try {
			return dao.getVerSolicitudCheque();
		}catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	public List<VerPagoChequeOut> getVerPagosCheque() throws AforeException  {
		
		try {
			return dao.getVerPagosCheque();
		}catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
}
