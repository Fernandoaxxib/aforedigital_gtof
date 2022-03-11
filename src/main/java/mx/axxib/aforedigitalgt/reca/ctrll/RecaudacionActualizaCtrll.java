package mx.axxib.aforedigitalgt.reca.ctrll;

import java.math.BigDecimal;
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
import mx.axxib.aforedigitalgt.reca.eml.Recaudacion;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionOut;
import mx.axxib.aforedigitalgt.reca.serv.RecaudacionActualizaServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudación - Actualiza
//** Interventor Principal: JJSC
//** Fecha Creación: 09/03/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "recaudacionActualiza")
@ELBeanName(value = "recaudacionActualiza")
public class RecaudacionActualizaCtrll extends ControllerBase {

	@Autowired
	private RecaudacionActualizaServ service;

	@Getter
	@Setter
	private List<Recaudacion> listLotes;

	@Getter
	@Setter
	private List<Recaudacion> filtro;

	@Getter
	@Setter
	private Recaudacion selectedLote;

	@Getter
	@Setter
	private Recaudacion lote1;

	@Getter
	@Setter
	private String lote;
	@Getter
	@Setter
	private Date fechaTransferencia;
	@Getter
	@Setter
	private Integer secuenciaLote;

	@Getter
	@Setter
	private String border;

	@Getter
	@Setter
	private Date fecha43;

	@Getter
	private Date today;

	@Getter
	@Setter
	private BigDecimal montoPendiente;

	@Getter
	@Setter
	private Integer participacion;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				border = "";
				lote = null;
				lote1 = null;
				today = new Date();
				filtro = null;
				fecha43 = null;
				listLotes = null;
				selectedLote = null;
				secuenciaLote = null;
				participacion = null;
				montoPendiente = null;
				fechaTransferencia = null;
				getLotes();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getLotes() throws Exception {
		RecaudacionOut resp = service.getListaLote();
		if (resp.getEstatus() == 1) {
			listLotes = resp.getListRecaudacion();
		} else {
			if (resp.getEstatus() == 2) {
				GenerarErrorNegocio(resp.getMensaje());
			}
		}
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			lote = lote1.getIdentificador();
			fechaTransferencia = lote1.getFechaTransferencia();
			secuenciaLote = lote1.getSecuenciaLote();
			obtenerMontoTotal();
			border="";
		}
	}

	public void obtenerMontoTotal() {
		try {
			RecaudacionOut resp = service.obtenerMontoTotal(lote, fechaTransferencia, secuenciaLote);
			if (resp.getEstatus() == 1) {
				montoPendiente = resp.getTotComprar();
				participacion = resp.getPartComprar();
			} else {
				if (resp.getEstatus() == 2) {
					GenerarErrorNegocio(resp.getMensaje());
				}
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void onRowSelect(SelectEvent<Recaudacion> event) {
		lote1 = new Recaudacion();
		lote1.setIdentificador(event.getObject().getIdentificador());
		lote1.setFechaTransferencia(event.getObject().getFechaTransferencia());
		lote1.setSecuenciaLote(event.getObject().getSecuenciaLote());
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		Recaudacion car = (Recaudacion) value;
		return car.getIdentificador().toString().contains(filterText);
	}

	public void actualizarSaldos() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Actualizar saldos");
		pr.setFechaInicial(DateUtil.getNowDate());

		try {
			if (isFormValid(pr)) {
				RecaudacionOut resp = service.actualizarSaldos(lote, fechaTransferencia, secuenciaLote, montoPendiente, fecha43);
				if (resp.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getEstatus() == 2) {
						GenerarErrorNegocio(resp.getMensaje());
					} else if (resp.getEstatus() == 0) {
						pr.setStatus(resp.getMensaje());
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
		if (lote == null) {
			border = "2px solid #ff0028 !important;";
			pr.setStatus("Se requiere el identificador de operación");
			pr.setFechaFinal(DateUtil.getNowDate());
			return false;
		} else if (fecha43 == null) {
			border = "";
			UIInput radio = (UIInput) findComponent("fecha43");
			radio.setValid(false);
			pr.setStatus("La fecha 43BIS es requerida");
			return false;
		}
		return true;
	}
}
