package mx.axxib.aforedigitalgt.ctrll;

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
import mx.axxib.aforedigitalgt.eml.CancelarSolicitudIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesOut;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudIn;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudOut;
import mx.axxib.aforedigitalgt.eml.ListaCancelacionOut;
import mx.axxib.aforedigitalgt.eml.MarcasIn;
import mx.axxib.aforedigitalgt.eml.MarcasOut;
import mx.axxib.aforedigitalgt.eml.RetparDetaIn;
import mx.axxib.aforedigitalgt.eml.RetparDetaOut;
import mx.axxib.aforedigitalgt.serv.ModDesempParcServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "modDesempParc")
@ELBeanName(value = "modDesempParc")
public class ModDesempParcCtrll extends ControllerBase {

	@Autowired
	private ModDesempParcServ modDesempParcServ;

	@Getter
	@Setter
	private String nss;

	@Getter
	private String noSolicitud;

	@Getter
	private String codEmpresa;

	@Getter
	private String codCuenta;

	@Getter
	private String noPago;

	@Getter
	private Integer claveProceso;

	@Getter
	private DatosSolicitudOut datosSol;

	@Getter
	private List<CargaParcialidadesOut> parcialidades;

	@Getter
	private List<RetparDetaOut> administrativos;

	@Getter
	private List<MarcasOut> marcas;

	@Getter
	private List<ListaCancelacionOut> listaCancelacion;

	@Getter
	private CargaParcialidadesOut selectedParcialidad;

	@Getter
	@Setter
	private Integer selectedClaveCancelacion;
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	private boolean mostrarCancelacion;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			nss = null;
			limpiarPantalla();
			try {
				consultarCancelaciones();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
			init = false;
		}
	}
	
	private void limpiarPantalla() {
		mostrarCancelacion = false;
		mensajeTabla = null;
		datosSol = new DatosSolicitudOut();
		parcialidades = null;
		administrativos = null;
		marcas = null;
		selectedParcialidad = null;
		selectedClaveCancelacion = null;
	}

	public void setSelectedParcialidad(CargaParcialidadesOut selected) {
		selectedParcialidad = selected;
		consultarAdministrativos();
	}

	public void consultarCancelaciones() throws Exception {
		listaCancelacion = modDesempParcServ.getListaCancelacion();
	}

	public void consultarSolicitud() {
		ProcessResult pr = new ProcessResult();
		try {
			limpiarPantalla();
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Búsqueda por NSS");
			if (nss != null && !nss.equals("") ) {
				if (ValidateUtil.isNSS(nss)) {
					DatosSolicitudIn parametros = new DatosSolicitudIn();
					parametros.setNss(nss);
					datosSol = modDesempParcServ.getDatosSolicitud(parametros);
					noSolicitud = datosSol.getNoSolicitud();
					codEmpresa = datosSol.getCodEmpresa();
					codCuenta = datosSol.getCodCuenta();
					
					if(codEmpresa != null && codEmpresa != null) {

						CargaParcialidadesIn parametrosPar = new CargaParcialidadesIn();
						parametrosPar.setNoSolicitud(noSolicitud);
						parametrosPar.setCodEmpresa(codEmpresa);
						parcialidades = modDesempParcServ.getCargaParcialidades(parametrosPar);
	
						if (parcialidades != null && parcialidades.size() > 0) {
							setSelectedParcialidad(parcialidades.get(0));
						} else {
							selectedParcialidad = null;
							administrativos = null;
							mensajeTabla = "Sin información";
						}
	
						MarcasIn parametrosMar = new MarcasIn();
						parametrosMar.setCodCuenta(codCuenta);
						marcas = modDesempParcServ.getMarcas(parametrosMar);
						if (marcas == null || marcas.size() == 0) {
							mensajeTabla = "Sin información";
						}
						if(datosSol.getEstatusSolicitud() == null || !datosSol.getEstatusSolicitud().toUpperCase().equals("CANCELADA")) {
							mostrarCancelacion = true;
						}
						pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					} else {
						mensajeTabla = "Sin información";
						datosSol.setFechaAccion(null);
						mostrarCancelacion = false;
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

	public void consultarAdministrativos() {
		try {
			if (selectedParcialidad != null) {
				RetparDetaIn parametrosAdm = new RetparDetaIn();
				parametrosAdm.setNoPago(selectedParcialidad.getPago());
				parametrosAdm.setNoSolicitud(noSolicitud);
				administrativos = modDesempParcServ.getRetpar_Deta(parametrosAdm);
				
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} 
	}

	public void cancelarSolicitud() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Cancelación individual");
			if (selectedClaveCancelacion != null) {
				CancelarSolicitudIn parametros = new CancelarSolicitudIn();
				parametros.setNss(nss);
				parametros.setNoSolicitud(noSolicitud);
				parametros.setCveProcesoOpe(selectedClaveCancelacion);
				String msg = modDesempParcServ.cancelarSolicitud(parametros);
				if (msg.trim().toUpperCase().equals("OK")) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					mostrarCancelacion = false;
				} else {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
					pr.setStatus(msg);
				}
			} else {
				UIInput input = (UIInput) findComponent("tipoCancelacion");
				input.setValid(false);
				pr.setStatus("Tipo cancelación es requerido");
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

}
