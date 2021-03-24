package mx.axxib.aforedigitalgt.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.SolicitudMatrimonioDesempleoRepo;
import mx.axxib.aforedigitalgt.eml.FopagosListOut;
import mx.axxib.aforedigitalgt.eml.VerChequeOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;

@Service
public class SolicitudMatrimonioDesempleoServ extends ServiceBase{
	
	@Autowired
	private SolicitudMatrimonioDesempleoRepo dao;
	
	public VerChequeOut getVerCheque(String nss) throws AforeException  {
		
		try {
			return dao.getVerCheque(nss);
		}catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	public VerSolicitudChequeOut getVerSolicitudCheque(String cuenta) throws AforeException  {
		
		try {
			return dao.getVerSolicitudCheque(cuenta);
		}catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	public VerPagoChequeOut getVerPagosCheque(String cuenta) throws AforeException  {
		
		try {
			return dao.getVerPagosCheque(cuenta);
		}catch (Exception e) {
			throw GenericException(e);
		}
		
	}
	
	//public List<FopagosListOut> getFopagos(Integer numSolicitud,String nss,String cuenta,String nombre) throws AforeException {
	public FopagosListOut getFopagos(Integer numSolicitud,String nss,String cuenta,String nombre) throws AforeException {
		try {
			return dao.getFopagos(numSolicitud,nss,cuenta,nombre);
		}catch (Exception e) {
			throw GenericException(e);
		}
	}
		
	
}
