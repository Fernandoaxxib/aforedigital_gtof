package mx.axxib.aforedigitalgt.ctrll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

import org.apache.myfaces.component.visit.FullVisitContext;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.util.AforeLogger;
import mx.axxib.aforedigitalgt.util.DateUtil;

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
	
	protected ProcessResult GenericException(Exception e) {		
		AforeException error;
		if (e instanceof AforeException) {
			error = (AforeException) e;
		} else {
			error = new AforeException(aforeMessage.getCode("error.ctrll.generic", null),
					aforeMessage.getOnlyMessage("error.ctrll.generic", null), e.getMessage());
		}
		error.printStackTrace(); // Muestra en consola
		aforeLogger.saveException(error); // Guarda en archivo
		
		//aforeMessage.showMessageFaces(error.getUserMessage()); //Muestra en front
		ProcessResult excepcion = new ProcessResult();
		excepcion.setDescProceso(error.getUserMessage());
		excepcion.setStatus("Error");
		excepcion.setFechaInicial(DateUtil.getNowDate());
		excepcion.setFechaFinal(DateUtil.getNowDate());
		return excepcion;
	}
	
	protected UIComponent findComponent(final String id) {

	    FacesContext context = FacesContext.getCurrentInstance(); 
	    UIViewRoot root = context.getViewRoot();
	    final UIComponent[] found = new UIComponent[1];

	    root.visitTree(new FullVisitContext(context), new VisitCallback() {     
	        @Override
	        public VisitResult visit(VisitContext context, UIComponent component) {
	            if (component != null 
	                && component.getId() != null 
	                && component.getId().equals(id)) {
	                found[0] = component;
	                return VisitResult.COMPLETE;
	            }
	            return VisitResult.ACCEPT;              
	        }
	    });

	    return found[0];

	}
}
