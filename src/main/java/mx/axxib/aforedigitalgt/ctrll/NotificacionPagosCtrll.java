package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.eml.ExportarIn;
import mx.axxib.aforedigitalgt.eml.ExportarOut;
import mx.axxib.aforedigitalgt.eml.LlenaInfo;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;
import mx.axxib.aforedigitalgt.serv.NotificacionPagosServ;

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

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			fecha = new Date();
			pagos = null;
			totTitulos = null;
			totPesos = null;

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void buscarPagos() {
		try {
			pagos = null;
			totTitulos = null;
			totPesos = null;
			LlenaInfoOut res = notificacionPagosServ.llenaInfo(fecha);
			if (res != null && res.getDatos() != null && res.getDatos().size() > 0) {
				pagos = res.getDatos();
				totTitulos = res.getTotTitulos();
				totPesos = res.getTotPesos();
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void enviar() {
		try {
			if (pagos != null && pagos.size() > 0) {
				String res = notificacionPagosServ.enviaFecha(fecha);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", res));
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void exportar() {
		try {
			if (pagos != null && pagos.size() > 0) {
				ExportarIn parametros = new ExportarIn();
				parametros.setFecha(fecha);
				ExportarOut res = notificacionPagosServ.exportar(parametros);
				if (res.getMensaje() == null && res.getError() == null) {
					String msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
				} else {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,
							"", res.getMensaje() + " " + res.getError()));
				}
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}
}
