package mx.axxib.aforedigitalgt.ctrll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.util.AforeLogger;

public class ControllerBase {

	@Getter
	@Setter
	public boolean init;
	
	@Getter
	@Setter
	public List<ProcessResult> resultados;
	
	private boolean force;

	@Autowired
	protected AforeMessage aforeMessage;

	@Autowired
	private AforeLogger aforeLogger;
	
	@PostConstruct
	public void init() {
		force = true;
		iniciar();
	}
	
	public void iniciar() {
		if(force) {
			resultados = new ArrayList<ProcessResult>();
			init = true;
			force = false;
			return;
		}
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params.size() == 1) {
			String param = params.get("init");
			if(param != null) {
				init = param.equals("true");
				resultados = new ArrayList<ProcessResult>();
				return;
			}
		}
		init = false;
	}
	
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
