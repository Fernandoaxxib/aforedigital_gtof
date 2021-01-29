package mx.axxib.aforedigitalgt.ctrll;

import java.util.List;
import java.util.regex.Pattern;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.eml.ConsultarDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarNombreCuentaIcefasOut;
import mx.axxib.aforedigitalgt.eml.ConsultarTraspasosIcefasOut;
import mx.axxib.aforedigitalgt.eml.CpDatosIcefasOut;
import mx.axxib.aforedigitalgt.eml.SalarioMinimoInsertTablaOut;
import mx.axxib.aforedigitalgt.serv.ConsultarMarcasCuentasService;

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
	
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
		curp_o_nssIn=null;
		nombre=null;
		nss=null;
		curp =null;
		cuenta=null;
		cpDatos=null;
		cpCursor=null;
	}
	}
	
	public void validarNssRfc() {
		try {
			if (validarCURP(curp_o_nssIn)) {
				System.out.println("***************Es Curp");
				ejecutarConsultaCurp(curp_o_nssIn);
				
		    } else {
		    	System.out.println("***************NO ES Curp");
		    	ejecutarConsultaNss(curp_o_nssIn);
		    }
		}
	catch (Exception e) {
		GenericException(e);
	}
		
	}
	
	public void ejecutarConsultaNss(String nssIn) {
		try {
			ConsultarNombreCuentaIcefasOut res=consultarMarcasCuentasServices.getConsultarNss(nssIn);	
			System.out.println("DATOS POR NSS: "+res);
			if (res != null && res.getCpDatos() != null && res.getCpDatos().size() > 0) {
				cpDatos=res.getCpDatos();
				nombre=res.getNombre();
				cuenta=res.getCuenta();
				curp=res.getCurp_o_nss();
				mensaje=res.getMensaje();
				nss=nssIn;
				valor="1";
			}
		}catch (Exception e) {
			GenericException(e);
		}
	}
	
	public void ejecutarConsultaCurp(String curpIn) {
			try {
				ConsultarNombreCuentaIcefasOut res=consultarMarcasCuentasServices.getConsultarCurp(curpIn);	
				System.out.println("DATOS POR CURP: "+res);
				if (res != null && res.getCpDatos() != null && res.getCpDatos().size() > 0) {
					cpDatos=res.getCpDatos();
					nombre=res.getNombre();
					cuenta=res.getCuenta();
					nss=res.getCurp_o_nss();
					mensaje=res.getMensaje();
					curp=curpIn;
					valor="1";
				}
			}catch (Exception e) {
				GenericException(e);
			}
	}
	
	public void ejecutarConsultarTraspasos() {
		try {
			System.out.println("*****************VALOR DE VISTA cuenta:"+cuenta+" "+ cpDatos2);
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
	
	private boolean validarCURP(String curp)
	{ String regex = 
	  "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
	  "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
	  "[HM]{1}" +
	  "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
	  "[B-DF-HJ-NP-TV-Z]{3}" +
	  "[0-9A-Z]{1}[0-9]{1}$";

	  Pattern patron = Pattern.compile(regex);
	  if(!patron.matcher(curp).matches())
	  { return false;
	  }else
	  { return true;
	  }
	}
}
