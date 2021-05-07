package mx.axxib.aforedigitalgt.ctrll;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UIInput;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.LoteOut;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.serv.ModDesParcLProcesarServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "modDesParcLProcesar")
@ELBeanName(value = "modDesParcLProcesar")
public class ModDesParcLProcesarCtrll extends ControllerBase {

	@Autowired
	private ModDesParcLProcesarServ service;

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
	private String archivo2;
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
	@Setter
	private List<LoteOut> listLotes;
	
	@Getter
	@Setter
	private List<LoteOut> filtro;
	
	@Getter
	private String border;
	
	@Getter
	private Date fecActual;
	
	
	
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			reset();
			fecActual=DateUtil.getNowDate();			
		}
	}
	
	public void radioSelected() {
		UIInput fec = (UIInput) findComponent("fCapturada3");
		UIInput arc2 = (UIInput) findComponent("idArchivo2");
		fec.setValid(true);			
		arc2.setValid(true);

		if(radioSelected.equals("1")) {			
			lote=null;
			archivo2=null;
			border="";									
		}
		if(radioSelected.equals("2")) {
			fecha=null;			
			archivo = "PRTFT.DP.A01530.CINACTIV.GDG";	
			archivo2=null;			
		}
		if(radioSelected.equals("3")) {
			fecha=null;
			lote=null;
			border="";
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
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		
		if(isFormValid(pr)) {			
			try {	
				if(radioSelected.equals("3")) {
					archivo=archivo2;
				}
				pr.setDescProceso(radioSelected.equals("1")?"Generación de lote":(radioSelected.equals("2")?"Generación de archivo con Layout para ProceSAR":"Carga de archivo con respuesta de ProceSAR"));
				ProcesResult result=	service.generarLayout(Integer.valueOf(radioSelected), fecha, lote, ruta, archivo);		    
				if (result.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (result.getOn_Estatus() == 2) {
						GenerarErrorNegocio(result.getOc_Avance());
					} else if (result.getOn_Estatus() == 0) {
						pr.setStatus(result.getOc_Avance());
					}
				}		  
			} catch (Exception e) {
				pr=GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}				
		}else {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public void getLotes() {		
		try {
			if (listLotes == null) {
				listLotes = service.getLotes();								
			}
			PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
		} catch (Exception e) {
			GenericException(e);
		}

	}
	public boolean isFormValid(ProcessResult pr) {
	  if(radioSelected!=null) {	
		if(radioSelected.equals("1")) {
			if(fecha==null) {
				UIInput radio = (UIInput) findComponent("fCapturada3");
				radio.setValid(false);
				pr.setDescProceso("Debe seleccionar una fecha");
				pr.setStatus("Selección requerida");					
				return false;
			}
		}
		
		if(radioSelected.equals("2")) {
			if(lote==null) {
				border="2px solid #ff0028 !important;";
				pr.setDescProceso("Debe seleccionar un lote");
				pr.setStatus("Selección requerida");					
				return false;
			}else {
				border="";
			}
		}	
		if(radioSelected.equals("3")) {
			if(archivo2==null || archivo2.isEmpty()) {	
				UIInput radio = (UIInput) findComponent("idArchivo2");
				radio.setValid(false);
				pr.setDescProceso("Debe introducir nombre del archivo");
				pr.setStatus("Información requerida");					
				return false;
			}
		}
		
	  }else {
		    UIInput radio = (UIInput) findComponent("radioSelect");
			radio.setValid(false);			
			pr.setDescProceso("Debe seleccionar una opción");
			pr.setStatus("Selección requerida");					
			return false;		  
	  }
	  return true;
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        LoteOut car = (LoteOut) value;
        
        String fechaOperacion= cadenaFecha(car.getFEC_OPERACION());
        return car.getID_LOTE().toString().contains(filterText)  
                || fechaOperacion.contains(filterText);
    }
	
    private String cadenaFecha(Date fecha) {		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate = dateFormat.format(fecha);  		
		return strDate;
	}

	public void reset() {
		fecha=null;		
		radioSelected=null;		
		archivo = "PRTFT.DP.A01530.CINACTIV.GDG";	
		archivo2=null;
		ruta = "/RESPALDOS/operaciones/pruebas";
		lote=null;
		Lote1=null;
		selectedLote=null;
		listLotes=null;
		border="";
	}	
}
