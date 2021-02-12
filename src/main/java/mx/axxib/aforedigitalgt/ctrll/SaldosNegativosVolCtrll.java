package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoImssIssteOut;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoNegativoOut;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.SaldosImssIssteServ;

@Scope(value = "session")
@Component(value = "saldosNegativosVol")
@ELBeanName(value = "saldosNegativosVol")
public class SaldosNegativosVolCtrll extends ControllerBase{
	
	@Autowired
	SaldosImssIssteServ saldosImssIsste;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	ConsultaSaldoImssIssteOut consultaSaldoImssIssteOut;
	
	@Getter
	ConsultaSaldoNegativoOut consultaSaldoNegativoOut;
	 
	@Getter
	@Setter
	private String rutaSaldoNegativo;
	
	@Getter
	@Setter
	private String nombreSaldoNegativo;
	 
	@Getter
	@Setter
	private Date saldoFechaMovimiento;
	
	@Getter
	private Date today;
	
	@Getter
	@Setter
	private ProcesoOut proceso;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			rutaSaldoNegativo="/RESPALDOS/operaciones";
			today= new Date();
			reset();
		}
	}
	
	public void reset() {
		nombreSaldoNegativo=null;
	}
	
	
	public void ejecutarReporteNegativo() {
		try {System.out.println("VALOR DE rutaSaldoNegativo:"+rutaSaldoNegativo+" /nombreSaldoNegativo:"+nombreSaldoNegativo+" /saldoFechaMovimiento:"+saldoFechaMovimiento);
		String res=saldosImssIsste.ejecutarReporteNegativo(rutaSaldoNegativo, nombreSaldoNegativo,saldoFechaMovimiento);
		System.out.println("VALOR DE ejecutarReporteNegativo:"+res);
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
 
    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }
}
