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
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.CompraTitMonto;
import mx.axxib.aforedigitalgt.reca.eml.CompraTitMontoOut;
import mx.axxib.aforedigitalgt.reca.eml.Empresas;
import mx.axxib.aforedigitalgt.reca.eml.EmpresasOut;
import mx.axxib.aforedigitalgt.reca.serv.CompraTitulosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Compra de Titulos
//** Interventor Principal: JSAS
//** Fecha Creación: 1/Mar/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "compraTitulos")
@ELBeanName(value = "compraTitulos")
public class CompraTitulosCtrll extends ControllerBase {

	@Autowired
	private CompraTitulosServ serv;

	@Getter
	@Setter
	private String selectedEmpresa;
	
	@Getter
	private List<Empresas> empresas;
	
	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	@Setter
	private String lote;
	
	@Getter
	@Setter
	private boolean generar;
	
	@Getter
	@Setter
	private String opcion;
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	@Setter
	private List<CompraTitMonto> datos;
	
	@Getter
	private boolean mostrarLote;
	
	@Getter
	private boolean mostrarFecha;
	
	@Getter
	private boolean mostrarCom;
	
	@Getter
	private boolean mostrarComCT;
	
	@Getter
	private boolean mostrarBuscar;
	

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			limpiar();
			empresas();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		selectedEmpresa = null;
		empresas = null;
		fecha = null;
		lote = null;
		generar = false;
		opcion = null;
		mensajeTabla = null;
		datos = null;
		mostrarCom = false;
		mostrarComCT = false;
		mostrarLote = false;
		mostrarFecha = false;
		mostrarBuscar = false;
	}
	
	public void changeOpcion() {
		if(opcion != null) {
			mostrarBuscar = true;
			if(opcion.equals("1")) {
				mostrarLote = true;
				mostrarFecha = false;
			} else if(opcion.equals("2")) {
				mostrarLote = false;
				mostrarFecha = true;
			}
		}
		UIInput lot = (UIInput) findComponent("lote");
		lot.setValid(true);
		UIInput fec = (UIInput) findComponent("fecha");
		fec.setValid(true);
		fecha = null;
		lote = null;
		generar = false;
	}
	
	public void empresas() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar empresas");

			EmpresasOut res = serv.empresas();
			if (res.getEstatus() == 1) {
				empresas = res.getDatos();
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

	public boolean isFormValid(ProcessResult pr) {
		if (selectedEmpresa == null) {
			UIInput fini = (UIInput) findComponent("empresa");
			fini.setValid(false);

			pr.setStatus("Debe elegir una empresa");
			return false;
		}
		
		if (fecha == null) {
			UIInput fini = (UIInput) findComponent("fecha");
			fini.setValid(false);

			pr.setStatus("Debe elegir una fecha");
			return false;
		}

		return true;
	}
	
	public boolean isFormValid2(ProcessResult pr) {
		if (selectedEmpresa == null) {
			UIInput fini = (UIInput) findComponent("empresa");
			fini.setValid(false);

			pr.setStatus("Debe elegir una empresa");
			return false;
		}
		
		if (lote == null || lote.equals("")) {
			UIInput fini = (UIInput) findComponent("lote");
			fini.setValid(false);

			pr.setStatus("Debe introducir un lote");
			return false;
		}
		
		return true;
	}
	
	public void buscar() {
		if(opcion != null) {
			if(opcion.equals("1")) {
				montoLote();
			} else if(opcion.equals("2")) {
				montoFecha();
			}
		}
	}

	public void montoFecha() {
		ProcessResult pr = new ProcessResult();
		try {
			datos = null;
			mostrarComCT = false;
			mensajeTabla = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar por fecha");
			if (isFormValid(pr)) {
				CompraTitMontoOut res = serv.montoFecha(fecha, selectedEmpresa);
				if (res.getEstatus() == 1) {
					datos = res.getDatos();
					if (datos != null && datos.size() > 0) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						mostrarComCT = true;
					} else {
						mensajeTabla = "Sin información";
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
	
	public void montoLote() {
		ProcessResult pr = new ProcessResult();
		try {
			datos = null;
			mostrarCom = false;
			mensajeTabla = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar por lote");
			if (isFormValid2(pr)) {
				CompraTitMontoOut res = serv.montoLote(lote, selectedEmpresa);
				if (res.getEstatus() == 1) {
					datos = res.getDatos();
					if (datos != null && datos.size() > 0) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						mostrarCom = true;
					} else {
						mensajeTabla = "Sin información";
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

	public void compraTitulosCT() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Compra títulos CT");
			if (isFormValid(pr)) {
				BaseOut res = serv.compraTitulos(fecha, selectedEmpresa, generar?"S":"N");
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
	
	public void compraTitulos() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Compra títulos");
			if (isFormValid2(pr)) {
				BaseOut res = serv.ejecuta(lote, selectedEmpresa);
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
