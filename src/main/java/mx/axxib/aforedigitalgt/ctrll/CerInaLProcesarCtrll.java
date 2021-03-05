package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
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
import mx.axxib.aforedigitalgt.serv.CerInaLPServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "cerInaLProcesar")
@ELBeanName(value = "cerInaLProcesar")
public class CerInaLProcesarCtrll extends ControllerBase {
	@Autowired
	private CerInaLPServ service;

	
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
	
	@Getter
	private String border;
	
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
			lote=null;
		}
		if(radioSelected.equals("2")) {
			fecha=null;
			display="inline";
			display2="inline";
			
		}
		if(radioSelected.equals("3")) {
			fecha=null;
			lote=null;
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
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		
		if(isFormValid(pr)) {			
			try {	
				pr.setDescProceso(radioSelected.equals("1")?"Generación de lote":(radioSelected.equals("2")?"Generación de archivo con Layout para ProceSAR":"Cargar de archivo con respuesta de ProceSAR"));
				ProcesResult result=	service.generarLayout(fecha, lote,Integer.valueOf(radioSelected),  ruta, archivo);		    
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
		
	  }else {
		    UIInput radio = (UIInput) findComponent("radioSelect");
			radio.setValid(false);
			pr.setDescProceso("Debe seleccionar una opción");
			pr.setStatus("Selección requerida");					
			return false;		  
	  }
	  return true;
	}

	public void reset() {
		fecha=null;
		
		radioSelected=null;
		
		archivo = "PRTFT.DP.A01530.CINACTIV.GDG";		           
		ruta = "/RESPALDOS/operaciones/pruebas";
		display="none";
		display2="none";
		display3="none";
		
	
		lote=null;
		Lote1=null;
		selectedLote=null;
		listLotes=null;
	}
	
}
