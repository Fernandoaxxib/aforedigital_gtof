package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.ResultOut;
import mx.axxib.aforedigitalgt.serv.CerInaRepServ;

@Scope(value = "session")
@Component(value = "cerInaReportes")
@ELBeanName(value = "cerInaReportes")
public class CerInaReportesCtrll extends ControllerBase {

	@Autowired
	private CerInaRepServ service;

	@Getter
	@Setter
	private String radioSelected;
	@Getter
	@Setter
	private String ruta;
	@Getter
	@Setter
	private String archivo;

	@Getter
	@Setter
	private ProcesoOut proceso;

	@Getter
	@Setter
	private Date fechaInicio;

	@Getter
	@Setter
	private Date fechaFin;

	@Getter
	@Setter
	private String disabled;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
		}
	}

	public void ejecutar() {
		if (radioSelected != null) {
			if (radioSelected.equals("1") || radioSelected.equals("2") || radioSelected.equals("3")
					|| radioSelected.equals("4")) {
				if (fechaInicio != null) {
					try {
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
						Date today = new Date();
						proceso = new ProcesoOut();
						proceso.setFechahoraInicio(format.format(today));
						ResultOut result = service.procesarReporte(fechaInicio, fechaFin, radioSelected);
						Date today2 = new Date();
						proceso.setFechahoraFinal(format.format(today2));			
						proceso.setEstadoProceso(result.getP_Avance());
						switch (radioSelected) {

						case "1":
							proceso.setAbrevProceso("Reporte de mensualidades");
							break;
						case "2":
							proceso.setAbrevProceso("Reporte cuentas no afectadas");
							break;
						case "3":
							proceso.setAbrevProceso("Reporte inactividad IMSS");
							break;
						case "4":
							proceso.setAbrevProceso("Generación layout pagos");
							break;
						default:
							proceso.setAbrevProceso("");

						}
					} catch (Exception e) {
						GenericException(e);
					}
				} else {
					addMessageFail("Seleccione la fecha");
				}
			} else {
				if (radioSelected.equals("5") || radioSelected.equals("6")) {
					if (fechaInicio != null && fechaFin != null) {
						if (fechaInicio.before(fechaFin) || fechaInicio.equals(fechaFin)) {
							try {
								SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
								Date today = new Date();
								proceso = new ProcesoOut();
								proceso.setFechahoraInicio(format.format(today));
								ResultOut result = service.procesarReporte(fechaInicio, fechaFin, radioSelected);
								Date today2 = new Date();
								proceso.setFechahoraFinal(format.format(today2));						
								proceso.setEstadoProceso(result.getP_Avance());
								
								switch (radioSelected) {

								case "5":
									proceso.setAbrevProceso("Reporte de negativas");
									break;
								case "6":
									proceso.setAbrevProceso("Reporte desglose de pagos");
									break;
								
								default:
									proceso.setAbrevProceso("");

								}
							} catch (Exception e) {
								GenericException(e);
							}
						}else {
						   addMessageFail("La fecha inicio debe ser menor o igual a la fecha fin");	
						}
					} else {
						addMessageFail("Seleccione fecha inicio y fecha fin");
					}
				}
			}
		} else {
			addMessageFail("Seleccione una opción");
		}
	}

	public void radioSelected() {
		switch (radioSelected) {

		case "1":
			this.disabled = "true";
			this.fechaInicio = null;
			this.fechaFin = null;
			break;

		case "2":
			this.disabled = "true";
			this.fechaInicio = null;
			this.fechaFin = null;
			break;

		case "3":
			this.disabled = "true";
			this.fechaInicio = null;
			this.fechaFin = null;
			break;

		case "4":
			this.disabled = "true";
			this.fechaInicio = null;
			this.fechaFin = null;
			break;

		case "5":
			this.disabled = "false";
			this.fechaInicio = null;
			this.fechaFin = null;
			break;

		case "6":
			this.disabled = "false";
			this.fechaInicio = null;
			this.fechaFin = null;
			break;

		default:
			this.disabled = "true";
			break;

		}
	}

	public void opcionSeleccionada() {

		if(radioSelected!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String f = format.format(fechaInicio);

			switch (radioSelected) {

			case "1":
				this.archivo = "RepMenALiq_" + f + ".xls";
				break;

			case "2":
				this.archivo = "RepCtasNoAfec_" + f + ".xls";
				break;

			case "3":
				this.archivo = "RepInacImss_" + f + ".xls";
				break;

			case "4":
				this.archivo = "LayoutPagParc_" + f + ".xls";
				break;

			case "5":
				this.archivo = "RepNegativas_" + f + ".xls";
				break;

			case "6":
				this.archivo = "RepDesglosePagos_" + f + ".xls";
				break;

			default:
				this.archivo = "";
				this.ruta = "";
				break;
			}
		}else {
			addMessageFail("Debe seleccionar una opción");
		}
		

	}

	public void reset() {
		disabled = "true";
		ruta = "/RESPALDOS/operaciones";
		fechaInicio = null;
		fechaFin = null;
		archivo = null;
		proceso=null;
		radioSelected=null;
	}

	public void addMessageOK(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageFail(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
