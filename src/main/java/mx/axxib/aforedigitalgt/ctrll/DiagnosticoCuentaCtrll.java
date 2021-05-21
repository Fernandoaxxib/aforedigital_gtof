package mx.axxib.aforedigitalgt.ctrll;

import java.util.ArrayList;
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
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.DesbloqueaCuentasIn;
import mx.axxib.aforedigitalgt.eml.GeneraArchivoIn;
import mx.axxib.aforedigitalgt.eml.ObtieneCodCuentaOut;
import mx.axxib.aforedigitalgt.eml.ObtieneTipoProceso;
import mx.axxib.aforedigitalgt.eml.ObtieneTipoProcesoOut;
import mx.axxib.aforedigitalgt.serv.DiagnosticoCuentaServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "diagCuenta")
@ELBeanName(value = "diagCuenta")
public class DiagnosticoCuentaCtrll extends ControllerBase {


	@Autowired
	private DiagnosticoCuentaServ diagCuentaServ;

	@Getter
	@Setter
	private String nss;

	@Getter
	private String codCuenta;

	@Getter
	private String nombre;

	@Getter
	private List<ObtieneTipoProceso> tipos;

	@Getter
	@Setter
	private Integer selectedTipo;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	private boolean mostrarBotones;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			nss = null;
			limpiar();
			tipos = new ArrayList<ObtieneTipoProceso>();
			obtieneTipoProceso();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void limpiar() {
		mostrarBotones = false;
		codCuenta = null;
		nombre = null;
		selectedTipo = null;
		fechaInicial = null;
		fechaFinal = null;
	}

	public boolean isNssCurpValid(ProcessResult pr) {
		if(nss == null || nss.equals("")) {
			UIInput fini = (UIInput) findComponent("nss");
			fini.setValid(false);
			pr.setStatus("NSS/CURP es requerido");
			return false;
		} else {
			if(ValidateUtil.isCURP(nss)) {
				return true;
			}
			
			if(ValidateUtil.isNSS(nss)) {
				return true;
			}
			
			UIInput fini = (UIInput) findComponent("nss");
			fini.setValid(false);
			pr.setStatus("NSS/CURP no válido");
			return false;
		}
	}

	public void obtieneCodCuenta() {
		ProcessResult pr = new ProcessResult();
		try {
			limpiar();
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar por NSS/CURP");
			if (isNssCurpValid(pr)) {
				boolean isCurp = ValidateUtil.isCURP(nss);
				ObtieneCodCuentaOut res = diagCuentaServ.getObtieneCodCuenta(nss, isCurp);
				if (res.getEstatus() == 1) {
					codCuenta = res.getCodCuenta();
					nombre = res.getNombre();
					if (codCuenta != null) {
						mostrarBotones = true;
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

	public void obtieneTipoProceso() {
		try {
			ObtieneTipoProcesoOut res = diagCuentaServ.getObtieneTipoProceso();
			if (res.getEstatus() == 1) {
				tipos = res.getTipos();
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				}
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
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

		if (selectedTipo == null) {
			UIInput fini = (UIInput) findComponent("tipoProceso");
			fini.setValid(false);
			pr.setStatus("Tipo proceso es requerido");
			return false;
		}

		return true;
	}

	public boolean isForm2Valid(ProcessResult pr) {
		if (fechaInicial == null) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);
			pr.setStatus("Fecha inicio es requerida");
			return false;
		}

		if (selectedTipo == null) {
			UIInput fini = (UIInput) findComponent("tipoProceso");
			fini.setValid(false);
			pr.setStatus("Tipo proceso es requerido");
			return false;
		}
		return true;
	}

	public void generarArchivo() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Generar reporte");
			if (isFormValid(pr)) {
				GeneraArchivoIn parametros = new GeneraArchivoIn();
				parametros.setClave(selectedTipo);
				parametros.setFechaInicio(fechaInicial);
				parametros.setFechaFin(fechaFinal);
				BaseOut res = diagCuentaServ.generaArchivo(parametros);
				if (res.getEstatus() == 1) {
					pr.setStatus(
							aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null) + ": " + res.getMensaje());
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

	public void desbloquearCuentas() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Desbloquear cuentas");
			if (isForm2Valid(pr)) {
				DesbloqueaCuentasIn parametros = new DesbloqueaCuentasIn();
				parametros.setClave(selectedTipo);
				parametros.setCodCuenta(codCuenta);
				parametros.setFechaInicio(fechaInicial);
				parametros.setUsuario("FO");
				BaseOut res = diagCuentaServ.desbloqueaCuentas(parametros);
				if (res.getEstatus() == 1) {
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

	public void bloquearCuentas() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Bloquear cuentas");
			if (isForm2Valid(pr)) {
				DesbloqueaCuentasIn parametros = new DesbloqueaCuentasIn();
				parametros.setClave(selectedTipo);
				parametros.setCodCuenta(codCuenta);
				parametros.setFechaInicio(fechaInicial);
				parametros.setUsuario("FO");
				BaseOut res = diagCuentaServ.bloqueaCuentas(parametros);
				if (res.getEstatus() == 1) {
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

}
