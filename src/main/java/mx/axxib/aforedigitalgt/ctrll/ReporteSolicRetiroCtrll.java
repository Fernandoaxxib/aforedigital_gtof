package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ReporteSolcChequeRetiroIn;
import mx.axxib.aforedigitalgt.eml.Subtransacciones;
import mx.axxib.aforedigitalgt.eml.SubtransaccionesOut;
import mx.axxib.aforedigitalgt.eml.Transacciones;
import mx.axxib.aforedigitalgt.eml.TransaccionesOut;
import mx.axxib.aforedigitalgt.serv.ReporteSolicRetiroServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de reporte de solicitudes de retiro
//** Interventor Principal: JSAS
//** Fecha Creación: 11/Mar/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "repSolRetiro")
@ELBeanName(value = "repSolRetiro")
public class ReporteSolicRetiroCtrll extends ControllerBase {

	@Autowired
	private ReporteSolicRetiroServ reporteService;

	@Autowired
	private AforeMessage aforeMessage;

	@Getter
	private List<Transacciones> transacciones;

	@Getter
	@Setter
	private String selectedTransaccion;

	@Getter
	private List<Subtransacciones> subtransacciones;

	@Getter
	@Setter
	private String selectedSubtransaccion;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	@Setter
	private String noSolicInicial;

	@Getter
	@Setter
	private String noSolicFinal;

	@Getter
	@Setter
	private String estado;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			selectedTransaccion = null;
			subtransacciones = null;
			selectedSubtransaccion = null;
			fechaInicial = null;
			fechaFinal = null;
			noSolicInicial = null;
			noSolicFinal = null;
			estado = null;

			consultarTransacciones();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void consultarTransacciones() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setDescProceso("Consultar transacciones");
			pr.setFechaInicial(DateUtil.getNowDate());
			TransaccionesOut res = reporteService.getTransacciones();
			if (res.getEstatus() == 1) {
				transacciones = res.getTransacciones();
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} 
	}

	public void consultarSubtransacciones() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setDescProceso("Consultar subtransacciones");
			pr.setFechaInicial(DateUtil.getNowDate());
			selectedSubtransaccion = null;
			subtransacciones = null;
			if (selectedTransaccion != null) {
				SubtransaccionesOut res = reporteService.getSubtransacciones(selectedTransaccion);
				if (res.getEstatus() == 1) {
					subtransacciones = res.getSubtransacciones();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} 
	}

	private boolean isValidForm(ProcessResult pr) {
		if (selectedTransaccion == null) {
			UIInput fini = (UIInput) findComponent("transaccion");
			fini.setValid(false);
			pr.setStatus("Tipo de retiro es requerido");
			return false;
		}

		if (selectedSubtransaccion == null) {
			UIInput fini = (UIInput) findComponent("subtransaccion");
			fini.setValid(false);
			pr.setStatus("Tipo de prestación es requerido");
			return false;
		}

		if (estado == null) {
			UIInput fini = (UIInput) findComponent("estatus");
			fini.setValid(false);
			pr.setStatus("Estado es requerido");
			return false;
		}

		if (fechaInicial == null) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);
			pr.setStatus("Fecha inicio es requerida");
			return false;
		}

		if (fechaFinal == null) {
			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);
			pr.setStatus("Fecha fin es requerida");
			return false;
		}

		if (!DateUtil.isValidDates(fechaInicial, fechaFinal)) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);

			pr.setStatus("La fecha final debe ser mayor o igual a la inicial");
			return false;
		}

		if (noSolicInicial != null && !noSolicInicial.equals("")  && !ValidateUtil.isInteger(noSolicInicial)) {
			UIInput fini = (UIInput) findComponent("noSolicInicial");
			fini.setValid(false);

			pr.setStatus("Solicitud inicial debe ser numérico");
			return false;
		}

		if (noSolicFinal != null && !noSolicFinal.equals("")  && !ValidateUtil.isInteger(noSolicFinal)) {
			UIInput fini = (UIInput) findComponent("noSolicFinal");
			fini.setValid(false);

			pr.setStatus("Solicitud final debe ser numérico");
			return false;
		}

		return true;

	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setDescProceso("Generar reporte");
			pr.setFechaInicial(DateUtil.getNowDate());

			if (isValidForm(pr)) {
				ReporteSolcChequeRetiroIn parametros = new ReporteSolcChequeRetiroIn();
				parametros.setIdTransaccion(Integer.parseInt(selectedTransaccion));
				parametros.setIdSubtransaccion(selectedSubtransaccion);
				parametros.setFechaInicial(fechaInicial);
				parametros.setFechaFinal(fechaFinal);
				if(!noSolicInicial.equals("")) {
					parametros.setNoSolicInicial(Integer.parseInt(noSolicInicial));
				}
				if(!noSolicFinal.equals("")) {
					parametros.setNoSolicFinal(Integer.parseInt(noSolicFinal));
				}
				parametros.setEstado(estado);
				BaseOut res = reporteService.reporteSolcChequeRetiro(parametros);
				if (res.getEstatus() == 1) {
					pr.setStatus(
							aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null) + ": " + res.getMensaje());
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
