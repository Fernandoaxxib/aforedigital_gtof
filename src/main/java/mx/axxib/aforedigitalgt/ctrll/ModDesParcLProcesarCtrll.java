package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
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
import mx.axxib.aforedigitalgt.serv.ModDesParcLProcesarService;

@Scope(value = "session")
@Component(value = "modDesParcLProcesar")
@ELBeanName(value = "modDesParcLProcesar")
public class ModDesParcLProcesarCtrll extends ControllerBase {

	@Autowired
	private ModDesParcLProcesarService service;

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

	public void radioSelected() {
		if(!radioSelected.equals("3")) {
			ruta=null;
			archivo=null;
			fecha=null;
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
		addMessage("El lote1 :" + Lote1);
		lote = Lote1.getID_LOTE();
		archivo = "PRTFT.DP.A01530.CINACTIV.GDG";
		ruta = "/RESPALDOS/operaciones/pruebas";

	}

	public void generarAccion() {		
		if(radioSelected!=null) {
			String idProceso=null;
			
			if (radioSelected.equals("1"))
				idProceso="Generación Lote";
			else if(radioSelected.equals("2"))
				idProceso="Generación Archivo ProceSAR";
			else if(radioSelected.equals("3"))
				idProceso="Carga Archivo ProceSAR";
			
			
			try {		
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				proceso= new ProcesoOut();	
				proceso.setFechahoraInicio(format.format(new Date()));
				ProcesResult result=	service.generarLayout(Integer.valueOf(radioSelected));
				proceso.setFechahoraFinal(format.format(new Date()));					
				proceso.setAbrevProceso(idProceso);
				proceso.setEstadoProceso(result.getPcAvance());
				
				if(radioSelected.equals("3")) {
					reset();
				}								
				
			} catch (Exception e) {
				GenericException(e);
			} 
		}
	}
	public void getLotes() {
		try {
			if (listLotes == null) {
				listLotes = service.getLotes();
				addMessage("entro a lotes: " + listLotes);

			}			
			//PrimeFaces.current().executeScript("PF('dlg2').show()");
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void reset() {
		fecha=null;
		
		radioSelected=null;
		
		archivo=null;
		
		ruta=null;
		
		lote=null;
		Lote1=null;
		selectedLote=null;
		listLotes=null;
	}
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
