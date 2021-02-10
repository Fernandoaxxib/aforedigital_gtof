package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.LoteOut;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.ModDesParcLProcesarServ;

@Scope(value = "session")
@Component(value = "modDesParcLProcesar")
@ELBeanName(value = "modDesParcLProcesar")
public class ModDesParcLProcesarCtrll extends ControllerBase {

	@Autowired
	private ModDesParcLProcesarServ service;

	@Getter
	@Setter
	private ProcesoOut proceso;
	@Getter
	@Setter
	private Date fecha;
	@Getter
	@Setter
	private String radioSelected;
	@Getter
	@Setter
	private String archivo;
	@Getter
	@Setter
	private String ruta;
	@Getter
	@Setter
	private String lote;
	@Getter
	@Setter
	private LoteOut Lote1;
	@Getter
	@Setter
	private LoteOut selectedLote;
	@Getter
	private List<LoteOut> listLotes;
	
	@Getter
	@Setter
	private String display;

	@Getter
	@Setter
	private String display2;
	
	@Getter
	@Setter
	private String display3;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			reset();
		}
	}
	
	public void radioSelected() {
		
		if(radioSelected.equals("1")) {
			display="none";
			display2="none";
			
		}
		if(radioSelected.equals("2")) {
			display="inline";
			display2="inline";
			
		}
		if(radioSelected.equals("3")) {
			display="none";
			display2="inline";
			
		}
		
	}
	public void onRowSelect(SelectEvent<LoteOut> event) {
		Lote1 = new LoteOut();
		Lote1.setID_LOTE(event.getObject().getID_LOTE());

	}

	public void onRowUnselect(UnselectEvent<LoteOut> event) {
		Lote1 = new LoteOut();
		Lote1.setID_LOTE(event.getObject().getID_LOTE());
	}

	public void opcionSeleccionada2() {
		if(Lote1!=null) {
			lote = Lote1.getID_LOTE();			
		}
	}

	public void generarAccion() {		
		if(radioSelected!=null) {
			String idProceso=null;
			
			if (radioSelected.equals("1")) {
				if(fecha!=null) {
					idProceso="Generación Lote";
					generar(idProceso);
				}else {
					addMessageFail("Seleccione la fecha");
				}
			}
				
		    if (radioSelected.equals("2")) {
					if(lote!=null) {
						idProceso="Generación archivo ProceSAR";
						generar(idProceso);
					}else {
						addMessageFail("Seleccione un lote");
					}
		    }
		    
		    if (radioSelected.equals("3")) {				
					idProceso="Carga archivo respuesta ProceSAR";
					generar(idProceso);				
	        }
			
			
		}else {
			addMessageFail("Seleccione una opción");
		}
	}
	public void generar(String idProceso) {
		try {		
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
			Date today= new Date();	
			proceso= new ProcesoOut();	
			proceso.setFechahoraInicio(format.format(today));
			ProcesResult result=	service.generarLayout(Integer.valueOf(radioSelected));
			Date today2= new Date();	
			proceso.setFechahoraFinal(format.format(today2));					
			proceso.setAbrevProceso(idProceso);
			proceso.setEstadoProceso(result.getPcAvance());																	
		} catch (Exception e) {
			GenericException(e);
		} 
	}
	public void getLotes() {
		try {
			if (listLotes == null) {
				listLotes = service.getLotes();
			}					
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void reset() {
		fecha=null;
		
		radioSelected=null;
		
		archivo = "PRTFT.DP.A01530.CINACTIV.GDG";
		ruta = "/RESPALDOS/operaciones/pruebas";	
		display="none";
		display2="none";
		display3="none";
		
		proceso=null;
		lote=null;
		Lote1=null;
		selectedLote=null;
		listLotes=null;
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
