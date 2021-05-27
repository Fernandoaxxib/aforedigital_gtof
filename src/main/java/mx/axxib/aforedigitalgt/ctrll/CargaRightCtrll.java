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
import mx.axxib.aforedigitalgt.eml.CargaRightIn;
import mx.axxib.aforedigitalgt.eml.CargaRightOut;
import mx.axxib.aforedigitalgt.serv.CargaRightServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de carga right fax
//** Interventor Principal: JSAS
//** Fecha Creación: 25/Ene/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "cargaRight")
@ELBeanName(value = "cargaRight")
public class CargaRightCtrll extends ControllerBase {

	@Autowired
	private CargaRightServ cargaService;

	@Getter
	@Setter
	private Date fecha;

	@Getter
	@Setter
	private String archivo;
	
	@Getter
	private boolean mostrarCarga;

	private String path = "/RESPALDOS/openserv/";
	private String ext = ".csv";

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			mostrarCarga = false;
			fecha = null;
			archivo = null;

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public boolean isFormValid(ProcessResult pr) {
		if (fecha == null) {
			UIInput input = (UIInput) findComponent("fecha");
			input.setValid(false);
			pr.setStatus("La fecha es requerida");
			return false;
		}
		
		if (archivo != null && !archivo.equals("")) {
			if (!ValidateUtil.isValidFileName(archivo)) {
				UIInput input = (UIInput) findComponent("archivo");
				input.setValid(false);
				pr.setStatus("Nombre de archivo no válido");
				return false;
			}
		} else {
			UIInput input = (UIInput) findComponent("archivo");
			input.setValid(false);
			pr.setStatus("Nombre de archivo es requerido");
			return false;
		}

		return true;
	}

	public void crucePrevio() {
		ProcessResult pr = new ProcessResult();
		try {
			mostrarCarga = false;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cruce previo");

			if (isFormValid(pr)) {
				CargaRightIn parametros = new CargaRightIn();
				parametros.setFecha(fecha);
				parametros.setNombreArchivo(path + archivo + ext);
				CargaRightOut res = cargaService.getCrucePrevio(parametros);
				if (res.getEstatus() == 1) {
					mostrarCarga = true;
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null) + ": <br>" + res.getMensaje()
							+ " línea: " + res.getLinea());
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}

			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void cargar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cargar");
			if (isFormValid(pr)) {
				CargaRightIn parametros = new CargaRightIn();
				parametros.setFecha(fecha);
				parametros.setNombreArchivo(path + archivo + ext);
				CargaRightOut res = cargaService.getCarga(parametros);
				if (res.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null) + ": <br>" + res.getMensaje()
							+ " línea: " + res.getLinea());
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
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
