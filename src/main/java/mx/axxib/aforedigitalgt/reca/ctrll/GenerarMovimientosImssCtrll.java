package mx.axxib.aforedigitalgt.reca.ctrll;

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
import mx.axxib.aforedigitalgt.reca.eml.MovimientoImssOut;
import mx.axxib.aforedigitalgt.reca.eml.MovimientosOut;
import mx.axxib.aforedigitalgt.reca.serv.GenerarMovimientosImssServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Generación de Movimientos Semanas de Cotizaciónn IMSS
//** Interventor Principal: JJSC
//** Fecha Creación: 16/NOV/2021
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "generarMovimientosImss")
@ELBeanName(value = "generarMovimientosImss")
public class GenerarMovimientosImssCtrll extends ControllerBase {

	@Autowired
	private GenerarMovimientosImssServ service;
	@Getter
	private List<MovimientoImssOut> listaMovimientos;
	@Getter
	@Setter
	private Date fechaInicio;
	@Getter
	@Setter
	private Date fechaFin;
	@Getter
	private Date fecActual;
	@Getter
	@Setter
	private List<MovimientoImssOut> selectedMovimiento;
	@Getter
	MovimientosOut movimientosOut;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			init = false;
			fecActual = DateUtil.getNowDate();
			listaMovimientos = null;
			selectedMovimiento = null;
			fechaInicio = null;
			fechaFin = null;

		}
	}

	public void consultar() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("CONSULTA DETALLE");

		if (fechaInicio != null && fechaFin != null) {
			if (DateUtil.isValidDates(fechaInicio, fechaFin)) {
				try {
					movimientosOut = service.getDetalle(fechaInicio, fechaFin);
					if (movimientosOut.getP_ESTATUS() == 1) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						listaMovimientos = movimientosOut.getListaMovimientos();
					} else {
						GenerarErrorNegocio(movimientosOut.getP_MENSAJE());
					}
				} catch (Exception e) {
					pr = GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			} else {
				UIInput radio = (UIInput) findComponent("fInicio");
				radio.setValid(false);

				UIInput radio2 = (UIInput) findComponent("fFin");
				radio2.setValid(false);

				pr.setStatus("La fecha inicial debe ser menor o igual a la fecha final");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			UIInput f1 = (UIInput) findComponent("fInicio");
			f1.setValid(false);

			UIInput f2 = (UIInput) findComponent("fFin");
			f2.setValid(false);

			pr.setStatus("Rango de fecha requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void generarMovimientos() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("GENERACIÓN DE MOVIMIENTOS");

		if (listaMovimientos != null) {
			if (selectedMovimiento != null && !selectedMovimiento.isEmpty()) {
				selectedMovimiento.forEach(x -> {
					ProcessResult pr2 = new ProcessResult();
					pr2.setFechaInicial(DateUtil.getNowDate());
					pr2.setDescProceso("PROCESAR MOVIMIENTO");

					try {
						MovimientosOut mov = service.procesar(1, x.getNSS());
						if(mov.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(mov.getP_MENSAJE());
						} else if(mov.getP_ESTATUS() == 0) {
							pr2.setStatus(mov.getP_MENSAJE());
							pr2.setFechaFinal(DateUtil.getNowDate());
							resultados.add(pr2);
						}
					} catch (Exception e) {
						pr2 = GenericException(e);
						pr2.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr2);
					}
				});

				try {
					MovimientosOut m = service.generarMovimientos(fechaInicio, fechaFin, 0);
					if (m.getP_ESTATUS() == 1) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					}else {
						if(m.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(m.getP_MENSAJE());
						} else if(m.getP_ESTATUS() == 0) {
							pr.setStatus(m.getP_MENSAJE());
						} 
					}
				} catch (Exception e) {
					pr = GenericException(e);
				}finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			} else {
				pr.setStatus("Debe seleccionar por lo menos un registro");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}
	}
}
