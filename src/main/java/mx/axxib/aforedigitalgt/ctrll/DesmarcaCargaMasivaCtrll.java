package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DesmarcaCargaConsultaMasivaOut;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.DesmarcaCargaConsultaMasivaService;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "desmarcaCargaMasiva")
@ELBeanName(value = "desmarcaCargaMasiva")
public class DesmarcaCargaMasivaCtrll extends ControllerBase {
	
	
	@Autowired
	private DesmarcaCargaConsultaMasivaService cargaMasiva;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private String rutaCarga;
	 
	@Getter
	@Setter
	private String nombreArchivoCarga;
	
	
	@Getter
	@Setter
	private DesmarcaCargaConsultaMasivaOut desmarcaCargaConsultaMasivaOut;

	
	@Getter
	private Date today;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			rutaCarga="/RESPALDOS/operaciones";		
			today= new Date();
			reset();
		}
	}
	
	public void reset() {
		nombreArchivoCarga=null;
	}
	
	public void cargarArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Cargar Archivo");
		

			if(isNombreValido(pr)) {
				
				try {	
			desmarcaCargaConsultaMasivaOut =cargaMasiva.ejecutarArchivoCarga(rutaCarga, nombreArchivoCarga);
		
			if(desmarcaCargaConsultaMasivaOut.getEstatus()==1 ) {
			pr.setStatus(desmarcaCargaConsultaMasivaOut.getMensaje());//"Proceso ejecutado Correctamente"
			}else {
				if (desmarcaCargaConsultaMasivaOut.getEstatus() == 2) {
					GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getMensaje());
				} else if (desmarcaCargaConsultaMasivaOut.getEstatus() == 0) {
					pr.setStatus(desmarcaCargaConsultaMasivaOut.getMensaje());
				}			
			}
		
			}catch (Exception e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
			}

		
	}
	
	
	public void reversaArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Revera Carga");
		try {

		desmarcaCargaConsultaMasivaOut =cargaMasiva.reversaArchivoCarga();
		
		if(desmarcaCargaConsultaMasivaOut.getEstatus()==1 ) {
			pr.setStatus(desmarcaCargaConsultaMasivaOut.getMensaje());//pr.setStatus("Proceso ejecutado Correctamente");
				}else {
					if (desmarcaCargaConsultaMasivaOut.getEstatus() == 2) {
						GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getMensaje());
					} else if (desmarcaCargaConsultaMasivaOut.getEstatus() == 0) {
						pr.setStatus(desmarcaCargaConsultaMasivaOut.getMensaje());
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
		if (nombreArchivoCarga == null || nombreArchivoCarga.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreCarga");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("[-_ A-Za-z0-9]+(.txt|.TXT)$");
			if (!pattern.matcher(nombreArchivoCarga.toUpperCase()).matches()) {
				UIInput radio = (UIInput) findComponent("nombreCarga");
				radio.setValid(false);
				pr.setStatus("el nombre del archivo debe tener extensión .txt");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}
	
}
