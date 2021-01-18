package mx.axxib.aforedigitalgt.ctrll;

import java.util.ArrayList;
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
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosIn;
import mx.axxib.aforedigitalgt.eml.ObtenerLoteTraspasosOut;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesIn;
import mx.axxib.aforedigitalgt.eml.ObtenerRgDevExcesOut;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtenerTipoRetiroOut;
import mx.axxib.aforedigitalgt.eml.ObtieneMonto;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoCorteIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoDevIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoRetiroIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTotalIn;
import mx.axxib.aforedigitalgt.eml.ObtieneMontoTraspasosIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorCTIn;
import mx.axxib.aforedigitalgt.eml.VentaTitulosMonitorIn;
import mx.axxib.aforedigitalgt.serv.VentaTitulosServ;

@Scope(value = "session")
@Component(value = "ventaTitulos")
@ELBeanName(value = "ventaTitulos")
public class VentaTitulosCtrll extends ControllerBase {

	@Autowired
	HikariDataSource dataSource;
	
	@Autowired
	private VentaTitulosServ ventaTitulosService;

	@Autowired
	private AforeMessage aforeMessage;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	private Date fechaActual;

	@Getter
	@Setter
	private String opcion;

	@Getter
	@Setter
	private String lote;

	@Getter
	private List<ObtenerTipoRetiroOut> tipoRetiros;

	@Getter
	@Setter
	private ObtenerTipoRetiroOut selectedTipoRetiro;

	@Getter
	private List<ObtenerLoteTraspasosOut> loteTraspasos;

	@Getter
	@Setter
	private ObtenerLoteTraspasosOut selectedLoteTraspaso;

	@Getter
	private List<ObtenerRgDevExcesOut> rgDevExces;

	@Getter
	@Setter
	private ObtenerRgDevExcesOut selectedrgDevExces;

	@Getter
	private List<ObtieneMonto> montos;
	
	@Getter
	private boolean mostrarVenta;
	
	@Getter
	private boolean mostrarVentaCT;
	
	@Getter
	@Setter
	private ObtieneMonto selectedSiefore;

	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			// Limpiar objetos
			opcion = null;
			selectedTipoRetiro = null;
			selectedLoteTraspaso = null;
			selectedrgDevExces = null;
			lote = null;
			montos = null;
			selectedSiefore = null;
			
			// Establecer valor inicial
			mostrarVenta = false;
			mostrarVentaCT = false;
			fechaActual = new Date();
			fechaInicial = fechaActual;
			fechaFinal = fechaActual;
			
