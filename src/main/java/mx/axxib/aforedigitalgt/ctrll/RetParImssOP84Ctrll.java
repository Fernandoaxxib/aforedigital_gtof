package mx.axxib.aforedigitalgt.ctrll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
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
import mx.axxib.aforedigitalgt.eml.LoteOP84Out;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.RegOP84Out;
import mx.axxib.aforedigitalgt.serv.RetParImssOP84Serv;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "retParImssOP84")
@ELBeanName(value = "retParImssOP84")
public class RetParImssOP84Ctrll extends ControllerBase {

	@Autowired
	private RetParImssOP84Serv service;

	@Getter
	private String ruta;
	@Getter
	private String ruta2;

	@Getter
	@Setter
	private String radioSelected;

	@Getter
	@Setter
	private String radioSelected2;

	@Getter
	@Setter
	private String nombreArchivo;
	@Getter
	@Setter
	private String archivo;

	@Getter
	@Setter
	private List<LoteOP84Out> listLotes;

	@Getter
	@Setter
	private List<LoteOP84Out> filtro;

	@Getter
	@Setter
	private LoteOP84Out selectedLote;

	@Getter
	@Setter
	private LoteOP84Out lote1;

	@Getter
	private List<RegOP84Out> registros;

	@Getter
	@Setter
	private Date fecIni;

	@Getter
	@Setter
	private Date fecFin;
	@Getter
	private Date today;

