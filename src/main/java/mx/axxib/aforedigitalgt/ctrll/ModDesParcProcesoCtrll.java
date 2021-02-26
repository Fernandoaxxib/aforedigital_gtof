package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DiagnosticoResult;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.RegisSinSalarioOut;
import mx.axxib.aforedigitalgt.serv.ModDesParcProcesoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "modDesParcProceso")
@ELBeanName(value = "modDesParcProceso")
public class ModDesParcProcesoCtrll extends ControllerBase {

	@Autowired
	private ModDesParcProcesoServ service;

	@Getter
	@Setter
	private List<RegisSinSalarioOut> listSolicitudes;

	@Getter
	@Setter
	private String radioSelected;
	@Getter
	@Setter
	private ProcesoOut proceso;
	@Getter
	@Setter
	private Integer pendientes;
	@Getter
	@Setter
	private Integer totales;
	@Getter
	@Setter
	private Integer unaExhibicion;
	@Getter
	@Setter
	private Integer sinSalario;
	@Getter
	@Setter
	private Integer parcialidades;
	@Getter
	@Setter
	private Date fecha;

	@Getter
	private String display3;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
			proceso = null;
		}
	}

	public void radioSelected() {		
			fecha=null;		 
	}

	public void buscarRegXProcesar() {
		ProcessResult pr = new ProcessResult();
			pr.setFechaInicial(DateUtil.getNowDate());
			if (isFormValid(pr)) {
				if (radioSelected.equals("1")) {
					try {
						DiagnosticoResult res = service.getRegistrosXProcesar(fecha);
						parcialidades = res.getParcialidades();
						unaExhibicion = res.getUnaExhibicion();
						sinSalario = res.getSinSalario();
						totales = res.getTotales();
						pendientes = res.getPendientes();
						display3 = (sinSalario > 0) ? "inline" : "none";
						pr.setDescProceso("Búsqueda de registros por procesar");
						pr.setStatus("Exitoso");
					} catch (Exception e) {
						pr.setDescProceso("Búsqueda de registros por procesar");
						pr.setStatus("Error");
						GenericException(e);
					}finally {
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				}
			}else {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
	}

	public void guardar() {
		ProcessResult pr = new ProcessResult();
		try {
			if (radioSelected != null && fecha != null) {
				pr.setFechaInicial(DateUtil.getNowDate());
				pr.setDescProceso("Actualización de registros sin salario");
				listSolicitudes.forEach(p -> {
					try {
						service.guardarDetSal(p);

					} catch (Exception e) {
						GenericException(e);
					}

				});
				pr.setStatus("Exitoso");
			}
			// buscarRegXProcesar();
			reset();
		} catch (Exception e) {
			proceso.setEstadoProceso("Error");
			GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void mostrarRegistros() {
		try {
			if (sinSalario != null) {
				if (sinSalario > 0) {
					listSolicitudes = service.getRegSinSalario();
				} else {
					listSolicitudes = null;
				}
			}

		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void ejecutarAccion() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if(isFormValid2(pr)) {
			if (radioSelected.equals("1")) {
			  if(sinSalario<=0) {
				  if (pendientes > 0) {					
						try {							
							pr.setDescProceso("Diagnóstico de Solicitudes");
							EjecucionResult result = service.ejecutar(fecha, Integer.valueOf(radioSelected));
							pr.setStatus("Exitoso");
							buscarRegXProcesar();
						} catch (Exception e) {
							pr.setStatus("Error");
							GenericException(e);
						} finally {
							pr.setFechaFinal(DateUtil.getNowDate());
							resultados.add(pr);
						}
					} else {
						pr.setDescProceso("Diagnóstico de solicitudes");
						pr.setStatus("El proceso no se ejecutará, no existen solicitudes por procesar");
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}				  
			  }	else {
				    pr.setDescProceso("Diagnóstico de solicitudes");
					pr.setStatus("El proceso no se ejecutará, existen registros sin salarios");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
			  }
				
			} else {
				try {							
					pr.setDescProceso("Aprobación de Solicitudes");
					EjecucionResult result = service.ejecutar(fecha, Integer.valueOf(radioSelected));
					pr.setStatus("Exitoso");
					reset();
				} catch (Exception e) {
					pr.setStatus("Error");
					GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
									
			}
		
		}else {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public boolean isFormValid(ProcessResult pr) {
		if (radioSelected == null) {
			UIInput radio = (UIInput) findComponent("radSelect");
			radio.setValid(false);

			pr.setDescProceso("Debe seleccionar una opción");
			pr.setStatus("Selección requerida");
			return false;
		}
		return true;
	}
	public boolean isFormValid2(ProcessResult pr) {
		if(!isFormValid(pr))
			return false;
		
		if (fecha == null) {
			UIInput radio = (UIInput) findComponent("fCapturada");
			radio.setValid(false);

			pr.setDescProceso("Debe seleccionar una fecha");
			pr.setStatus("Selección requerida");
			return false;
		}

		return true;
	}

	public void reset() {
		fecha = null;
		listSolicitudes = null;
		radioSelected = null;
		pendientes = null;
		totales = null;
		unaExhibicion = null;
		sinSalario = null;
		parcialidades = null;
		display3 = "none";
	}

	public void addMessageOK(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", summary);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageFail(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", summary);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
