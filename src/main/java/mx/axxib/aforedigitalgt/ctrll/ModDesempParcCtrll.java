package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
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

@Scope(value = "session")
@Component(value = "modDesempParc")
@ELBeanName(value = "modDesempParc")
public class ModDesempParcCtrll extends ControllerBase {

	@Autowired
	private ModDesempParcServ modDesempParcServ;

	@Autowired
	private AforeMessage aforeMessage;

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

	@PostConstruct
	public void init() {
		datosSol = new DatosSolicitudOut();
	}

	public void setSelectedParcialidad(CargaParcialidadesOut selected) {
		selectedParcialidad = selected;
		consultarAdministrativos();
	}

	public void consultarSolicitud() {
		try {
			listaCancelacion = modDesempParcServ.getListaCancelacion();

			DatosSolicitudIn parametros = new DatosSolicitudIn();
			parametros.setNss(nss);
			datosSol = modDesempParcServ.getDatosSolicitud(parametros);
			noSolicitud = datosSol.getNoSolicitud();
			codEmpresa = datosSol.getCodEmpresa();
			codCuenta = datosSol.getCodCuenta();

			CargaParcialidadesIn parametrosPar = new CargaParcialidadesIn();
			parametrosPar.setNoSolicitud(noSolicitud);
			parametrosPar.setCodEmpresa(codEmpresa);
			parcialidades = modDesempParcServ.getCargaParcialidades(parametrosPar);

			if (parcialidades != null && parcialidades.size() > 0) {
				setSelectedParcialidad(parcialidades.get(0));
			} else {
				selectedParcialidad = null;
				administrativos = null;
			}

			MarcasIn parametrosMar = new MarcasIn();
			parametrosMar.setCodCuenta(codCuenta);
			marcas = modDesempParcServ.getMarcas(parametrosMar);
		} catch (Exception e) {
			GenericException(e);
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
			GenericException(e);
		}
	}

	public void cancelarSolicitud() {
		try {
			if (selectedClaveCancelacion != null) {
				CancelarSolicitudIn parametros = new CancelarSolicitudIn();
				parametros.setNss(nss);
				parametros.setNoSolicitud(noSolicitud);
				parametros.setCveProcesoOpe(selectedClaveCancelacion);
				System.out.println("cancelado con clave: " + selectedClaveCancelacion);
				String msg = "OK"; // Todo: quitar para probar la cancelación
//			String msg = modDesempParcServ.cancelarSolicitud(parametros);
				if (msg.trim().toUpperCase().equals("OK")) {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
				} else {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
				}
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

}
