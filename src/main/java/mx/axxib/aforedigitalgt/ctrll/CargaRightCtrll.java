package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.CargaRightIn;
import mx.axxib.aforedigitalgt.eml.CargaRightOut;
import mx.axxib.aforedigitalgt.serv.CargaRightServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "cargaRight")
@ELBeanName(value = "cargaRight")
public class CargaRightCtrll extends ControllerBase {

	@Autowired
	private CargaRightServ cargaService;

	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	@Setter
	private String archivo;
	
	@Getter
	private String mensaje;
	
	@Getter
	private String linea;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			fecha = null;
			archivo = null;
			mensaje = null;
			linea = null;
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}
	
	public void crucePrevio() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cruce previo");
			CargaRightIn parametros = new CargaRightIn();
			parametros.setFecha(fecha);
			parametros.setNombreArchivo(archivo);
			CargaRightOut res = cargaService.getCrucePrevio(parametros);
			if (res.getEstatus() == 1) {
				linea = res.getLinea();
				mensaje = res.getMensaje();
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
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public void cargar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cargar");
			CargaRightIn parametros = new CargaRightIn();
			parametros.setFecha(fecha);
			parametros.setNombreArchivo(archivo);
			CargaRightOut res = cargaService.getCarga(parametros);
			if (res.getEstatus() == 1) {
				linea = res.getLinea();
				mensaje = res.getMensaje();
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
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
}
