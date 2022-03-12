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
import mx.axxib.aforedigitalgt.reca.eml.LoteDesmarcar;
import mx.axxib.aforedigitalgt.reca.eml.RecaudacionOut;
import mx.axxib.aforedigitalgt.reca.serv.RecaudacionCargaServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudación - Carga
//** Interventor Principal: JJSC
//** Fecha Creación: 09/03/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "recaudacionCarga")
@ELBeanName(value = "recaudacionCarga")
public class RecaudacionCargaCtrll extends ControllerBase {

	@Autowired
	private RecaudacionCargaServ service;

	@Getter
	@Setter
	private String archivo;

	@Getter
	@Setter
	private List<LoteDesmarcar> listDesmarcar;

	@Getter
	@Setter
	private String border;

	@Getter
	@Setter
	private LoteDesmarcar selectedLote;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				border = "";
				archivo = null;
				selectedLote = null;
				listDesmarcar = null;
				getLotes();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getLotes() throws Exception {
		RecaudacionOut resp = service.getListaCarga();
		if (resp.getEstatus() == 1) {
			listDesmarcar = resp.getListDesmarcar();
		} else {
			if (resp.getEstatus() == 2) {
				GenerarErrorNegocio(resp.getMensaje());
			}
		}
	}

	public void cargar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Cargar Archivo");
		pr.setFechaInicial(DateUtil.getNowDate());
		try {
			if (!archivo.isEmpty()) {
				RecaudacionOut resp = service.cargar(archivo);
				if (resp.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getEstatus() == 2) {
						GenerarErrorNegocio(resp.getMensaje());
					} else if (resp.getEstatus() == 0) {
						pr.setStatus(resp.getMensaje());
					}
				}
			} else {
				UIInput radio = (UIInput) findComponent("archivo");
				radio.setValid(false);
				pr.setStatus("Favor de introducir el nombre de archivo");
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void aplicar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Aplicar");
		pr.setFechaInicial(DateUtil.getNowDate());
		try {
			if (selectedLote != null) {
				RecaudacionOut resp = service.aplicar(selectedLote.getLote());
				if (resp.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getEstatus() == 2) {
						GenerarErrorNegocio(resp.getMensaje());
					} else if (resp.getEstatus() == 0) {
						pr.setStatus(resp.getMensaje());
					}
				}
			} else {
				UIInput radio = (UIInput) findComponent("combLote");
				radio.setValid(false);
				pr.setStatus("Por favor seleccione un lote");
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

}
