package mx.axxib.aforedigitalgt.dal;

import org.springframework.beans.factory.annotation.Autowired;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;

public class RepoBase {

	@Autowired
	private AforeMessage aforeMessage;

	protected AforeException GenericException(Exception e) {
		AforeException error;
		if (e instanceof AforeException) {
			error = (AforeException) e;
		} else {
			error = new AforeException(aforeMessage.getCode("error.dal.generic", null),
					aforeMessage.getOnlyMessage("error.dal.generic", null), e.getMessage());
		}
		return error;
	}

}
