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
import mx.axxib.aforedigitalgt.reca.eml.InterInversion;
import mx.axxib.aforedigitalgt.reca.eml.InterInversionOut;
import mx.axxib.aforedigitalgt.reca.eml.InterLote;
import mx.axxib.aforedigitalgt.reca.eml.InterLoteOut;
import mx.axxib.aforedigitalgt.reca.serv.InterfacesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Interfaces
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Mar/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "interfaces")
@ELBeanName(value = "interfaces")
public class InterfacesCtrll extends ControllerBase {

	@Autowired
	private InterfacesServ serv;
	
	
	@Getter
	private List<InterLote> lotes;
	
	@Getter
	private List<InterInversion> inversiones;
	
	@Getter
	@Setter
	private InterLote lote;
	
	@Getter
	@Setter
	private InterInversion inversion;

	
	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	@Setter
	private String opcion;
	
		
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			limpiar();
			lotes();
			inversiones();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		lotes = null;
		inversiones = null;
		lote = null;
		inversion = null;
		fecha = null;
		opcion = null;
	}
	
	public void lotes() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar lotes");

			InterLoteOut res = serv.lote();
			if (res.getEstatus() == 1) {
				lotes = res.getDatos();
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
	
	public void inversiones() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar inversiones");

			InterInversionOut res = serv.inversion();
			if (res.getEstatus() == 1) {
				inversiones = res.getDatos();
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
		if (lote == null) {
			UIInput radio = (UIInput) findComponent("lote");
			radio.setValid(false);

			pr.setStatus("Debe elegir un lote");
			return false;
		}
		
		if (inversion == null) {
			UIInput radio = (UIInput) findComponent("inversion");
			radio.setValid(false);

			pr.setStatus("Debe elegir una inversión");
			return false;
		}
		
		if (fecha == null) {
			UIInput fini = (UIInput) findComponent("fecha");
			fini.setValid(false);

			pr.setStatus("Debe elegir un fecha");
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
	

	public void generar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Generar");
			if (isFormValid(pr)) {
				BaseOut res = serv.interfaces(opcion, lote.getLote(), inversion.getClave(), inversion.getCodInversion(), fecha);  
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
