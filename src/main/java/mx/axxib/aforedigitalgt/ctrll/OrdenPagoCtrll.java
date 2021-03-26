package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.AforeMessage;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.OrdenPagoFechasOut;
import mx.axxib.aforedigitalgt.eml.PermisoResult;
import mx.axxib.aforedigitalgt.eml.TiposReportes;
import mx.axxib.aforedigitalgt.serv.OrdenPagoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "ordenPago")
@ELBeanName(value = "ordenPago")
public class OrdenPagoCtrll extends ControllerBase {

	@Autowired
	private OrdenPagoServ ordenPagoServ;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private OrdenPagoFechasOut ordenPagoFechasOut;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private Integer boxUno;
	
	@Getter
	@Setter
	private Integer boxDos;
	@Getter
	@Setter
	private List<String> tipoReporte;
	@Getter
	@Setter
	private String seleccionarA;
	
	@Getter
	@Setter
	private Date fechaInicio;
	
	@Getter
	@Setter
	private Date fechaUltima;
	
	@Getter
	@Setter
	private TiposReportes tiposReportes;
	
	@Getter
	@Setter
	private Boolean radio1;
	
	@Getter
	@Setter
	private Boolean radio2;
	@Getter
	@Setter
	private String msg;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
	
		cargaFechas();
		inicializarA();
		//obtenerNombre();
		}
	}
	
	public void cargaFechas() {
	try {
		 ordenPagoFechasOut=ordenPagoServ.cargaFechas();
	}catch (Exception e) {
		GenericException(e);
	}	
		
	}
	
	public void inicializarA() {
		try {
			tipoReporte=ordenPagoServ.inicializarA();
		}catch (Exception e) {
			GenericException(e);
		}	
			
		}
	
	public void impresoraReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Búsqueda por NSS");
		try {
			System.out.println("Valor de boxUno es: "+boxUno);
			if(boxUno!=null) {
			ordenPagoServ.enviarImpresora(ordenPagoFechasOut, boxUno);
			}
		 else {
			UIInput input = (UIInput) findComponent("listSelect");
			input.setValid(false);
			pr.setStatus("Tipo Reporte es requerido");
			
		}
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}	
		
	}
	
	public void generarArchivo() {
		try {
			System.out.println("Valor de boxDos es: "+boxDos);
			if(boxDos!=null){
			ordenPagoServ.generarArchivo(ordenPagoFechasOut, boxDos);
			}
		}catch (Exception e) {
			GenericException(e);
		}		
		
	}
	
	public void creaTipoReporte() {
		try {
			System.out.println("Valor de tipoReporte es A: "+tipoReporte);
			tiposReportes=ordenPagoServ.creaTipoReporte("A");
		}catch (Exception e) {
			GenericException(e);
		}	
	}
	
	public void obtenerNombre () {
		try {
			System.out.println("VALOR DE ORDEN PAGO "+ordenPagoFechasOut.getFechaInicio());
			nombre=ordenPagoServ.generaNombre(ordenPagoFechasOut);//ordenPagoFechasOut
			nombre=tiposReportes.getP_NOMBRE_ARCHIVO();
		}catch (Exception e) {
			GenericException(e);
		}	
	}
	
	public void botonGenerarReporte() {
		try {
			
			impresoraReporte();
			generarArchivo();
				if(seleccionarA!=null) {
				System.out.println("Valor de tipoReporte es A: "+seleccionarA);
				tiposReportes=ordenPagoServ.creaTipoReporte(seleccionarA);
				System.out.println("Valor tiposReportes;"+tiposReportes); 
				nombre=tiposReportes.getP_NOMBRE_ARCHIVO();
				if(tiposReportes.getP_MENSAJE().equals("SE GENERO CORRECTAMENTE EL REPORTE A") || tiposReportes.getP_MENSAJE().equals("SE GENERO CORRECTAMENTE EL REPORTE")) {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
				} else {
					msg = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_ERROR, null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
				}
				}
		
		}catch (Exception e) {
			GenericException(e);
		}
		
			
	}
}
