package mx.axxib.aforedigitalgt.ctrll;

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
import mx.axxib.aforedigitalgt.eml.LlenaDetalleH;
import mx.axxib.aforedigitalgt.eml.LlenaDetalleHOut;
import mx.axxib.aforedigitalgt.eml.LlenaMovimientoH;
import mx.axxib.aforedigitalgt.eml.LlenaMovimientosHOut;
import mx.axxib.aforedigitalgt.eml.ObtenerCodCuentaHOut;
import mx.axxib.aforedigitalgt.serv.ConsultaMovHistoricosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de movimientos históricos
//** Interventor Principal: JSAS
//** Fecha Creación: 4/Mar/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "conHistoricos")
@ELBeanName(value = "conHistoricos")
public class ConsultaMovHistoricosCtrll extends ControllerBase {

	@Autowired
	private ConsultaMovHistoricosServ consultaMovHistoricosServ;

	@Getter
	@Setter
	private String nssCurp;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	private String codCuenta;

	@Getter
	private String codEmpresa;

	@Getter
	private ObtenerCodCuentaHOut cuenta;

	@Getter
	private List<LlenaMovimientoH> movimientos;

	@Getter
	private LlenaMovimientoH selectedMov;

	@Getter
	private Integer movCount;

	@Getter
	private Integer detCount;

	@Getter
	private List<LlenaDetalleH> detalle;

	@Getter
	@Setter
	private LlenaDetalleH selectedDetalle;
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	@Setter
	private boolean mostrar;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			limpiar();
			nssCurp = null;
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}
	
	public void limpiar() {
		mostrar = false;
		mensajeTabla = null;
		selectedDetalle = null;
		detalle = null;
		detCount = null;
		movCount = null;
		selectedMov = null;
		movimientos = null;
		cuenta = null;
		codEmpresa = null;
		codCuenta = null;
		fechaFinal = null;
		fechaInicial = null;
	}

	public boolean isNssCurpValid(ProcessResult pr) {
		if (nssCurp == null || nssCurp.equals("")) {
			UIInput fini = (UIInput) findComponent("nss");
			fini.setValid(false);
			pr.setStatus("NSS/CURP es requerido");
			return false;
		} else {
			if (ValidateUtil.isCURP(nssCurp)) {
				return true;
			}

			if (ValidateUtil.isNSS(nssCurp)) {
				return true;
			}

			UIInput fini = (UIInput) findComponent("nss");
			fini.setValid(false);
			pr.setStatus("NSS/CURP no válido");
			return false;
		}
	}

	public void consultarCuenta() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar por NSS/Curp");
			limpiar();
			if (isNssCurpValid(pr)) {
				boolean isCurp = ValidateUtil.isCURP(nssCurp);
				cuenta = consultaMovHistoricosServ.getCodCuenta(nssCurp, isCurp);
				if (cuenta.getEstatus() == 1) {
					codCuenta = cuenta.getCodCuenta();
					codEmpresa = cuenta.getCodEmpresa();
					mostrar = true;
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (cuenta.getEstatus() == 2) {
						GenerarErrorNegocio(cuenta.getMensaje());
					} else if (cuenta.getEstatus() == 0) {
						pr.setStatus(cuenta.getMensaje());
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
		if (fechaInicial == null) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);
			pr.setStatus("Fecha inicio es requerida");
			return false;
		}

		if (fechaFinal == null) {
			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);
			pr.setStatus("Fecha fin es requerida");
			return false;
		}

		if (!DateUtil.isValidDates(fechaInicial, fechaFinal)) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);

			pr.setStatus("La fecha final debe ser mayor o igual a la inicial");
			return false;
		}

		return true;
	}

	public void consultarMovimientos() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar movimientos");
			movCount = null;
			selectedMov = null;
			movimientos = null;
			detalle = null;
			selectedDetalle = null;
			detCount = null; 
			mensajeTabla = null;
			if (isFormValid(pr)) {
				LlenaMovimientosHOut res = consultaMovHistoricosServ.getLlenaMovimientos(codCuenta, fechaInicial, fechaFinal);
				if (res.getEstatus() == 1) {
					movimientos = res.getMovimientos();
					if (movimientos != null && movimientos.size() > 0) {
						movCount = movimientos.size();
						setSelectedMov(movimientos.get(0));
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
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void setSelectedMov(LlenaMovimientoH selectedMov) {
		this.selectedMov = selectedMov;
		if (this.selectedMov != null)
			consultarDetalle();
	}

	public void consultarDetalle() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar detalle");
			
			detalle = null;
			selectedDetalle = null;
			detCount = null; 
			mensajeTabla = null;
			LlenaDetalleHOut res = consultaMovHistoricosServ.getLlenaDetalle(codEmpresa, selectedMov.getFechaMov(),
					selectedMov.getNoMov());
			if (res.getEstatus() == 1) {
				detalle = res.getDetalle();
				if (detalle != null && detalle.size() > 0) {
					detCount = detalle.size();
					selectedDetalle = detalle.get(0);
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
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

}
