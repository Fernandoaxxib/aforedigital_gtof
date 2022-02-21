package mx.axxib.aforedigitalgt.reca.ctrll;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.Estatus;
import mx.axxib.aforedigitalgt.reca.eml.EstatusOut;
import mx.axxib.aforedigitalgt.reca.eml.RefPago;
import mx.axxib.aforedigitalgt.reca.eml.RefPagoOut;
import mx.axxib.aforedigitalgt.reca.eml.SaldosIndi;
import mx.axxib.aforedigitalgt.reca.eml.SaldosIndiOut;
import mx.axxib.aforedigitalgt.reca.eml.SaldosSief;
import mx.axxib.aforedigitalgt.reca.eml.SaldosSiefOut;
import mx.axxib.aforedigitalgt.reca.serv.ReporteConciliadosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de de Reporte Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Feb/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "reporteConciliados")
@ELBeanName(value = "reporteConciliados")
public class ReporteConciliadosCtrll extends ControllerBase {

	@Autowired
	private ReporteConciliadosServ serv;

	@Getter
	@Setter
	private Date fechaI;

	@Getter
	@Setter
	private Date fechaF;

	@Getter
	@Setter
	private Integer selectedEstatus;

	@Getter
	private List<Estatus> estatus;

	@Getter
	private String directorio;

	@Getter
	@Setter
	private String archivo;

	@Getter
	@Setter
	private Date fechaN;

	@Getter
	@Setter
	private String opcion;

	@Getter
	private boolean mostrarFechaI;

	@Getter
	private boolean mostrarFechaF;

	@Getter
	private boolean mostrarEstatus;

	@Getter
	private boolean mostrarRefPago;

	@Getter
	private boolean mostrarReintPen;

	@Getter
	private boolean mostrarSaldosSief;

	@Getter
	private boolean mostrarReintOp;

	@Getter
	private StreamedContent file;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			directorio = "/RESPALDOS/operaciones/anexo_025";

			opcion = null;
			archivo = null;

			limpiar();
			estatus();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		fechaI = null;
		fechaF = null;
		selectedEstatus = null;
		fechaN = null;
		file = null;

