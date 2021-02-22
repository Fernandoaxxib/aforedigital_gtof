package mx.axxib.aforedigitalgt.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;

@Service
public class ServiceBase {

	@Autowired
	private AforeMessage aforeMessage;

	protected AforeException GenericException(Exception e) {		
		AforeException error;
		if (e instanceof AforeException) {
			error = (AforeException) e;
		} else if (e instanceof UnexpectedRollbackException) {
			error = new AforeException(aforeMessage.getCode("error.dal.generic", null),
					aforeMessage.getOnlyMessage("error.dal.generic", null), e.getMessage());
		} else {
			error = new AforeException(aforeMessage.getCode("error.serv.generic", null),
					aforeMessage.getOnlyMessage("error.serv.generic", null), e.getMessage());
		}
		return error;
	}
	
}
