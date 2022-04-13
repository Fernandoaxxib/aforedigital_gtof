package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.ArrayList;
import java.util.List;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.Banco;
import mx.axxib.aforedigitalgt.reca.eml.BeneficiarioFis;
import mx.axxib.aforedigitalgt.reca.eml.CompaniaCelular;
import mx.axxib.aforedigitalgt.reca.eml.DatosInicialesFocapdom;
import mx.axxib.aforedigitalgt.reca.eml.FocapdomOut;
import mx.axxib.aforedigitalgt.reca.eml.OrigenAporta;
import mx.axxib.aforedigitalgt.reca.eml.OrigenCaptura;
import mx.axxib.aforedigitalgt.reca.eml.OrigenRecursos;
import mx.axxib.aforedigitalgt.reca.eml.Periodicidad;
import mx.axxib.aforedigitalgt.reca.eml.TipoCuenta;
import mx.axxib.aforedigitalgt.reca.eml.TipoIdentificacion;
import mx.axxib.aforedigitalgt.reca.eml.TipoIncremento;
import mx.axxib.aforedigitalgt.reca.eml.TipoMovimiento;
import mx.axxib.aforedigitalgt.reca.eml.TipoPeriodo;
import mx.axxib.aforedigitalgt.reca.eml.TipoPersona;
import mx.axxib.aforedigitalgt.reca.serv.FocapdomCapturaServ;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de FOCAPDOM - Captura
//** Interventor Principal: JJSC
//** Fecha Creación: 24/03/2022
//** Última Modificación:

@Scope(value = "session")
@Component(value = "focapdomCaptura")
@ELBeanName(value = "focapdomCaptura")
public class FocapdomCapturaCtrll extends ControllerBase {

	@Autowired
	private FocapdomCapturaServ service;

	@Getter
	@Setter
	private DatosInicialesFocapdom datosIniciales;
	@Getter
	@Setter
	private List<OrigenAporta> listOrigen;
	@Getter
	@Setter
	private OrigenAporta selectedOrigen;
	@Getter
	@Setter
	private Periodicidad selectedPeriodicidad;
	@Getter
	@Setter
	private List<Periodicidad> listPeriodicidad;
	@Getter
	@Setter
	private BeneficiarioFis selectedBeneficiario;
	@Getter
	@Setter
	private List<BeneficiarioFis> listBeneficiario;
	@Getter
	@Setter
	private OrigenCaptura selectedOrigenCaptura;
	@Getter
	@Setter
	private List<OrigenCaptura> listOrigenCaptura;
	@Getter
	@Setter
	private OrigenRecursos selectedOrigenRecursos;
	@Getter
	@Setter
	private List<OrigenRecursos> listOrigenRecursos;
	@Getter
	@Setter
	private TipoMovimiento selectedTipoMovimiento;
	@Getter
	@Setter
	private List<TipoMovimiento> listTipoMovimiento;
	@Getter
	@Setter
	private String etiquetaValorIncremento;
	@Getter
	@Setter
	private TipoIncremento selectedTipoIncremento;
	@Getter
	@Setter
	private List<TipoIncremento> listTipoIncremento;
	@Getter
	@Setter
	private TipoPeriodo selectedTipoPeriodo;
	@Getter
	@Setter
	private List<TipoPeriodo> listTipoPeriodo;
	@Getter
	@Setter
	private CompaniaCelular selectedCompaniaCelular;
	@Getter
	@Setter
	private List<CompaniaCelular> listCompaniaCelular;
	@Getter
	@Setter
	private TipoPersona selectedTipoPersona;
	@Getter
	@Setter
	private List<TipoPersona> listTipoPersona;
	@Getter
	@Setter
	private TipoIdentificacion selectedTipoIdentificacion;
	@Getter
	@Setter
	private List<TipoIdentificacion> listTipoIdentificacion;
	@Getter
	@Setter
	private Banco selectedBanco;
	@Getter
	@Setter
	private List<Banco> listBancos;
	@Getter
	@Setter
	private TipoCuenta selectedTipoCuenta;
	@Getter
	@Setter
	private List<TipoCuenta> listTipoCuenta;
	

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				init = false;
				etiquetaValorIncremento = "Valor Inc.";
				selectedTipoPeriodo = null;
				listTipoPeriodo = null;				
				selectedTipoCuenta = new TipoCuenta();
				listTipoCuenta = new ArrayList<>();
				selectedBanco = new Banco();
				listBancos = new ArrayList<>();
				selectedTipoIdentificacion = new TipoIdentificacion();
				listTipoIdentificacion = new ArrayList<>();
				selectedTipoPersona = new TipoPersona();
				listTipoPersona = new ArrayList<>();
				selectedCompaniaCelular = new CompaniaCelular();
				listCompaniaCelular = new ArrayList<>();
				selectedTipoIncremento = new TipoIncremento();
				listTipoIncremento = new ArrayList<>();
				listPeriodicidad = new ArrayList<Periodicidad>();
				selectedPeriodicidad = new Periodicidad();
				selectedOrigen = new OrigenAporta();
				listOrigen = new ArrayList<>();
				selectedBeneficiario = new BeneficiarioFis();
				listBeneficiario = new ArrayList<BeneficiarioFis>();
				selectedOrigenCaptura = new OrigenCaptura();
				listOrigenCaptura = new ArrayList<>();
				selectedOrigenRecursos = null;
				listOrigenRecursos = null;
				datosIniciales = null;
				selectedTipoMovimiento = null;
				listTipoMovimiento = null;
				getDatosOrigenAporta();
				getBeneficiario();
				getOrigenCaptura();
				getOrigenRecursos();
				getTipoMovimiento();
				getTipoPeriodo();
				getTipoPersona();
				getBancos();
				getTipoCuenta();			
				getTipoIdentificacion();
				getCompaniaCelular();
				initTipoIncremento();
				initDatosIniciales();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void initTipoIncremento() {
		listTipoIncremento.add(new TipoIncremento(0, "NINGUNO"));
		listTipoIncremento.add(new TipoIncremento(1, "MONTO"));
		listTipoIncremento.add(new TipoIncremento(0, "PORCENTAJE"));
	}

