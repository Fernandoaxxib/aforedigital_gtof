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
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;
import mx.axxib.aforedigitalgt.reca.serv.AclaraEspecProcesosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aclaraciones Especiales - Procesos
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:

@Scope(value = "session")
@Component(value = "aclaraEspecProcesos")
@ELBeanName(value = "aclaraEspecProcesos")
public class AclaraEspecProcesosCtrll extends ControllerBase {

	@Autowired
	private AclaraEspecProcesosServ service;

	@Getter
	private String ruta;

	@Getter
	@Setter
	private String archivo;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			archivo = null;
			ruta = "/iprod/PROCESAR/RECEPCION/AFORE/RECAUDACION";
			init = false;
		}
	}

	public void cargarArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Carga de archivo");
		if (isNombreValid(pr)) {
			try {
				RespuestaOut resp = service.cargarArchivo(archivo, ruta);
				if (resp.getOn_Estatus() == 1) {
					String msj = "";
					msj = msj.concat(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null)).concat(" - ")
							.concat(resp.getOc_Mensaje());
					pr.setStatus(msj);
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getOc_Mensaje());
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

	public boolean isNombreValid(ProcessResult pr) {
		if (archivo == null || archivo.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreArchivo");
			radio.setValid(false);
			pr.setStatus("Se requiere que ingrese el nombre del archivo");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		}
		return true;
	}

	public void ejecutarPunteoAut() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Ejecutar punteo automático");
		try {
			RespuestaOut resp = service.generarPunteoAutomatico();
			if (resp.getOn_Estatus() == 1) {
				String msj = "";
				msj = msj.concat(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null)).concat(" - ")
						.concat(resp.getOc_Mensaje());
				pr.setStatus(msj);
			} else {
				if (resp.getOn_Estatus() == 2) {
					GenerarErrorNegocio(resp.getOc_Mensaje());
				} else if (resp.getOn_Estatus() == 0) {
					pr.setStatus(resp.getOc_Mensaje());
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
