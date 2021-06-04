package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.faces.component.UIInput;


import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.DatosSolicitudOut;
import mx.axxib.aforedigitalgt.eml.FopagosListOut;
import mx.axxib.aforedigitalgt.eml.LlenaInfoOut;
import mx.axxib.aforedigitalgt.eml.VerChequeOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeListOut;
import mx.axxib.aforedigitalgt.eml.VerPagoChequeOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeListOut;
import mx.axxib.aforedigitalgt.eml.VerSolicitudChequeOut;
import mx.axxib.aforedigitalgt.serv.SolicitudMatrimonioDesempleoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "solicitudMatrimonioDesempleo")
@ELBeanName(value = "solicitudMatrimonioDesempleo")
public class SolicitudMatrimonioDesempleoCtrll extends ControllerBase{
	
	
	@Autowired
	private SolicitudMatrimonioDesempleoServ  solicitudMatrimonioDesempleoServ;
	
	@Getter
	@Setter
	private VerChequeOut verChequeOut;
	
	@Getter
	@Setter
	private Integer totalSolicitud;	
	
	@Getter
	@Setter
	private Integer totalPago;	
	
	@Getter
	private String mensajeSolicitud;
	
	@Getter
	private String mensajePago;
	
	@Getter
	@Setter
	private List<VerSolicitudChequeListOut> listSolicitudChequeOut;
	
	@Getter
	@Setter
	private VerSolicitudChequeListOut solicitudChequeOut;
	
	@Getter
	@Setter
	private VerSolicitudChequeListOut selectSolicitudChequeOut;
	
	@Getter
	@Setter
	private List <VerPagoChequeListOut> listPagoChequeOut;
	
	@Getter
	@Setter
	private VerPagoChequeListOut pagoChequeOut;
	
	@Getter
	@Setter
	private VerPagoChequeListOut selectPagoChequeOut;
	
	@Getter
	@Setter
	private String nss;
	

	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String apellidoPaterno;
	
	@Getter
	@Setter
	private String apellidoMaterno;
	
	@Getter
	@Setter
	private String cuenta;
	
	@Getter
	private String mensajeTablaPago;
	
	@Getter
	private String mensajeTablaSolicitud;
	
	@Getter
	private String mensajeTablaFopagos;
	
	@Getter
	private boolean verDatoCheque;

	@Getter
	private boolean mostrarFopagos;
	
//	@Getter
//	private List<FopagosListOut> fopagos;
	
	@Getter
	private FopagosListOut fopagos;
	
	@Getter
	@Setter
	private Double totalMontoRetiro;
	
	@Getter
	@Setter
	private Double totalMontoIsr;
	
	@Getter
	@Setter
	private Double totalNeto;
	
	@Getter
	@Setter
	private VerPagoChequeOut verPagoChequeOut;
	
