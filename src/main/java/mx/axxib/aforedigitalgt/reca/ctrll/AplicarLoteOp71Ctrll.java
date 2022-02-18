package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.component.UIInput;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.LoteIssOut;
import mx.axxib.aforedigitalgt.reca.eml.RespAplicarOut;
import mx.axxib.aforedigitalgt.reca.serv.AplicarLoteOp71Serv;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de aplicar lote intereses en tránsito Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 16/02/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "aplicarLoteOp71")
@ELBeanName(value = "aplicarLoteOp71")
public class AplicarLoteOp71Ctrll extends ControllerBase {

	@Autowired
	private AplicarLoteOp71Serv service;

	@Getter
	@Setter
	private List<LoteIssOut> listLotes;

	@Getter
	@Setter
	private List<LoteIssOut> filtro;

	@Getter
	@Setter
	private LoteIssOut selectedLote;

	@Getter
	@Setter
	private LoteIssOut lote1;

	@Getter
	@Setter
	private String lote;

	@Getter
	private String border;
	@Getter
	@Setter
	private Date fecha;

	@Getter
	private Date today;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				init = false;
				border = "";
				fecha = null;
				today = new Date();
				listLotes = null;
				lote = null;
				lote1 = null;
				filtro = null;
				selectedLote = null;
				getsLotes();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getsLotes() throws Exception {
		RespAplicarOut resp = service.getLotes();
		if (resp.getP_ESTATUS() == 1) {
			listLotes = resp.getLotesBono();
		} else {
			if (resp.getP_ESTATUS() == 2) {
				GenerarErrorNegocio(resp.getP_MENSAJE());
			}
		}
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Ejecutar");
		pr.setFechaInicial(DateUtil.getNowDate());

		try {
			if(isFormValid(pr)) {
				RespAplicarOut resp = service.ejecutar(lote, fecha, "L");
				if (resp.getP_ESTATUS() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getP_ESTATUS() == 2) {
						GenerarErrorNegocio(resp.getP_MENSAJE());
					} else if (resp.getP_ESTATUS() == 0) {
						pr.setStatus(resp.getP_MENSAJE());
					}
				}				
			}
			
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteIssOut car = (LoteIssOut) value;
		return car.getLote_carga().toString().contains(filterText);
	}

	public void onRowSelect(SelectEvent<LoteIssOut> event) {
		lote1 = new LoteIssOut();
		lote1.setLote_carga(event.getObject().getLote_carga());
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			lote = lote1.getLote_carga();
		}
	}
	
	public boolean isFormValid(ProcessResult pr) {
		if (lote == null) {
			border = "2px solid #ff0028 !important;";
			pr.setStatus("Se requiere el número de lote");
			pr.setFechaFinal(DateUtil.getNowDate());			
			return false;
		}else if(fecha==null){
			border="";
			UIInput radio = (UIInput) findComponent("fechaMovimientos");
			radio.setValid(false);
			pr.setStatus("La fecha de movimientos es requerida");
			return false;
		}
		return true;
	}
}
