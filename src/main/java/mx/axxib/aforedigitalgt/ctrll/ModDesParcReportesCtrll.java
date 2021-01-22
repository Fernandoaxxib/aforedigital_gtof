package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.ModDesParcReportesService;


@Scope(value = "session")
@Component(value = "modDesParcReportes")
@ELBeanName(value = "modDesParcReportes")
  
public class ModDesParcReportesCtrll extends ControllerBase{

	@Autowired
	private ModDesParcReportesService service;
	
	@Getter
	@Setter
	private String archivo;	
	@Getter
	@Setter
	private String ruta;
	@Getter
	@Setter
	private Date fecha; 
	@Getter
	@Setter
	private ProcesoOut proceso;
	@Getter
	@Setter
	private String radioSelected;
	
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			reset();
		}
	}
	
	public void radioSelected() {}
	public void procesarReporte() {
    	if(fecha!=null) {
    		
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				proceso= new ProcesoOut();				
				proceso.setFechahoraInicio(format.format(new Date()));			
				EjecucionResult result;
				result = service.procesarReporte( Integer.valueOf(radioSelected) ,fecha);
				proceso.setFechahoraFinal(format.format(new Date()));
				proceso.setAbrevProceso(result.getOcMensaje());
				proceso.setEstadoProceso(result.getOcAvance());
				
				switch(radioSelected) {
				  
				  case "1":    proceso.setAbrevProceso("Reporte de Diagnóstico");
					           break;				            
				  case "2":    proceso.setAbrevProceso("Generación layout pagos");
			                   break;				           
				  case "3":    proceso.setAbrevProceso("Reporte negativos subcuentas");
					           break;				           
				  case "4":    proceso.setAbrevProceso("Reporte movimientos aplicados");
			                   break;			               
				  default:
			          proceso.setAbrevProceso("");
				  
				  } 
				
				reset();
			}catch (Exception e) {			
				 GenericException(e);		
		    }			
    	}    	
    }
	public void reset() {
		radioSelected=null;
		fecha=null;		
		archivo=null;
		ruta=null;
	}
	public void opcionSeleccionada() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String f=format.format(fecha);
		addMessage("si entro con la fecha "+f+" radio selected: "+radioSelected);	
		
	  switch(radioSelected) {
	  
	  case "1": 
		           this.ruta="/RESPALDOS/operaciones/pruebas";		           
		           this.archivo="RepParDiag_"+f+".xls";
		           this.proceso.setAbrevProceso("Reporte de Diagnóstico");
		           break;
	            
	  case "2":
		  		   this.ruta="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";		           
                   this.archivo="LayoutPagos_"+f+".xls";	
                   this.proceso.setAbrevProceso("Generación layout pagos");
                   break;
	           
	  case "3":
		           this.ruta="/RESPALDOS/operaciones/pruebas";
		           this.archivo="RepParNeg_"+f+".xls";
		           this.proceso.setAbrevProceso("Reporte negativos subcuentas");
		           break;
	           
	  case "4":
                   this.archivo="Reporte Pendiente de Confirmar";
                   this.ruta="Reporte Pendiente de Confirmar";
                   this.proceso.setAbrevProceso("Reporte movimientos aplicados");
                   break;
               
	  default:
          this.archivo = "";
          this.ruta="";
          break;
	  
	  }       
    }
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
