package mx.axxib.aforedigitalgt.ctrll;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteUMAIn;
import mx.axxib.aforedigitalgt.eml.ValorUMA;
import mx.axxib.aforedigitalgt.serv.ValorUMAServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "valorUMA")
@ELBeanName(value = "valorUMA")
public class ValorUMACtrll extends ControllerBase {

	@Autowired
	private ValorUMAServ valorUMAService;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	private String ruta;

	@Getter
	private List<ValorUMA> valores;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {

			fechaInicial = null;
			fechaFinal = null;
			ruta = "/RESPALDOS/operaciones";
			valores = new ArrayList<ValorUMA>();

			valorUMA();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void editar(ValorUMA valor) {
		//valores.remove(valor);
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Editar");
		pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
		resultados.add(pr);
	}

	
	public void borrar(ValorUMA valor) {
		valores.remove(valor);
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Eliminar");
		pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
		resultados.add(pr);
	}
	
	

	public void valorUMA() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Obtener valores UMA");

			ValorUMA v1 = new ValorUMA();
			v1.setUser("User1");
			v1.setMonto(new BigDecimal(10));
			v1.setFechaActual(new Date());
			v1.setFechaUltAct(new Date());

			ValorUMA v2 = new ValorUMA();
			v2.setUser("User2");
			v2.setMonto(new BigDecimal(11));
			v2.setFechaActual(new Date());
			v2.setFechaUltAct(new Date());

			ValorUMA v3 = new ValorUMA();
			v3.setUser("User3");
			v3.setMonto(new BigDecimal(12));
			v3.setFechaActual(new Date());
			v3.setFechaUltAct(new Date());

			valores.add(v1);
			valores.add(v2);
			valores.add(v3);

//			String usuario = "USER1"; // TODO: revisar qué usuario se debe enviar en la versión final
//			ValorUMAOut res = valorUMAService.getValorUMA(usuario);
//			if (res.getEstatus() == 1) {
//				valores = res.getValores();
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
//			} else {
//				if (res.getEstatus() == 2) {
//					GenerarErrorNegocio(res.getMensaje());
//				} else if (res.getEstatus() == 0) {
//					pr.setStatus(res.getMensaje());
//				}
//			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}

	}

	public boolean isFormValid(ProcessResult pr) {
		if (fechaInicial == null) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);
			pr.setStatus("Fecha inicio es requerida");
			return false;
		}

		if (fechaFinal == null) {
			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);
			pr.setStatus("Fecha fin es requerida");
			return false;
		}

		if (!DateUtil.isValidDates(fechaInicial, fechaFinal)) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);

			pr.setStatus("La fecha final debe ser mayor o igual a la inicial");
			return false;
		}

		return true;
	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Generar reporte");

			if (isFormValid(pr)) {
				GeneraReporteUMAIn parametros = new GeneraReporteUMAIn();
				parametros.setFechaInicial(fechaInicial);
				parametros.setFechaFinal(fechaFinal);
				parametros.setRuta(ruta);
				BaseOut res = valorUMAService.getGeneraReporte(parametros);
				if (res.getEstatus() == 1) {
					pr.setStatus(
							aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null) + ": " + res.getMensaje());
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			}

		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

}
