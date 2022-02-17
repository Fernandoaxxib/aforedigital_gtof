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
import mx.axxib.aforedigitalgt.reca.serv.AplicarLoteRecaudaServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aplicar Lote Recauda Issste
//** Interventor Principal: JJSC
//** Fecha Creación: 14/02/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "aplicarLoteRecauda")
@ELBeanName(value = "aplicarLoteRecauda")
public class AplicarLoteRecaudaCtrll extends ControllerBase {

	@Autowired
	private AplicarLoteRecaudaServ service;
	@Getter
	@Setter
	private List<LoteIssOut> lotesBono;
	@Getter
	@Setter
	private List<LoteIssOut> filtro;
	@Getter
	@Setter
	private List<LoteIssOut> filtro2;
	@Getter
	@Setter
	private LoteIssOut selectedLote;
	@Getter
	@Setter
	private LoteIssOut selectedLote2;
	@Getter
	@Setter
	private LoteIssOut lote1;
	@Getter
	@Setter
	private LoteIssOut lote1_2;
	@Getter
	@Setter
	private List<LoteIssOut> lotesReca;
	@Getter
	@Setter
	private String radioSelected;
	@Getter
	private String border;
	@Getter
	private String border2;
	@Getter
	@Setter
	private String lote;
	@Getter
	@Setter
	private String lote2;
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
				radioSelected = null;
				fecha = null;
				border = "";
				border2 = "";
				today = new Date();
				getsLoteBono();
				getsLoteReca();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getsLoteBono() throws Exception {
		RespAplicarOut resp = service.getLotesBono();
		if (resp.getP_ESTATUS() == 1) {
			lotesBono = resp.getLotesBono();
		} else {
			if (resp.getP_ESTATUS() == 2) {
				GenerarErrorNegocio(resp.getP_MENSAJE());
			}
		}
	}

	public void getsLoteReca() throws Exception {
		RespAplicarOut resp = service.getLotesReca();
		if (resp.getP_ESTATUS() == 1) {
			lotesReca = resp.getLotesReca();
		} else {
			if (resp.getP_ESTATUS() == 2) {
				GenerarErrorNegocio(resp.getP_MENSAJE());
			}
		}
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Ejecutar");
		try {
			if (isFormValid(pr)) {
				RespAplicarOut res = service.ejecutar(lote2, 0, lote, radioSelected);
				if (res.getP_ESTATUS() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getP_ESTATUS() == 2) {
						GenerarErrorNegocio(res.getP_MENSAJE());
					} else if (res.getP_ESTATUS() == 0) {
						pr.setStatus(res.getP_MENSAJE());
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

	public boolean isFormValid(ProcessResult pr) {
		if (lote == null && lote2 == null) {
			border = "2px solid #ff0028 !important;";
			border2 = "2px solid #ff0028 !important;";
			pr.setStatus("Se requiere el número de lote");
			pr.setFechaFinal(DateUtil.getNowDate());
			return false;
		} else {
			border = "";
			border2 = "";
			if (fecha == null) {
				UIInput radio = (UIInput) findComponent("fechaMovimientos");
				radio.setValid(false);
				pr.setDescProceso("Fecha de movimientos");
				pr.setStatus("La fecha de movimientos es requerida");
				return false;
			}
		}
		return true;
	}

	public void radioSelected() {
		border = "";
		border2 = "";
		lote = null;
		lote2 = null;
		lote1 = null;
		fecha = null;
		filtro = null;
		filtro2 = null;
		lote1_2 = null;
		selectedLote = null;
		selectedLote2 = null;
		UIInput radio = (UIInput) findComponent("fechaMovimientos");
		radio.setValid(true);
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteIssOut car = (LoteIssOut) value;
		return car.getLote_carga().toString().contains(filterText);
	}

	public boolean globalFilterFunction2(Object value, Object filter, Locale locale) {
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
		lote1.setAcept(event.getObject().getAcept());
		lote1.setNo_apl(event.getObject().getNo_apl());
		lote1.setRech(event.getObject().getRech());
	}

	public void onRowSelect2(SelectEvent<LoteIssOut> event) {
		lote1_2 = new LoteIssOut();
		lote1_2.setLote_carga(event.getObject().getLote_carga());
		lote1_2.setAcept(event.getObject().getAcept());
		lote1_2.setNo_apl(event.getObject().getNo_apl());
		lote1_2.setRech(event.getObject().getRech());
	}

	public void opcionSeleccionada() {
		if (lote1 != null) {
			lote = lote1.getLote_carga();
		}
	}

	public void opcionSeleccionada2() {
		if (lote1_2 != null) {
			lote2 = lote1_2.getLote_carga();
		}
	}
}
