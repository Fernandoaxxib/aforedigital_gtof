package mx.axxib.aforedigitalgt.ctrll;

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
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.ConsultaNSS;
import mx.axxib.aforedigitalgt.eml.ConsultaNSSOut;
import mx.axxib.aforedigitalgt.serv.ConsultaRecaudacionServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudación
//** Interventor Principal: JSAS
//** Fecha Creación: 22/Feb/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "consultaRecaudacion")
@ELBeanName(value = "consultaRecaudacion")
public class ConsultaRecaudacionCtrll extends ControllerBase {

	@Autowired
	private ConsultaRecaudacionServ consultaService;

	@Getter
	@Setter
	private String nss;

	@Getter
	private List<ConsultaNSS> datos;

	@Getter
	private String mensajeTabla;

	@Getter
	private boolean mostrarReporte;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			mensajeTabla = null;
			nss = null;
			datos = null;
			mostrarReporte = false;
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void consultarNSS() {
		ProcessResult pr = new ProcessResult();
		try {
			datos = null;
			mostrarReporte = false;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar por NSS");

			if (nss != null && !nss.equals("")) {
				if (ValidateUtil.isNSS(nss)) {
					ConsultaNSSOut res = consultaService.getConsultaNSS(nss);
					if(res.getEstatus() == 1) {
						datos = res.getDatos();
						if (datos != null && datos.size() > 0) {
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
							mostrarReporte = true;
						} else {
							mensajeTabla = "Sin información";
							pr.setStatus("No se encontró información");
						}
					} else {
						if(res.getEstatus() == 2) {
							GenerarErrorNegocio(res.getMensaje());
						} else if(res.getEstatus() == 0) {
							pr.setStatus(res.getMensaje());
						} 
					}
				} else {
					UIInput input = (UIInput) findComponent("nss");
					input.setValid(false);
					pr.setStatus("NSS no válido");
				}
			} else {
				UIInput input = (UIInput) findComponent("nss");
				input.setValid(false);
				pr.setStatus("NSS es requerido");
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		try {
			if (nss != null && !nss.equals("")) {
				if (ValidateUtil.isNSS(nss)) {
					pr.setFechaInicial(DateUtil.getNowDate());
					pr.setDescProceso("Generar reporte");

					BaseOut res = consultaService.getGeneraReporte(nss);
					if(res.getEstatus() == 1) {
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null)+": "+res.getMensaje());
					} else {
						if(res.getEstatus() == 2) {
							GenerarErrorNegocio(res.getMensaje());
						} else if(res.getEstatus() == 0) {
							pr.setStatus(res.getMensaje());
						} 
					}
				} else {
					UIInput input = (UIInput) findComponent("nss");
					input.setValid(false);
					pr.setStatus("NSS no válido");
				}
			} else {
				UIInput input = (UIInput) findComponent("nss");
				input.setValid(false);
				pr.setStatus("NSS es requerido");
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

}
