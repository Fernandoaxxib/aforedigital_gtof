package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;
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
import mx.axxib.aforedigitalgt.eml.LoteOP84Out;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.RegOP84Out;
import mx.axxib.aforedigitalgt.serv.RetParImssOP84Serv;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

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
	private String border;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			ruta = "/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
			ruta2 = "/RESPALDOS/operaciones/ReportesOperaciones";
			today = new Date();
			reset();
            border="";
		}
	}

	public void radioSelected() {
		if (radioSelected != null) {
			radioSelected2 = null;
			lote = null;
			seleccion2 = false;
			disabled1 = false;
			disabled2 = false;
			disabled3 = false;
			disabled4 = true;
			border="";
		}

	}

	public void radioSelected2() {

		if (radioSelected2 != null) {
			fecIni = null;
			fecFin = null;
			radioSelected = null;
			seleccion1 = false;
			disabled1 = true;
			disabled2 = true;
			disabled3 = false;
			disabled4 = false;
			border="";
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
			}
			fecIni = null;
			fecFin = null;
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void opcionSeleccionada2() {

		lote = lote1.getID_LOTE();

	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Generación de reporte");
		if (registros != null && !registros.isEmpty()) {
			if (archivo != null && !archivo.isEmpty()) {
				if (ValidateUtil.isValidFileName(archivo)) {
					try {
						ProcesResult res = service.generarReporteOP84(ruta2, archivo, lote, fecIni, fecFin);
						if (res.getOn_Estatus() == 1) {
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
				}else {
					pr.setDescProceso("Generación de reporte");
					pr.setStatus("Nombre de archivo no válido");
					UIInput radio = (UIInput) findComponent("vArchivo");
					radio.setValid(false);
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			} else {
				pr.setDescProceso("Generación de reporte");
				pr.setStatus("Nombre de archivo requerido");
				UIInput radio = (UIInput) findComponent("vArchivo");
				radio.setValid(false);
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			pr.setStatus("No existen datos o no se ha realizado la consulta");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
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
				if (lote != null && !lote.isEmpty()) {
					try {
						ProcesResult res = service.getConsultaOP84(fecIni, fecFin, lote);
						if (res.getOn_Estatus() == 1) {
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
							registros = res.getRegistros();
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
					UIInput radio2 = (UIInput) findComponent("vLote");
					radio2.setValid(false);

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
            border="red";
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
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} 

		return true;
	}

	public void limpiar() {
		lote = null;
	}

	public void reset() {
		registros = null;
		nombreArchivo = null;
		lote = null;
		fecIni = null;
		fecFin = null;
		archivo = null;
		proceso = null;
		radioSelected = null;
		radioSelected2 = null;
		seleccion1 = false;
		seleccion2 = false;
		disabled1 = true;
		disabled2 = true;
		disabled3 = true;
		disabled4 = true;
	}
}
