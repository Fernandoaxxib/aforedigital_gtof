package mx.axxib.aforedigitalgt.ctrll;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.serv.CerInaAppServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** Funcionalidad: Controlador - Certificación de inactividad - AppMovil
//** Desarrollador: JJSC
//** Fecha de creación: 18/Ene/2021
//** Última modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "cerInaApp")
@ELBeanName(value = "cerInaApp")
public class CerInaAppCtrll extends ControllerBase {

	@Autowired
	private CerInaAppServ service;
	@Getter	
	private String ruta;
	@Getter
	private String archivo;	
	@Getter
	private String archivo2;
	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	private Date fecActual;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
			fecActual=DateUtil.getNowDate();
			init=false;
		}
	}

	public void reset() {
		ruta = "/RESPALDOS/operaciones";
		archivo = "PRTFT.DP.A01530.S180221.NOTFOLIO.C001";				
		fecha = new Date();
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String f = format.format(fecha);			
		archivo2 = "DP_A01530_" + f;
	}

	public void ejecutar() {
		if ((ruta != null && !ruta.isEmpty()) && (archivo != null && !archivo.isEmpty())) {
			ProcessResult pr = new ProcessResult();
			pr.setFechaInicial(DateUtil.getNowDate());
			try {
                pr.setDescProceso("Carga respuesta de AppMovil");
				ProcesResult resp = service.ejecutar(ruta, archivo);
				if (resp.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getP_Message());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getP_Message());
					}
				}
			} catch (Exception e) {
				pr=GenericException(e);
			}finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}
	}

	public void generar() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if (fecha != null) {
			try {
				pr.setDescProceso("Generación de archivo liquidaciones AppMovil");
				ProcesResult resp = service.generarArchivo(fecha);
				if (resp.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getP_Message());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getP_Message());
					}
				}
			} catch (Exception e) {
				pr=GenericException(e);
			}finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			UIInput radio = (UIInput) findComponent("fCapturada");
			radio.setValid(false);
			pr.setDescProceso("Debe seleccionar una fecha");
			pr.setStatus("Selección requerida");
		}
	}

	public void addMessageOK(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageFail(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void opcionSeleccionada() {		
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
			String f = format.format(fecha);			
			this.archivo2 = "DP_A01530_" + f;									
	}
}