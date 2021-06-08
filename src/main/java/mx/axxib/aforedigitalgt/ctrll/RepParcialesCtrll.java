package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import javax.faces.component.UIInput;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ReporteOut;
import mx.axxib.aforedigitalgt.serv.RepParcialesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** Funcionalidad: Controlador - Reporte de parciales CONSAR
//** Desarrollador: JJSC
//** Fecha de creación: 15/Feb/2021
//** Última modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "repParcialesConsar")
@ELBeanName(value = "repParcialesConsar")
public class RepParcialesCtrll extends ControllerBase {

	@Autowired
	private RepParcialesServ service;

	@Getter
	@Setter
	private Date fechaInicio;
	@Getter
	@Setter
	private Date fechaFin;
	@Getter
	private String ruta;
	@Getter
	@Setter
	private String archivo;
	@Getter
	private Date fecActual;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
		}
	}

	public void reset() {
		ruta = "/RESPALDOS/operaciones";
		archivo = null;
		fechaInicio = null;
		fechaFin = null;
		fecActual = DateUtil.getNowDate();
	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Generación de reporte");

		if (validarFechas(pr)) {
			if (validarNombre(pr)) {
				try {
					ReporteOut res = service.generarReporte(fechaInicio, fechaFin, ruta, archivo);
					if (res.getOn_Estatus() == 1) {
						String respuesta = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
						respuesta=respuesta.concat(" - Se generó el arachivo "+archivo + ".xls , ruta: " + ruta);
						pr.setStatus(respuesta);
						reset();
					} else {
						if (res.getOn_Estatus() == 2) {
							GenerarErrorNegocio(res.getOc_Mensaje());
						} else if (res.getOn_Estatus() == 0) {
							pr.setStatus(res.getOc_Mensaje());							
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
		}		
	}

	public boolean validarFechas(ProcessResult pr) {
		if (fechaInicio != null && fechaFin != null) {
			if (DateUtil.isValidDates(fechaInicio, fechaFin)) {
				return true;
			} else {
				UIInput fechaIni = (UIInput) findComponent("dini");
				fechaIni.setValid(false);
				UIInput fechaFin = (UIInput) findComponent("dffin");
				fechaFin.setValid(false);
				pr.setStatus("Fecha inicio debe ser menor o igual a la fecha fin");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
		} else {
			UIInput fechaIni = (UIInput) findComponent("dini");
			fechaIni.setValid(false);
			UIInput fechaFin = (UIInput) findComponent("dffin");
			fechaFin.setValid(false);
			pr.setStatus("Se requiere fecha inicio y fecha fin");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		}
	}

	public boolean validarNombre(ProcessResult pr) {
		if (archivo != null && !archivo.isEmpty()) {
			if (ValidateUtil.isValidFileName(archivo)) {
				return true;
			} else {				
				UIInput archivo = (UIInput) findComponent("archivo");
				archivo.setValid(false);
				pr.setStatus("El nombre de archivo no es válido");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
		} else {		
			UIInput archivo = (UIInput) findComponent("archivo");
			archivo.setValid(false);
			pr.setStatus("Se requiere nombre de archivo");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		}
	}
}
