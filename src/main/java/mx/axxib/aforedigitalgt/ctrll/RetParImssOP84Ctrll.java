package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.LoteOP84Out;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.RegOP84Out;
import mx.axxib.aforedigitalgt.serv.RetParImssOP84Serv;

@Scope(value = "session")
@Component(value = "retParImssOP84")
@ELBeanName(value = "retParImssOP84")
public class RetParImssOP84Ctrll extends ControllerBase{

    @Autowired 
    private RetParImssOP84Serv service;
	
	
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
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			ruta="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";		
			ruta2="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";	
			today= new Date();
			reset();
		}
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
			fecIni=null;
			fecFin=null;
		} catch (Exception e) {
			GenericException(e);
		}

	}
	public void opcionSeleccionada2() {
	
		lote = lote1.getID_LOTE();		

	}
	public void generarReporte() {
		try {			
			if(lote!=null) {
				if(archivo!=null && !archivo.isEmpty() ) {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
					Date today= new Date();		
					proceso = new ProcesoOut();
					proceso.setFechahoraInicio(format.format(today));
					String resp=service.generarReporteOP84(ruta2, archivo, lote, fecIni, fecFin, "QUERY");
					Date today2= new Date();		
					proceso.setFechahoraFinal(format.format(today2));
					proceso.setAbrevProceso("Generar reporte");
					proceso.setEstadoProceso("Proceso ejecutado");													
					addMessageOK(resp);
				}else {
					addMessageFail("Ingrese el nombre del reporte a generar. ");
				}
			}else {
				if(fecIni!=null && fecFin!=null) {
					if(archivo!=null && !archivo.isEmpty()) {
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
						Date today= new Date();		
						proceso = new ProcesoOut();
						proceso.setFechahoraInicio(format.format(today));
						String resp=service.generarReporteOP84(ruta2, archivo, lote, fecIni, fecFin, "QUERY");
						Date today2= new Date();		
						proceso.setFechahoraFinal(format.format(today2));
						proceso.setAbrevProceso("Generar reporte");
						proceso.setEstadoProceso("Proceso ejecutado");													
						addMessageOK(resp);
					}else {
						addMessageFail("Ingrese el nombre del reporte a generar. ");
					}					
				}else {
					addMessageFail("Seleccione la fecha de inicio y fin o el lote.");
				}
			}
			
		} catch (Exception e) {
			GenericException(e);
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
								if(registros==null || registros.isEmpty()) {
									addMessageFail("No hay registros.");
								}
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
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
				Date today= new Date();		
				proceso = new ProcesoOut();
				proceso.setFechahoraInicio(format.format(today));
				String resp=service.cargarArchivoOP84(ruta, nombreArchivo);
				Date today2= new Date();		
				proceso.setFechahoraFinal(format.format(today2));
				proceso.setAbrevProceso("Carga de Archivo");
				proceso.setEstadoProceso("Proceso ejecutado");													
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
	public void reset() {
		nombreArchivo=null;
		lote=null;
		fecIni=null;
		fecFin=null;
		archivo=null;
		proceso=null;
	}
}
