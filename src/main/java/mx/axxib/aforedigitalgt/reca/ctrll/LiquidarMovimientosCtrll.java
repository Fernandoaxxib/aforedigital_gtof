package mx.axxib.aforedigitalgt.reca.ctrll;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;

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
	
	@Getter
	private BigDecimal acciones;
	
	@Getter
	private BigDecimal acciones1;
	
	@Getter
	private BigDecimal acciones2;
	
	@Getter
	private BigDecimal acciones3;
	
	@Getter
	private BigDecimal acciones4;
	
	@Getter
	private BigDecimal monto;
	
	@Getter
	private BigDecimal monto1;
	
	@Getter
	private BigDecimal monto2;
	
	@Getter
	private BigDecimal monto3;
	
	@Getter
	private BigDecimal monto4;
	
	@Getter
	private boolean mostrarLiquidar;

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
		fecha = null;
		lote = null;
		lotes = null;
		limpiarBusqueda();
	}
	
	private void limpiarBusqueda() {
		mostrarLiquidar = false;
		movimientos = null;
		acciones = null;
		acciones1 = null;
		acciones2 = null;
		acciones3 = null;
		acciones4 = null;
		monto = null;
		monto1 = null;
		monto2 = null;
		monto3 = null;
		monto4 = null;
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
		} 
	}

//	public void buscarFecha() throws Exception {
//		ProcessResult pr = new ProcessResult();
//
//		try {
//			pr.setFechaInicial(DateUtil.getNowDate());
//			pr.setDescProceso("Consultar por fecha");
//
//			LiquidarBusquedaOut res = serv.buscarFecha(fecha);
//			if (res.getEstatus() == 1) {
//				movimientos = res.getLotes();
//				if (movimientos != null && movimientos.size() > 0) {
//
//				} else {
//					mensajeTabla = "Sin información";
//				}
//				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
//			} else {
//				if (res.getEstatus() == 2) {
//					GenerarErrorNegocio(res.getMensaje());
//				} else if (res.getEstatus() == 0) {
//					pr.setStatus(res.getMensaje());
//				}
//			}
//
//		} catch (Exception e) {
//			resultados.add(GenericException(e));
//		} finally {
//			pr.setFechaFinal(DateUtil.getNowDate());
//			resultados.add(pr);
//		}
//	}

	public boolean isFormValid(ProcessResult pr) {
		if (fecha == null) {
			UIInput fini = (UIInput) findComponent("fecha");
			fini.setValid(false);

			pr.setStatus("Debe elegir un fecha");
			return false;
		}

		if (lote == null) {
			UIInput radio = (UIInput) findComponent("lotes");
			radio.setValid(false);

			pr.setStatus("Debe elegir un lote");
			return false;
		}

		return true;
	}

	public void buscarLote() throws Exception {
		ProcessResult pr = new ProcessResult();

		try {
			limpiarBusqueda();
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar");
			if (isFormValid(pr)) {
				LiquidarBusquedaOut res = serv.buscarLote(lote, fecha);
				if (res.getEstatus() == 1) {
					movimientos = res.getLotes();
					if (movimientos != null && movimientos.size() > 0) {
						acciones = res.getAcciones();
						acciones1 = res.getAcciones1();
						acciones2 = res.getAcciones2();
						acciones3 = res.getAcciones3();
						acciones4 = res.getAcciones4();
						monto = res.getMonto();
						monto1 = res.getMonto1();
						monto2 = res.getMonto2();
						monto3 = res.getMonto3();
						monto4 = res.getMonto4();
						
						mostrarLiquidar = true;
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
			BaseOut res = serv.liquidar(lote, fecha);
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
