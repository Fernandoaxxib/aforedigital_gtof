package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.LiqEjecutaReporteIn;
import mx.axxib.aforedigitalgt.eml.LiqObtieneParametrosOut;
import mx.axxib.aforedigitalgt.eml.LiqObtieneSiefore;
import mx.axxib.aforedigitalgt.eml.LiqObtieneSieforeOut;
import mx.axxib.aforedigitalgt.serv.ReporteLiquidacionServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "reporteLiquidacion")
@ELBeanName(value = "reporteLiquidacion")
public class ReporteLiquidacionCtrll extends ControllerBase {

	@Autowired
	HikariDataSource dataSource;

	@Autowired
	private ReporteLiquidacionServ reporteLiquidacionService;

	@Getter
	private LiqObtieneParametrosOut parametros;

	@Getter
	private List<LiqObtieneSiefore> sieforeList;

	@Getter
	@Setter
	private String selectedEstatus;

	@Getter
	@Setter
	private String selectedTipoRetiro;

	@Getter
	@Setter
	private LiqObtieneSiefore selectedSiefore;

	@Getter
	@Setter
	private String selectedTipo;

	@Getter
	@Setter
	private String lote;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			selectedEstatus = null;
			selectedTipoRetiro = null;
			selectedSiefore = null;
			selectedTipo = null;
			lote = null;
			try {
				getObtieneParametros();
				getObtieneSiefores();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void getObtieneParametros() throws Exception {
		parametros = reporteLiquidacionService.getObtieneParametros();
		if (parametros.getEstatus() == 1) {
			lote = parametros.getIdLote();
		} else {
			if (parametros.getEstatus() == 2) {
				GenerarErrorNegocio(parametros.getMensaje());
			}
		}
	}

	public void getObtieneSiefores() throws Exception {
		LiqObtieneSieforeOut res = reporteLiquidacionService.getObtieneSiefore();
		if (res.getEstatus() == 1) {
			sieforeList = res.getSiefore();
		} else {
			if (res.getEstatus() == 2) {
				GenerarErrorNegocio(res.getMensaje());
			}
		}

	}

	public boolean isValidForm(ProcessResult pr) {
		if (selectedEstatus == null) {
			UIInput ffin = (UIInput) findComponent("estatus");
			pr.setStatus("Estatus es requerido");
			ffin.setValid(false);
			return false;
		}
		
		if (!ValidateUtil.isValidLote(lote)) {
			UIInput fini = (UIInput) findComponent("lote");
			pr.setStatus("Lote no válido");
			fini.setValid(false);
			return false;
		}
		
		if (selectedTipo == null) {
			UIInput ffin = (UIInput) findComponent("tipo");
			pr.setStatus("Tipo es requerido");
			ffin.setValid(false);
			return false;
		}
		
		if (selectedTipoRetiro == null) {
			UIInput ffin = (UIInput) findComponent("tipoRetiro");
			pr.setStatus("Tipo retiro es requerido");
			ffin.setValid(false);
			return false;
		}
		
		if (selectedSiefore == null) {
			UIInput ffin = (UIInput) findComponent("siefore");
			pr.setStatus("Siefore es requerido");
			ffin.setValid(false);
			return false;
		}
		


		return true;
	}

	public void ejecutarReporte() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Ejecutar reporte");
			if (isValidForm(pr)) {
				LiqEjecutaReporteIn parametros = new LiqEjecutaReporteIn();
				parametros.setDescripcion(selectedSiefore.getDescripcion());
				parametros.setEstado(selectedEstatus);
				parametros.setFecha(DateUtil.getNowDate());
				parametros.setIdLote(lote);
				parametros.setSiefore(selectedSiefore.getSiefore());
				parametros.setTipoReporte(selectedTipo);
				parametros.setTipoRetiro(selectedTipoRetiro);
				parametros.setUsuario(dataSource.getUsername());
				BaseOut res = reporteLiquidacionService.ejecutarReporte(parametros);
				if (res.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null)+": "+res.getMensaje());
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
