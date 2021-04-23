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
		Date myDate = new Date();
		//RTP-MOVS-SLD-FIN-20210221.xls
		new SimpleDateFormat("yyyyMMdd").format(myDate);
		if(init) {
			rutaSaldoNegativo="/RESPALDOS/operaciones/pruebas";
			nombreSaldoNegativo="RPT-MOVS-SLD-FIN-"+new SimpleDateFormat("yyyyMMdd").format(myDate)+".xls";
			today= new Date();
			saldoFechaMovimiento=today;
			reset();
		}
	}
	
	public void reset() {
		
		
	}
	
	public void ejecutarReporteNegativo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga por Saldos Vol.");
		try {
			
			if(nombreSaldoNegativo != null && !nombreSaldoNegativo.equals("") ) {
				//if(nombreSaldoNegativo.toLowerCase().endsWith(".xls")) {
					if(nombreSaldoNegativo.endsWith(".xls") && nombreSaldoNegativo.contains("RPT-MOVS-SLD-FIN-")) {
					
					consultaSaldoImssIssteOut=saldosImssIsste.ejecutarReporteNegativo(rutaSaldoNegativo, nombreSaldoNegativo,saldoFechaMovimiento);
					
					if(consultaSaldoImssIssteOut.getOn_Estatus()==1) {
						pr.setStatus("Proceso Exitoso");
						}else {
						pr.setStatus("Proceso Fallido");	
						//pr.setStatus(consultaSaldoImssIssteOut.getMensaje());
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

	
}
