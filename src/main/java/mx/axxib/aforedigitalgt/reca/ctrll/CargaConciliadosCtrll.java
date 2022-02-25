package mx.axxib.aforedigitalgt.reca.ctrll;

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
import mx.axxib.aforedigitalgt.reca.eml.CargaOut;
import mx.axxib.aforedigitalgt.reca.eml.Conciliar;
import mx.axxib.aforedigitalgt.reca.eml.ConciliarOut;
import mx.axxib.aforedigitalgt.reca.serv.CargaConciliadosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Carga Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 21/Feb/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "cargaConciliados")
@ELBeanName(value = "cargaConciliados")
public class CargaConciliadosCtrll extends ControllerBase {

	@Autowired
	private CargaConciliadosServ serv;
	
	@Getter
	private String directorio;

	@Getter
	@Setter
	private String archivo;
	
	@Getter
	private String lote;
	
	@Getter
	@Setter
	private String opcion;
	
	@Getter
	private List<Conciliar> estados;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			directorio = "/RESPALDOS/operaciones/CONCILIADOS";
			limpiar();
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		archivo = null;
		lote = null;
		opcion = null;
		estados = null;
	}
	
	public boolean isFormValid(ProcessResult pr) {
		
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
		if (opcion == null) {
			UIInput radio = (UIInput) findComponent("opcion");
			radio.setValid(false);

			pr.setStatus("Debe elegir una opción");
			return false;
		}
		
		return true;
	}
	
	public boolean isFormValid2(ProcessResult pr) {
		
		if (lote == null) {
			UIInput radio = (UIInput) findComponent("lote");
			radio.setValid(false);

			pr.setStatus("Debe haberse generado un lote previamente");
			return false;
		} 
		if (opcion == null) {
			UIInput radio = (UIInput) findComponent("opcion");
			radio.setValid(false);

			pr.setStatus("Debe elegir una opción");
			return false;
		}
		
		return true;
	}
	
			
	public void cargarArchivo() {
		ProcessResult pr = new ProcessResult();
		try {
			lote = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cargar archivo");
			if (isFormValid(pr)) {
				CargaOut res = serv.cargaArchivo(directorio, archivo, opcion); // NORMAL O PREVA
				if (res.getEstatus() == 1) { 
					lote = res.getLote();
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
	
	public void conciliarArchivo() {
		ProcessResult pr = new ProcessResult();
		try {
			estados = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Conciliar archivo");
			if (isFormValid2(pr)) {
				ConciliarOut res = serv.conciliarArchivo(opcion, lote); 
				if (res.getEstatus() == 1) { 
					estados = res.getDatos();
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
