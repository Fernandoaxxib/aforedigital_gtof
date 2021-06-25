package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

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
	@Setter
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
	private Date today;
	
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
		}
	}
	
	public void ejecutarImssCarga() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga Imss por NSS");
		
		try {
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssCarga(rutaNssImss, nombreNssImss);
				
				if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
					pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
					}else {
						if (consultaSaldoImssIssteOut.getOn_Estatus() == 2) {
							GenerarErrorNegocio(consultaSaldoImssIssteOut.getMensaje());
						} else if (consultaSaldoImssIssteOut.getOn_Estatus() == 0) {
							pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
						}	
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
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarImssReporte(rutaReporteImss, nombreReporteImss);		
				
				if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
					pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
					}else {
						if (consultaSaldoImssIssteOut.getOn_Estatus() == 2) {
							GenerarErrorNegocio(consultaSaldoImssIssteOut.getMensaje());
						} else if (consultaSaldoImssIssteOut.getOn_Estatus() == 0) {
							pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
						}	
						
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
			
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteCarga(rutaCurpIsste, nombreCurpIsste);
				
				if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
					pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
					}else {
						if (consultaSaldoImssIssteOut.getOn_Estatus() == 2) {
							GenerarErrorNegocio(consultaSaldoImssIssteOut.getMensaje());
						} else if (consultaSaldoImssIssteOut.getOn_Estatus() == 0) {
							pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
						}	
						
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
			
				consultaSaldoImssIssteOut=saldosImssIsste.ejecutarIssteReporte(rutaReporteIsste, nombreReporteIsste);
				
				if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
					pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
					}else {
						if (consultaSaldoImssIssteOut.getOn_Estatus() == 2) {
							GenerarErrorNegocio(consultaSaldoImssIssteOut.getMensaje());
						} else if (consultaSaldoImssIssteOut.getOn_Estatus() == 0) {
							pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
						}		
					}
			
		}catch (Exception e) {
			pr = GenericException(e);
			
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	
	public boolean isNombreValidoNssImss(ProcessResult pr) {
		if (nombreNssImss == null || nombreNssImss.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreNssImss");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("(NSS-CARGA-REP-)+(\\d{4}\\d{2}\\d{2})+(.txt|.TXT)$");
			if (!pattern.matcher(nombreNssImss.toUpperCase()).matches()) {
				UIInput radio = (UIInput) findComponent("nombreNssImss");
				radio.setValid(false);
				pr.setStatus("el nombre del archivo debe tener extensión .txt");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}
	
	public boolean isReporteValidoNssImss(ProcessResult pr) {
		if (nombreReporteImss == null || nombreReporteImss.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreReporteImss");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("(RPT-SLD-IMSS-FIN-)+(\\d{4}\\d{2}\\d{2})+(.xls|.XLS)$");
			if (!pattern.matcher(nombreReporteImss.toUpperCase()).matches()) {
				UIInput radio = (UIInput) findComponent("nombreReporteImss");
				radio.setValid(false);
				pr.setStatus("el nombre del archivo debe tener extensión .xls");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}
	
	public boolean isNombreValidoCurpIsste(ProcessResult pr) {
		if (nombreCurpIsste == null || nombreCurpIsste.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreCurpIsste");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("(CURP-CARGA-REP-)+(\\d{4}\\d{2}\\d{2})+(.txt|.TXT)$");
			if (!pattern.matcher(nombreCurpIsste.toUpperCase()).matches()) {
				UIInput radio = (UIInput) findComponent("nombreCurpIsste");
				radio.setValid(false);
				pr.setStatus("el nombre del archivo debe tener extensión .txt");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}
	
	public boolean isReporteValidoCurpIsste(ProcessResult pr) {
		if (nombreReporteIsste == null || nombreReporteIsste.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreReporteImss");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("(RPT-SLD-ISSS-FIN-)+(\\d{4}\\d{2}\\d{2})+(.xls|.XLS)$");
			if (!pattern.matcher(nombreReporteIsste.toUpperCase()).matches()) {
				UIInput radio = (UIInput) findComponent("nombreReporteImss");
				radio.setValid(false);
				pr.setStatus("el nombre del archivo debe tener extensión .xls");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}
}