	@Getter
	@Setter
	private String lote;
	@Getter
	@Setter
	private ProcesoOut proceso;
	@Getter
	private boolean seleccion1;
	@Getter
	private boolean seleccion2;
	@Getter
	private boolean disabled1;
	@Getter
	private boolean disabled2;
	@Getter
	private boolean disabled3;
	@Getter
	private boolean disabled4;
	@Getter
	private boolean disabled5;
	@Getter
	private String border;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
		}
	}

	public void radioSelected() {
		if (radioSelected != null) {
			border = "";
			radioSelected2 = null;
			archivo = null;
			lote = null;
			registros = null;
			seleccion2 = false;
			disabled1 = false;
			disabled2 = false;
			disabled3 = false;
			disabled4 = true;
			disabled5 = true;
			UIInput radio = (UIInput) findComponent("vArchivo");
			radio.setValid(true);
		}

	}

	public void radioSelected2() {
		if (radioSelected2 != null) {
			border = "";
			archivo = null;
			fecIni = null;
			fecFin = null;
			radioSelected = null;
			seleccion1 = false;
			registros = null;
			disabled1 = true;
			disabled2 = true;
			disabled3 = false;
			disabled4 = false;
			disabled5 = true;
			UIInput inpArch = (UIInput) findComponent("vArchivo");
			inpArch.setValid(true);
			UIInput inpF1 = (UIInput) findComponent("dfini");
			inpF1.setValid(true);
			UIInput inpF2 = (UIInput) findComponent("dffin");
			inpF2.setValid(true);			
		}

	}

	public void onRowSelect(SelectEvent<LoteOP84Out> event) {
		lote1 = new LoteOP84Out();
		lote1.setID_LOTE(event.getObject().getID_LOTE());
	}

	public void getLotes() {
		try {
			if (listLotes == null) {
				listLotes = service.getLotesOP84();
			} else {
				PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
			}
			fecIni = null;
			fecFin = null;
			selectedLote = null;
			lote=null;
			lote1=null;
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			lote = lote1.getID_LOTE();
		}
	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Generación de reporte");
		if (registros != null && !registros.isEmpty()) {
			if (isNombreValid2(pr)) {
				try {
					ProcesResult res = service.generarReporteOP84(ruta2, archivo, lote, fecIni, fecFin);
					if (res.getOn_Estatus() == 1) {
						String resp = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
						resp = resp.concat(" - SE GENERÓ EL ARCHIVO: ").concat(archivo).concat(" , RUTA: ")
								.concat(ruta2);
						pr.setStatus(resp);
						reset();
					} else {
						if (res.getOn_Estatus() == 2) {
							GenerarErrorNegocio(res.getP_Message());
						} else if (res.getOn_Estatus() == 0) {
							pr.setStatus(res.getP_Message());
						}
					}
				} catch (Exception e) {
					pr = GenericException(e);
				} finally {
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
		} else {
			pr.setStatus("No existen datos o no se ha realizado la consulta");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public boolean isNombreValid2(ProcessResult pr) {
		if (archivo == null || archivo.isEmpty()) {
			UIInput radio = (UIInput) findComponent("vArchivo");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("[-_ A-Za-z0-9]{1,}(.xls|.XLS)$");
			if (!pattern.matcher(archivo).matches()) {
				UIInput radio = (UIInput) findComponent("vArchivo");
				radio.setValid(false);
				pr.setStatus("El archivo debe tener extensión .xls");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}

	public void consultarOP84() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Consulta");

		if (radioSelected != null || radioSelected2 != null) {
			if (radioSelected != null && radioSelected.equals("1")) {
				if (fecIni != null && fecFin != null) {
					if (DateUtil.isValidDates(fecIni, fecFin)) {
						try {
							ProcesResult res = service.getConsultaOP84(fecIni, fecFin, lote);
							if (res.getOn_Estatus() == 1) {
								pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
								registros = res.getRegistros();
								if (registros.size() > 0) {
									disabled5 = false;
								} else {
									disabled5 = true;
								}
							} else {
								if (res.getOn_Estatus() == 2) {
									GenerarErrorNegocio(res.getP_Message());
								} else if (res.getOn_Estatus() == 0) {
									pr.setStatus(res.getP_Message());
								}
							}
						} catch (Exception e) {
							pr = GenericException(e);
						} finally {
							pr.setFechaFinal(DateUtil.getNowDate());
							resultados.add(pr);
						}
					} else {
						UIInput radio = (UIInput) findComponent("dfini");
						radio.setValid(false);

						UIInput radio2 = (UIInput) findComponent("dffin");
						radio2.setValid(false);

						pr.setStatus("La fecha inicial debe ser menor o igual a la fecha final");
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				} else {
					UIInput radio = (UIInput) findComponent("dfini");
					radio.setValid(false);

					UIInput radio2 = (UIInput) findComponent("dffin");
					radio2.setValid(false);

					pr.setStatus("Rango de fecha requerido");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
			if (radioSelected2 != null && radioSelected2.equals("1")) {
				if (lote != null) {
					border = "";
					try {
						ProcesResult res = service.getConsultaOP84(fecIni, fecFin, lote);
						if (res.getOn_Estatus() == 1) {
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
							registros = res.getRegistros();
							if (registros.size() > 0) {
								disabled5 = false;
							} else {
								disabled5 = true;
							}
						} else {
							if (res.getOn_Estatus() == 2) {
								GenerarErrorNegocio(res.getP_Message());
							} else if (res.getOn_Estatus() == 0) {
								pr.setStatus(res.getP_Message());
							}
						}
					} catch (Exception e) {
						pr = GenericException(e);
					} finally {
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				} else {
					border = "2px solid #ff0028 !important;";
					pr.setStatus("Se requiere el número de lote");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
		} else {
			UIInput radio = (UIInput) findComponent("customRadio");
			radio.setValid(false);
			UIInput radio2 = (UIInput) findComponent("customRadio2");
			radio2.setValid(false);
			pr.setStatus("Selección requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void cargarArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga de archivo");
		if (isNombreValid(pr)) {
			try {
				ProcesResult resp = service.cargarArchivoOP84(ruta, nombreArchivo);
				if (resp.getOn_Estatus() == 1) {
					String msj = "";
					msj = msj.concat(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null)).concat(" - ")
							.concat(resp.getP_Message());
					pr.setStatus(msj);
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getP_Message());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getP_Message());
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

	public boolean isNombreValid(ProcessResult pr) {
		if (nombreArchivo == null || nombreArchivo.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreArchivo");
			radio.setValid(false);
			pr.setStatus("Se requiere que ingrese el nombre del archivo");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("\\d{8}(.OP84|.op84)$");
			if (!pattern.matcher(nombreArchivo).matches()) {
				UIInput radio = (UIInput) findComponent("nombreArchivo");
				radio.setValid(false);
				pr.setStatus("el nombre del archivo debe tener el formato yyyymmdd.OP84");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}

	public void limpiar() {
		lote = null;
	}

	public void reset() {
		archivo = null;
		border = "";
		disabled1 = true;
		disabled2 = true;
		disabled3 = true;
		disabled4 = true;
		disabled5 = true;
		fecIni = null;
		fecFin = null;
		filtro = null;
		listLotes = null;
		lote = null;
		lote1 = null;
		nombreArchivo = null;
		proceso = null;
		radioSelected = null;
		radioSelected2 = null;
		registros = null;
		ruta = "/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
		ruta2 = "/RESPALDOS/operaciones/ReportesOperaciones";
		seleccion1 = false;
		seleccion2 = false;
		selectedLote = null;
		today = new Date();

	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		LoteOP84Out car = (LoteOP84Out) value;

		String fechaOperacion = cadenaFecha(car.getFECHA_LOTE());
		return car.getID_LOTE().toString().contains(filterText) || fechaOperacion.contains(filterText);
	}

	private String cadenaFecha(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = dateFormat.format(fecha);
		return strDate;
	}
}
