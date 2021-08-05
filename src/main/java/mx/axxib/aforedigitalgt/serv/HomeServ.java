package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.HomeRepo;
import mx.axxib.aforedigitalgt.eml.DatosAccesoOut;
import mx.axxib.aforedigitalgt.eml.InfoUsuarioOut;

@Service
public class HomeServ extends ServiceBase{

	@Autowired
	private HomeRepo repo;
	
	public DatosAccesoOut validarAcceso(String P_CUENTA) throws AforeException {
		try {
			return repo.validarAcceso(P_CUENTA);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
	
	public InfoUsuarioOut getObtenerDatosUsuario(Integer P_ID_USUARIO) throws AforeException {
		try {
			return repo.getObtenerDatosUsuario(P_ID_USUARIO);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
