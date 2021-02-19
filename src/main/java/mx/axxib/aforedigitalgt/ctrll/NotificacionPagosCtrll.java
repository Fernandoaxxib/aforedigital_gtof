package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ExportarIn;
import mx.axxib.aforedigitalgt.eml.ExportarOut;
import mx.axxib.aforedigitalgt.eml.LlenaInfo;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;
import mx.axxib.aforedigitalgt.serv.NotificacionPagosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

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
			if (res != null && res.getDatos() != null && res.getDatos().size() > 0) {
				pagos = res.getDatos();
				totTitulos = res.getTotTitulos();
				totPesos = res.getTotPesos();
				mostrarEnviar = true;
				pr.setStatus("Exitoso");
			} else {
				pr.setStatus("No se encontraron resultados");
				mensajeTabla = "Sin información";
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
			if (pagos != null && pagos.size() > 0) {
				String res = notificacionPagosServ.enviaFecha(fecha);
//				FacesContext.getCurrentInstance().addMessage(null,
//						new FacesMessage(FacesMessage.SEVERITY_INFO, "", res));
				pr.setStatus("Exitoso");
				mostrarEnviar = false;
				mostrarExportar = true;
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
			if (pagos != null && pagos.size() > 0) {
				ExportarIn parametros = new ExportarIn();
				parametros.setFecha(fecha);
				ExportarOut res = notificacionPagosServ.exportar(parametros);
				if (res.getMensaje() == null && res.getError() == null) {
					mostrarExportar = false;
//					String msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
//					FacesContext.getCurrentInstance().addMessage(null,
//							new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
					pr.setStatus("Exitoso");
				} else {
//					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,
//							"", res.getMensaje() + " " + res.getError()));
					pr.setStatus(res.getError());
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
