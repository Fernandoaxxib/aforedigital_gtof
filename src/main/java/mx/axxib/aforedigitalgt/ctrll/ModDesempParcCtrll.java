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
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.CancelarSolicitudIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidades;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesIn;
import mx.axxib.aforedigitalgt.eml.CargaParcialidadesOut;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudIn;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudOut;
import mx.axxib.aforedigitalgt.eml.ListaCancelacion;
import mx.axxib.aforedigitalgt.eml.ListaCancelacionOut;
import mx.axxib.aforedigitalgt.eml.Marcas;
import mx.axxib.aforedigitalgt.eml.MarcasIn;
import mx.axxib.aforedigitalgt.eml.MarcasOut;
import mx.axxib.aforedigitalgt.eml.RetparDeta;
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
	private List<CargaParcialidades> parcialidades;

	@Getter
	private List<RetparDeta> administrativos;

	@Getter
	private List<Marcas> marcas;

	@Getter
	private List<ListaCancelacion> listaCancelacion;

	@Getter
	private CargaParcialidades selectedParcialidad;

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

	public void setSelectedParcialidad(CargaParcialidades selected) {
		selectedParcialidad = selected;
		consultarAdministrativos();
	}

	public void consultarCancelaciones() throws Exception {
		ListaCancelacionOut res = modDesempParcServ.getListaCancelacion();
		if(res.getEstatus() == 1) {
			listaCancelacion = res.getDatos();
		} else {
			if(res.getEstatus() == 2) {
				GenerarErrorNegocio(res.getMensaje());
			}
		}
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
						
						if(datosSol.getEstatusSolicitud() == null || !datosSol.getEstatusSolicitud().toUpperCase().equals("CANCELADA")) {
							mostrarCancelacion = true;
						}

						CargaParcialidadesIn parametrosPar = new CargaParcialidadesIn();
						parametrosPar.setNoSolicitud(noSolicitud);
						parametrosPar.setCodEmpresa(codEmpresa);
						CargaParcialidadesOut res = modDesempParcServ.getCargaParcialidades(parametrosPar);
						if(res.getEstatus() == 1) {
							parcialidades = res.getParcialidades();
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
							
							if (parcialidades != null && parcialidades.size() > 0) {
								setSelectedParcialidad(parcialidades.get(0));
							} else {
								selectedParcialidad = null;
								administrativos = null;
								mensajeTabla = "Sin información";
							}
						} else {
							if(res.getEstatus() == 2) {
								GenerarErrorNegocio(res.getMensaje());
							} else if(res.getEstatus() == 0) {
								pr.setStatus(res.getMensaje());
								return;
							} 
						}
						
						MarcasIn parametrosMar = new MarcasIn();
						parametrosMar.setCodCuenta(codCuenta);
						MarcasOut resM = modDesempParcServ.getMarcas(parametrosMar);
						if(resM.getEstatus() == 1) {
							marcas = resM.getDatos();
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
							if (marcas == null || marcas.size() == 0) {
								mensajeTabla = "Sin información";
							}
						} else {
							if(resM.getEstatus() == 2) {
								GenerarErrorNegocio(resM.getMensaje());
							} else if(resM.getEstatus() == 0) {
								pr.setStatus(resM.getMensaje());
								return;
							} 
						}
						
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
				RetparDetaOut res = modDesempParcServ.getRetpar_Deta(parametrosAdm);
				if(res.getEstatus() == 1) {
					administrativos = res.getAdministrativos();
				} else {
					if(res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					}
				}
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
				BaseOut res = modDesempParcServ.cancelarSolicitud(parametros);
				if(res.getEstatus() == 1) {
					mostrarCancelacion = false;
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if(res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if(res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					} 
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
