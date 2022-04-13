package mx.axxib.aforedigitalgt.reca.ctrll;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteArchivoRespuestaOut;
import mx.axxib.aforedigitalgt.reca.serv.RecaudacionIsssteArchivoRespuestaServ;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudacion ISSSTE
//** Interventor Principal: EAG
//** Fecha Creación: 04/Abril/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "recaudacionIsssteArchivoRespuesta")
@ELBeanName(value = "recaudacionIsssteArchivoRespuesta")
public class RecaudacionIsssteArchivoRespuestaCtrll extends ControllerBase {

	@Autowired
	private RecaudacionIsssteArchivoRespuestaServ serv;

	@Getter
	private String lote;
	
	

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
		lote = null;
	}
	
	public void lote() {
		ProcessResult pr = new ProcessResult();
		
		try {
		pr.setDescProceso("LOTE");

		if (lote != null) {
			try {
				RecaudacionIsssteArchivoRespuestaOut res = serv.lote();
				if (res.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			} catch (Exception e) {
				pr = GenericException(e);
			}
		}else {
			pr.setStatus("Se requiere el número de lote");
		}
		
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			resultados.add(pr);
		}
	}
	
	public void iniciaProceso() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setDescProceso("Inicia Proceso");
			if (isFormValid(pr)) {
				RecaudacionIsssteArchivoRespuestaOut res = serv.iniciaProceso(lote);
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
			resultados.add(pr);
		}
	}
	
	public boolean isFormValid(ProcessResult pr) {
		if (lote == null || lote.equals("")) {
			UIInput radio = (UIInput) findComponent("lote");
			radio.setValid(false);

			pr.setStatus("Debe elegir un lote");
			return false;
		} else if (!ValidateUtil.isValidFileName(lote)) {
			UIInput radio = (UIInput) findComponent("lote");
			radio.setValid(false);

			pr.setStatus("Nombre de lote inválido");
			return false;
		}


		return true;
	}

	
}
