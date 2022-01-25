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
import mx.axxib.aforedigitalgt.reca.eml.DatosConciOut;
import mx.axxib.aforedigitalgt.reca.eml.Semana;
import mx.axxib.aforedigitalgt.reca.eml.SemanaDetalle;
import mx.axxib.aforedigitalgt.reca.eml.SemanasOut;
import mx.axxib.aforedigitalgt.reca.serv.ConciSemCotIMSSServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Conciliación Semanas de Cotización IMSS
//** Interventor Principal: JSAS
//** Fecha Creación: 31/Ene/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "conciSemCotIMSS")
@ELBeanName(value = "conciSemCotIMSS")
public class ConciSemCotIMSSCtrll extends ControllerBase {

	@Autowired
	private ConciSemCotIMSSServ serv;
	
	@Getter
	@Setter
	private Date fechaI;

	@Getter
	@Setter
	private Date fechaF;
	
	@Getter
	@Setter
	private String nss;
	
	@Getter
	private List<Semana> semanas;
	
	@Getter
	@Setter
	private Semana selectedSemana;
	
	@Getter
	private List<SemanaDetalle> detalle;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			limpiar();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		fechaI = null;
		fechaF = null;
		nss = null;
		semanas = null;
		selectedSemana = null;
		detalle = null;
	}
	
	public void buscar() {
		ProcessResult pr = new ProcessResult();
		try {
			semanas = null;
			detalle = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar");
			if (isFormValid(pr)) {
				SemanasOut res = serv.buscar(fechaI, fechaF, nss);
				if (res.getEstatus() == 1) { 
					semanas = res.getSemanas();
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
		
		if (!ValidateUtil.isNSS(nss)) {
			UIInput radio = (UIInput) findComponent("nss");
			radio.setValid(false);

			pr.setStatus("NSS no válido");
			return false;
		}
		
		return true;
	}

	public void detalle() {
		ProcessResult pr = new ProcessResult();
		try {
			detalle = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Detalle");
			if(selectedSemana != null) {
				DatosConciOut res = serv.datos(selectedSemana.getNoMov(), selectedSemana.getFechaMov());
				if (res.getEstatus() == 1) {
					detalle = res.getDetalle();
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				}
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		}
	}
	
}
