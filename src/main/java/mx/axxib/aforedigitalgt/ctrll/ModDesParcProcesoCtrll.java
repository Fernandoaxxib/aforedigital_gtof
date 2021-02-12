package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.DiagnosticoResult;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.RegisSinSalarioOut;
import mx.axxib.aforedigitalgt.serv.ModDesParcProcesoServ;

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

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
			proceso=null;
		}
	}

	public void radioSelected() {
	}

	public void buscarRegXProcesar() {
		if (radioSelected != null) {
          if(radioSelected.equals("1")) {
        	  try {
  				DiagnosticoResult res = service.getRegistrosXProcesar(fecha);
  				parcialidades = res.getParcialidades();
  				unaExhibicion = res.getUnaExhibicion();
  				sinSalario = res.getSinSalario();
  				totales = res.getTotales();
  				pendientes = res.getPendientes();

  			} catch (Exception e) {
  				GenericException(e);
  			}
          }
		} else {
			addMessageFail("Debe seleccionar una opción") ;
		}
	}

	public void guardar() {
		try {
			if (radioSelected != null && fecha != null) {
				listSolicitudes.forEach(p -> {
					try {
						service.guardarDetSal(p);

					} catch (Exception e) {
						GenericException(e);
					}

				});
			}
			buscarRegXProcesar();
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void mostrarRegistros() {
		try {
			if (sinSalario!=null) {
				if(sinSalario > 0) {
					listSolicitudes = service.getRegSinSalario();
				}else {
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

		try {
			if (fecha != null && radioSelected != null) {	
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
				Date today= new Date();		
				proceso = new ProcesoOut();
				proceso.setFechahoraInicio(format.format(today));
				EjecucionResult result = service.ejecutar(fecha, Integer.valueOf(radioSelected));
				Date today2= new Date();		
				proceso.setFechahoraFinal(format.format(today2));
				proceso.setAbrevProceso(result.getOcMensaje());
				proceso.setEstadoProceso(result.getOcAvance());		
				
				if(radioSelected.equals("2")){
					reset();
				}
			}else {
				 addMessageFail("Debe seleccionar una acción a ejecutar y la fecha");
			}
		} catch (Exception e) {
			GenericException(e);
		}
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
