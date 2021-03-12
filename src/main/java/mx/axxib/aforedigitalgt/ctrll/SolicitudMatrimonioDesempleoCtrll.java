package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.StringTokenizer;

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
	private String mensajeTabla;
	
	@Getter
	private boolean verDatoCheque;

	@Getter
	private boolean mostrarFopagos;
	
	@Getter
	private List<FopagosListOut> fopagos;
	
	@Getter
	private Integer totalMontoRetiro;
	
	@Getter
	private Integer totalMontoIsr;
	
	@Getter
	private Integer totalNeto;
	
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
		mensajeTabla=null;
		totalSolicitud=null;
		totalPago=null;
		verDatoCheque=false;
		mostrarFopagos=false;
	}
	
	public void consultar() {
		ProcessResult pr = new ProcessResult();
		try {System.out.println("VALOR DE NNS:"+nss);
		limpiar();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por NSS");
		if (nss != null && !nss.equals("") ) {
			if (ValidateUtil.isNSS(nss)) {
			verChequeOut=solicitudMatrimonioDesempleoServ.getVerCheque(nss);
			System.out.println("VALOR DE verChequeOut: "+verChequeOut);
			nombre=verChequeOut.getNombre();
			cuenta=verChequeOut.getCuenta();
			pr.setStatus("Consulta Exitosa");
			verDatoCheque=true;
//			consultarPago(verChequeOut.getCuenta());
//			System.out.println("VALOR DE consultarPago");
//			
//			consultarSolicitud(verChequeOut.getCuenta());
//			System.out.println("VALOR DE consultarSolicitud");
			
			
//			StringTokenizer tokens=new StringTokenizer(verChequeOut.getNombre()," ");
//		        int nDatos=tokens.countTokens();
//		        String [] datos=new String[nDatos];
//		        int i=0;
//		        while(tokens.hasMoreTokens()){
//		            String str=tokens.nextToken();
//		           // datos[i]=Double.valueOf(str).doubleValue();
//		            datos[i]=str;
//		            System.out.println(datos[i]);
//		            i++;
//		        }
//		        
//		        if(datos.length == 4) {
//		        String nombre1= datos[0];
//		        String nombre2= datos[1];
//		        nombre=nombre1+" "+nombre2;
//		       
//		        apellidoPaterno=datos[2];
//		        apellidoMaterno=datos[3];
//		        System.out.println("Valor concatenado:"+nombre);
//		        System.out.println("Valor concatenado:"+apellidoPaterno);
//		        System.out.println("Valor concatenado:"+apellidoMaterno);
//		        }else {
//		        	nombre= datos[0];
//			       		       
//			        apellidoPaterno=datos[1];
//			        apellidoMaterno=datos[2];
//			        System.out.println("Valor concatenado nombre1:"+nombre);
//			        System.out.println("Valor concatenado apellidoPaterno:"+apellidoPaterno);
//			        System.out.println("Valor concatenado apellidoMaterno:"+apellidoMaterno);	
//		        }
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
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public void botonDatosCheque() {
		consultarSolicitud(cuenta);
		
	}
	
	public void consultarSolicitud(String cuenta) {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Búsqueda por Cuenta Solicitud Matrimonio");
			limpiar();
			VerSolicitudChequeOut res=solicitudMatrimonioDesempleoServ.getVerSolicitudCheque(cuenta);
			consultarPago(cuenta);
			System.out.println("VALOR DE consultarSolicitud: "+res.getVerSolicitudChequeListOut().size());
			System.out.println("VALOR DE NNS:"+res.getVerSolicitudChequeListOut());
			if (res != null && res.getVerSolicitudChequeListOut() != null && res.getVerSolicitudChequeListOut().size() > 0) {
				listSolicitudChequeOut = res.getVerSolicitudChequeListOut();
				totalSolicitud = res.getVerSolicitudChequeListOut().size();
				
				if ( res.getVerSolicitudChequeListOut().size() == 0) {
					mensajeTabla = "Sin información";
					pr.setStatus("No se encontraron resultados");
				}else {
				System.out.println("VALOR DE totalSolicitud:"+totalSolicitud);
				mensajeSolicitud=res.getMensaje();
				pr.setStatus(mensajeSolicitud);//"Consulta Exitosa"
				}
			}else {
				
				pr.setStatus("No se encontraron resultados");
				mensajeTabla = "Sin información";
			}
		
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
			limpiar();
			VerPagoChequeOut res=solicitudMatrimonioDesempleoServ.getVerPagosCheque(cuenta);
			System.out.println("VALOR DE consultarPago: "+res.getVerPagoChequeListOut().size());
			System.out.println("VALOR DE NNS:"+res.getVerPagoChequeListOut());
			if (res != null && res.getVerPagoChequeListOut() != null && res.getVerPagoChequeListOut().size() > 0) {
				listPagoChequeOut = res.getVerPagoChequeListOut();
				totalPago = res.getVerPagoChequeListOut().size();
				mostrarFopagos=true;
				if ( res.getVerPagoChequeListOut().size() == 0) {
					mensajeTabla = "Sin información";
					pr.setStatus("No se encontraron resultados");
				}else {
				mensajePago=res.getMensaje();
				pr.setStatus(mensajePago);//"Consulta Exitosa"
				}
			}else {
				pr.setStatus("No se encontraron resultados");
				mensajeTabla = "Sin información";
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
			pr.setDescProceso("Búsqueda por fecha");
			limpiar();
			
			
			//System.out.println("PARAMETROS DE FOPAGOS:"+pagoChequeOut.getIdentificarOperacion()+"---"+ nss+"---"+ cuenta+"---"+ nombre);
			fopagos= solicitudMatrimonioDesempleoServ.getFopagos(12, nss, cuenta, nombre);
//			for(FopagosListOut iterar:fopagos) {
//			totalMontoRetiro  += iterar.getPMontoNeto_Re();
//			}
			System.out.println("VALOR DE FOPAGOS On_Estatus:"+fopagos);
			///System.out.println(""+pagoChequeOut.getIdentificarOperacion());
				if (fopagos != null ) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					totalPago = 1;
				} else {
					pr.setStatus("No se encontraron resultados");
					mensajeTabla = "Sin información";
				}
				
			 
			
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
}
