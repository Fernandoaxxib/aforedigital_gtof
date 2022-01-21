package mx.axxib.aforedigitalgt.reca.ctrll;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarLoteOp71Out;
import mx.axxib.aforedigitalgt.reca.eml.LoteOp71Out;
import mx.axxib.aforedigitalgt.reca.eml.OperacionOut;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarLoteOp71Serv;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Liquidar lote OP71
//** Interventor Principal: JJSC
//** Fecha Creación: 18/01/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "liquidarLoteOp71")
@ELBeanName(value = "liquidarLoteOp71")
public class LiquidarLoteOp71Ctrll extends ControllerBase {

	@Autowired
	private LiquidarLoteOp71Serv service;
	
	@Getter
	@Setter
	private String lote;

	@Getter
	@Setter
	private List<LoteOp71Out> listLotes;
	
	@Getter
	@Setter
	private List<LoteOp71Out> filtro;
	
	@Getter
	@Setter
	private LoteOp71Out selectedLote;
	@Getter
	@Setter
	private LoteOp71Out lote1;
	
	@Getter
	@Setter
	private List<OperacionOut> listaOperaciones;
	@Getter
	@Setter
	private String id_operacion;
	@Getter
	@Setter
	private Integer consecutivo_dia;
	
	
	@Getter
	@Setter
	private Integer fec_lote;
	
	@Getter
	@Setter
	private String border;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {					
			init = false;			
			lote = null;
			lote1 = null;			
			border = "";			
			filtro = null;
			listLotes = null;
			selectedLote = null;		
			getLotes();
			//getDetalle();
		}
	}
	
	public void getDetalle() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar montos");
			
			String sDate1="20192312";  
			DateFormat format= new SimpleDateFormat("yyyyMMdd");
		    Date date1=format.parse(sDate1);  
			
				LiquidarLoteOp71Out res = service.getDetalle("71","20191223", "799", "%", "TOD");
			    System.out.println("Aquí");	
			
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}		
	}
	public void getLotes() {
		try {
			if (listLotes == null) {
				listLotes = service.getLote().getListaLotes();
			} else {
				PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
			}
			lote = null;
		} catch (Exception e) {
			GenericException(e);
		}

	}
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteOp71Out car = (LoteOp71Out) value;
		return car.getFec_lote().toString().contains(filterText);
	}

	public void onRowSelect(SelectEvent<LoteOp71Out> event) {
		lote1 = new LoteOp71Out();
		lote1.setId_operacion(event.getObject().getId_operacion());
		lote1.setConsecutivo_dia(event.getObject().getConsecutivo_dia());
		lote1.setFec_lote(event.getObject().getFec_lote());
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			//lote = lote1.getId_operacion();
			id_operacion=lote1.getId_operacion();
			consecutivo_dia=lote1.getConsecutivo_dia();
			fec_lote=lote1.getFec_lote();
		}
	}
	
}
