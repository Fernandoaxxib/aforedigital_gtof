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
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenConsulta;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenConsultaOut;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenLotes;
import mx.axxib.aforedigitalgt.reca.eml.LiquidarRenOut;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarRendimientosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 05/Ene/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "liquidarRendimientos")
@ELBeanName(value = "liquidarRendimientos")
public class LiquidarRendimientosCtrll extends ControllerBase {

	@Autowired
	private LiquidarRendimientosServ serv;

	@Getter
	@Setter
	private String lote;
	
	@Getter
	@Setter
	private List<LiquidarRenLotes> lotes;
	
	@Getter
	@Setter
	private boolean opcion;
	
	@Getter
	@Setter
	private List<LiquidarRenConsulta> rendimientos;
	
	@Getter
	@Setter
	private boolean mostrarLiquidar;
	
	@Getter
	private String mensajeTabla;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			limpiar();
			consultarLotes();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
			
		}
	}

	private void limpiar() {
		mensajeTabla = null;
		mostrarLiquidar = false;
		lote = null;
		lotes = null;
		opcion = false;
		rendimientos = null;
	}

	public void consultarLotes() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar lotes");

			LiquidarRenOut res = serv.lote();
			if (res.getEstatus() == null) { //TODO sustituir null por 1, actualmente el stored no devuelve status
				lotes = res.getLotes();
				if(lotes.size() == 0) {
					pr.setStatus("No se encontraron lotes");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
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
			UIInput radio = (UIInput) findComponent("lotes");
			radio.setValid(false);

			pr.setStatus("Debe elegir un lote");
			return false;
		}

		return true;
	}
	
	
	public void consultarLote() {
		ProcessResult pr = new ProcessResult();

		try {
			opcion = false;
			rendimientos = null;
			mostrarLiquidar = false;
			mensajeTabla = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar rendimientos");
			if (isFormValid(pr)) {
				LiquidarRenConsultaOut res = serv.consulta(lote);
				if (res.getEstatus() == null) {  //TODO reemplazar null por 1
					rendimientos = res.getMontos();
					if (rendimientos != null && rendimientos.size() > 0) {
						mostrarLiquidar = true;
					} else {
						mensajeTabla = "Sin información";
					}
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
	
	public void liquidar() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Liquidar");
			String tipo = opcion ? "G" : "";
			BaseOut res = serv.liquidar(lote, tipo);
			if (res.getEstatus() == null) { //TODO sustituir null por 1
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
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