		mostrarFechaI = false;
		mostrarFechaF = false;
		mostrarEstatus = false;
		mostrarRefPago = false;
		mostrarReintPen = false;
		mostrarSaldosSief = false;
		mostrarReintOp = false;
	}

	public void estatus() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar estatus");

			EstatusOut res = serv.estatus();
			if (res.getEstatus() == 1) {
				estatus = res.getDatos();
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

	public void changeOpcion() {
		limpiar();

		UIInput lot = (UIInput) findComponent("fechaI");
		lot.setValid(true);
		UIInput fec = (UIInput) findComponent("fechaF");
		fec.setValid(true);
		UIInput estatus = (UIInput) findComponent("estatus");
		estatus.setValid(true);

		switch (opcion) {
		case "1":
			mostrarFechaI = true;
			mostrarFechaF = true;
			mostrarEstatus = true;
			mostrarRefPago = true;
			break;

		case "2":
			mostrarFechaI = true;
			mostrarFechaF = true;
			mostrarReintPen = true;
			break;

		case "3":
			mostrarFechaI = true;
			mostrarSaldosSief = true;
			break;

		case "4":
			mostrarFechaI = true;
			mostrarReintOp = true;
			break;
		}
	}

	public boolean isFormValid(ProcessResult pr) {
		if (fechaI == null) {
			UIInput fini = (UIInput) findComponent("fechaI");
			fini.setValid(false);

			pr.setStatus("Debe elegir una fecha inicio");
			return false;
		}

		if (fechaF == null) {
			UIInput fini = (UIInput) findComponent("fechaF");
			fini.setValid(false);

			pr.setStatus("Debe elegir una fecha fin");
			return false;
		}

		if (!DateUtil.isValidDates(fechaI, fechaF)) {
			UIInput fini = (UIInput) findComponent("fechaI");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaF");
			ffin.setValid(false);

			pr.setStatus("La fecha final debe ser mayor o igual a la inicial");
			return false;
		}

		if (selectedEstatus == null) {
			UIInput fini = (UIInput) findComponent("estatus");
			fini.setValid(false);

			pr.setStatus("Debe elegir un estatus");
			return false;
		}

		return true;
	}

	public boolean isFormValid2(ProcessResult pr) {
		if (fechaI == null) {
			UIInput fini = (UIInput) findComponent("fechaI");
			fini.setValid(false);

			pr.setStatus("Debe elegir una fecha inicio");
			return false;
		}

		if (fechaF == null) {
			UIInput fini = (UIInput) findComponent("fechaF");
			fini.setValid(false);

			pr.setStatus("Debe elegir una fecha fin");
			return false;
		}

		if (!DateUtil.isValidDates(fechaI, fechaF)) {
			UIInput fini = (UIInput) findComponent("fechaI");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaF");
			ffin.setValid(false);

			pr.setStatus("La fecha final debe ser mayor o igual a la inicial");
			return false;
		}
		return true;
	}

	public boolean isFormValid3(ProcessResult pr) {
		if (fechaI == null) {
			UIInput fini = (UIInput) findComponent("fechaI");
			fini.setValid(false);

			pr.setStatus("Debe elegir una fecha");
			return false;
		}

		return true;
	}

	public boolean isFormValid4(ProcessResult pr) {
		if (archivo == null || archivo.equals("")) {
			UIInput radio = (UIInput) findComponent("archivo");
			radio.setValid(false);

			pr.setStatus("Debe elegir un nombre de archivo");
			return false;
		} else if (!ValidateUtil.isValidFileName(archivo)) {
			UIInput radio = (UIInput) findComponent("archivo");
			radio.setValid(false);

			pr.setStatus("Nombre de archivo inválido");
			return false;
		}
		if (fechaN == null) {
			UIInput fini = (UIInput) findComponent("fechaN");
			fini.setValid(false);

			pr.setStatus("Debe elegir una fecha");
			return false;
		}

		return true;
	}

	private void FileDownloadView(String nombre, InputStream f) {
		file = DefaultStreamedContent.builder().name(nombre).contentType("application/xls").stream(() -> f).build();
	}

	private InputStream generateRefPago(List<RefPago> datos) {
		StringBuilder sb = new StringBuilder();
		sb.append(datos.get(0).getHeader());
		for (RefPago d : datos) {
			sb.append(d.getLine());
		}
		return new ByteArrayInputStream(sb.toString().getBytes());
	}

	public void reporteRefPago() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Referencia de pago");
			if (isFormValid(pr)) {
				RefPagoOut res = serv.refPago(fechaI, fechaF, selectedEstatus);
				if (res.getEstatus() == 1) {
					List<RefPago> datos = res.getDatos();
					if (datos != null && datos.size() > 0) {
						FileDownloadView("REP_SOL_REF_PAGO.xls", generateRefPago(datos));
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						pr.setStatus("Sin información");
					}
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

	public void reporteReintPen() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Reintegro pendientes");
			if (isFormValid2(pr)) {
				BaseOut res = serv.reintPen(fechaI, fechaF);
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
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	private InputStream generateSaldosSief(List<SaldosSief> datos) {
		StringBuilder sb = new StringBuilder();
		final String SEPARATOR = "\t";
		final String END_OF_LINE = "\n";

		for (SaldosSief d : datos) {
			if (d.getAcciones() == null && d.getImporte() == null && d.getPrecio() == null) {
				sb.append("Subcuenta").append(SEPARATOR).append("Acciones").append(SEPARATOR).append("Precio ").append(d.getSubCuenta())
						.append(SEPARATOR).append("Importe").append(END_OF_LINE);
			} else {
				if(d.getSubCuenta() != null) {
					if(d.getSubCuenta().toUpperCase().equals("TOTAL")) {
						sb.append(d.getSubCuenta()).append(SEPARATOR).append(d.getAcciones()).append(SEPARATOR).append("")
						.append(SEPARATOR).append(NumberFormat.getCurrencyInstance().format(d.getImporte())).append(END_OF_LINE).append(END_OF_LINE).append(END_OF_LINE);
					} else {
						sb.append(d.getSubCuenta()).append(SEPARATOR).append(d.getAcciones()).append(SEPARATOR).append(d.getPrecio())
						.append(SEPARATOR).append(NumberFormat.getCurrencyInstance().format(d.getImporte())).append(END_OF_LINE);
					}
				}
			}
		}
		return new ByteArrayInputStream(sb.toString().getBytes());
	}
	
	

	public void reporteSaldosSief() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Saldos Sief por día");
			if (isFormValid3(pr)) {
				SaldosSiefOut res = serv.saldosSief(fechaI);
				if (res.getEstatus() == 1) {
					List<SaldosSief> datos = res.getDatos();
					if (datos != null && datos.size() > 0) {
						FileDownloadView("REINTEGRO_SEMANAS_COTIZACION_"+DateUtil.getDateToString(fechaI, "ddMMyyyy")+".xls", generateSaldosSief(datos));
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						pr.setStatus("Sin información");
					}

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
	
	private InputStream generateSaldosIndi(List<SaldosIndi> datos) {
		StringBuilder sb = new StringBuilder();
		final String SEPARATOR = "\t";
		final String END_OF_LINE = "\n";
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		for (SaldosIndi d : datos) {
			if (d.getNss() != null) {
				if(!d.getNss().toUpperCase().equals("TOTAL")) {
					sb.append("NSS").append(SEPARATOR).append(d.getNss()).append(END_OF_LINE);
					sb.append("Nombre").append(SEPARATOR).append(d.getNombre()).append(END_OF_LINE);
					sb.append("Importe a Reintegrar").append(SEPARATOR).append(nf.format(d.getImporteR())).append(END_OF_LINE);
					sb.append("Siefore").append(SEPARATOR).append(d.getSiefore()).append(END_OF_LINE);
					sb.append("Precio").append(SEPARATOR).append(d.getPrecio()).append(END_OF_LINE).append(END_OF_LINE);
					sb.append("Subcuenta").append(SEPARATOR).append("Acciones").append(SEPARATOR).append("Importe").append(END_OF_LINE);
				} else { 
					sb.append("TOTAL").append(SEPARATOR).append(d.getAcciones()).append(SEPARATOR).append(nf.format(d.getImporte())).append(END_OF_LINE).append(END_OF_LINE).append(END_OF_LINE);
				}
			} else {
				sb.append(d.getSubCuenta()).append(SEPARATOR).append(d.getAcciones()).append(SEPARATOR).append(nf.format(d.getImporte())).append(END_OF_LINE);
			}
		}
		return new ByteArrayInputStream(sb.toString().getBytes());
	}

	public void reporteSaldosIndi() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Saldos Individual por día");
			if (isFormValid3(pr)) {
				SaldosIndiOut res = serv.saldosIndi(fechaI);
				if (res.getEstatus() == 1) {
					List<SaldosIndi> datos = res.getDatos();
					if (datos != null && datos.size() > 0) {
						FileDownloadView("REINTEGRO_SEMANAS_COTIZACION_INDI_"+DateUtil.getDateToString(fechaI, "ddMMyyyy")+".xls", generateSaldosIndi(datos));
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						pr.setStatus("Sin información");
					}

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

	public void reporteReintOp() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Reintegro a op.");
			if (isFormValid3(pr)) {
				BaseOut res = serv.reintOp(fechaI);
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
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void cargarArchivo() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cargar archivo");
			if (isFormValid4(pr)) {
				BaseOut res = serv.cargarArchivo(directorio, archivo, fechaN);
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
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
}