	public void initDatosIniciales() throws Exception {
		FocapdomOut resp = service.getDatosIniciales();
		datosIniciales = resp.getDatosIniciales();
		selectedOrigen.setCve(datosIniciales.getCve_orig_aporta());
		selectedOrigen.setValor(datosIniciales.getDummy_origen_aporta());
		getPeriodicidad(Integer.toString(datosIniciales.getCve_orig_aporta()));
		selectedPeriodicidad.setCve(Integer.valueOf(datosIniciales.getCve_tipo_periodo()));
		selectedPeriodicidad.setValor(datosIniciales.getDummy_periodo());
		selectedBeneficiario.setCve(datosIniciales.getCve_benef_fiscal());
		selectedBeneficiario.setDescripcion(datosIniciales.getDummy_beneficiario_fiscal());
		selectedOrigenCaptura.setCve(Integer.valueOf(datosIniciales.getCve_orig_captura()));
		selectedOrigenCaptura.setDescripcion("Oficinas");
		selectedTipoIncremento.setCve(0);
		selectedTipoIncremento.setDescripcion(datosIniciales.getTipo_incremento());
		selectedCompaniaCelular.setCve(1);
		selectedCompaniaCelular.setCompania(datosIniciales.getDummy_comp_celular());
		selectedTipoPersona.setCve(datosIniciales.getCve_tipo_persona());
		selectedTipoPersona.setDescripcion(datosIniciales.getDummy_tipo_persona());
		selectedTipoIdentificacion.setCve(datosIniciales.getCve_tipo_ident());
		selectedTipoIdentificacion.setDescripcion(datosIniciales.getDummy_tipo_identificacion());
		selectedBanco.setCve(Integer.valueOf(datosIniciales.getCve_banco()));
		selectedBanco.setNombre(datosIniciales.getDummy_banco());
		selectedTipoCuenta.setCve(datosIniciales.getCve_tipo_cta());
		selectedTipoCuenta.setDescripcion(datosIniciales.getDummy_tipo_cuenta());
	}

	public void getBeneficiario() throws Exception {
		FocapdomOut resp = service.getBeneficiario();
		listBeneficiario = resp.getListBenefeciario();
	}

	public void getTipoPersona() throws Exception {
		FocapdomOut resp = service.getTipoPersona();
		listTipoPersona = resp.getListTipoPersona();
	}

	public void getDatosOrigenAporta() throws Exception {
		FocapdomOut resp = service.getDatosOrigAporta();
		listOrigen = resp.getListOrigen();
	}

	public void getTipoCuenta() throws Exception {
		FocapdomOut resp = service.getTipoCuenta();
		listTipoCuenta = resp.getListTipoCuenta();
	}

