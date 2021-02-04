package mx.axxib.aforedigitalgt.ctrll;

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
import mx.axxib.aforedigitalgt.eml.CatRechazosOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteIn;
import mx.axxib.aforedigitalgt.eml.GeneraReporteOut;
import mx.axxib.aforedigitalgt.eml.RechazosOut;
import mx.axxib.aforedigitalgt.serv.RechazosSolicitudesServ;

@Scope(value = "session")
@Component(value = "rechazos")
@ELBeanName(value = "rechazos")
public class RechazosSolicitudesCtrll extends ControllerBase {

	@Autowired
	private RechazosSolicitudesServ rechazosService;

	@Getter
	@Setter
	private Integer folio;

	@Getter
	@Setter
	private String resolucion;

	@Getter
	@Setter
	private String nss;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	@Setter
	private String nombreArchivo;

	@Getter
	RechazosOut rechazo;

	@Getter
	List<CatRechazosOut> catRechazos;

	@Getter
	@Setter
	private String opcion;

	@Getter
	@Setter
	private boolean disFolio;

	@Getter
	@Setter
	private boolean disResolucion;

	@Getter
	@Setter
	private boolean disNss;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			folio = null;
			resolucion = null;
			nss = null;

			fechaInicial = null;
			fechaFinal = null;
			nombreArchivo = null;

			rechazo = null;
			catRechazos = null;

			opcion = "F";
			disFolio = false;
			disResolucion = true;
			disNss = true;
			
			obtenerCatalogo();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void cambio() {
		switch (opcion) {
		case "F":
			disFolio = false;
			disResolucion = true;
			disNss = true;
			break;
		case "R":
			disFolio = true;
			disResolucion = false;
			disNss = true;
			break;
		case "N":
			disFolio = true;
			disResolucion = true;
			disNss = false;
			break;
		}
	}

	public void buscar() {
		switch (opcion) {
		case "F":
			if (folio != null) {
				consultaFolio();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Folio requerido"));
			}

			break;
		case "R":
			if (resolucion != null && resolucion.length() > 3) {
				consultaResolucion();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Resolución requerido"));
			}
			break;
		case "N":
			if (nss != null && nss.length() > 3) {
				consultaNSS();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "NSS requerido"));
			}
			break;
		}
	}

	public void consultaFolio() {
		try {
			rechazo = rechazosService.getConsultaFolio(folio);
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void consultaResolucion() {
		try {

			rechazo = rechazosService.getConsultaResolucion(resolucion);
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void consultaNSS() {
		try {
			rechazo = rechazosService.getConsultaNSS(nss);
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void obtenerCatalogo() {
		try {
			catRechazos = rechazosService.getCatalogo();
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void generarReporte() {
		try {
			GeneraReporteIn parametros = new GeneraReporteIn();
			parametros.setFechaInicial(fechaInicial);
			parametros.setFechaFinal(fechaFinal);
			parametros.setNombreArchivo(nombreArchivo);
			GeneraReporteOut res = rechazosService.generaReporte(parametros);
			String mensaje = res.getMensaje();
			String seguimiento = res.getSeguimiento();

			if (mensaje != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensaje));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", seguimiento));
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	
	

}
