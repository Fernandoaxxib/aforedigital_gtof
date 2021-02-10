package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import mx.axxib.aforedigitalgt.eml.SalarioMinOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoInsertTablaOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoTablaOut;
import mx.axxib.aforedigitalgt.serv.SalarioMinimoServ;

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
	
	@Getter
	@Setter
	private List<SalarioMinimoTablaOut> salarioMinimoTablaOut;
	
	
	
	@Getter
	@Setter
	private SalarioMinimoInsertTablaOut salarioMinimoInsertTablaOut;
	
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
	}
	}
	
	
	public void botonGenerarTabla() {
		try {
			
			SalarioMinOut salarioMinOut =new SalarioMinOut();
			salarioMinOut = salarioMinService.getSalarioMinimo(idUsuario);	 
			//salarioMinimoOut = salarioMinService.getSalarioMinimo2("JGALICIA");
			System.out.println("Tamaño de salarioMinOut:" +salarioMinOut.getListSalarioMin().size());
			System.out.println("IMPRIMIR LISTA BASE:" +salarioMinOut.getListSalarioMin());
			List<SalarioMinimoTablaOut> salarioMinimoOut= new ArrayList<SalarioMinimoTablaOut> ();
			 for (int i = 0; i < salarioMinOut.getListSalarioMin().size(); i++) {
				 SalarioMinimoTablaOut tabla= new SalarioMinimoTablaOut();
				 tabla.setCdZona(salarioMinOut.getListSalarioMin().get(i).getCdZona());
				 tabla.setFechaCalendario(salarioMinOut.getListSalarioMin().get(i).getFechaCalendario());
				 tabla.setFechaUltimo(salarioMinOut.getListSalarioMin().get(i).getFechaUltimo());
				 tabla.setMontoDiario(salarioMinOut.getListSalarioMin().get(i).getMontoDiario());
				 tabla.setUserId(salarioMinOut.getListSalarioMin().get(i).getUserId());
				 salarioMinimoOut.add(tabla);
			    }
			 salarioMinimoTablaOut=salarioMinimoOut;
			 System.out.println("Tamaño de salarioMinimoTablaOut:" +salarioMinimoOut.size());
			 System.out.println("IMPRIMIR LISTA VISTA:" +salarioMinimoTablaOut);
			
		} catch (Exception e) {
			GenericException(e);
		}
	}
		
    public void onRowEdit(RowEditEvent<SalarioMinimoTablaOut> event) {
    	 System.out.println("entrando al onRowEdit");
    	 SalarioMinimoTablaOut salarioMinimoTabla= (SalarioMinimoTablaOut) event.getObject();
    	
    	 System.out.println("VISTA ");
        System.out.println("Datos de La Vista Usuario: "+salarioMinimoTabla.getUserId());
        System.out.println("Datos de La Vista Zona: "+salarioMinimoTabla.getCdZona());
        System.out.println("Datos de La Vista  Fecha Calendario: "+salarioMinimoTabla.getFechaCalendario());
        System.out.println("Datos de La Vista  Fecha Inicio: "+salarioMinimoTabla.getFechaUltimo());
        System.out.println("Datos de La Vista  Monto: "+salarioMinimoTabla.getMontoDiario());
        //salarioMinimoTablaOut
        try {
			String msg=salarioMinService.update(salarioMinimoTabla.getUserId(), salarioMinimoTabla.getCdZona(), salarioMinimoTabla.getFechaCalendario(), salarioMinimoTabla.getMontoDiario());
			System.out.println("VALOR DE STORED UPDATE: "+msg);
			if(msg.trim().toUpperCase().equals("SE ACTUALIZO CORECTAMENTE")) {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
        }  catch (Exception e) {
			GenericException(e);
		}
        
    }
     
    public void onRowCancel(RowEditEvent<SalarioMinimoTablaOut> event) {
        FacesMessage msg = new FacesMessage("Update Cancelado", event.getObject().getUserId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
   
    public void onAddNew() {
        // Add one new car to the table:
//        Car car2Add = service.createCars(1).get(0);
//        cars1.add(car2Add);
    	//se insertaron  correctamente los datos
    									//String usuario, Date calendario, Double monto
    	System.out.println("Datos de La Vista Usuario: "+insertUsuario);
        System.out.println("Datos de La Vista  Calendario: "+fechaCalendario);
        System.out.println("Datos de La Vista  Monto: "+montoDiario);
    	try {
    		//String msg=salarioMinService.save(salarioMinimoInsertTablaOut.getUserId(), salarioMinimoInsertTablaOut.getFechaCalendario(), salarioMinimoInsertTablaOut.getMontoDiario());
			String msg=salarioMinService.save(insertUsuario, fechaCalendario, montoDiario);
			System.out.println("Valor de msg: "+msg);
			if(msg.equals("se insertaron  correctamente los datos")) { 
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
			} else {
				msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
			}
			
		}  catch (Exception e) {
			GenericException(e);
		}
        
    }
     
}
