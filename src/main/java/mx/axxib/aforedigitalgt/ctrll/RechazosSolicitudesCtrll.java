package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

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
import mx.axxib.aforedigitalgt.eml.CatRechazos;
import mx.axxib.aforedigitalgt.eml.CatRechazosOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteIn;
import mx.axxib.aforedigitalgt.eml.GeneraReporteOut;
import mx.axxib.aforedigitalgt.eml.RechazosOut;
import mx.axxib.aforedigitalgt.serv.RechazosSolicitudesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "rechazos")
@ELBeanName(value = "rechazos")
public class RechazosSolicitudesCtrll extends ControllerBase {

	@Autowired
	private RechazosSolicitudesServ rechazosService;

	@Getter
	@Setter
	private String folio;

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
	List<CatRechazos> catRechazos;

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
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar");
			rechazo = null;
			RechazosOut res = rechazosService.getConsultaRechazos(folio, 1);
			if(res.getEstatus() == 1) {
				rechazo = res;
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if(res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if(res.getEstatus() == 0) {
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

	public void obtenerCatalogo() {
		try {
			CatRechazosOut res = rechazosService.getCatalogo();
			catRechazos = res.getRechazos();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public boolean isFormValidR(ProcessResult pr) {
		if(fechaInicial == null) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);
			pr.setDescProceso("Debe elegir una fecha inicio");
			pr.setStatus("Fecha inicio es requerida");
			return false;
		}
		
		if(fechaFinal == null) {
			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);
			pr.setDescProceso("Debe elegir una fecha fin");
			pr.setStatus("Fecha fin es requerida");
			return false;
		}
		
		if (!DateUtil.isValidDates(fechaInicial, fechaFinal)) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);

			pr.setDescProceso("La fecha final debe ser mayor o igual a la inicial");
			pr.setStatus("Error en las fechas");
			return false;
		}

		if (nombreArchivo != null && !nombreArchivo.equals("")) {
			if (!ValidateUtil.isValidFileName(nombreArchivo)) {
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

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Generar reporte");
			if(isFormValidR(pr)) {
				GeneraReporteIn parametros = new GeneraReporteIn();
				parametros.setFechaInicial(fechaInicial);
				parametros.setFechaFinal(fechaFinal);
				parametros.setNombreArchivo(nombreArchivo);
				GeneraReporteOut res = rechazosService.generaReporte(parametros);
	
				if(res.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null)+": "+res.getMensaje());
				} else {
					if(res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if(res.getEstatus() == 0) {
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
