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
@Component(value = "saldosNegativosImssIsste")
@ELBeanName(value = "saldosNegativosImssIsste")
public class SaldosImssIssteCtrll extends ControllerBase{
	
	@Autowired
	SaldosImssIssteServ saldosImssIsste;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	List<ConsultaSaldoImssIssteOut> consultaSaldoImssIssteOut;
	
	@Getter
	List<ConsultaSaldoNegativoOut> consultaSaldoNegativoOut;
	
	@Getter
	@Setter
	private String rutaNssImss;
	 
	@Getter
	@Setter
	private String nombreNssImss;
	
	@Getter
	@Setter
	private String rutaReporteImss;
	
	@Getter
	@Setter
	private String nombreReporteImss;
	 
	@Getter
	@Setter
	private String rutaCurpIsste;
	
	@Getter
	@Setter
	private String nombreCurpIsste;
	 
	@Getter
	@Setter
	private String rutaReporteIsste;
	
	@Getter
	@Setter
	private String nombreReporteIsste;
	 
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
			rutaNssImss="/RESPALDOS/operaciones";		
			rutaReporteImss="/RESPALDOS/operaciones";	
			rutaCurpIsste="/RESPALDOS/operaciones";	
			rutaReporteIsste="/RESPALDOS/operaciones";	
			today= new Date();
			reset();
		}
	}
	
	public void reset() {
		nombreNssImss=null;
		nombreReporteImss=null;
		nombreCurpIsste=null;
		nombreReporteIsste=null;
	}
	
	public void ejecutarImssCarga() {
		try {System.out.println("VALOR DE rutaNssImss:"+rutaNssImss+" /nombreNssImss:"+nombreNssImss);
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssCarga(rutaNssImss, nombreNssImss);			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutarImssReporte() {
		try {System.out.println("VALOR DE rutaReporteImss:"+rutaReporteImss+" /nombreReporteImss:"+nombreReporteImss);
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutarIssteCarga() {
		try {System.out.println("VALOR DE rutaCurpIsste:"+rutaCurpIsste+" /nombreCurpIsste:"+nombreCurpIsste);
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteCarga(rutaCurpIsste, nombreCurpIsste);			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutarIssteReporte() {
		try {System.out.println("VALOR DE rutaReporteIsste:"+rutaReporteIsste+" /nombreReporteIsste:"+nombreReporteIsste);
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteReporte(rutaReporteIsste, nombreReporteIsste);			
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutarReporteNegativo() {
		try {System.out.println("VALOR DE rutaSaldoNegativo:"+rutaSaldoNegativo+" /nombreSaldoNegativo:"+nombreSaldoNegativo+" /saldoFechaMovimiento:"+saldoFechaMovimiento);
		consultaSaldoNegativoOut=saldosImssIsste.ejecutarReporteNegativo(rutaSaldoNegativo, nombreSaldoNegativo,saldoFechaMovimiento);			
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
