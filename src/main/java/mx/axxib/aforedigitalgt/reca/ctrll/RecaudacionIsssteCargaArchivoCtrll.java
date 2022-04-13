package mx.axxib.aforedigitalgt.reca.ctrll;

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
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionIsssteCargaArchivoOut;
import mx.axxib.aforedigitalgt.reca.serv.RecaudacionIsssteCargaArchivoServ;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudacion ISSSTE
//** Interventor Principal: EAG
//** Fecha Creación: 04/Abril/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "recaudacionIsssteCargaArchivo")
@ELBeanName(value = "recaudacionIsssteCargaArchivo")
public class RecaudacionIsssteCargaArchivoCtrll extends ControllerBase {

	@Autowired
	private RecaudacionIsssteCargaArchivoServ serv;

	@Getter
	@Setter
	private String arch_entrada;
	
	@Getter
	@Setter
	private String dir_archivo;
	
	@Getter
	@Setter
	private String tipo;
	
	@Getter
	@Setter
	private String lote_reversa;
	

	

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			dir_archivo = "/????/PROCESAR/RECEPCION/AFORE/RECAUDACION/ISSSTE-SIEFORE/";

			limpiar();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		arch_entrada = null;
		dir_archivo = null;
		tipo = null;

	}



	
	public void cargarArchivo() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setDescProceso("Cargar archivo");
			if (isFormValid(pr)) {
				BaseOut res = serv.cargarArchivo(arch_entrada, dir_archivo, tipo);
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
	
	
	public void reversa() {
		ProcessResult pr = new ProcessResult();
		
		try {
		pr.setDescProceso("REVERSA DE CARGA DE ARCHIVOS");

		if (lote_reversa != null) {
			try {
				BaseOut res = serv.reversa(lote_reversa);
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
	
	
	
	public void lReversa() {
		ProcessResult pr = new ProcessResult();
		
		try {
		pr.setDescProceso("REVERSA DE CARGA DE ARCHIVOS");

		if (lote_reversa != null) {
			try {
				RecaudacionIsssteCargaArchivoOut res = serv.lReversa();
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
	
	
	public boolean isFormValid(ProcessResult pr) {
		if (arch_entrada == null || arch_entrada.equals("")) {
			UIInput radio = (UIInput) findComponent("archivo");
			radio.setValid(false);

			pr.setStatus("Debe elegir un nombre de archivo");
			return false;
		} else if (!ValidateUtil.isValidFileName(arch_entrada)) {
			UIInput radio = (UIInput) findComponent("archivo");
			radio.setValid(false);

			pr.setStatus("Nombre de archivo inválido");
			return false;
		}


		return true;
	}

	
}
