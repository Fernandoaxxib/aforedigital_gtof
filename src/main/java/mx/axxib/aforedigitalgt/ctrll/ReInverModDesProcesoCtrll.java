package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.serv.ReInverModDesProcesoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** Funcionalidad: Controlador - Reinversiones a básicas parcialidades - Proceso
//** Desarrollador: JJSC
//** Fecha de creación: 01/Feb/2021
//** Última modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "reinversionModDesProceso")
@ELBeanName(value = "reinversionModDesProceso")
public class ReInverModDesProcesoCtrll extends ControllerBase {

	@Autowired
	private ReInverModDesProcesoServ service;
	@Getter
	@Setter
	private Date fecha;

	@Getter
	@Setter
	private Date fechaI;
	@Getter
	@Setter
	private Date fechaF;

	@Getter
	@Setter
	private String radioSelected;
	@Getter
	@Setter
	private Integer cuentasPendientes;
	@Getter
	private String ruta;
	@Getter
	private String archivo;
	@Getter
	private Date fecActual;
    @Getter
	private boolean disabled;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
			fecActual=DateUtil.getNowDate();
		}
	}

	public void reset() {
		ruta = "/RESPALDOS/operaciones";
		fecha = DateUtil.getNowDate();
		fechaI = DateUtil.getNowDate();
		fechaF = DateUtil.getNowDate();			
		archivo = null;
		radioSelected = null;
		disabled=false;
	}

	public void radioSelected() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Consulta de cuentas pendientes");
		pr.setFechaInicial(DateUtil.getNowDate());

		if (radioSelected.equals("1")) {
			disabled=true;
			try {
				cuentasPendientes = service.getValorCuentas().intValue();				
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));												
				archivo = null;
				if(cuentasPendientes>0) {
					disabled=false;
				}
			} catch (AforeException e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}
		if (radioSelected.equals("2")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String f = format.format(fecha);
			archivo = "REPORTE_REINVERSION_" + f;
			cuentasPendientes = null;			
			disabled=false;
		}
		if (radioSelected.equals("3")) {
			cuentasPendientes = null;						
			disabled=false;
		}
		if (radioSelected.equals("4")) {
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
			String fI = format.format(fechaI);
			String fF = format.format(fechaF);
			cuentasPendientes = null;						
			archivo = "REPORTE_CANCELADAS_" + fI + "_" + fF + ".xls";
		}
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if (isFormValid(pr)) {
			try {
				switch (radioSelected) {

				case "1": {
					pr.setDescProceso("Obtener Saldos Actuales Cuentas a Reinvertir");
					break;
				}
				case "2": {
					pr.setDescProceso("Reporte de Cuentas a Reinvertir");
					break;
				}
				case "3": {
					pr.setDescProceso("Generación de Movimientos");
					break;
				}
				case "4": {
					pr.setDescProceso("Reporte de Cuentas Canceladas");
					break;
				}
				}
				EjecucionResult result = service.procesoEjecutar(Integer.valueOf(radioSelected), fechaI, fechaF, null);
				if (result.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (result.getOn_Estatus() == 2) {
						GenerarErrorNegocio(result.getOcMensaje());
					} else if (result.getOn_Estatus() == 0) {
						pr.setStatus(result.getOcMensaje());
					}
				}
			} catch (Exception e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}

	}

	public boolean isFormValid(ProcessResult pr) {
		boolean resp = false;
		if (radioSelected == null) {
			UIInput radio = (UIInput) findComponent("radSelect");
			radio.setValid(false);

			pr.setDescProceso("Debe seleccionar una opción");
			pr.setStatus("Selección requerida");
			return false;
		} else {
			switch (radioSelected) {

			case "1": {
				if (fecha != null) {
					resp = true;
					break;
				} else {
					UIInput radio = (UIInput) findComponent("fCapturada");
					radio.setValid(false);
					pr.setDescProceso("Debe seleccionar una fecha");
					pr.setStatus("Selección requerida");
				}
			}
			case "2": {
				if (fecha != null) {
					resp = true;
					break;
				} else {
					UIInput radio = (UIInput) findComponent("fCapturada");
					radio.setValid(false);
					pr.setDescProceso("Debe seleccionar una fecha");
					pr.setStatus("Selección requerida");
				}
			}
			case "3": {
				if (fecha != null) {
					resp = true;
					break;
				} else {
					UIInput radio = (UIInput) findComponent("fCapturada");
					radio.setValid(false);
					pr.setDescProceso("Debe seleccionar una fecha");
					pr.setStatus("Selección requerida");
				}
			}
			case "4": {
				if (fechaI != null && fechaF != null) {
					if (DateUtil.isValidDates(fechaI, fechaF)) {
						resp = true;
						break;
					} else {
						UIInput fI = (UIInput) findComponent("fechaI");
						fI.setValid(false);
						UIInput fF = (UIInput) findComponent("fechaF");
						fF.setValid(false);
						pr.setDescProceso("La fecha inicial debe ser menor o igual a la fecha final");
						pr.setStatus("Selección requerida");
						resp = false;
						break;
					}
				} else {
					UIInput fI = (UIInput) findComponent("fechaI");
					fI.setValid(false);
					UIInput fF = (UIInput) findComponent("fechaF");
					fF.setValid(false);
					pr.setDescProceso("Debe seleccionar fecha inicio y fecha fin");
					pr.setStatus("Selección requerida");
					resp = false;
					break;
				}
			}
			}

		}

		return resp;
	}
}
