package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
public class SaldosNegativosImssIssteCtrll extends ControllerBase{
	
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
	private List<ProcesoOut> procesos =new ArrayList<ProcesoOut>();
	
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
		//procesos=new ArrayList<ProcesoOut>();
		ProcesoOut proceso=new ProcesoOut();
		Date today= new Date();	
		Date today2= new Date();	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
		if(nombreNssImss.toLowerCase().endsWith(".txt")){
			System.out.println("SI TEMINA EN .TXT");
		try {System.out.println("VALOR DE rutaNssImss:"+rutaNssImss+" /nombreNssImss:"+nombreNssImss);
		//Date today= new Date();		
		///proceso = new ProcesoOut();
		proceso.setFechahoraInicio(format.format(today));
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssCarga(rutaNssImss, nombreNssImss);
		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
		
		//Date today2= new Date();		
		proceso.setFechahoraFinal(format.format(today2));
		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO LANZADO A MONITOR FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
			proceso.setAbrevProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
			proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
			addMessageOK(consultaSaldoImssIssteOut.getMensaje());
			}else {
				proceso.setAbrevProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
				proceso.setEstadoProceso("FALLIDO");
				addMessageFail( consultaSaldoImssIssteOut.getMensaje());
			}
		}catch (Exception e) {
			System.out.println("ENTRO A CATCH HUBO ERROR");
			proceso.setFechahoraFinal(format.format(today2));
			proceso.setAbrevProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO NSS IMSS");//"Generar reporte"
			proceso.setEstadoProceso("FALLIDO");
			GenericException(e);
		}
		}else {
			
			//Date today= new Date();		
			//proceso = new ProcesoOut();
			proceso.setFechahoraInicio(format.format(today));
			//Date today2= new Date();		
			proceso.setFechahoraFinal(format.format(today2));
			if(nombreNssImss==null || nombreNssImss=="" ) {
				proceso.setAbrevProceso("NO TIENE NOMBRE PARA NSS IMSS");//"Generar reporte"	
				addMessageFail("Ingrese el nombre del archivo");
			}else {
				proceso.setAbrevProceso("NOMBRE INCORRECTO NSS IMSS");//"Generar reporte"
				addMessageFail("Ingrese el nombre del archivo correcto");
			}
			proceso.setEstadoProceso("FALLIDO");
		}
		procesos.add(proceso);
	}
	//consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);	
	public void ejecutarImssReporte() {
		//procesos=new ArrayList<ProcesoOut>();
		ProcesoOut proceso=new ProcesoOut();
		Date today= new Date();		
		Date today2= new Date();	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
		if(nombreReporteImss.toLowerCase().endsWith(".txt")){
			System.out.println("SI TEMINA EN .TXT");
		try {System.out.println("VALOR DE rutaReporteImss:"+rutaReporteImss+" /nombreReporteImss:"+nombreReporteImss);
		
		///proceso = new ProcesoOut();
		proceso.setFechahoraInicio(format.format(today));
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);		
		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
			
		proceso.setFechahoraFinal(format.format(today2));
		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO LANZADO A MONITOR FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
			proceso.setAbrevProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
			proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
			addMessageOK(consultaSaldoImssIssteOut.getMensaje());
			}else {
				proceso.setAbrevProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
				proceso.setEstadoProceso("FALLIDO");
				addMessageFail( consultaSaldoImssIssteOut.getMensaje());
			}
		}catch (Exception e) {
			System.out.println("ENTRO A CATCH HUBO ERROR");
			proceso.setFechahoraFinal(format.format(today2));
			proceso.setAbrevProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO REPORTE IMSS");//"Generar reporte"
			proceso.setEstadoProceso("FALLIDO");
			addMessageFail("ERROR AL PROCESAR EL ARCHIVO");
			GenericException(e);
			
		}
		}else {
			
			//Date today= new Date();		
			//proceso = new ProcesoOut();
			proceso.setFechahoraInicio(format.format(today));
			//Date today2= new Date();		
			proceso.setFechahoraFinal(format.format(today2));
			if(nombreReporteImss==null || nombreReporteImss=="") {
				proceso.setAbrevProceso("NO TIENE NOMBRE REPORTE IMSS");//"Generar reporte"	
				addMessageFail("Ingrese el nombre del archivo");
			}else {
				proceso.setAbrevProceso("NOMBRE INCORRECTO REPORTE IMSS");//"Generar reporte"
				addMessageFail("Ingrese el nombre del archivo correcto");
			}
			proceso.setEstadoProceso("FALLIDO ");
		}
		procesos.add(proceso);
	}
	
	public void ejecutarIssteCarga() {
		//procesos=new ArrayList<ProcesoOut>();
		ProcesoOut proceso=new ProcesoOut();
		Date today= new Date();	
		Date today2= new Date();	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
		if(nombreCurpIsste.toLowerCase().endsWith(".txt")){
			System.out.println("SI TEMINA EN .TXT");
		try {System.out.println("VALOR DE rutaCurpIsste:"+rutaCurpIsste+" /nombreCurpIsste:"+nombreCurpIsste);
		//Date today= new Date();		
		///proceso = new ProcesoOut();
		proceso.setFechahoraInicio(format.format(today));
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteCarga(rutaCurpIsste, nombreCurpIsste);	
		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
		
		//Date today2= new Date();		
		proceso.setFechahoraFinal(format.format(today2));
		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
			proceso.setAbrevProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
			proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
			addMessageOK(consultaSaldoImssIssteOut.getMensaje());
			}else {
				proceso.setAbrevProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
				proceso.setEstadoProceso("FALLIDO");
				addMessageFail( consultaSaldoImssIssteOut.getMensaje());
			}
		}catch (Exception e) {
			proceso.setFechahoraFinal(format.format(today2));
			proceso.setAbrevProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO CURP ISSTE");//"Generar reporte"
			proceso.setEstadoProceso("FALLIDO");
			addMessageFail("ERROR AL PROCESAR EL ARCHIVO");
			GenericException(e);
		}
		}else {
			//Date today= new Date();		
			proceso = new ProcesoOut();
			proceso.setFechahoraInicio(format.format(today));
			//Date today2= new Date();		
			proceso.setFechahoraFinal(format.format(today2));
			if(nombreCurpIsste==null || nombreCurpIsste=="") {
				proceso.setAbrevProceso("NO TIENE NOMBRE CURP ISSTE");//"Generar reporte"	
				addMessageFail("Ingrese el nombre del archivo");
			}else {
				proceso.setAbrevProceso("NOMBRE INCORRECTO CURP ISSTE");//"Generar reporte"
				addMessageFail("Ingrese el nombre del archivo correcto");
			}
			proceso.setEstadoProceso("FALLIDO ");
		}
		procesos.add(proceso);
	}
	
	public void ejecutarIssteReporte() {
		//procesos=new ArrayList<ProcesoOut>();
		ProcesoOut proceso=new ProcesoOut();
		Date today= new Date();	
		Date today2= new Date();	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
		if(nombreReporteIsste.toLowerCase().endsWith(".txt")){
			System.out.println("SI TEMINA EN .TXT");
		try {System.out.println("VALOR DE rutaReporteIsste:"+rutaReporteIsste+" /nombreReporteIsste:"+nombreReporteIsste);
		//Date today= new Date();		
		///proceso = new ProcesoOut();
		proceso.setFechahoraInicio(format.format(today));
		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteReporte(rutaReporteIsste, nombreReporteIsste);
		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);	
		//Date today2= new Date();		
		proceso.setFechahoraFinal(format.format(today2));
		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
			proceso.setAbrevProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
			proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
			addMessageOK(consultaSaldoImssIssteOut.getMensaje());
			}else {
				proceso.setAbrevProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
				proceso.setEstadoProceso("FALLIDO");
				addMessageFail( consultaSaldoImssIssteOut.getMensaje());
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}else {
		//Date today= new Date();		
		//proceso = new ProcesoOut();
		proceso.setFechahoraInicio(format.format(today));
		//Date today2= new Date();		
		proceso.setFechahoraFinal(format.format(today2));
		if(nombreReporteIsste==null || nombreReporteIsste=="") {
			proceso.setAbrevProceso("NO TIENE NOMBRE REPORTE ISSTE");//"Generar reporte"	
			addMessageFail("Ingrese el nombre del archivo");
		}else {
			proceso.setAbrevProceso("NOMBRE INCORRECTO REPORTE ISSTE");//"Generar reporte"
			addMessageFail("Ingrese el nombre del archivo correcto");
		}
		proceso.setEstadoProceso("FALLIDO ");
	}
		procesos.add(proceso);
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
    
    public void addMessageOK(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public void addMessageFail(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
