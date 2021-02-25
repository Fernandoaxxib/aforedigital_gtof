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
import mx.axxib.aforedigitalgt.eml.ExportarIn;
import mx.axxib.aforedigitalgt.eml.LlenaInfo;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;
import mx.axxib.aforedigitalgt.serv.NotificacionPagosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "notificacionPagos")
@ELBeanName(value = "notificacionPagos")
public class NotificacionPagosCtrll extends ControllerBase {

	@Autowired
	private NotificacionPagosServ notificacionPagosServ;

	@Autowired
	private AforeMessage aforeMessage;

	@Getter
	@Setter
	private Date fecha;

	@Getter
	private List<LlenaInfo> pagos;

	@Getter
	private Integer totTitulos;

	@Getter
	private Integer totPesos;

	@Getter
	private boolean mostrarEnviar;

	@Getter
	private boolean mostrarExportar;

	@Getter
	private String mensajeTabla;

	@Getter
	@Setter
	private String archivo;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			fecha = new Date();
			limpiar();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		archivo = null;
		mensajeTabla = null;
		mostrarEnviar = false;
		mostrarExportar = false;
		pagos = null;
		totTitulos = null;
		totPesos = null;
	}

	public void buscarPagos() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Búsqueda por fecha");
			limpiar();
			LlenaInfoOut res = notificacionPagosServ.llenaInfo(fecha);
			if (res.getEstatus() == 1) {
				if (res != null && res.getDatos() != null && res.getDatos().size() > 0) {
					pagos = res.getDatos();
					totTitulos = res.getTotTitulos();
					totPesos = res.getTotPesos();
					mostrarEnviar = true;
				} else {
					pr.setStatus("No se encontraron resultados");
					mensajeTabla = "Sin información";
				}
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void enviar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Acción enviar");
			BaseOut res = notificacionPagosServ.enviaFecha(fecha);
			if (res.getEstatus() == 1) {
				mostrarEnviar = false;
				mostrarExportar = true;
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void exportar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Acción exportar");

			if (archivo != null && !archivo.equals("")) {
				if (ValidateUtil.isValidFileName(archivo)) {

					ExportarIn parametros = new ExportarIn();
					parametros.setFecha(fecha);
					parametros.setArchivo(archivo);
					BaseOut res = notificacionPagosServ.exportar(parametros);
					if (res.getEstatus() == 1) {
						//pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						pr.setStatus(res.getMensaje());
					} else {
						if (res.getEstatus() == 2) {
							GenerarErrorNegocio(res.getMensaje());
						} else if (res.getEstatus() == 0) {
							pr.setStatus(res.getMensaje());
						}
					}

				} else {
					UIInput input = (UIInput) findComponent("archivo");
					input.setValid(false);
					pr.setStatus("Nombre de archivo no válido");
				}
			} else {
				UIInput input = (UIInput) findComponent("archivo");
				input.setValid(false);
				pr.setStatus("Nombre de archivo es requerido");
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
}
