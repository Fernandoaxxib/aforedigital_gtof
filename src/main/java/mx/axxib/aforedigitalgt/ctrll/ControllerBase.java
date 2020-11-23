package mx.axxib.aforedigitalgt.ctrll;

import org.springframework.beans.factory.annotation.Autowired;

import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.util.AforeLogger;

public class ControllerBase {


	@Autowired
	private AforeMessage aforeMessage;

	@Autowired
	private AforeLogger aforeLogger;
	
	
	protected void GenericException(Exception e) {		
		AforeException error;
		if (e instanceof AforeException) {
			error = (AforeException) e;
		} else {
			error = new AforeException(aforeMessage.getCode("error.ctrll.generic", null),
					aforeMessage.getOnlyMessage("error.ctrll.generic", null), e.getMessage());
		}
		error.printStackTrace(); // Muestra en consola
		aforeLogger.saveException(error); // Guarda en archivo
		aforeMessage.showMessageFaces(error.getUserMessage()); //Muestra en front
	}
}