	public void getPeriodicidad(String cve) throws Exception {
		FocapdomOut resp = service.getPeriodicidad(cve);
		listPeriodicidad = resp.getListPeriodicidad();
	}

	public void getOrigenCaptura() throws Exception {
		FocapdomOut resp = service.getOrigenCaptura();
		listOrigenCaptura = resp.getListOrigenCaptura();
	}

	public void getOrigenRecursos() throws Exception {
		FocapdomOut resp = service.getOrigenRecursos();
		listOrigenRecursos = resp.getListOrigenRecursos();
	}

	public void getBancos() throws Exception {
		FocapdomOut resp = service.getBancos();
		listBancos = resp.getListBancos();
	}

	public void getTipoMovimiento() throws Exception {
		FocapdomOut resp = service.getTipoMovimiento();
		listTipoMovimiento = resp.getListTipoMovimiento();
		if (listTipoMovimiento != null) {
			listTipoMovimiento.forEach(x -> {
				if (x.getCve() == null) {
					x.setCve("");
				}
			});
		}
	}

	public void getTipoPeriodo() throws Exception {
		FocapdomOut resp = service.getTipoPeriodo();
		listTipoPeriodo = resp.getListTipoPeriodo();
	}

	public void getTipoIdentificacion() throws Exception {
		FocapdomOut resp = service.getTipoIdentificacion();
		listTipoIdentificacion = resp.getListTipoIdentificacion();
	}

	public void getCompaniaCelular() throws Exception {
		FocapdomOut resp = service.getCompaniaCelular();
		listCompaniaCelular = resp.getListCompaniaCelular();
	}


	public void selectedCompaniaCelular() {
		if (selectedCompaniaCelular != null) {
			datosIniciales.setDummy_comp_celular(selectedCompaniaCelular.getCompania());
		}
	}

	public void selectedTipoPersona() {
		if (selectedTipoPersona != null) {
			datosIniciales.setDummy_tipo_persona(selectedTipoPersona.getDescripcion());
		}
	}

	public void selectedTipoCuenta() {
		if (selectedTipoCuenta != null) {
			datosIniciales.setDummy_tipo_cuenta(selectedTipoCuenta.getDescripcion());
		}
	}

	public void selectedOrigenRecursos() {
		if (selectedOrigenRecursos != null) {
			datosIniciales.setDescripcion_orig_recursos(selectedOrigenRecursos.getDescripcion());
		}
	}

	public void selectedTipoMovimiento() {
		if (selectedTipoMovimiento != null) {
			datosIniciales.setDummy_tip_mov(selectedTipoMovimiento.getDescripcion());
		}
	}

	public void selectedPeriodicidad() {
		if (selectedPeriodicidad != null) {
			datosIniciales.setDummy_periodo(selectedPeriodicidad.getValor());
		}
	}

	public void selectedTipoIdentificacion() {
		if (selectedTipoIdentificacion != null) {
			datosIniciales.setDummy_tipo_identificacion(selectedTipoIdentificacion.getDescripcion());
		}
	}

	public void selectedBeneficiario() {
		if (selectedBeneficiario != null) {
			datosIniciales.setDummy_beneficiario_fiscal(selectedBeneficiario.getDescripcion());
		}
	}

	public void selectedOrigenCaptura() {
		if (selectedOrigenCaptura != null) {
			datosIniciales.setDummy_orig_captura(selectedOrigenCaptura.getDescripcion());
		}
	}

	public void selectedBanco() {
		if (selectedBanco != null) {
			datosIniciales.setDummy_banco(selectedBanco.getNombre());
		}
	}

	public void selectedOrigen() throws Exception {
		if (selectedOrigen != null) {
			datosIniciales.setDummy_origen_aporta(selectedOrigen.getValor());
			getPeriodicidad(Integer.toString(selectedOrigen.getCve()));
		}
	}

	public void selectedTipoIncremento() {
		if (selectedTipoIncremento != null) {
			switch (selectedTipoIncremento.getDescripcion()) {
			case "NINGUNO": {
				this.etiquetaValorIncremento = "Valor Inc.";
				break;
			}
			case "MONTO": {
				this.etiquetaValorIncremento = "Monto Inc.";
				break;
			}
			case "PORCENTAJE": {
				this.etiquetaValorIncremento = "Porcentaje Inc.";
				break;
			}
			}
		}
	}

	public void selectedTipoPeriodo() {
		if (selectedTipoPeriodo != null) {
			datosIniciales.setDummy_incrementalidad_periodicidad(selectedTipoPeriodo.getValor());
		}
	}

}