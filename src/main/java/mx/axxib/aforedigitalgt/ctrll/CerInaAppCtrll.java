package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.CerInaAppServ;

@Scope(value = "session")
@Component(value = "cerInaApp")
@ELBeanName(value = "cerInaApp")
public class CerInaAppCtrll extends ControllerBase {

	@Autowired
	private CerInaAppServ service;
	@Getter
	@Setter
	private String ruta;
	@Getter
	@Setter
	private String archivo;
	@Getter
	@Setter
	private Date fecha;

	@Getter
	@Setter
	private ProcesoOut proceso;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
		}
	}

	public void reset() {
		ruta = "/RESPALDOS/operaciones";
		archivo = "PRTFT.DP.A01530.S180221.NOTFOLIO.C001";
		proceso=null;
		fecha= new Date();
	}

	public void ejecutar() {
       if((ruta!=null&&!ruta.isEmpty()) && (archivo!=null&&!archivo.isEmpty())) {
    	  try { 
    		    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
				Date today= new Date();	
				proceso= new ProcesoOut();	
				proceso.setFechahoraInicio(format.format(today));
				String resp=  service.ejecutar(ruta, archivo);
				Date today2= new Date();	
				proceso.setFechahoraFinal(format.format(today2));	
				proceso.setAbrevProceso("Generación de archivo liquidación AppMovil");    				
				proceso.setEstadoProceso(resp);	   		      	 
    	  }catch(Exception e) {
    		  GenericException(e);
    	  }
       }else {
    	  addMessageFail("Ingrese la ruta y el archivo");  
       }
	}

	public void generar() {
         if(fecha!=null) {
        	 try {
        	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
				Date today= new Date();	
				proceso= new ProcesoOut();	
				proceso.setFechahoraInicio(format.format(today));
				String resp=  service.generarArchivo(fecha);
				Date today2= new Date();	
				proceso.setFechahoraFinal(format.format(today2));	
				proceso.setAbrevProceso("Carga de respuesta de AppMovil");    				
				proceso.setEstadoProceso(resp);	 
        	 }catch(Exception e) {
        		 GenericException(e);
        	 }
         }else {
        	 addMessageFail("Debe ingresar la fecha");
         }
	}
	public void addMessageOK(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void addMessageFail(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
