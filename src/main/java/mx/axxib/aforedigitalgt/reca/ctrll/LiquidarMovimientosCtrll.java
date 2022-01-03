package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarBusquedaOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarLotes;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenLotes;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenOut;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarMovimientosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de liquidar movimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 3/Ene/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "liquidarMovimientos")
@ELBeanName(value = "liquidarMovimientos")
public class LiquidarMovimientosCtrll extends ControllerBase {

	@Autowired
	private LiquidarMovimientosServ serv;

	@Getter
	@Setter
	private Date fecha;

	@Setter
	@Getter
	private String lote;

	@Getter
	private List<LiquidarRenLotes> lotes;

	@Getter
	private List<LiquidarLotes> movimientos;

	@Getter
	private String mensajeTabla;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {

			limpiar();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		mensajeTabla = null;
	}

	public void consultarLotes() throws Exception {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar lotes");

			LiquidarRenOut res = serv.lote(fecha);
			if (res.getEstatus() == 1) {
				lotes = res.getLotes();
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}

		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void consultar() throws Exception {
		mensajeTabla = null;
		// TODO: Elegir si buscará por fecha o lote
//		if(fecha != null) {
//			buscarFecha();
//		} else if(lote != null){
//			buscarLote();
//		}
	}

	public void buscarFecha() throws Exception {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar por fecha");

			LiquidarBusquedaOut res = serv.buscarFecha(fecha);
			if (res.getEstatus() == 1) {
				movimientos = res.getLotes();
				if (movimientos != null && movimientos.size() > 0) {

				} else {
					mensajeTabla = "Sin información";
				}
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}

		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void buscarLote() throws Exception {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar por lote");

			LiquidarBusquedaOut res = serv.buscarLote(lote);
			if (res.getEstatus() == 1) {
				movimientos = res.getLotes();
				if (movimientos != null && movimientos.size() > 0) {

				} else {
					mensajeTabla = "Sin información";
				}
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}

		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void liquidar() throws Exception {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Liquidar lote");
			BaseOut res = serv.liquidar(lote, "FO");
			if (res.getEstatus() == 1) {
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}

		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

}
