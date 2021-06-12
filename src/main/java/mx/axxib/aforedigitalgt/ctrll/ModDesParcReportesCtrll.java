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
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.ModDesParcReportesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** Funcionalidad: Controlador - Desempleo parcialidades - Reportes
//** Desarrollador: JJSC
//** Fecha de creación: 10/Dic/2020
//** Última modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "modDesParcReportes")
@ELBeanName(value = "modDesParcReportes")

public class ModDesParcReportesCtrll extends ControllerBase {

	@Autowired
	private ModDesParcReportesServ service;

	@Getter
	@Setter
	private String archivo;
	@Getter
	@Setter
	private String ruta;
	@Getter
	@Setter
	private Date fecha;
	@Getter
	@Setter
	private ProcesoOut proceso;
	@Getter
	@Setter
	private String radioSelected;

	@Getter
	private String disabled;
	
	@Getter
	private Date fecActual;
		

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			reset();
			fecActual=DateUtil.getNowDate();			
			init=false;
		}
	}

	public void radioSelected() {
		fecha=null;
		ruta=null;
		archivo=null;
		disabled="false";		
	}

	public void procesarReporte() {				
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if (isFormValid(pr)) {
			
			try {				
				switch (radioSelected) {
				case "1":
					pr.setDescProceso("Reporte de Diagnóstico");					
					break;
				case "2":
					pr.setDescProceso("Generación layout pagos");
					break;
				case "3":
					pr.setDescProceso("Reporte negativos subcuentas");
					break;
				case "4":
					pr.setDescProceso("Reporte movimientos aplicados");
					break;
				default:
					proceso.setAbrevProceso("");
				}
								
				EjecucionResult result = service.procesarReporte(Integer.valueOf(radioSelected), fecha,ruta,archivo);
				if (result.getOn_Estatus() == 1) {
					if(radioSelected.equals("4")) {
						pr.setStatus("Reporte en implementación");
					}else {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				    }
				} else {
					if (result.getOn_Estatus() == 2) {
						GenerarErrorNegocio(result.getOcMensaje());
					} else if (result.getOn_Estatus() == 0) {
						pr.setStatus(result.getOcMensaje());
					}
				}
			} catch (Exception e) {				
				pr=GenericException(e);
			}finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		} else {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void reset() {
		radioSelected = null;
		fecha = null;
		archivo = null;
		ruta = null;
		proceso = null;
		disabled="true";
	}

	public void opcionSeleccionada() {

		if (radioSelected != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String f = format.format(fecha);

			switch (radioSelected) {

			case "1":
				this.ruta = "/RESPALDOS/operaciones/pruebas";
				this.archivo = "RepParDiag_" + f + ".xls";
				break;

			case "2":
				this.ruta = "/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
				this.archivo = "LayoutPagos_" + f + ".xls";
				break;

			case "3":
				this.ruta = "/RESPALDOS/operaciones/pruebas";
				this.archivo = "RepParNeg_" + f + ".xls";
				break;

			case "4":
				this.archivo = "Reporte Pendiente de Confirmar";
				this.ruta = "Reporte Pendiente de Confirmar";
				break;

			default:
				this.archivo = "";
				this.ruta = "";
				break;

			}
		}
	}
	
	public boolean isFormValid(ProcessResult pr) {
		if (radioSelected == null) {
			UIInput radio = (UIInput) findComponent("customRadio2");
			radio.setValid(false);
			
			pr.setDescProceso("Debe seleccionar una opción");
			pr.setStatus("Selección requerida");
			return false;
		}
		if (fecha == null) {
			UIInput radio = (UIInput) findComponent("fCapturada2");
			radio.setValid(false);

			pr.setDescProceso("Debe seleccionar una fecha");
			pr.setStatus("Selección requerida");
			return false;
		}
		return true;
	}
}
