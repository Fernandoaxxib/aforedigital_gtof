package mx.axxib.aforedigitalgt.ctrll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import mx.axxib.aforedigitalgt.eml.SolicitudOut;
import mx.axxib.aforedigitalgt.serv.AprobSolicTipRetiroService;
import mx.axxib.aforedigitalgt.serv.MonitorProcesosServ;

@Scope(value ="session")
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
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			recuperarSolicPendientes();
			PrimeFaces.current().executeScript("PF('listSolicitudes').selectAllRows()");
			
		}
	}
	
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
			
			//PrimeFaces.current().executeScript("PF('listSolicitudes').selectAllRows()");
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
        
        
 
        SolicitudOut car = (SolicitudOut) value;
        
        String fechaOperacion= cadenaFecha(car.getFechaOperacion());
        return car.getNumSolicitud().toString().contains(filterText)
                || car.getTransaccion().toLowerCase().contains(filterText)
                || car.getSubTransaccion().toLowerCase().contains(filterText)
                || car.getCodCuenta().contains(filterText)   
                || fechaOperacion.contains(filterText)       
                || car.getNombre().toLowerCase().contains(filterText) ;
    }
	
	private String cadenaFecha(Date fecha) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
		String strDate = dateFormat.format(fecha);  
		
		return strDate;
	}
	


	
	public void aprobarSolicitud()  {	
		
	  if(selectedSolicitud.size()>0 && selectedSolicitud!=null)	{
		  addMessageOK("Proceso ejecutado!");
          selectedSolicitud.forEach(p->{
        	try {
				 res=service.aprobarSolicitud(p.getNumSolicitud(), Integer.valueOf(p.getTransaccion().substring(0, 1)), p.getSubTransaccion().substring(0,1));
				
        	} catch (Exception e) {
				GenericException(e);
			}        	
        });    
        //recuperarProcesoEjecutado();		
	    recuperarSolicPendientes();	   
	   }else {
		  addMessageFail("Seleccionar solicitud!"); 
	   }
	}

	
	public void addMessageOK(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void addMessageFail(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
