package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	
	
//	@Getter
//	@Setter
//	public List<ProcessResult> resultados;
	
	@Getter
	private String mensajeTabla;
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			rutaNssImss="/RESPALDOS/operaciones";		
			rutaReporteImss="/RESPALDOS/operaciones";	
			rutaCurpIsste="/RESPALDOS/operaciones";	
			rutaReporteIsste="/RESPALDOS/operaciones";	
			today= new Date();
			limpiar();
		}
	}
	
	public void limpiar() {
		nombreNssImss=null;
		nombreReporteImss=null;
		nombreCurpIsste=null;
		nombreReporteIsste=null;
		mensajeTabla=null;
		
	}
	
	public void ejecutarImssCarga() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga Imss por NSS");
		
		try {
			System.out.println("VALOR DE nombreNssImss:" +nombreNssImss);
			if(nombreNssImss != null && !nombreNssImss.equals("") ) {
			//if(nombreNssImss.toLowerCase().endsWith(".txt")) {
				if(nombreNssImss.endsWith(".txt") && nombreNssImss.contains("NSS-CARGA-REP-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssCarga(rutaNssImss, nombreNssImss);
				System.out.println("VALOR DE NSS IMSS consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
				if(consultaSaldoImssIssteOut.getEstatus()==1) {
				pr.setStatus("Proceso Exitoso");
				}else {
				pr.setStatus("Proceso Fallido");	
				}
			}else {
				UIInput input = (UIInput) findComponent("cargaImss");
				input.setValid(false);
				pr.setStatus("Nombre Carga Imss formato invalido");
				
			}
			}else {
				UIInput input = (UIInput) findComponent("cargaImss");
				input.setValid(false);
				pr.setStatus("Nombre Carga Imss es requerido");
				
			}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
		
//		
//		if(nombreNssImss.toLowerCase().endsWith(".txt")){
//			System.out.println("SI TEMINA EN .TXT");
//		try {
//		System.out.println("VALOR DE rutaNssImss:"+rutaNssImss+" /nombreNssImss:"+nombreNssImss);
//		proceso.setFechaInicial(DateUtil.getNowDate());
//		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssCarga(rutaNssImss, nombreNssImss);
//		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
//		
//		//Date today2= new Date();		
//		proceso.setFechaFinal(DateUtil.getNowDate());
//		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO LANZADO A MONITOR FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
//			proceso.setDescProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//			proceso.setStatus("SATISFACTORIO");		//"Proceso ejecutado"
//			addMessageOK(consultaSaldoImssIssteOut.getMensaje());
//			}else {
//				proceso.setDescProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//				proceso.setStatus("FALLIDO");
//				addMessageFail( consultaSaldoImssIssteOut.getMensaje());
//			}
//		}catch (Exception e) {
//			System.out.println("ENTRO A CATCH HUBO ERROR");
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			proceso.setDescProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO NSS IMSS");//"Generar reporte"
//			proceso.setStatus("FALLIDO");
//			GenericException(e);
//		}
//		}else {
//			
//			
//			proceso.setFechaInicial(DateUtil.getNowDate());
//			
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			if(nombreNssImss==null || nombreNssImss=="" ) {
//				proceso.setDescProceso("NO TIENE NOMBRE PARA NSS IMSS");//"Generar reporte"	
//				addMessageFail("Ingrese el nombre del archivo");
//			}else {
//				proceso.setDescProceso("NOMBRE INCORRECTO NSS IMSS");//"Generar reporte"
//				addMessageFail("Ingrese el nombre del archivo correcto");
//			}
//			proceso.setStatus("FALLIDO");
//		}
//		resultados.add(proceso);
	}
	
//	public void ejecutarImssCarga() {
//		//procesos=new ArrayList<ProcesoOut>();
//		ProcessResult proceso = new ProcessResult();
//		if(nombreNssImss.toLowerCase().endsWith(".txt")){
//			System.out.println("SI TEMINA EN .TXT");
//		try {System.out.println("VALOR DE rutaNssImss:"+rutaNssImss+" /nombreNssImss:"+nombreNssImss);
//		//Date today= new Date();		
//		///proceso = new ProcesoOut();
//		proceso.setFechaInicial(DateUtil.getNowDate());
//		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssCarga(rutaNssImss, nombreNssImss);
//		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
//		
//		//Date today2= new Date();		
//		proceso.setFechaFinal(DateUtil.getNowDate());
//		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO LANZADO A MONITOR FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
//			proceso.setDescProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//			proceso.setStatus("SATISFACTORIO");		//"Proceso ejecutado"
//			addMessageOK(consultaSaldoImssIssteOut.getMensaje());
//			}else {
//				proceso.setDescProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//				proceso.setStatus("FALLIDO");
//				addMessageFail( consultaSaldoImssIssteOut.getMensaje());
//			}
//		}catch (Exception e) {
//			System.out.println("ENTRO A CATCH HUBO ERROR");
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			proceso.setDescProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO NSS IMSS");//"Generar reporte"
//			proceso.setStatus("FALLIDO");
//			GenericException(e);
//		}
//		}else {
//			
//			//Date today= new Date();		
//			//proceso = new ProcesoOut();
//			proceso.setFechaInicial(DateUtil.getNowDate());
//			//Date today2= new Date();		
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			if(nombreNssImss==null || nombreNssImss=="" ) {
//				proceso.setDescProceso("NO TIENE NOMBRE PARA NSS IMSS");//"Generar reporte"	
//				addMessageFail("Ingrese el nombre del archivo");
//			}else {
//				proceso.setDescProceso("NOMBRE INCORRECTO NSS IMSS");//"Generar reporte"
//				addMessageFail("Ingrese el nombre del archivo correcto");
//			}
//			proceso.setStatus("FALLIDO");
//		}
//		resultados.add(proceso);
//	}
	//consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);	
	
//	public void ejecutarImssReporte() {
//		//procesos=new ArrayList<ProcesoOut>();
//		ProcessResult proceso = new ProcessResult();
//		Date today= new Date();		
//		Date today2= new Date();	
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//		if(nombreReporteImss.toLowerCase().endsWith(".txt")){
//			System.out.println("SI TEMINA EN .TXT");
//		try {System.out.println("VALOR DE rutaReporteImss:"+rutaReporteImss+" /nombreReporteImss:"+nombreReporteImss);
//		
//		///proceso = new ProcesoOut();
//		proceso.setFechaInicial(DateUtil.getNowDate());
//		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);		
//		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
//			
//		proceso.setFechaFinal(DateUtil.getNowDate());
//		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO LANZADO A MONITOR FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
//			proceso.setDescProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//			proceso.setStatus("SATISFACTORIO");		//"Proceso ejecutado"
//			//addMessageOK(consultaSaldoImssIssteOut.getMensaje());
//			}else {
//				proceso.setDescProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//				proceso.setStatus("FALLIDO");
//				//addMessageFail( consultaSaldoImssIssteOut.getMensaje());
//			}
//		}catch (Exception e) {
//			System.out.println("ENTRO A CATCH HUBO ERROR");
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			proceso.setDescProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO REPORTE IMSS");//"Generar reporte"
//			proceso.setStatus("FALLIDO");
//			//addMessageFail("ERROR AL PROCESAR EL ARCHIVO");
//			GenericException(e);
//			
//		}
//		}else {
//			
//			//Date today= new Date();		
//			//proceso = new ProcesoOut();
//			proceso.setFechaInicial(DateUtil.getNowDate());
//			//Date today2= new Date();		
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			if(nombreReporteImss==null || nombreReporteImss=="") {
//				proceso.setDescProceso("NO TIENE NOMBRE REPORTE IMSS");//"Generar reporte"	
//				//addMessageFail("Ingrese el nombre del archivo");
//			}else {
//				proceso.setDescProceso("NOMBRE INCORRECTO REPORTE IMSS");//"Generar reporte"
//				//addMessageFail("Ingrese el nombre del archivo correcto");
//			}
//			proceso.setStatus("FALLIDO ");
//		}
//		resultados.add(proceso);
//	}
	
	public void ejecutarImssReporte() {
		
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Reporte Imss por NSS");
		try {
			if(nombreReporteImss != null && !nombreReporteImss.equals("") ) {
			//if(nombreReporteImss.toLowerCase().endsWith(".xls")) {
				if(nombreReporteImss.endsWith(".xls") && nombreReporteImss.contains("RPT-SLD-IMSS-FIN-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);		
				System.out.println("VALOR DE REPORTE IMSS consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
				if(consultaSaldoImssIssteOut.getEstatus()==1) {
					pr.setStatus("Proceso Exitoso");
					}else {
					pr.setStatus("Proceso Fallido");	
					}
			}else {
				UIInput input = (UIInput) findComponent("reporteImss");
				input.setValid(false);
				pr.setStatus("Reporte Nombre Imss formato invalido");	
			}
			}else {
				UIInput input = (UIInput) findComponent("reporteImss");
				input.setValid(false);
				pr.setStatus("Reporte Nombre Imss es requerido");
			}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
//			if(nombreReporteImss.toLowerCase().endsWith(".txt")){
//			System.out.println("SI TEMINA EN .TXT");
//		try {System.out.println("VALOR DE rutaReporteImss:"+rutaReporteImss+" /nombreReporteImss:"+nombreReporteImss);
//		
//		///proceso = new ProcesoOut();
//		proceso.setFechaInicial(DateUtil.getNowDate());
//		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);		
//		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
//			
//		proceso.setFechaFinal(DateUtil.getNowDate());
//		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO LANZADO A MONITOR FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
//			proceso.setDescProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//			proceso.setStatus("SATISFACTORIO");		//"Proceso ejecutado"
//			//addMessageOK(consultaSaldoImssIssteOut.getMensaje());
//			}else {
//				proceso.setDescProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//				proceso.setStatus("FALLIDO");
//				//addMessageFail( consultaSaldoImssIssteOut.getMensaje());
//			}
//		}catch (Exception e) {
//			System.out.println("ENTRO A CATCH HUBO ERROR");
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			proceso.setDescProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO REPORTE IMSS");//"Generar reporte"
//			proceso.setStatus("FALLIDO");
//			//addMessageFail("ERROR AL PROCESAR EL ARCHIVO");
//			GenericException(e);
//			
//		}
//		}else {
//			
//			//Date today= new Date();		
//			//proceso = new ProcesoOut();
//			proceso.setFechaInicial(DateUtil.getNowDate());
//			//Date today2= new Date();		
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			if(nombreReporteImss==null || nombreReporteImss=="") {
//				proceso.setDescProceso("NO TIENE NOMBRE REPORTE IMSS");//"Generar reporte"	
//				//addMessageFail("Ingrese el nombre del archivo");
//			}else {
//				proceso.setDescProceso("NOMBRE INCORRECTO REPORTE IMSS");//"Generar reporte"
//				//addMessageFail("Ingrese el nombre del archivo correcto");
//			}
//			proceso.setStatus("FALLIDO ");
//		}
//		resultados.add(proceso);
//	}
	
	
//	public void ejecutarIssteCarga() {
//		//procesos=new ArrayList<ProcesoOut>();
//		ProcessResult proceso = new ProcessResult();
//		Date today= new Date();	
//		Date today2= new Date();	
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//		if(nombreCurpIsste.toLowerCase().endsWith(".txt")){
//			System.out.println("SI TEMINA EN .TXT");
//		try {System.out.println("VALOR DE rutaCurpIsste:"+rutaCurpIsste+" /nombreCurpIsste:"+nombreCurpIsste);
//		//Date today= new Date();		
//		///proceso = new ProcesoOut();
//		proceso.setFechaInicial(DateUtil.getNowDate());
//		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteCarga(rutaCurpIsste, nombreCurpIsste);	
//		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
//		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
//		
//		//Date today2= new Date();		
//		proceso.setFechaFinal(DateUtil.getNowDate());
//		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
//			proceso.setDescProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//			proceso.setStatus("SATISFACTORIO");		//"Proceso ejecutado"
//			//addMessageOK(consultaSaldoImssIssteOut.getMensaje());
//			}else {
//				proceso.setDescProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//				proceso.setStatus("FALLIDO");
//				//addMessageFail( consultaSaldoImssIssteOut.getMensaje());
//			}
//		}catch (Exception e) {
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			proceso.setDescProceso( "HUBO ERROR EL PROCESAR EL ARCHIVO CURP ISSTE");//"Generar reporte"
//			proceso.setStatus("FALLIDO");
//			//addMessageFail("ERROR AL PROCESAR EL ARCHIVO");
//			GenericException(e);
//		}
//		}else {
//			//Date today= new Date();		
//			proceso = new ProcessResult();
//			proceso.setFechaInicial(DateUtil.getNowDate());
//			//Date today2= new Date();		
//			proceso.setFechaFinal(DateUtil.getNowDate());
//			if(nombreCurpIsste==null || nombreCurpIsste=="") {
//				proceso.setDescProceso("NO TIENE NOMBRE CURP ISSTE");//"Generar reporte"	
//				//addMessageFail("Ingrese el nombre del archivo");
//			}else {
//				proceso.setDescProceso("NOMBRE INCORRECTO CURP ISSTE");//"Generar reporte"
//				//addMessageFail("Ingrese el nombre del archivo correcto");
//			}
//			proceso.setStatus("FALLIDO ");
//		}
//		resultados.add(proceso);
//	}
	
	public void ejecutarIssteCarga() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga Isste por CURP");
		
		try {
			System.out.println("VALOR DE nombreCurpIsste:" +nombreCurpIsste);
			if(nombreCurpIsste != null && !nombreCurpIsste.equals("") ) {
			//if(nombreCurpIsste.toLowerCase().endsWith(".txt")) {
				if(nombreCurpIsste.endsWith(".txt") && nombreCurpIsste.contains("CURP-CARGA-REP-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteCarga(rutaCurpIsste, nombreCurpIsste);
				System.out.println("VALOR DE ISSTE CURP consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
				if(consultaSaldoImssIssteOut.getEstatus()==1) {
				pr.setStatus("Proceso Exitoso");
				}else {
				pr.setStatus("Proceso Fallido");	
				}
			}else {
				UIInput input = (UIInput) findComponent("cargaIsste");
				input.setValid(false);
				pr.setStatus("Nombre Carga Isste formato invalido");
				
			}
			}else {
				UIInput input = (UIInput) findComponent("cargaIsste");
				input.setValid(false);
				pr.setStatus("Nombre Carga Isste es requerido");
				
			}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
	
	public void ejecutarIssteReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Reporte Isste por CURP");
		
		try {
			System.out.println("VALOR DE nombreReporteIsste:" +nombreReporteIsste);
			if(nombreReporteIsste != null && !nombreReporteIsste.equals("") ) {
			//if(nombreReporteIsste.toLowerCase().endsWith(".xls")) {
				if(nombreReporteIsste.endsWith(".xls") && nombreReporteIsste.contains("RPT-SLD-ISSS-FIN-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteReporte(rutaReporteIsste, nombreReporteIsste);
				System.out.println("VALOR DE REPORTE ISSTE CURP consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);
				if(consultaSaldoImssIssteOut.getEstatus()==1) {
				pr.setStatus("Proceso Exitoso");
				}else {
				pr.setStatus("Proceso Fallido");	
				}
			}else {
				UIInput input = (UIInput) findComponent("reporteIsste");
				input.setValid(false);
				pr.setStatus("Reporte Nombre Isste formato invalido");
				
			}
			}else {
				UIInput input = (UIInput) findComponent("reporteIsste");
				input.setValid(false);
				pr.setStatus("Reporte Nombre Isste es requerido");
				
			}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
//	public void ejecutarIssteReporte() {
//		//procesos=new ArrayList<ProcesoOut>();
//		ProcessResult proceso = new ProcessResult();
//		Date today= new Date();	
//		Date today2= new Date();	
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
//		if(nombreReporteIsste.toLowerCase().endsWith(".txt")){
//			System.out.println("SI TEMINA EN .TXT");
//		try {System.out.println("VALOR DE rutaReporteIsste:"+rutaReporteIsste+" /nombreReporteIsste:"+nombreReporteIsste);
//		//Date today= new Date();		
//		///proceso = new ProcesoOut();
//		proceso.setFechaInicial(DateUtil.getNowDate());
//		consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteReporte(rutaReporteIsste, nombreReporteIsste);
//		System.out.println("VALOR DE consultaSaldoImssIssteOut es; "+consultaSaldoImssIssteOut);	
//		//Date today2= new Date();		
//		proceso.setFechaFinal(DateUtil.getNowDate());
//		if(consultaSaldoImssIssteOut.getMensaje().equals("PROCESO ENVIADO A MONITOR, FAVOR DE VERIFICAR...")  || consultaSaldoImssIssteOut.getMensaje().equals("Proceso enviado a monitor...")) {
//			proceso.setDescProceso(consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//			proceso.setStatus("SATISFACTORIO");		//"Proceso ejecutado"
//			//addMessageOK(consultaSaldoImssIssteOut.getMensaje());
//			}else {
//				proceso.setDescProceso( consultaSaldoImssIssteOut.getMensaje());//"Generar reporte"
//				proceso.setStatus("FALLIDO");
//			//	addMessageFail( consultaSaldoImssIssteOut.getMensaje());
//			}
//		}catch (Exception e) {
//			GenericException(e);
//		}
//	}else {
//		//Date today= new Date();		
//		//proceso = new ProcesoOut();
//		proceso.setFechaInicial(DateUtil.getNowDate());
//		//Date today2= new Date();		
//		proceso.setFechaFinal(DateUtil.getNowDate());
//		if(nombreReporteIsste==null || nombreReporteIsste=="") {
//			proceso.setDescProceso("NO TIENE NOMBRE REPORTE ISSTE");//"Generar reporte"	
//			//addMessageFail("Ingrese el nombre del archivo");
//		}else {
//			proceso.setDescProceso("NOMBRE INCORRECTO REPORTE ISSTE");//"Generar reporte"
//			//addMessageFail("Ingrese el nombre del archivo correcto");
//		}
//		proceso.setStatus("FALLIDO ");
//	}
//		resultados.add(proceso);
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
