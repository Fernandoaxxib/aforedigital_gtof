package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		try {
		if(nombreArchivoCarga==null || nombreArchivoCarga.isEmpty()) {
			UIInput input = (UIInput) findComponent("nombreCarga");
			input.setValid(false);
			pr.setStatus("Ingrese nombre para la carga de Archivo");
		}else {
			if((nombreArchivoCarga.endsWith(".txt")) && (nombreArchivoCarga.length()>4)) {
			desmarcaCargaConsultaMasivaOut =cargaMasiva.ejecutarArchivoCarga(rutaCarga, nombreArchivoCarga);
			System.out.println("cargar archivo: "+desmarcaCargaConsultaMasivaOut);
			if(desmarcaCargaConsultaMasivaOut.getOn_Estatus()==1 ) {
			pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());//"Proceso ejecutado Correctamente"
			}else {
				if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 2) {
					GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
				} else if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 0) {
					pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
				}			
			}
			}else {
				UIInput input = (UIInput) findComponent("nombreCarga");
				input.setValid(false);
				pr.setStatus("Ingrese Nombre de Archivo con extensión .txt");	
			}
		}

		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	
	public void reversaArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Cargar Archivo");
		try {

		desmarcaCargaConsultaMasivaOut =cargaMasiva.reversaArchivoCarga();
		System.out.println("reversa archivo: "+desmarcaCargaConsultaMasivaOut);
		if(desmarcaCargaConsultaMasivaOut.getOn_Estatus()==1 ) {
			pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());//pr.setStatus("Proceso ejecutado Correctamente");
				}else {
					if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 2) {
						GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
					} else if (desmarcaCargaConsultaMasivaOut.getOn_Estatus() == 0) {
						pr.setStatus(desmarcaCargaConsultaMasivaOut.getP_Mensaje());
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
