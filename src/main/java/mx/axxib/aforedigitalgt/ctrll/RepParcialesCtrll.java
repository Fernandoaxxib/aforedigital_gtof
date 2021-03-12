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
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ReporteOut;
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
    
    public void generarReporte() throws Exception {
    	ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Generación de reporte");
		
    	if(fechaInicio!=null & fechaFin!=null) {
    		if(DateUtil.isValidDates(fechaInicio, fechaFin)) {
    			if(archivo!=null && !archivo.isEmpty()) {
    				try {    		
    					ReporteOut	res=service.generarReporte(fechaInicio, fechaFin, ruta, archivo); 
    					if (res.getOn_Estatus() == 1) { 
    						respuesta="Reporte "+archivo+".xls generado en "+ruta;
    						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));	
    						reset();
    					} else {
    						if (res.getOn_Estatus() == 2) {
    							GenerarErrorNegocio(res.getOc_Mensaje());
    						} else if (res.getOn_Estatus() == 0) {
    							pr.setStatus(res.getOc_Mensaje());
    							respuesta=res.getOc_Mensaje();
    						}
    					}        				
        			} catch (AforeException e) {
        				pr=	GenericException(e);
        			}finally{
        				pr.setFechaFinal(DateUtil.getNowDate());
        				resultados.add(pr);
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
