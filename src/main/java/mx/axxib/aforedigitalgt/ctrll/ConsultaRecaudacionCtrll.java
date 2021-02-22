package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ConsultaNSS;
import mx.axxib.aforedigitalgt.eml.ConsultaNSSOut;
import mx.axxib.aforedigitalgt.serv.ConsultaRecaudacionServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

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
					datos = res.getDatos();
					if (datos != null && datos.size() > 0) {
						pr.setStatus("Exitoso");
						mostrarReporte = true;
					} else {
						mensajeTabla = "Sin información";
						pr.setStatus("No se encontró información");
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

					String msg = consultaService.getGeneraReporte(nss);

					pr.setStatus(msg);
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
