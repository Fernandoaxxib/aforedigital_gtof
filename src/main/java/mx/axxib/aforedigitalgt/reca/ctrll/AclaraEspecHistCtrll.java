package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.List;
import javax.faces.component.UIInput;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.DatosHistoricoOut;
import mx.axxib.aforedigitalgt.reca.eml.InfoPunteoOut;
import mx.axxib.aforedigitalgt.reca.serv.AclaraEspecHistServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Aclaraciones Especiales - Histórico
//** Interventor Principal: JJSC
//** Fecha Creación: 06/01/2022
//** Última Modificación:

@Scope(value = "session")
@Component(value = "aclaraEspecHist")
@ELBeanName(value = "aclaraEspecHist")
public class AclaraEspecHistCtrll extends ControllerBase {

	@Autowired
	private AclaraEspecHistServ service;
	@Getter
	@Setter
	private List<DatosHistoricoOut> datosHistorico;
	@Getter
	@Setter
	private DatosHistoricoOut selectedDato;
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
	private String nss_aceptado;
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

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			init = false;
			nss = null;
			rfc = null;
			limpiar();
		}
	}

	public void limpiar() {
		nombre = "";
		datosHistorico = null;
		selectedDato = null;
		aceptado = null;
		nss_afil = null;
		rfc_afil = null;
		cod_estado = null;
		tip_solicitud = null;
		nombre_afil = null;
	}

	public void consultar() {
		limpiar();
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Consultar punteo histórico");
		pr.setFechaInicial(DateUtil.getNowDate());
		if (!rfc.isEmpty() || !nss.isEmpty()) {
			try {
				InfoPunteoOut resp = service.llenarDatosHistorico(nss, rfc, nombre);
				if (resp.getOn_Estatus() == 1) {
					datosHistorico = resp.getDatosHistorico();
					nss = resp.getIc_nss_afil();
					rfc = resp.getIc_rfc_afil();
					nombre = resp.getIc_nombre_afil();
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

			pr.setStatus("Por favor debe ingresar NSS o RFC");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void editar(DatosHistoricoOut dato) {
		aceptado = dato.getSTATUS();
		nss_aceptado = dato.getNSS();
	}

	public void guardar() {
		if (nss_aceptado!=null && aceptado != null) {
			ProcessResult pr = new ProcessResult();
			pr.setDescProceso("Actualizar estado");
			pr.setFechaInicial(DateUtil.getNowDate());
			try {
				InfoPunteoOut resp = service.actualizarHistorico(nss_aceptado, aceptado);
				if (resp.getOn_Estatus() == 1) {
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
		}
	}

	public void onRowSelect(SelectEvent<DatosHistoricoOut> event) {
		ProcessResult pr = new ProcessResult();
		pr.setDescProceso("Consultar datos de afiliación");
		pr.setFechaInicial(DateUtil.getNowDate());
		try {
			InfoPunteoOut resp = service.cargarInfoHist(event.getObject().getNSS());
			if (resp.getOn_Estatus() == null) {
				this.nss_afil = resp.getIc_nss_afil();
				this.rfc_afil = resp.getIc_rfc_afil();
				this.cod_estado = resp.getIc_cod_estado_afil();
				this.tip_solicitud = resp.getIc_tip_solicitud_afil();
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
	}
}