	@Getter
	@Setter
	private VerSolicitudChequeOut res;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			nss = null;
			limpiar();
			init = false;
		}
	
	}
	
	private void limpiar() {
		mensajeTablaSolicitud=null;
		totalSolicitud=0;
		totalPago=0;
		//verDatoCheque=false;
		mostrarFopagos=false;
		listSolicitudChequeOut=null;
		listPagoChequeOut=null;
		nombre=null;
		cuenta=null;
	}
	
	public void consultar() {
		ProcessResult pr = new ProcessResult();
		try {
		limpiar();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por NSS");
		if (nss != null && !nss.equals("") ) {
			if(isNSS(pr)) {
			//if (ValidateUtil.isNSS(nss)) {
			//	if (nss.matches("[0-9]*")) {
			verChequeOut=solicitudMatrimonioDesempleoServ.getVerCheque(nss);
			
			nombre=verChequeOut.getNombre();
			cuenta=verChequeOut.getCuenta();
			if(verChequeOut.getNombre()!=null) {
			pr.setStatus("Consulta Exitosa Nss");
			verDatoCheque=true;
			consultarSolicitud(cuenta);
			}else {
				pr.setStatus("No se encontraron resultados");	
			}
			
			} 

		} else {
			UIInput input = (UIInput) findComponent("nss");
			input.setValid(false);
			pr.setStatus("NSS es requerido");
			
		}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
//	public void botonDatosCheque() {
//		consultarSolicitud(cuenta);
//		
//	}
	
	public void consultarSolicitud(String cuenta) {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Búsqueda por Cuenta Solicitud Matrimonio");
			//limpiar();
			//totalSolicitud=0;
			res=solicitudMatrimonioDesempleoServ.getVerSolicitudCheque(cuenta);
			
			
			
			if (res != null && res.getVerSolicitudChequeListOut() != null && res.getVerSolicitudChequeListOut().size() > 0) {
				listSolicitudChequeOut = res.getVerSolicitudChequeListOut();
				totalSolicitud = res.getVerSolicitudChequeListOut().size();
				System.out.println("TOTAL SOLICITUD"+totalSolicitud);
				if ( res.getVerSolicitudChequeListOut().size() > 0) {
					
					mensajeSolicitud=res.getMensaje();
					pr.setStatus("Consulta Exitosa Solicitud");//"Consulta Exitosa"
				}else {
					mensajeTablaSolicitud = "Sin información";
					pr.setStatus("No se encontraron resultados");
				
				}
			}else {
				
				pr.setStatus("No se encontraron resultados");
				mensajeTablaSolicitud = "Sin información";
			}
			consultarPago(cuenta);
		}catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public void consultarPago(String cuenta) {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Búsqueda por Cuenta Pagos Efectuados");
			//limpiar();
			//totalPago=0;
			verPagoChequeOut=solicitudMatrimonioDesempleoServ.getVerPagosCheque(cuenta);
			
			
			if (verPagoChequeOut != null && verPagoChequeOut.getVerPagoChequeListOut() != null && verPagoChequeOut.getVerPagoChequeListOut().size() > 0) {
				listPagoChequeOut = verPagoChequeOut.getVerPagoChequeListOut();
				totalPago = verPagoChequeOut.getVerPagoChequeListOut().size();
				mostrarFopagos=true;
				if ( verPagoChequeOut.getVerPagoChequeListOut().size() == 0) {
					mensajeTablaPago = "Sin información";
					pr.setStatus("No se encontraron resultados");
				}else {
				mensajePago=verPagoChequeOut.getMensaje();
				pr.setStatus("Consulta Exitosa Pago");//"Consulta Exitosa"
				}
			}else {
				pr.setStatus("No se encontraron resultados");
				mensajeTablaPago = "Sin información";
			}
		
		}catch (Exception e) {
			resultados.add(GenericException(e));
		}finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public void consultarFopagos() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Búsqueda por Fopagos");
			//limpiar();
			
			
			
			if(solicitudChequeOut.getNumeroSolicitud() != null) {
			System.out.println("valor de Solicitud"+solicitudChequeOut.getNumeroSolicitud());
			fopagos= solicitudMatrimonioDesempleoServ.getFopagos(Long.valueOf(solicitudChequeOut.getNumeroSolicitud()), nss, cuenta, nombre);
			System.out.println("VALORES DE FOPAGO:"+fopagos);
			System.out.println("MONTO BRUTO:"+fopagos.getPMonBruto_Re());
			System.out.println("MONTO ISR:"+fopagos.getPMontoIsr_Re());
			System.out.println("MONTO NETO:"+fopagos.getPMontoNeto_Re());
			totalMontoRetiro=fopagos.getPMonBruto_Re();
			totalMontoIsr=fopagos.getPMontoIsr_Re();
			totalNeto=fopagos.getPMontoNeto_Re();
			}
			//fopagos= solicitudMatrimonioDesempleoServ.getFopagos(pagoChequeOut.getIdentificarOperacion(), nss, cuenta, nombre);
			///System.out.println(""+pagoChequeOut.getIdentificarOperacion());
			if(fopagos != null) {
				if (fopagos.getOn_Estatus() == 1 ) {
					pr.setStatus("Consulta Exitosa Fopagos");
					
				}else {
					pr.setStatus("No se encontraron resultados");
					mensajeTablaFopagos = "Sin información";	
				}
			}else {
					pr.setStatus("No se encontraron resultados");
					mensajeTablaFopagos = "Sin información";
				}
				
			 
			
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
	
	public boolean isNSS(ProcessResult pr) {

		Pattern pattern = Pattern.compile("\\d{11}"); 
		if (!pattern.matcher(nss.toUpperCase()).matches()) {//if (!pattern.matcher(curp_o_nssIn.toUpperCase()).matches())
			UIInput radio = (UIInput) findComponent("nss");
			radio.setValid(false);
			pr.setStatus("Ingresar NSS Valido");
			return false;
		}
		return true;
	}
	
}
