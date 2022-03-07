package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.List;
import java.util.Locale;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.EmpresaOut;
import mx.axxib.aforedigitalgt.reca.eml.LoteDispOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaDispOut;
import mx.axxib.aforedigitalgt.reca.serv.NominaEmpleadosBanServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Nómina Empleados Grupo Financiero Banorte
//** Interventor Principal: JJSC
//** Fecha Creación: 22/NOV/2021
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "nominaEmpleadosBan")
@ELBeanName(value = "nominaEmpleadosBan")
public class NominaEmpleadosBanCtrll extends ControllerBase {

	@Autowired
	private NominaEmpleadosBanServ serv;

	@Getter
	@Setter
	private List<LoteDispOut> lotesDisp;
	@Getter
	@Setter
	private LoteDispOut selectedLot;
	@Getter
	@Setter
	private List<EmpresaOut> listLotes;
	@Getter
	@Setter
	private List<EmpresaOut> filtro;
	@Getter
	@Setter
	private EmpresaOut lote1;
	@Getter
	@Setter
	private EmpresaOut selectedLote;
	@Getter
	@Setter
	private String opcion;
	@Getter
	@Setter
	private boolean check;
	@Getter
	@Setter
	private String lote;
	@Getter
	@Setter
	private String empresa;
	@Getter
	@Setter
	private String border;
	@Getter
	@Setter
	private Integer valor;
	@Getter
	@Setter
	private String msj;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				init = false;
				msj = "";
				opcion = null;
				check = false;
				valor = 0;
				border = "";
				empresa = null;
				lote = null;
				lote1 = null;
				filtro = null;
				listLotes = null;
				selectedLote = null;
				getEmpresas();
				getLotesDispersion();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getLotesDispersion() throws Exception {
		RespuestaDispOut resp = serv.getLoteDispersion();
		if (resp.getOn_Estatus() == null) {
			lotesDisp = resp.getLotes();
		} else {
			if (resp.getOn_Estatus() == 2) {
				GenerarErrorNegocio(resp.getOc_Mensaje());
			}
		}
	}

	public void getEmpresas() throws Exception {
		RespuestaDispOut resp = serv.getListaEmpresas();
		if (resp.getOn_Estatus() == null) {
			listLotes = resp.getListaEmpresas();
		} else {
			if (resp.getOn_Estatus() == 2) {
				GenerarErrorNegocio(resp.getOc_Mensaje());
			}
		}
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		EmpresaOut car = (EmpresaOut) value;
		return car.getLote().toString().contains(filterText);
	}

	public void onRowSelect(SelectEvent<EmpresaOut> event) {
		lote1 = new EmpresaOut();
		lote1.setEmpresa(event.getObject().getEmpresa());
		lote1.setLote(event.getObject().getLote());
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			lote = lote1.getLote();
			empresa = lote1.getEmpresa();
			border = "";
		}
	}

	public void aplicarDispersion() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Aplicar Dispersión");
		pr.setFechaInicial(DateUtil.getNowDate());
		if (check) {
			opcion = "L";
		} else {
			opcion = "V";
		}

		try {
			if (isFormValid(pr)) {
				RespuestaDispOut resp = serv.aplicarDispersion(opcion, selectedLot.getNum_referencia());
				if (resp.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getOc_Mensaje());
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
		if (selectedLot == null) {
			UIInput radio = (UIInput) findComponent("combLotes");
			radio.setValid(false);
			pr.setStatus("Se requiere el número de lote");
			pr.setFechaFinal(DateUtil.getNowDate());
			return false;
		}
		return true;
	}

	public void aplicarDispersionVol() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Aplicar Dispersión Vol. Empresa");
		pr.setFechaInicial(DateUtil.getNowDate());
		if (isFormValid2(pr)) {
			try {
				RespuestaDispOut resp = serv.confirmarDatos(empresa, lote);
				if (resp.getOn_Estatus() == 1) {
					this.valor = resp.getP_VALOR();
					if (this.valor > 1) {
						msj = "   Se tienen " + valor + " lotes del mismo día. ¿Desea dispersar el lote " + lote + " ?";
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						PrimeFaces.current().executeScript("PF('dlg4').show();");
					} else {
						RespuestaDispOut resp2 = serv.aplicarDispersionVolEmp(lote, empresa, lote.concat(empresa),
								valor, null, null);
						if (resp2.getOn_Estatus() == 1) {
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						} else {
							if (resp2.getOn_Estatus() == 2) {
								GenerarErrorNegocio(resp2.getOc_Mensaje());
							} else if (resp2.getOn_Estatus() == 0) {
								pr.setStatus(resp2.getOc_Mensaje());
							}
						}
					}
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getOc_Mensaje());
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

	public void dispersar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Aplicar Dispersión Vol. Empresa");
		pr.setFechaInicial(DateUtil.getNowDate());
		try {			
			RespuestaDispOut resp2 = serv.aplicarDispersionVolEmp(lote, empresa, lote.concat(empresa), valor, null,
					null);
			if (resp2.getOn_Estatus() == 1) {
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (resp2.getOn_Estatus() == 2) {
					GenerarErrorNegocio(resp2.getOc_Mensaje());
				} else if (resp2.getOn_Estatus() == 0) {
					pr.setStatus(resp2.getOc_Mensaje());
				}
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());			
		}
	}

	public boolean isFormValid2(ProcessResult pr) {
		if (lote == null || empresa == null) {
			border = "2px solid #ff0028 !important;";
			pr.setStatus("Se requiere la selección de la empresa y lote");
			pr.setFechaFinal(DateUtil.getNowDate());
			return false;
		} else {
			border = "";
		}
		return true;
	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Generar Reporte");
		pr.setFechaInicial(DateUtil.getNowDate());
		try {
			RespuestaDispOut resp2 = serv.aplicarDispersionVolEmp(lote, empresa, lote.concat(empresa), valor, null,
					"REPORTE");
			if (resp2.getOn_Estatus() == 1) {
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (resp2.getOn_Estatus() == 2) {
					GenerarErrorNegocio(resp2.getOc_Mensaje());
				} else if (resp2.getOn_Estatus() == 0) {
					pr.setStatus(resp2.getOc_Mensaje());
				}
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		System.out.println("Se generó el reporte");
	}
}
