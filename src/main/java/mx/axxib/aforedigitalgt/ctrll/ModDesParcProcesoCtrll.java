package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.DiagnosticoResult;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.RegisSinSalarioOut;
import mx.axxib.aforedigitalgt.serv.ModDesParcProcesoService;


@Scope(value = "session")
@Component(value = "modDesParcProceso")
@ELBeanName(value = "modDesParcProceso")
public class ModDesParcProcesoCtrll  extends ControllerBase{

	@Autowired
	private ModDesParcProcesoService service;
	
	@Getter
	@Setter
	private List<RegisSinSalarioOut> listSolicitudes;
	@Getter
	@Setter
	private String radioSelected;
	@Getter
	@Setter
	private ProcesoOut proceso;
	@Getter
	@Setter
	private Integer pendientes; 
	@Getter
	@Setter
	private Integer totales;
	@Getter
	@Setter
	private Integer unaExhibicion;
	@Getter
	@Setter
	private Integer sinSalario; 
	@Getter
	@Setter
	private Integer parcialidades;
	@Getter
	@Setter
	private Date fecha;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			reset();
		}
	}
	
	 public void radioSelected() {}
	 public void buscarRegXProcesar() {
		 
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String f=format.format(fecha);
			addMessage("si entro con la fecha "+f+" radio selected: "+radioSelected);
			
			try {
				DiagnosticoResult res=service.getRegistrosXProcesar(fecha);			
				parcialidades=res.getParcialidades();
				unaExhibicion=res.getUnaExhibicion();
				sinSalario=res.getSinSalario();
				totales=res.getTotales();
				pendientes=res.getPendientes();
				
			} catch (Exception e) {
				 GenericException(e);
			}
		}
	public void guardar() {
		try {											      			    			     
			      if(radioSelected!=null && fecha!=null) {
			    	  listSolicitudes.forEach(p->{
			    		  try {
			 				 service.guardarDetSal(p);
			 				
			         	} catch (Exception e) {
			 				GenericException(e);
			 			}  
			    		  
			    	  });
			       }
			      buscarRegXProcesar();
		} catch (Exception e) {			
				 GenericException(e);		
		}
	}
	 public void mostrarRegistros() {
	    	try {
	    		
	    		listSolicitudes=service.getRegSinSalario();    		    			    		
			} catch (Exception e) {
				GenericException(e);
			}
	    	
	 }
	 public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	         
	        if(newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
	        addMessage("lista: "+listSolicitudes);
	 }
	 public void ejecutarAccion() {
			
			try {
				if(fecha!=null && radioSelected!=null) {										
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					proceso= new ProcesoOut();					
					proceso.setFechahoraInicio(format.format(new Date()));					
					EjecucionResult result=service.ejecutar(fecha,Integer.valueOf(radioSelected));					
					proceso.setFechahoraFinal(format.format(new Date()));
					proceso.setAbrevProceso(result.getOcMensaje());
					proceso.setEstadoProceso(result.getOcAvance());
					
					if(radioSelected.equals("1")) {
						buscarRegXProcesar();
					}else {
						reset();
					}
					
				}
				
				
			} catch (Exception e) {			
					 GenericException(e);		
			}
		}
	 public void reset() {			
			fecha=null;					
			listSolicitudes=null;
			radioSelected=null;			
			pendientes=null; 
			totales=null;
			unaExhibicion=null;
			sinSalario=null; 
			parcialidades=null;
			 
		}
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
