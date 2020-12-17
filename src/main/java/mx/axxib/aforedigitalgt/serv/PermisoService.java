package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.dal.PermisoDAO;
import mx.axxib.aforedigitalgt.eml.PermisoResult;

@Service
public class PermisoService extends ServiceBase {

	@Autowired
	private PermisoDAO dao;
	
	public PermisoResult getPermisosUsuario(String usuario) throws AforeException {
		try {
			return dao.getPermisosUsuario(usuario);
		} catch (Exception e) {
			throw GenericException(e);
		}
	}
}
