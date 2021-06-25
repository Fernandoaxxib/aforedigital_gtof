package mx.axxib.aforedigitalgt.ctrll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
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
@Component(value = "saldosNegativosVol")
@ELBeanName(value = "saldosNegativosVol")
public class SaldosNegativosVolCtrll extends ControllerBase{
	
	@Autowired
	SaldosImssIssteServ saldosImssIsste;
		
	@Getter
	ConsultaSaldoImssIssteOut consultaSaldoImssIssteOut;
	
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
	private String saldoNegativoFecha;

	@Override
	public void iniciar() {
		super.iniciar();
		Date myDate = new Date();
		Date myDate2 = new Date();
		new SimpleDateFormat("yyyyMMdd").format(myDate);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		
		if(init) {
			rutaSaldoNegativo="/RESPALDOS/operaciones/pruebas";
			nombreSaldoNegativo="RPT-MOVS-SLD-FIN-"+new SimpleDateFormat("yyyyMMdd").format(myDate)+".xls";
			today= new Date();
			saldoFechaMovimiento=today;
			saldoNegativoFecha = formatter.format(myDate2);
			
			
		}
	}
	
	public void ejecutarReporteNegativo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga por Saldos Vol.");
		
		try {
			
					consultaSaldoImssIssteOut=saldosImssIsste.ejecutarReporteNegativo(rutaSaldoNegativo, nombreSaldoNegativo,saldoFechaMovimiento);
					
					if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
						pr.setStatus(consultaSaldoImssIssteOut.getMensaje());//"Proceso Exitoso"
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
	
	public boolean isNombreValido(ProcessResult pr) {
		if (nombreSaldoNegativo == null || nombreSaldoNegativo.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreVol");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("(RPT-MOVS-SLD-FIN-)+(\\d{4}\\d{2}\\d{2})+(.xls|.XLS)$");
			if (!pattern.matcher(nombreSaldoNegativo.toUpperCase()).matches()) {
				UIInput radio = (UIInput) findComponent("nombreVol");
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
