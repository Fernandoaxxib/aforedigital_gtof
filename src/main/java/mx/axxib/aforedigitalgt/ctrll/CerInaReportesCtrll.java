package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
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
import mx.axxib.aforedigitalgt.serv.CerInaRepServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

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
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if (radioSelected != null) {
			if (radioSelected.equals("1") || radioSelected.equals("2") || radioSelected.equals("3")	|| radioSelected.equals("4")) {
				if (fechaInicio != null) {
					switch (radioSelected) {
					case "1":
						pr.setDescProceso("Reporte de mensualidades");
						break;
					case "2":
						pr.setDescProceso("Reporte cuentas no afectadas");
						break;
					case "3":
						pr.setDescProceso("Reporte inactividad IMSS");
						break;
					case "4":
						pr.setDescProceso("Generación layout pagos");
						break;					
					}
					
					try {						
						ReporteOut resp = service.procesarReporte(fechaInicio, fechaFin, radioSelected);
						if(resp.getOn_Estatus()==1) {							
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));						
						}else {
							if(resp.getOn_Estatus()== 2) {
								GenerarErrorNegocio(resp.getOc_Mensaje());
							} else if(resp.getOn_Estatus()== 0) {
								pr.setStatus(resp.getOc_Mensaje());
							} 
						}							
					} catch (Exception e) {
						pr=GenericException(e);
					}finally {
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				} else {
					UIInput radio = (UIInput) findComponent("fIni");
					radio.setValid(false);
					pr.setDescProceso("Debe seleccionar una fecha");
					pr.setStatus("Selección requerida");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			} else {
				if (radioSelected.equals("5") || radioSelected.equals("6")) {
					if (fechaInicio != null && fechaFin != null) {
						if (DateUtil.isValidDates(fechaInicio, fechaFin)) {
							switch (radioSelected) {
							case "5":
								pr.setDescProceso("Reporte de negativas");
								break;
							case "6":
								pr.setDescProceso("Reporte desglose de pagos");
								break;
							}
							
							try {								
								ReporteOut resp = service.procesarReporte(fechaInicio, fechaFin, radioSelected);
								if(resp.getOn_Estatus()==1) {							
									pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));						
								}else {
									if(resp.getOn_Estatus()== 2) {
										GenerarErrorNegocio(resp.getOc_Mensaje());
									} else if(resp.getOn_Estatus()== 0) {
										pr.setStatus(resp.getOc_Mensaje());
									} 
								}								
							} catch (Exception e) {
								pr=GenericException(e);
							}finally {
								pr.setFechaFinal(DateUtil.getNowDate());
								resultados.add(pr);	
							}
						} else {
							UIInput fechaI = (UIInput) findComponent("fIni");
							fechaI.setValid(false);
							UIInput fechaF = (UIInput) findComponent("fFin");
							fechaF.setValid(false);						
							pr.setDescProceso("Fecha inicio debe ser menor o igual a fecha fin");
							pr.setStatus("Selección requerida");
							pr.setFechaFinal(DateUtil.getNowDate());
							resultados.add(pr);	
						}
					} else {
						UIInput fechaI = (UIInput) findComponent("fIni");
						fechaI.setValid(false);
						UIInput fechaF = (UIInput) findComponent("fFin");
						fechaF.setValid(false);						
						pr.setDescProceso("Debe seleccionar fecha inicio y fecha fin");
						pr.setStatus("Selección requerida");
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);												
					}
				}
			}
		} else {
			UIInput radio = (UIInput) findComponent("radSelect");
			radio.setValid(false);
			pr.setDescProceso("Debe seleccionar una opción");
			pr.setStatus("Selección requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
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
		this.archivo=null;
	}

	public void opcionSeleccionada() {

		if (radioSelected != null) {
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
		}
	}

	public void reset() {
		disabled = "true";
		ruta = "/RESPALDOS/operaciones";
		fechaInicio = null;
		fechaFin = null;
		archivo = null;		
		radioSelected = null;
	}

}
