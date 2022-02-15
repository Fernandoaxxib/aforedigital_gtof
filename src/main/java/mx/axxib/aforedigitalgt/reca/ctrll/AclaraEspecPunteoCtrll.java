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
import mx.axxib.aforedigitalgt.reca.eml.DatosPunteoOut;
import mx.axxib.aforedigitalgt.reca.eml.InfoPunteoOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;
import mx.axxib.aforedigitalgt.reca.serv.AclaraEspecPunteoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aclaraciones Especiales - Punteo
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:

@Scope(value = "session")
@Component(value = "aclaraEspecPunteo")
@ELBeanName(value = "aclaraEspecPunteo")
public class AclaraEspecPunteoCtrll extends ControllerBase {

	@Autowired
	private AclaraEspecPunteoServ service;

	@Getter
	private List<DatosPunteoOut> datosPunteo;

	@Getter
	@Setter
	private List<DatosPunteoOut> selectedDato;

	@Getter
	@Setter
	private String nss;

	@Getter
	@Setter
	private String rfc;

	@Getter
	@Setter
	private String nombre;

	@Getter
	@Setter
	private String aceptado;

	@Getter
	@Setter
	private String nss_afil;

	@Getter
	@Setter
	private String rfc_afil;

	@Getter
	@Setter
	private String cod_estado;

	@Getter
	@Setter
	private String tip_solicitud;

	@Getter
	@Setter
	private String nombre_afil;

	@Getter
	@Setter
	private String casosActual;

	@Getter
	@Setter
	private String casosRestantes;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			init = false;
			datosPunteo = null;
			selectedDato = null;
			nss = null;
			rfc = null;
			nombre = null;
			limpiar();
		}
	}

	public void limpiar() {				
		nss = null;
		rfc = null;
		nombre = null;
		aceptado = null;
		nss_afil = null;
		rfc_afil = null;
		cod_estado = null;		
		nombre_afil = null;
		datosPunteo = null;
		casosActual = null;
		selectedDato = null;
		tip_solicitud = null;
		casosRestantes = null;
	}

	public void consultar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Consultar datos de punteo");
		pr.setFechaInicial(DateUtil.getNowDate());
		if (!rfc.isEmpty() || !nss.isEmpty() || !nombre.isEmpty()) {
			try {
				InfoPunteoOut resp = service.llenarDatosPunteo(nss, rfc, nombre);
				if (resp.getOn_Estatus() == null) {
					datosPunteo = resp.getDatosPunteo();
					casosActual = resp.getIc_Casos();
					casosRestantes = resp.getIc_Restantes();
					nss = resp.getIc_nss_afil();
					rfc = resp.getIc_rfc_afil();
					nombre = resp.getIc_nombre_afil();
					nss_afil = resp.getIc_nss_afil();
					rfc_afil = resp.getIc_rfc_afil();
					nombre_afil = resp.getIc_nombre_afil();
					cod_estado = resp.getIc_cod_estado_afil();
					tip_solicitud = resp.getIc_tip_solicitud_afil();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
		} else {
			UIInput nss = (UIInput) findComponent("nss");
			nss.setValid(false);
			UIInput rfc = (UIInput) findComponent("rfc");
			rfc.setValid(false);
			UIInput nombre = (UIInput) findComponent("nombre");
			nombre.setValid(false);
			pr.setStatus("Por favor debe ingresar NSS, RFC o nombre");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void actualizar() {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Actualizar estado");
		pr.setFechaInicial(DateUtil.getNowDate());
		try {
			if (nss.isEmpty()) {
				UIInput nss = (UIInput) findComponent("nss");
				nss.setValid(false);
				pr.setStatus("Por favor debe ingresar NSS");
				pr.setFechaFinal(DateUtil.getNowDate());

			} else if (aceptado == null) {
				UIInput nss = (UIInput) findComponent("combAceptado");
				nss.setValid(false);
				pr.setStatus("Por favor seleccione el estado");
				pr.setFechaFinal(DateUtil.getNowDate());

			} else {
				RespuestaOut resp = service.actualizarPunteo(nss, aceptado);
				if (resp.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					} else if (resp.getOn_Estatus() == 0) {
						pr.setStatus(resp.getOc_Mensaje());
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
