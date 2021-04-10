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
import mx.axxib.aforedigitalgt.com.AforeException;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DatosClienteOut;
import mx.axxib.aforedigitalgt.eml.DetalleSaldoOut;
import mx.axxib.aforedigitalgt.eml.ResultadoSaldosOut;
import mx.axxib.aforedigitalgt.eml.SaldoOut;
import mx.axxib.aforedigitalgt.serv.SaldosActualesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "saldosActuales")
@ELBeanName(value = "saldosActuales")
public class SaldosActualesCtrll extends ControllerBase {

	@Autowired
	private SaldosActualesServ service;

	@Getter
	@Setter
	private List<DatosClienteOut> listaNombres;
	@Getter
	@Setter
	private DatosClienteOut selectedNombre;

	@Getter
	@Setter
	private String valorEntrada;
	@Getter
	@Setter
	private Date fechaCorte;

	@Getter
	@Setter
	private String radioSelected;

	@Getter
	@Setter
	private List<DetalleSaldoOut> listaSaldos;
	@Getter
	@Setter
	private List<SaldoOut> saldos;
	@Getter
	@Setter
	private SaldoOut selectedSaldo;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			listaNombres = null;
			valorEntrada = null;
			selectedNombre = null;
			fechaCorte = DateUtil.getNowDate();
			radioSelected = null;
			saldos=null;
		}
	}

	public void radioSelected() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Filtrado");
		if (radioSelected.equals("0")) {
			try {
				ResultadoSaldosOut res = service.getSaldosBloque(selectedNombre.getCOD_CUENTA(), "1", fechaCorte, "1",
						"0");
				if (res.getP_ESTATUS() == 1) {
					listaSaldos = res.getListSaldos();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getP_ESTATUS() == 2) {
						GenerarErrorNegocio(res.getP_MENSAJE());
					} else if (res.getP_ESTATUS() == 0) {
						pr.setStatus(res.getP_MENSAJE());
					}
				}
			} catch (Exception e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			if (radioSelected.equals("1")) {
				try {
					ResultadoSaldosOut res = service.getSaldosBloque(selectedNombre.getCOD_CUENTA(), "1", fechaCorte,
							"0", "1");
					if (res.getP_ESTATUS() == 1) {
						listaSaldos = res.getListSaldos();
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						if (res.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(res.getP_MENSAJE());
						} else if (res.getP_ESTATUS() == 0) {
							pr.setStatus(res.getP_MENSAJE());
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
	}

	public void probar() throws Exception {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());

		try {
			// ResultadoSaldosOut res = service.getDatosXNSS("53876932673");
			ResultadoSaldosOut res = service.getDatosXNombre("ABAD MORAYOLANDA");
			if (res.getP_ESTATUS() == 1) {
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getP_ESTATUS() == 2) {
					GenerarErrorNegocio(res.getP_MENSAJE());
				} else if (res.getP_ESTATUS() == 0) {
					pr.setStatus(res.getP_MENSAJE());
				}
			}
		} catch (AforeException e) {
			pr = GenericException(e);
		}
	}

	public void buscar() {
		this.selectedNombre = null;
		this.saldos=null;
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());

		if (valorEntrada != null && !valorEntrada.isEmpty()) {
			if (ValidateUtil.isNSS(valorEntrada)) {
				try {
					ResultadoSaldosOut res = service.getDatosXNSS(valorEntrada);
					if (res.getP_ESTATUS() == 1) {
						listaNombres = new ArrayList<DatosClienteOut>();
						res.getDatosClienteNSS().forEach(p -> {
							DatosClienteOut dato = new DatosClienteOut();
							dato.setCOD_ESTADO(p.getCOD_ESTADO());
							dato.setCod_cliente(p.getCod_cliente());
							dato.setCOD_CUENTA(p.getCOD_CUENTA());
							dato.setDIAS_ACUMULADOS(p.getDIAS_ACUMULADOS());
							dato.setEST_CUENTA(p.getEST_CUENTA());
							dato.setDIAS_PAGADOS(p.getDIAS_PAGADOS());
							dato.setCOD_PRODUCTO(p.getCOD_PRODUCTO());
							dato.setFEC_EMISION(p.getFEC_EMISION());
							dato.setDescripcion(p.getDescripcion());
							dato.setNombre(p.getNombre());
							dato.setNUM_SEGURO_SOCIAL(valorEntrada);
							listaNombres.add(dato);
						});

					} else {
						if (res.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(res.getP_MENSAJE());
						} else if (res.getP_ESTATUS() == 0) {
							pr.setStatus(res.getP_MENSAJE());
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				}
			} else {
				try {
					ResultadoSaldosOut res = service.getDatosXNombre(valorEntrada);
					if (res.getP_ESTATUS() == 1) {
						listaNombres = res.getDatosCliente();
					} else {
						if (res.getP_ESTATUS() == 2) {
							GenerarErrorNegocio(res.getP_MENSAJE());
						} else if (res.getP_ESTATUS() == 0) {
							pr.setStatus(res.getP_MENSAJE());
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				}
			}
		}
	}

	public boolean isFormValid(ProcessResult pr) {

		if (!ValidateUtil.isNSS(valorEntrada)) {
			UIInput fini = (UIInput) findComponent("valorEntrada");
			fini.setValid(false);
			pr.setStatus("NSS no válido");
			return false;
		}

		return true;
	}

	public void pruebita() {
		radioSelected = null;
		listaSaldos = null;
	}

	public void cargaSaldos() {
		if (selectedNombre != null) {
			ProcessResult pr = new ProcessResult();
			try {
				pr.setFechaInicial(DateUtil.getNowDate());
				pr.setDescProceso("Carga de saldos");
				ResultadoSaldosOut res = service.getCargaSaldos(this.selectedNombre.getCOD_PRODUCTO(),
						this.selectedNombre.getCOD_CUENTA());
				if (res.getP_ESTATUS() == 1) {
					saldos = res.getSaldos();
				} else {
					if (res.getP_ESTATUS() == 2) {
						GenerarErrorNegocio(res.getP_MENSAJE());
					} else if (res.getP_ESTATUS() == 0) {
						pr.setStatus(res.getP_MENSAJE());
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

	public void cargaNuevoNivel() {
		if (selectedSaldo != null) {
			ProcessResult pr = new ProcessResult();
			try {
				pr.setFechaInicial(DateUtil.getNowDate());
				pr.setDescProceso("Carga nuevo nivel");
				ResultadoSaldosOut res = service.getCargaNuevoNivel(this.selectedSaldo.getCod_subproduct(),
						this.selectedNombre.getCOD_CUENTA());
				if (res.getP_ESTATUS() == 1) {
					saldos = res.getSaldos();
				} else {
					if (res.getP_ESTATUS() == 2) {
						GenerarErrorNegocio(res.getP_MENSAJE());
					} else if (res.getP_ESTATUS() == 0) {
						pr.setStatus(res.getP_MENSAJE());
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
}
