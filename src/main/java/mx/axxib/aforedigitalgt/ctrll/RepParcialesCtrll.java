package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.serv.RepParcialesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "repParcialesConsar")
@ELBeanName(value = "repParcialesConsar")
public class RepParcialesCtrll extends ControllerBase {

    @Autowired		
	private RepParcialesServ service;
    
    @Getter
    @Setter
    private Date fechaInicio;
    @Getter
    @Setter
    private Date fechaFin;
    @Getter
    @Setter
    private String ruta;
    @Getter
    @Setter
    private String archivo;
    @Getter
    private String respuesta;
    
    @Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			reset();
		}
	}
    
    public void reset() {
    	ruta="/RESPALDOS/operaciones";
    	archivo=null;
    	fechaInicio=null;
    	fechaFin=null;
    }
    
    public void generarReporte() {
    	if(fechaInicio!=null & fechaFin!=null) {
    		if(DateUtil.isValidDates(fechaInicio, fechaFin)) {
    			if(archivo!=null && !archivo.isEmpty()) {
    				try {    		
        				respuesta=service.generarReporte(fechaInicio, fechaFin, ruta, archivo);  
        				reset();
        			} catch (AforeException e) {
        				respuesta="Error";
        				GenericException(e);
        			}
    			}else {
    				respuesta="Se requiere nombre de archivo";
    				UIInput archivo = (UIInput) findComponent("archivo");
    				archivo.setValid(false);
    			}
    			
    		}else {
    			respuesta="Fecha inicio debe ser menor o igual a la fecha fin";
    			UIInput fechaIni = (UIInput) findComponent("dini");
        		fechaIni.setValid(false);
        		
        		UIInput fechaFin = (UIInput) findComponent("dffin");
        		fechaFin.setValid(false);
    		}    		
    	}else {
    		respuesta="Se requiere fecha inicio y fecha fin";
    		UIInput fechaIni = (UIInput) findComponent("dini");
    		fechaIni.setValid(false);
    		
    		UIInput fechaFin = (UIInput) findComponent("dffin");
    		fechaFin.setValid(false);			    		
    	}   
    	PrimeFaces.current().executeScript("PF('dlg3').show()");
    }
    
}
