package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.AprobarSolicResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;
import mx.axxib.aforedigitalgt.serv.AprobSolicTipRetiroService;

@Scope(value = "session")
@Component(value = "aprobSolicTipRetiro")
@ELBeanName(value = "aprobSolicTipRetiro")
public class AprobSolicTipRetiroCtrll  extends ControllerBase{

	@Autowired
	private AprobSolicTipRetiroService service;
	
	
	
	@Getter
	private List<SolicitudOut> listSolicitudes;
	
	@Getter
	@Setter
	private List<ProcesoOut> procesoEjecutado;
	
	@Getter
	@Setter
	private List<SolicitudOut> selectedSolicitud;
	
	@Getter
	@Setter
	private List<SolicitudOut> filtro;
	
	@Getter
	private Integer idProceso;
	
	
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
			recuperarProcesoEjecutado(Integer.valueOf("7014162"));
			addMessage("Welcome to PrimeFaces!!");
		} catch (Exception e) {
			GenericException(e);
		}

	}
	public void recuperarProcesoEjecutado(Integer idSesion) {
		try {
			procesoEjecutado = service.getProceso(idSesion);
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
        selectedSolicitud.forEach(p->{
        	try {
				AprobarSolicResult res=service.aprobarSolicitud(p.getNumSolicitud(), Integer.valueOf(p.getTransaccion().substring(0, 1)), p.getSubTransaccion().substring(0,1));
				recuperarProcesoEjecutado(recuperarIdProceso(p.getNumSolicitud(),Integer.valueOf(p.getTransaccion().substring(0, 1)), p.getSubTransaccion().substring(0,1)));
				addMessage("solicitud: "+p.getNumSolicitud()+" msj: "+res.getOcMensaje()); 
        	} catch (Exception e) {
				GenericException(e);
			}        	
        });                
	    recuperarSolicPendientes();	         
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
