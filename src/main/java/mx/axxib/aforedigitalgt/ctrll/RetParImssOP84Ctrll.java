package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.eml.LoteOP84Out;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.RegOP84Out;
import mx.axxib.aforedigitalgt.serv.RetParImssOP84Service;

@Scope(value = "session")
@Component(value = "retParImssOP84")
@ELBeanName(value = "retParImssOP84")
public class RetParImssOP84Ctrll extends ControllerBase{

    @Autowired 
    private RetParImssOP84Service service;
	
	
	@Getter
	private String ruta;
	@Getter
	private String ruta2;
	
	@Getter
	@Setter
	private String nombreArchivo;
	@Getter
	@Setter
	private String archivo;
	
	@Getter
	@Setter
	private List<LoteOP84Out> listLotes;
	
	@Getter
	@Setter
	private LoteOP84Out selectedLote;
	
	@Getter
	@Setter
	private LoteOP84Out lote1;
	
	
	@Getter
	private List<RegOP84Out> registros;
	
	@Getter
	@Setter
	private Date fecIni;
	
	@Getter
	@Setter
	private Date fecFin;
	@Getter
	private Date today;
	
	@Getter
	@Setter
	private String lote;
	@Getter
	@Setter
	private ProcesoOut proceso;
	
	@PostConstruct
	public void init() {
		ruta="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";		
		ruta2="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";	
		today= new Date();
		//getListaRegistros();
	}
	
	public void getListaRegistros(){
		try {
			registros= service.getConsultaOP84(fecIni, fecFin, lote);
		}catch(Exception e) {
			GenericException(e);
		}
	}
	public void onRowSelect(SelectEvent<LoteOP84Out> event) {
		lote1 = new LoteOP84Out();
		lote1.setID_LOTE(event.getObject().getID_LOTE());
	}
	public void getLotes() {
		try {
			if (listLotes == null) {
				listLotes = service.getLotesOP84();
			

			}			
			//PrimeFaces.current().executeScript("PF('dlg2').show()");
		} catch (Exception e) {
			GenericException(e);
		}

	}
	public void opcionSeleccionada2() {
	
		lote = lote1.getID_LOTE();		

	}
	public void generarReporte() {
		try {			
			String resp=service.generarReporteOP84(ruta2, archivo, lote, fecIni, fecFin, "QUERY");
			addMessageOK(resp);
		} catch (AforeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consultarOP84() {
		try {
			if((fecIni !=null || fecFin!=null)&&(lote!=null)) {
				addMessageFail("No se debe ingresar el lote si la busqueda se realiza por fecha.");
			}
			else {
				if(fecIni==null && fecFin==null) {
					if(lote!=null) {
						registros=service.getConsultaOP84(fecIni, fecFin, lote);
					}else {
						addMessageFail("Seleccione la fecha o el lote.");
					}
				}else {
					if(fecIni!=null && fecFin !=null) {									
						if(fecIni.before(fecFin)||fecIni.equals(fecFin)) {
								registros=service.getConsultaOP84(fecIni, fecFin, lote);
						}else {
							    addMessageFail("La fecha inicio debe ser menor o igual a la fecha fin.");
						}																																				
					}else {
						addMessageFail("Seleccione la fecha inicio y fecha fin.");
					}
				}				
			}
			
			
		}catch(Exception e) {
			GenericException(e);
		}
	}
	public void cargarArchivo() {
		if(nombreArchivo==null || nombreArchivo.isEmpty()) {
			addMessageFail("Ingrese el nombre del archivo.");
		}else {
			try {
				String resp=service.cargarArchivoOP84(ruta, nombreArchivo);
				addMessageOK(resp);
			} catch (Exception e) {
				GenericException(e);
			}
		}
	}

	public void addMessageOK(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageFail(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public void limpiar() {
		lote=null;
	}
}
