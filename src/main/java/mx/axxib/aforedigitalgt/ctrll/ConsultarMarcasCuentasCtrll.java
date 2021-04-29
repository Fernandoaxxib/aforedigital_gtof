package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.regex.Pattern;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.ConsultarDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosIcefasOut;
import mx.axxib.aforedigitalgt.eml.CpDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoInsertTablaOut;
import mx.axxib.aforedigitalgt.serv.ConsultarMarcasCuentasService;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "consultarMarcasCuentas")
@ELBeanName(value = "consultarMarcasCuentas")
public class ConsultarMarcasCuentasCtrll extends ControllerBase {

	@Autowired
	ConsultarMarcasCuentasService consultarMarcasCuentasServices;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	List<CpDatosIcefasOut> cpDatos;
	
	@Getter
	@Setter
	private CpDatosIcefasOut cpDatos2;
	
	@Getter
	List<ConsultarDatosIcefasOut> cpCursor;
	
	@Getter
	@Setter
	private String nombre;
	@Getter
	@Setter
	private Integer cuenta;
	@Getter
	@Setter
	private String curp_o_nssIn;
	
	@Getter
	@Setter
	private String curp_o_nss_Out;
	@Getter
	private String mensaje;
	
	@Getter
	@Setter
	private String codCuenta;
	@Getter
	@Setter
	private String codTipoSaldo;
	@Getter
	@Setter
	private String claveProceso;
	@Getter
	@Setter
	private String estado;
	
	@Getter
	@Setter
	private String nss;
	 
	@Getter
	@Setter
	private String curp;
	
	@Getter
	@Setter
	private String valor;
	
	@Getter
	private String mensajeTabla;
	
	@Getter
	@Setter
	private Integer totalSolicitud;	
	
	@Getter
	@Setter
	private Integer totalNSSCURP;	
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
		curp_o_nssIn=null;
		limpiar();
		init = false;	
	}
	}
	private void limpiar() {
		nombre=null;
		nss=null;
		curp =null;
		cuenta=null;
		cpDatos=null;
		cpCursor=null;
		totalSolicitud=0;
		totalNSSCURP=0;
	}
	public void validarNssRfc() {
		ProcessResult pr = new ProcessResult();
		try {
		limpiar();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por NSS o CURP");
			if (curp_o_nssIn != null && !curp_o_nssIn.equals("") ) {
					if (ValidateUtil.isCURP(curp_o_nssIn)) {
						
						int valorCurp=ejecutarConsultaCurp(curp_o_nssIn);
						if(valorCurp ==1) {
							pr.setStatus("Consulta Exitosa CURP");//"Consulta Exitosa"
						}else {
							pr.setStatus("No se encontraron resultados por CURP");
							mensajeTabla = "Sin información por CURP";
						}
						
				    } else {
				    	
				    	int valorNss= ejecutarConsultaNss(curp_o_nssIn);
				    	if(valorNss ==1) {
							pr.setStatus("Consulta Exitosa NSS");//"Consulta Exitosa"
						}else {
							pr.setStatus("No se encontraron resultados por NSS");
							mensajeTabla = "Sin información por NSS";
						}
				    }
				}else {
					UIInput input = (UIInput) findComponent("curpNSS");
					input.setValid(false);
					pr.setStatus("NSS o CURP es requerido");
					
				}
		}catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
		
	}
	
	public int ejecutarConsultaNss(String nssIn) {
		int bandera=0;
		try {
			ConsultarNombreCuentaIcefasOut res=consultarMarcasCuentasServices.getConsultarNss(nssIn);	
			System.out.println("VALOR DE RES: "+res);
			if (res != null && res.getCpDatos() != null && res.getCpDatos().size() > 0) {
				totalNSSCURP=res.getCpDatos().size();
				cpDatos=res.getCpDatos();
				nombre=res.getNombre();
				cuenta=res.getCuenta();
				curp=res.getCurp_o_nss();
				mensaje=res.getMensaje();
				nss=nssIn;
				valor="1";
				bandera=1;
			}
		}catch (Exception e) {
			GenericException(e);
		}
		return bandera;
	}
	
	public int ejecutarConsultaCurp(String curpIn) {
			int bandera=0;
			try {
				ConsultarNombreCuentaIcefasOut res=consultarMarcasCuentasServices.getConsultarCurp(curpIn);	
				System.out.println("VALOR DE RES: "+res);
				if (res != null && res.getCpDatos() != null && res.getCpDatos().size() > 0) {
					totalNSSCURP=res.getCpDatos().size();
					cpDatos=res.getCpDatos();
					nombre=res.getNombre();
					cuenta=res.getCuenta();
					nss=res.getCurp_o_nss();
					mensaje=res.getMensaje();
					curp=curpIn;
					valor="1";
					bandera=1;
				}
			}catch (Exception e) {
				GenericException(e);
			}
			return bandera;
	}
	
	public void ejecutarConsultarTraspasos() {
	
		try {
			
			//ConsultarTraspasosIcefasOut res=consultarMarcasCuentasServices.getConsultarTraspasos(codCuenta, codTipoSaldo, claveProceso, estado);
			
			ConsultarTraspasosIcefasOut res=consultarMarcasCuentasServices.getConsultarTraspasos(Integer.toString(cuenta), cpDatos2.getCOD_TIPSALDO(), Integer.toString(cpDatos2.getCLAVE_PROCESO()), cpDatos2.getESTADO());			
			if((res != null && res.getCpCursor() != null && res.getCpCursor().size() > 0)) {
				cpCursor=res.getCpCursor();
				mensaje=res.getMensaje();
				
			}
		}catch (Exception e) {
			GenericException(e);
		}
		
	}
	

}
