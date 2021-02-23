package mx.axxib.aforedigitalgt.ctrll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
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
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoImssIssteOut;
import mx.axxib.aforedigitalgt.eml.ConsultaSaldoNegativoOut;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.SaldosImssIssteServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

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
	
//	@Getter
//	@Setter
//	private ProcesoOut proceso;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			rutaSaldoNegativo="/RESPALDOS/operaciones";
			today= new Date();
			saldoFechaMovimiento=today;
			reset();
		}
	}
	
	public void reset() {
		nombreSaldoNegativo=null;
		
	}
	
	public void ejecutarReporteNegativo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga por Saldos Vol.");
		try {
			System.out.println("VALOR DE nombreSaldoNegativo:" +nombreSaldoNegativo);
			if(nombreSaldoNegativo != null && !nombreSaldoNegativo.equals("") ) {
				//if(nombreSaldoNegativo.toLowerCase().endsWith(".xls")) {
					if(nombreSaldoNegativo.endsWith(".xls")) {
					System.out.println("VALOR DE nombreSaldoNegativo:" +nombreSaldoNegativo+"-----saldoFechaMovimiento:"+saldoFechaMovimiento);
					String resp=saldosImssIsste.ejecutarReporteNegativo(rutaSaldoNegativo, nombreSaldoNegativo,saldoFechaMovimiento);
					System.out.println("VALOR DE ejecutarReporteNegativo:"+resp);
					if(resp != null) {
						pr.setStatus("Proceso Exitoso");
						}else {
						pr.setStatus("Proceso Fallido");	
						}
				}else {
					UIInput input = (UIInput) findComponent("nombreVol");
					input.setValid(false);
					pr.setStatus("Carga Nombre Saldos Vol. formato invalido");	
				}
				}else {
					UIInput input = (UIInput) findComponent("nombreVol");
					input.setValid(false);
					pr.setStatus("Carga Nombre Saldos Vol. es requerido");
				}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
//		System.out.println("CONVERTIR CADENA A MINISCULA+:"+nombreSaldoNegativo.toLowerCase());
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//		Date today= new Date();		
//		Date today2= new Date();		
//		if(nombreSaldoNegativo.toLowerCase().endsWith(".xls") || nombreSaldoNegativo.toLowerCase().endsWith(".xlsx")){
//			System.out.println("SI TEMINA EN .XLS O .XLSX");
//			try {System.out.println("VALOR DE rutaSaldoNegativo:"+rutaSaldoNegativo+" /nombreSaldoNegativo:"+nombreSaldoNegativo+" /saldoFechaMovimiento:"+saldoFechaMovimiento);
//			
//			//Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
//			String resp=saldosImssIsste.ejecutarReporteNegativo(rutaSaldoNegativo, nombreSaldoNegativo.toLowerCase(),saldoFechaMovimiento);
//			System.out.println("VALOR DE ejecutarReporteNegativo:"+resp);
//			//Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
//			if(resp.equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")  || resp.equals("Proceso enviado a monitor...")) {
//				proceso.setAbrevProceso(resp);//"Generar reporte"
//				proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
//				//addMessageOK(resp);
//				}else {
//					proceso.setAbrevProceso(resp);//"Generar reporte"
//					proceso.setEstadoProceso("FALLIDO");
//					 //addMessageFail(resp);
//				}
//			}catch (Exception e) {
//				System.out.println("ENTRO A CATCH HUBO ERROR");
//				proceso.setFechahoraFinal(format.format(today2));
//				proceso.setAbrevProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO SALDOS VOL");//"Generar reporte"
//				proceso.setEstadoProceso("FALLIDO");
//				//addMessageFail("ERROR AL PROCESAR EL ARCHIVO");
//				GenericException(e);
//			}
//		}else {
//			//addMessageFail("Ingrese el nombre del archivo correcto");
//			//Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
//			//Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
//			proceso.setAbrevProceso("NOMBRE INCORRECTO");//"Generar reporte"
//			proceso.setEstadoProceso("FALLIDO ");
//		}
//	}
	
//	public void ejecutarReporteNegativo() {
//		System.out.println("CONVERTIR CADENA A MINISCULA+:"+nombreSaldoNegativo.toLowerCase());
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//		Date today= new Date();		
//		Date today2= new Date();		
//		if(nombreSaldoNegativo.toLowerCase().endsWith(".xls") || nombreSaldoNegativo.toLowerCase().endsWith(".xlsx")){
//			System.out.println("SI TEMINA EN .XLS O .XLSX");
//			try {System.out.println("VALOR DE rutaSaldoNegativo:"+rutaSaldoNegativo+" /nombreSaldoNegativo:"+nombreSaldoNegativo+" /saldoFechaMovimiento:"+saldoFechaMovimiento);
//			
//			//Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
//			String resp=saldosImssIsste.ejecutarReporteNegativo(rutaSaldoNegativo, nombreSaldoNegativo.toLowerCase(),saldoFechaMovimiento);
//			System.out.println("VALOR DE ejecutarReporteNegativo:"+resp);
//			//Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
//			if(resp.equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")  || resp.equals("Proceso enviado a monitor...")) {
//				proceso.setAbrevProceso(resp);//"Generar reporte"
//				proceso.setEstadoProceso("SATISFACTORIO");		//"Proceso ejecutado"
//				//addMessageOK(resp);
//				}else {
//					proceso.setAbrevProceso(resp);//"Generar reporte"
//					proceso.setEstadoProceso("FALLIDO");
//					 //addMessageFail(resp);
//				}
//			}catch (Exception e) {
//				System.out.println("ENTRO A CATCH HUBO ERROR");
//				proceso.setFechahoraFinal(format.format(today2));
//				proceso.setAbrevProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO SALDOS VOL");//"Generar reporte"
//				proceso.setEstadoProceso("FALLIDO");
//				//addMessageFail("ERROR AL PROCESAR EL ARCHIVO");
//				GenericException(e);
//			}
//		}else {
//			//addMessageFail("Ingrese el nombre del archivo correcto");
//			//Date today= new Date();		
//			proceso = new ProcesoOut();
//			proceso.setFechahoraInicio(format.format(today));
//			//Date today2= new Date();		
//			proceso.setFechahoraFinal(format.format(today2));
//			proceso.setAbrevProceso("NOMBRE INCORRECTO");//"Generar reporte"
//			proceso.setEstadoProceso("FALLIDO ");
//		}
//	}
	
//	public void onDateSelect(SelectEvent<Date> event) {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
//    }
// 
//    public void click() {
//        PrimeFaces.current().ajax().update("form:display");
//        PrimeFaces.current().executeScript("PF('dlg').show()");
//    }
//    
//    public void addMessageOK(String summary) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}
//	public void addMessageFail(String summary) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}
	
	
}
