package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoInsertTablaOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoMensaje;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoTablaOut;
import mx.axxib.aforedigitalgt.serv.SalarioMinimoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "salarioMinimo")
@ELBeanName(value = "salarioMinimo")
public class SalarioMinimoCtrll extends ControllerBase {
	
	@Autowired
	private SalarioMinimoServ salarioMinService;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Setter
	@Getter
	private String zona;
	
	@Setter
	@Getter
	private Double montoDiario;
	
	@Getter
	@Setter
	private Date fechaIni;
	
	@Getter
	@Setter
	private Date fechaCalendario;
	
	@Getter
	@Setter
	private String idUsuario;
	
	@Getter
	@Setter
	private String insertUsuario;
	
//	@Getter
//	@Setter
//	private List<SalarioMinimoTablaOut> salarioMinimoTablaOut;
	
	@Getter
	@Setter
	private List<SalarioMinimoOut> salarioMinimoTablaOut;
	
	@Getter
	@Setter
	private SalarioMinimoInsertTablaOut salarioMinimoInsertTablaOut;
	
	@Getter
	@Setter
	private SalarioMinimoMensaje salarioMinimoMensaje;
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	@Setter
	private Integer totalIdUsuario;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
		idUsuario=null;
		insertUsuario=null;
		fechaCalendario=null;
		montoDiario=null;
		fechaIni=null;
		zona=null;
		salarioMinimoTablaOut=null;
		totalIdUsuario=null;
	}
	}
	
	
	public void botonGenerarTabla() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por Id Usuario");
		try {
			if (idUsuario != null && !idUsuario.equals("") ){
			//SalarioMinOut salarioMinOut =new SalarioMinOut();
		    SalarioMinOut salarioMinOut = salarioMinService.getSalarioMinimo(idUsuario);
		   
		    if (salarioMinOut.getEstatus() == 1 && salarioMinOut.getListSalarioMin() != null && salarioMinOut.getListSalarioMin().size() > 0) {
				totalIdUsuario=salarioMinOut.getListSalarioMin().size();
				salarioMinimoTablaOut=salarioMinOut.getListSalarioMin();
		    	pr.setStatus("Consulta Exitosa Id Usuario");//"Consulta Exitosa"
			}else {
				pr.setStatus("No se encontraron resultados por Id Usuario");
				mensajeTabla = "Sin información por Id Usuario";
			}
		    			
			}else {
				UIInput input = (UIInput) findComponent("usuario");
				input.setValid(false);
				pr.setStatus("Usuario es requerido");
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
		
    public void onRowEdit(RowEditEvent<SalarioMinimoOut> event) { 
	//@SuppressWarnings("rawtypes")
	//public void onRowEdit(RowEditEvent event) {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Editar columna Usuario");
		
    	 
    	 //SalarioMinimoTablaOut salarioMinimoTabla=  (SalarioMinimoTablaOut) event.getObject();
    	 SalarioMinimoOut salarioMinimoTabla=  (SalarioMinimoOut) event.getObject();
    	
        //salarioMinimoTablaOut
        try {
        	salarioMinimoMensaje=salarioMinService.update(salarioMinimoTabla.getUserId(), salarioMinimoTabla.getCdZona(), salarioMinimoTabla.getFechaCalendario(), salarioMinimoTabla.getMontoDiario());
			
			if (salarioMinimoMensaje.getEstatus() == 1) {
				
		    	pr.setStatus("Se edito columna Usuario");//"Consulta Exitosa"
			}else {
				pr.setStatus("Error al  editar columna Usuario");
				
			}

        }  catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
        
    }
     
    public void onRowCancel(RowEditEvent<SalarioMinimoOut> event) {
    	
    	ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Actualizacion de Usuario Cancelada");
		
        try {
        event.getObject().getUserId();
        pr.setStatus("Se cancelo el update columna");
        }catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
    }
   
    public void onAddNew() {
    	ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Guardar Nuevo Usuario");
        //se insertaron  correctamente los datos
    									//String usuario, Date calendario, Double monto
    	
    	try {
    		//String msg=salarioMinService.save(salarioMinimoInsertTablaOut.getUserId(), salarioMinimoInsertTablaOut.getFechaCalendario(), salarioMinimoInsertTablaOut.getMontoDiario());
    		
    		if((insertUsuario == null || insertUsuario == "") || fechaCalendario ==null || montoDiario ==null) {
    			
    			boolean bandera=false;
    			
    			if((insertUsuario == null || insertUsuario=="") && fechaCalendario ==null && montoDiario ==null){
    			UIInput inputUsuario = (UIInput) findComponent("usuarioGuardar");
    			inputUsuario.setValid(false);
				UIInput inputFecha = (UIInput) findComponent("fechaGuardar");
				inputFecha.setValid(false);
				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
				inputMonto.setValid(false);
				pr.setStatus("Ingresar Usuario,Fecha y Monto");
				bandera=true;
    			}
    			
    			
    			if(fechaCalendario ==null && montoDiario ==null && bandera==false){
        			UIInput inputFecha = (UIInput) findComponent("fechaGuardar");
    				inputFecha.setValid(false);
    				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Fecha y Monto");
    				bandera=true;
        		}
    			
    			
    			
    			if((insertUsuario == null || insertUsuario=="") && montoDiario ==null && bandera==false){
        			UIInput inputFecha = (UIInput) findComponent("usuarioGuardar");
    				inputFecha.setValid(false);
    				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Usuario y Monto");
    				bandera=true;
        		}
    			
    			if((insertUsuario == null || insertUsuario=="") && fechaCalendario ==null && bandera==false){
        			UIInput inputFecha = (UIInput) findComponent("usuarioGuardar");
    				inputFecha.setValid(false);
    				UIInput inputMonto = (UIInput) findComponent("fechaGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Usuario y Fecha");
    				bandera=true;
        		}
    			
    			if((insertUsuario == null || insertUsuario=="") && bandera==false){
    				UIInput inputUsuario = (UIInput) findComponent("usuarioGuardar");
        			inputUsuario.setValid(false);
    				pr.setStatus("Ingresar Usuario");
    				bandera=true;
        		}
    			
    			if(montoDiario ==null && bandera==false){
    				UIInput inputMonto = (UIInput) findComponent("montoGuardar");
    				inputMonto.setValid(false);
    				pr.setStatus("Ingresar Monto");
    				bandera=true;
        		}
    			
    			if(fechaCalendario ==null && bandera==false){
    				UIInput inputFecha = (UIInput) findComponent("fechaGuardar");
    				inputFecha.setValid(false);
    				pr.setStatus("Ingresar Fecha");
    				bandera=true;
        		}
    			
    		}else {
    			
    			
				salarioMinimoMensaje=salarioMinService.save(insertUsuario, fechaCalendario, montoDiario);
				
				if (salarioMinimoMensaje.getEstatus() == 1) {
					
			    	pr.setStatus("Se guardo el nuevo Usuario");//"Consulta Exitosa"
				}else {
					pr.setStatus("Error al guardar nuevo Usuario");
				}	
				
				
    		}
			
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
    }
     
}
