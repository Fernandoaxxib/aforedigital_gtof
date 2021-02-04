package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.eml.DesbloqueaCuentasIn;
import mx.axxib.aforedigitalgt.eml.GeneraArchivoIn;
import mx.axxib.aforedigitalgt.eml.ObtieneCodCuentaOut;
import mx.axxib.aforedigitalgt.eml.ObtieneTipoProceso;
import mx.axxib.aforedigitalgt.eml.ObtieneTipoProcesoOut;
import mx.axxib.aforedigitalgt.serv.DiagnosticoCuentaServ;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "diagCuenta")
@ELBeanName(value = "diagCuenta")
public class DiagnosticoCuentaCtrll extends ControllerBase {

	@Autowired
	HikariDataSource dataSource;
	
	@Autowired
	private DiagnosticoCuentaServ diagCuentaServ;
	
	@Getter
	@Setter
	private String valor;
	
	@Getter
	private String codCuenta;
	
	@Getter
	private String nombre;
	
	@Getter
	private List<ObtieneTipoProceso> tipos;
	
	@Getter
	@Setter
	private Integer selectedTipo;
	
	@Getter
	@Setter
	private Date fechaInicio;
	
	@Getter
	@Setter
	private Date fechaFin;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			valor = null;
			codCuenta = null;
			nombre = null;
			tipos = null;
			selectedTipo = null;
			fechaInicio = null;
			fechaFin = null;
			
			obtieneTipoProceso();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void obtieneCodCuenta()  {
		try {
			boolean isCurp = ValidateUtil.isCURP(valor);
			ObtieneCodCuentaOut res = diagCuentaServ.getObtieneCodCuenta(valor, isCurp);
			codCuenta = res.getCodCuenta();
			nombre = res.getNombre();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", res.getMensaje()));
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void obtieneTipoProceso() {
		try {
			ObtieneTipoProcesoOut res = diagCuentaServ.getObtieneTipoProceso();
			tipos = res.getTipos();
			
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void generarArchivo() {
		try {
			GeneraArchivoIn parametros = new GeneraArchivoIn();
			parametros.setClave(selectedTipo);
			parametros.setFechaInicio(fechaInicio);
			parametros.setFechaFin(fechaFin);
			String msg = diagCuentaServ.generaArchivo(parametros );
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void desbloquearCuentas() {
		try {
			DesbloqueaCuentasIn parametros = new DesbloqueaCuentasIn();
			parametros.setClave(selectedTipo);
			parametros.setCodCuenta(codCuenta);
			parametros.setFechaInicio(fechaInicio);
			parametros.setUsuario(dataSource.getUsername());
			String msg = diagCuentaServ.desbloqueaCuentas(parametros);
			FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void bloquearCuentas() {
		try {
			DesbloqueaCuentasIn parametros = new DesbloqueaCuentasIn();
			parametros.setClave(selectedTipo);
			parametros.setCodCuenta(codCuenta);
			parametros.setFechaInicio(fechaInicio);
			parametros.setUsuario(dataSource.getUsername());
			String msg = diagCuentaServ.bloqueaCuentas(parametros);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	

}
