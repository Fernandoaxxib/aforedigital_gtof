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
import mx.axxib.aforedigitalgt.eml.LoteOP85Out;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.RetParImssOP8586Serv;

@Scope(value = "session")
@Component(value = "retParImssOP8586")
@ELBeanName(value = "retParImssOP8586")
public class RetParImssOP8586Ctrll extends ControllerBase {

	@Autowired
	private RetParImssOP8586Serv service;
	
	@Getter
	@Setter
	private List<LoteOP85Out> listLotes;
	
	@Getter
	@Setter
	private LoteOP85Out selectedLote;
	
	@Getter
	@Setter
	private LoteOP85Out lote1;
	
	@Getter
	@Setter
	private String ruta;
	
	@Getter
	@Setter
	private String archivo;
	
	@Getter
	@Setter
	private String ruta2;
	
	@Getter
	@Setter
	private String archivo2;
	
	@Getter
	@Setter
	private String ruta3;
	
	@Getter
	@Setter
	private String archivo3;
	
	@Getter
	@Setter
	private ProcesoOut proceso;
	
	@Getter
	@Setter
	private Date fecIni;
	
	@Getter
	@Setter
	private Date fecFin;
	
	@Getter
	@Setter
	private String lote;
	
	@Getter
	private Date today;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			ruta="/iprod/PROCESAR/TRANSMISION/AFORE/RETIROS";
			ruta2="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
			ruta3="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
			today= new Date();
			reset();
		}
	}
	public void onRowSelect(SelectEvent<LoteOP85Out> event) {
		lote1 = new LoteOP85Out();
		lote1.setID_LOTE(event.getObject().getID_LOTE());
	}
	public void getLotes() {
		try {
			if(listLotes==null) {
			listLotes=service.getLotesOP85();
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
	public void generarOP85(){
		if(archivo==null || archivo.isEmpty()) {
			addMessageFail("Ingrese el nombre del archivo.");
		}else {
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
				Date today= new Date();		
				proceso = new ProcesoOut();
				proceso.setFechahoraInicio(format.format(today));
				String resp=service.cargarArchivoOP85(ruta, archivo);
				Date today2= new Date();		
				proceso.setFechahoraFinal(format.format(today2));
				proceso.setAbrevProceso("Generar OP85");
				proceso.setEstadoProceso("Proceso ejecutado");													
				addMessageOK(resp);		
			}catch(Exception e) {
				GenericException(e);
			}
		}
	}
	public void cargarOP86() {
		if(archivo2==null || archivo2.isEmpty()) {
			addMessageFail("Ingrese el nombre del archivo.");
		}else {
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
				Date today= new Date();		
				proceso = new ProcesoOut();
				proceso.setFechahoraInicio(format.format(today));
				String resp=service.cargarArchivoOP86(ruta2, archivo2);
				Date today2= new Date();		
				proceso.setFechahoraFinal(format.format(today2));
				proceso.setAbrevProceso("Cargar OP86");
				proceso.setEstadoProceso("Proceso ejecutado");													
				addMessageOK(resp);		
			}catch(Exception e) {
				GenericException(e);
			}
		}
	}
	public void generarReporte() {
		
		if(archivo3!=null && !archivo3.isEmpty()) {
			if(fecIni!=null && fecFin!=null) {
			 if(fecIni.before(fecFin)||fecIni.equals(fecFin)) {
				 if(lote==null) {					
						try {
							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
							Date today= new Date();		
							proceso = new ProcesoOut();
							proceso.setFechahoraInicio(format.format(today));
							String resp=service.generarReporteOP86(ruta3, archivo3, lote, fecIni, fecFin);
							Date today2= new Date();		
							proceso.setFechahoraFinal(format.format(today2));
							proceso.setAbrevProceso("Generar Reporte");
							proceso.setEstadoProceso("Proceso ejecutado");													
							addMessageOK(resp);		
						}catch(Exception e) {
							GenericException(e);
						}					
				}else {
					addMessageFail("Seleccionar fecha o lote.");
				}
			 }else {
				 addMessageFail("La fecha inicio debe ser menor o igual a la fecha fin.");
			 }					
			}else {
				if(lote!=null) {
					try {
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
						Date today= new Date();		
						proceso = new ProcesoOut();
						proceso.setFechahoraInicio(format.format(today));
						String resp=service.generarReporteOP86(ruta3, archivo3, lote, fecIni, fecFin);
						Date today2= new Date();		
						proceso.setFechahoraFinal(format.format(today2));
						proceso.setAbrevProceso("Cargar OP86");
						proceso.setEstadoProceso("Proceso ejecutado");													
						addMessageOK(resp);		
					}catch(Exception e) {
						GenericException(e);
					}
				}else {
					addMessageFail("Seleccionar fecha o lote.");
				}
			}
		}else {
			addMessageFail("Ingrese el nombre del archivo.");
		}
		
	}
	public void reset() {
		fecIni=null;
		fecFin=null;
		lote=null;
		archivo=null;
		archivo2=null;
		archivo3=null;
		proceso=null;
	}
	public void limpiar() {
		lote=null;
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
