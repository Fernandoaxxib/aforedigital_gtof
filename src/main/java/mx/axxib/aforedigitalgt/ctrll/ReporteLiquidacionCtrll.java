package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.eml.LiqEjecutaReporteIn;
import mx.axxib.aforedigitalgt.eml.LiqObtieneParametrosOut;
import mx.axxib.aforedigitalgt.eml.LiqObtieneSieforeOut;
import mx.axxib.aforedigitalgt.serv.ReporteLiquidacionServ;

@Scope(value = "session")
@Component(value = "reporteLiquidacion")
@ELBeanName(value = "reporteLiquidacion")
public class ReporteLiquidacionCtrll extends ControllerBase {

	@Autowired
	HikariDataSource dataSource;
	
	@Autowired
	private ReporteLiquidacionServ reporteLiquidacionService;

	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	private LiqObtieneParametrosOut parametros;
	
	@Getter
	private List<LiqObtieneSieforeOut> sieforeList;
	
	@Getter
	@Setter
	private String selectedEstatus;
	
	@Getter
	@Setter
	private String selectedTipoRetiro;
	
	@Getter
	@Setter
	private LiqObtieneSieforeOut selectedSiefore;
	
	@Getter
	@Setter
	private String selectedTipo;

	@Getter
	private String lote;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			selectedEstatus = null;
			selectedTipoRetiro = null;
			selectedSiefore = null;
			selectedTipo = null;
			lote = null;
			getObtieneParametros();
			getObtieneSiefores();
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void getObtieneParametros() {
		try {
			parametros = reporteLiquidacionService.getObtieneParametros();
			lote = parametros.getIdLote();
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void getObtieneSiefores() {
		try {
			sieforeList = reporteLiquidacionService.getObtieneSiefore();
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void ejecutarReporte() {
		try {
			LiqEjecutaReporteIn parametros = new LiqEjecutaReporteIn();
			parametros.setDescripcion(selectedSiefore.getDescripcion());
			parametros.setEstado(selectedEstatus);
			parametros.setFecha(parametros.getFecha());
			parametros.setIdLote(parametros.getIdLote());
			parametros.setSiefore(selectedSiefore.getSiefore());
			parametros.setTipoReporte(selectedTipo);
			parametros.setTipoRetiro(selectedTipoRetiro);
			parametros.setUsuario(dataSource.getUsername());
			String msg = reporteLiquidacionService.ejecutarReporte(parametros);
			if(msg.toUpperCase().contains("PROCESO TERMINADO")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

}
