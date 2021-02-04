package mx.axxib.aforedigitalgt.ctrll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.GeneraReporteUMAIn;
import mx.axxib.aforedigitalgt.eml.ValorUMA;
import mx.axxib.aforedigitalgt.eml.ValorUMAOut;
import mx.axxib.aforedigitalgt.serv.ValorUMAServ;

@Scope(value = "session")
@Component(value = "valorUMA")
@ELBeanName(value = "valorUMA")
public class ValorUMACtrll extends ControllerBase {

	@Autowired
	private ValorUMAServ valorUMAService;

	@Getter
	@Setter
	private Date fechaInicial;
	
	@Getter
	@Setter
	private Date fechaFinal;
	
	@Getter
	private String ruta;

	@Getter
	private List<ValorUMA> valores;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			fechaInicial = null;
			fechaFinal = null;
			ruta = "/RESPALDOS/operaciones";
			valores = new ArrayList<ValorUMA>();
			
			valorUMA();
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void valorUMA() {
		try {
			String usuario = "USER1"; // TODO: revisar qué usuario se debe enviar en la versión final
			ValorUMAOut res = valorUMAService.getValorUMA(usuario);
			if (res.getMensaje().equals("PROCESO TERMINADO CORRECTAMENTE")) {
				valores = res.getValores();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", res.getMensaje()));
			}
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void generarReporte() {
		try {
			GeneraReporteUMAIn parametros = new GeneraReporteUMAIn();
			parametros.setFechaInicial(fechaInicial);
			parametros.setFechaFinal(fechaFinal);
			parametros.setRuta(ruta);
			String msg = valorUMAService.getGeneraReporte(parametros);
			if (msg.toLowerCase().startsWith("error")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

}
