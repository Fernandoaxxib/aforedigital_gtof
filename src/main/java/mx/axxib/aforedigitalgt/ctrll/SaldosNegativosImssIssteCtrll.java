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
		Date myDate = new Date();
		
		new SimpleDateFormat("yyyyMMdd").format(myDate);
		if(init) {
			rutaNssImss="/RESPALDOS/operaciones";
			nombreNssImss="NSS-CARGA-REP-"+new SimpleDateFormat("yyyyMMdd").format(myDate)+".txt";
			rutaReporteImss="/RESPALDOS/operaciones";	
			nombreReporteImss="RPT-SLD-IMSS-FIN-"+new SimpleDateFormat("yyyyMMdd").format(myDate)+".xls";
			rutaCurpIsste="/RESPALDOS/operaciones";	
			nombreCurpIsste="CURP-CARGA-REP-"+new SimpleDateFormat("yyyyMMdd").format(myDate)+".txt";
			rutaReporteIsste="/RESPALDOS/operaciones";	
			nombreReporteIsste="RPT-SLD-ISSS-FIN-"+new SimpleDateFormat("yyyyMMdd").format(myDate)+".xls";;
			today= new Date();
			limpiar();
		}
	}
	
	public void limpiar() {
		mensajeTabla=null;
		
	}
	
	public void ejecutarImssCarga() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga Imss por NSS");
		
		try {
			
			if(nombreNssImss != null && !nombreNssImss.isEmpty() ) {
			//if(nombreNssImss.toLowerCase().endsWith(".txt")) {
				if(nombreNssImss.endsWith(".txt") && nombreNssImss.contains("NSS-CARGA-REP-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssCarga(rutaNssImss, nombreNssImss);
				
				
				if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
				pr.setStatus("Proceso Exitoso");
				}else {
				pr.setStatus("Proceso Fallido");	
				}
			}else {
//				UIInput input = (UIInput) findComponent("cargaImss");
//				input.setValid(false);
//				pr.setStatus("Nombre Carga Imss formato invalido");
				
			}
			}else {
//				UIInput input = (UIInput) findComponent("cargaImss");
//				input.setValid(false);
//				pr.setStatus("Nombre Carga Imss es requerido");
				
			}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
		

	}
	

	

	
	public void ejecutarImssReporte() {
		
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Reporte Imss por NSS");
		try {
			if(nombreReporteImss != null && !nombreReporteImss.isEmpty() ) {
			//if(nombreReporteImss.toLowerCase().endsWith(".xls")) {
				if(nombreReporteImss.endsWith(".xls") && nombreReporteImss.contains("RPT-SLD-IMSS-FIN-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);		
				
				if(consultaSaldoImssIssteOut.getEstatus()==1) {
					pr.setStatus("Proceso Exitoso");
					}else {
					pr.setStatus("Proceso Fallido");	
					}
			}else {
//				UIInput input = (UIInput) findComponent("reporteImss");
//				input.setValid(false);
//				pr.setStatus("Reporte Nombre Imss formato invalido");	
			}
			}else {
//				UIInput input = (UIInput) findComponent("reporteImss");
//				input.setValid(false);
//				pr.setStatus("Reporte Nombre Imss es requerido");
			}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}

	

	public void ejecutarIssteCarga() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga Isste por CURP");
		
		try {
			
			if(nombreCurpIsste != null && !nombreCurpIsste.isEmpty()) {
			//if(nombreCurpIsste.toLowerCase().endsWith(".txt")) {
				if(nombreCurpIsste.endsWith(".txt") && nombreCurpIsste.contains("CURP-CARGA-REP-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteCarga(rutaCurpIsste, nombreCurpIsste);
				
				if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
				pr.setStatus("Proceso Exitoso");
				}else {
				pr.setStatus("Proceso Fallido");	
				}
			}else {
//				UIInput input = (UIInput) findComponent("cargaIsste");
//				input.setValid(false);
//				pr.setStatus("Nombre Carga Isste formato invalido");
				
			}
			}else {
//				UIInput input = (UIInput) findComponent("cargaIsste");
//				input.setValid(false);
//				pr.setStatus("Nombre Carga Isste es requerido");
				
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
			
			if(nombreReporteIsste != null && !nombreReporteIsste.isEmpty() ) {
			//if(nombreReporteIsste.toLowerCase().endsWith(".xls")) {
				if(nombreReporteIsste.endsWith(".xls") && nombreReporteIsste.contains("RPT-SLD-ISSS-FIN-")) {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteReporte(rutaReporteIsste, nombreReporteIsste);
				
				if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
				pr.setStatus("Proceso Exitoso");
				}else {
				pr.setStatus("Proceso Fallido");	
				}
			}else {
//				UIInput input = (UIInput) findComponent("reporteIsste");
//				input.setValid(false);
//				pr.setStatus("Reporte Nombre Isste formato invalido");
				
			}
			}else {
//				UIInput input = (UIInput) findComponent("reporteIsste");
//				input.setValid(false);
//				pr.setStatus("Reporte Nombre Isste es requerido");
				
			}
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
}
