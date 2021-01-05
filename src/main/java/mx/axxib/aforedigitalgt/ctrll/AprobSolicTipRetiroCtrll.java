package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.AprobarSolicResult;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;
import mx.axxib.aforedigitalgt.serv.AprobSolicTipRetiroService;
import mx.axxib.aforedigitalgt.serv.ModDesParcLProcesarService;
import mx.axxib.aforedigitalgt.serv.MonitorProcesosServ;

@Scope(value = "session")
@Component(value = "aprobSolicTipRetiro")
@ELBeanName(value = "aprobSolicTipRetiro")
public class AprobSolicTipRetiroCtrll  extends ControllerBase{

	@Autowired
	private AprobSolicTipRetiroService service;
	
	@Autowired
	private MonitorProcesosServ monitorService;
	
	@Getter
	private List<SolicitudOut> listSolicitudes;
	
	
	@Getter
	private List<ObtieneMonitorOut> procesoEjecutado;
	
	@Getter
	@Setter
	private List<SolicitudOut> selectedSolicitud;
	
	@Getter
	@Setter
	private List<SolicitudOut> filtro;
	
	@Getter
	private Integer idProceso;
	
	@Getter
	private AprobarSolicResult res;
	
	@Getter
	@Setter
	private boolean seleccionado;
	
	
	public int getCount() {
	
		if(filtro!=null) {
			return filtro.size();
		}
		else if (listSolicitudes != null) {
			filtro=listSolicitudes;
			return listSolicitudes.size();
		}		
			return 0;		
	}
	
	public void recuperarSolicPendientes() {
		try {
			listSolicitudes = service.getListSolicitudes();			
		} catch (Exception e) {
			GenericException(e);
		}

	}
	public void recuperarProcesoEjecutado() {
		try {
			procesoEjecutado = monitorService.getMonitor();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	public Integer recuperarIdProceso(Integer pnNoSolicitud ,Integer pTipTransac,String pSubTipTransac)  {
		try {			
			return service.getIdProceso(pnNoSolicitud, pTipTransac, pSubTipTransac);
		} catch (Exception e) {
			 GenericException(e);			
		}	
		return 0;
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
 
        SolicitudOut car = (SolicitudOut) value;
        return car.getNumSolicitud()< filterInt
                || car.getTransaccion().toLowerCase().contains(filterText)
                || car.getSubTransaccion().toLowerCase().contains(filterText)
                || car.getCodCuenta().contains(filterText)             
                || car.getNombre().toLowerCase().contains(filterText) ;
    }
	private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }


	
	public void aprobarSolicitud()  {	
		addMessage(selectedSolicitud.size()+"");
	  if(selectedSolicitud.size()>0 && selectedSolicitud!=null)	{
        selectedSolicitud.forEach(p->{
        	try {
				 res=service.aprobarSolicitud(p.getNumSolicitud(), Integer.valueOf(p.getTransaccion().substring(0, 1)), p.getSubTransaccion().substring(0,1));
				
        	} catch (Exception e) {
				GenericException(e);
			}        	
        });    
        //recuperarProcesoEjecutado();		
	    recuperarSolicPendientes();	   
	   }
	}

	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
