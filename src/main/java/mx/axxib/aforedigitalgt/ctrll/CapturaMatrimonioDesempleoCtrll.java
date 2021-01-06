package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;

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
import mx.axxib.aforedigitalgt.eml.CapturaMatrimonioTablaOut;
import mx.axxib.aforedigitalgt.eml.SiaforesCalculoPesos;
import mx.axxib.aforedigitalgt.eml.TiposReportes;
import mx.axxib.aforedigitalgt.serv.CapturaMatrimonioDesempleoService;
import mx.axxib.aforedigitalgt.serv.CargaMasivaService;

@Scope(value = "session")
@Component(value = "capturaMatrimonioDesempleo")
@ELBeanName(value = "capturaMatrimonioDesempleo")
public class CapturaMatrimonioDesempleoCtrll extends ControllerBase {
	
	@Autowired
	private CapturaMatrimonioDesempleoService capturaMatrimonioDesempleoService;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private CapturaMatrimonioTablaOut capturaMatrimonioTablaOut;
	
	@Getter
	@Setter
	private Integer nss;
	
	@Getter
	@Setter
	private Integer noSolicitud;
	 
	@Getter
	@Setter
	private String codEmpresa;
	
	@Getter
	@Setter
	private String tipoPrestacion;
	
	@Getter
	@Setter
	private Integer noResolucion;
	
	@Getter
	@Setter
	private String codCuenta;
	
	@Getter
	@Setter
	private String 	codProducto;
	
	@Getter
	@Setter
	private String codSistema;
	
	@Getter
	@Setter
	private String tipoTransaccion;
	
	@Getter
	@Setter
	private Date fechaSistema;
	
	
	@Getter
	@Setter
	private String tipPagoHab;
	
	@Getter
	@Setter
	private Integer tipoPago;
	
	@Getter
	@Setter
	private Integer cuenta;
	
	@Getter
	@Setter
	private String esperaProc;
	
	@Getter
	@Setter
	private String rechazaActualizar; 
	
	@Getter
	@Setter
	private String cuentaAlta;
	
	@Getter
	@Setter
	private String codTipoSaldo; 
	
	@Getter
	@Setter
	private String codInversion;
	
	
	@Getter
	@Setter
	private String curp;
	
	@Getter
	@Setter
	private String afiliado;
	
	@Getter
	@Setter
	private String rfc;
	
	@Getter
	@Setter
	private String tipoRetiro;
	
	@Getter
	@Setter
	private String nombreRetiro;
	 
	@Getter
	@Setter
	private String tipoPrestamo;
	
	@Getter
	@Setter
	private String nombrePrestamo;
	
	@Getter
	@Setter
	private String beneficiarios;
	
	@Getter
	@Setter
	private Integer porcentaje;
	
	@Getter
	@Setter
	private String telefono;
	
	@Getter
	@Setter
	private String notas;
	
	@Getter
	@Setter
	private String pagarEn;
	
	@Getter
	@Setter
	private String ordenPagoCuenta;
	
	@Getter
	@Setter
	private String pagoEn;
	
	@Getter
	@Setter
	private TiposReportes tiposReportes;
	
	@Getter
	@Setter
	private SiaforesCalculoPesos siaforesCalculoPesos;
	
	@Getter
	@Setter
	private String desempleo;
	
	@Getter
	@Setter
	private String fechaEmision;
	
	@Getter
	@Setter
	private String vigencia;
	
	@Getter
	@Setter
	private String autMatricula;
	
	@Getter
	@Setter
	private String salarioBase;
	
	@Getter
	@Setter
	private String fechaFolio;
	
	@Getter
	@Setter
	private String noEmpleado;
	
	@Getter
	@Setter
	private String rightFax;
	
	@Getter
	@Setter
	private String fechaInclusion;
	
	public void obtenerNSS() {
		try {
			capturaMatrimonioDesempleoService.obtenerNSS(nss, noSolicitud, codEmpresa, Integer.parseInt(tipoPrestacion), noResolucion);	
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void obtenerNoResolucion() {
		try {
			capturaMatrimonioDesempleoService.obtenerNoResolucion(codEmpresa, Integer.parseInt(codCuenta), tipoPrestacion, noResolucion);
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutarSiefore() {
		try {
			capturaMatrimonioDesempleoService.ejecutarSiefore(codCuenta, codEmpresa, codProducto, codSistema, tipoTransaccion, tipoPrestacion, fechaSistema);
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void obtenerCuenta() {
		try {
			capturaMatrimonioDesempleoService.obtenerCuenta(tipPagoHab, tipoPago, cuenta, curp, nss, esperaProc, rechazaActualizar, cuentaAlta);
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void obtenerSaldoInversion() {
		try {
			capturaMatrimonioDesempleoService.obtenerSaldoInversion(codEmpresa, codCuenta, codTipoSaldo, codInversion);
		}catch (Exception e) {
			GenericException(e);
		}
	}
}