			// Obtener catálogos necesarios
			obtenerTipoRetiro();
			obtenerLoteTraspaso();
			obtenerRgDevExces();
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	public void obtenerTipoRetiro() {
		try {
			ObtenerTipoRetiroIn parametros = new ObtenerTipoRetiroIn();
			parametros.setFecha(fechaInicial);
			tipoRetiros = ventaTitulosService.getObtenerTipoRetiro(parametros);
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void obtenerLoteTraspaso() {
		try {
			ObtenerLoteTraspasosIn parametros = new ObtenerLoteTraspasosIn();
			parametros.setFecha(fechaInicial);
			loteTraspasos = ventaTitulosService.getObtenerLoteTraspasos(parametros);
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void obtenerRgDevExces() {
		try {
			ObtenerRgDevExcesIn parametros = new ObtenerRgDevExcesIn();
			parametros.setFecha(fechaInicial);
			rgDevExces = ventaTitulosService.getObtenerRgDevExces(parametros);
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void buscarMontos() {
		try {
			selectedSiefore = null;
			mostrarVenta = false;
			mostrarVentaCT = false;
			montos = new ArrayList<ObtieneMonto>();
			switch (opcion) {
			case "T":
				ObtieneMontoTotalIn parametrosT = new ObtieneMontoTotalIn();
				parametrosT.setFechaFinal(fechaFinal);
				parametrosT.setFechaInicial(fechaInicial);
				montos = ventaTitulosService.getObtieneMontoTotal(parametrosT);
				break;
			case "R":
				ObtieneMontoRetiroIn parametrosR = new ObtieneMontoRetiroIn();
				parametrosR.setFechaFinal(fechaFinal);
				parametrosR.setFechaInicial(fechaInicial);
				if(selectedTipoRetiro != null) {
					parametrosR.setTipoRetiro(selectedTipoRetiro.getDescripcion());
					parametrosR.setTipoTransaccion(selectedTipoRetiro.getTipoTransaccion());
					parametrosR.setSubTipoTransaccion(selectedTipoRetiro.getSubTipoTransaccion());
					montos = ventaTitulosService.getObtieneMontoRetiro(parametrosR);
					mostrarVenta = true;
				} else {
					String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO, new Object[] {"Retiros"} );
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
				}
				break;
			case "A":
				ObtieneMontoTraspasosIn parametrosA = new ObtieneMontoTraspasosIn();
				parametrosA.setFecha(fechaInicial);
				if (selectedLoteTraspaso != null) {
					parametrosA.setLoteTraspaso(selectedLoteTraspaso.getIdLote());
					montos = ventaTitulosService.getObtieneMontoTraspasos(parametrosA);
					mostrarVenta = true;
				} else {
					String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO, new Object[] {"Traspasos Afore-Afore"} );
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
				}
				break;
			case "V":
				ObtieneMontoDevIn parametrosV = new ObtieneMontoDevIn();
				parametrosV.setFecha(fechaInicial);
				if (selectedrgDevExces != null) {
					parametrosV.setLoteRev(selectedrgDevExces.getIdLote());
					montos = ventaTitulosService.getObtieneMontoDev(parametrosV);
					mostrarVenta = true;
				} else {
					String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO, new Object[] {"Dev pago excs lote"} );
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
				}
				break;
			case "L":
				ObtieneMontoCorteIn parametrosL = new ObtieneMontoCorteIn();
				parametrosL.setFecha(fechaInicial);
				if (lote != null && lote.length() > 0) {
					parametrosL.setLoteCorte(lote);
					montos = ventaTitulosService.getObtieneMontoCorte(parametrosL);
					mostrarVentaCT = true;
				} else {
					String msg = aforeMessage.getMessage(ConstantesMsg.CAMPO_REQUERIDO, new Object[] {"Lote corte"} );
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
				}
				break;
			}

		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void ventaTitulosMonitor() {
		try {
			if( selectedSiefore != null) {
				VentaTitulosMonitorIn parametros = new VentaTitulosMonitorIn();
				parametros.setSeleccion(opcion);
				parametros.setIndCuotaRend("C"); // De acuerdo a BD se manda fijo C
				parametros.setFechaI(fechaInicial);
				parametros.setFecha(fechaFinal);
				parametros.setSiafore(selectedSiefore.getSiefore());
				parametros.setRetiroAforeMnd(selectedSiefore.getRetiroAforeMND());
				parametros.setValorCuota(selectedSiefore.getValCuota());
				parametros.setUsuario(dataSource.getUsername()); 
				switch (opcion) {
					case "R":
						parametros.setTransacMov(selectedTipoRetiro.getTipoTransaccion());
						parametros.setSubtransMov(selectedTipoRetiro.getSubTipoTransaccion()); 	
						break;
					case "A": 
						parametros.setIdLote(selectedLoteTraspaso.getIdLote());
						break;
					case "V": 
						parametros.setLoteRev(selectedrgDevExces.getIdLote());
						break;
				}
				ventaTitulosService.ventaTitulosMonitor(parametros );
				String msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				String msg = aforeMessage.getMessage(ConstantesMsg.SELECCION_REQUERIDA, null );
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public void ventaTitulosMonitorCT() {
		try {
			if( selectedSiefore != null && opcion.equals("L")) {
				VentaTitulosMonitorCTIn parametros = new VentaTitulosMonitorCTIn();
				parametros.setIndCuotaRend("C"); // De acuerdo a BD se manda fijo C
				parametros.setSiafore(selectedSiefore.getSiefore());
				parametros.setRetiroAforeMnd(selectedSiefore.getRetiroAforeMND());
				parametros.setValorCuota(selectedSiefore.getValCuota());
				parametros.setUsuario(dataSource.getUsername()); 
				parametros.setLoteCorte(lote);
			
				ventaTitulosService.ventaTitulosMonitorCT(parametros );
				String msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
			} else {
				String msg = aforeMessage.getMessage(ConstantesMsg.SELECCION_REQUERIDA, null );
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
			}
		} catch (Exception e) {
			GenericException(e);
		}
	}
}
