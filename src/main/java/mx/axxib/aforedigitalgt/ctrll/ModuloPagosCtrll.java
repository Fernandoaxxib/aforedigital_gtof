package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.serv.ModPagosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "moduloPagos")
@ELBeanName(value = "moduloPagos")
public class ModuloPagosCtrll extends ControllerBase {

	@Autowired
	private ModPagosServ service;

	@Getter
	@Setter
	private String tiposPagos;
	@Getter
	@Setter
	private String institucion;
	@Getter
	@Setter
	private String pagosAfiliados;
	@Getter
	@Setter
	private String procesosRetiros;
	@Getter
	@Setter
	private Date fechaProceso;
	@Getter
	@Setter
	private Date fechaProcesoPagos;
	@Getter
	private String msj;
	@Getter
	private String ruta;
	@Getter
	private Date fecActual;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			fechaProceso = DateUtil.getNowDate();
			fechaProcesoPagos = DateUtil.getNowDate();
			refresh();
			fecActual=DateUtil.getNowDate();
			msj=null;
			ruta=null;
		}
	}

	public void fechaProceso() {
	}

	public void refresh() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Proceso inicial");
		try {
			if (fechaProceso != null && fechaProcesoPagos != null) {
				EjecucionResult res = service.refresh("OK", fechaProceso, fechaProcesoPagos);
				if (res.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getOcMensaje());
					} else if (res.getOn_Estatus() == 0) {
						pr.setStatus(res.getOcMensaje());
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

	public void generarFondos() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Genera interface fondos");
		if (fechaProceso != null && pagosAfiliados != null) {
			try {
				EjecucionResult res = service.generarFondos(fechaProceso, procesosRetiros, tiposPagos, pagosAfiliados,
						institucion);
				if (res.getOn_Estatus() == 1) {
					msj=res.getOcMensaje();
					ruta="Ruta= /iprod/PROCESAR/RECEPCION/AFORE/RETIROS/";					
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getOcMensaje());
					} else if (res.getOn_Estatus() == 0) {
						msj=res.getOcMensaje();
						pr.setStatus(res.getOcMensaje());
					}
				}
				PrimeFaces.current().executeScript("PF('dlg1_2').show()");
			} catch (Exception e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}
	}

	public void generarPagos() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Genera interface pagos");
		if (procesosRetiros != null && tiposPagos != null && institucion != null && fechaProcesoPagos != null) {
			try {
				EjecucionResult res = service.generarPagos(fechaProcesoPagos, procesosRetiros, institucion, tiposPagos);
				if (res.getOn_Estatus() == 1) {
					msj=res.getOcMensaje();					
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getOcMensaje());
					} else if (res.getOn_Estatus() == 0) {
						msj=res.getOcMensaje();
						pr.setStatus(res.getOcMensaje());
					}
				}
				PrimeFaces.current().executeScript("PF('dlg1_2').show()");
			} catch (Exception e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}
	}
}
