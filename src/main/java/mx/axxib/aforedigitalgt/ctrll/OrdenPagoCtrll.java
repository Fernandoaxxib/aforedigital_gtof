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
import mx.axxib.aforedigitalgt.eml.BaseOut;
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
		//nombre=null;
		
		}
	}
	
	public void cargaFechas() {
	try {
		 ordenPagoFechasOut=ordenPagoServ.cargaFechas();
		 System.out.println("VALOR DE FECHAS: "+ordenPagoFechasOut);
	}catch (Exception e) {
		GenericException(e);
	}	
		
	}
	
	public void inicializarA() {
		try {
			nombre=null;
			boxDos=null;
			seleccionarA=null;
			tipoReporte=null;
			tipoReporte=ordenPagoServ.inicializarA();
		}catch (Exception e) {
			GenericException(e);
		}	
			
		}
	
	public void impresoraReporte() {
		ProcessResult pr = new ProcessResult();
		try {
			
			if(boxUno==1) {
				
				pr.setFechaInicial(DateUtil.getNowDate());
				pr.setDescProceso("Mandar a Imprimir");
				BaseOut res =ordenPagoServ.enviarImpresora(ordenPagoFechasOut, boxUno);
				if (res.getEstatus() == 1) {
					pr.setStatus("Ejecución con exito");
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			}
		 
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}	
		
	}
	
	public void generarArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Generar Reporte");	
		try {
		
			if(boxDos ==2){				
			
				BaseOut res=ordenPagoServ.generarArchivo(ordenPagoFechasOut, boxDos);
				if (res.getEstatus() == 1) {
					pr.setStatus("Ejecución con exito");
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			}
			}catch (Exception e) {
				pr = GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}	
		
	}
	

	public void botonGenerarReporte() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Obtener Nombre Archivo");
		try {
			
				if(seleccionarA !=null) {
					
					if(boxUno != null){	
						impresoraReporte();
						}
						if(boxDos!= null){	
						generarArchivo();
						}
					
				
				tiposReportes=ordenPagoServ.creaTipoReporte(seleccionarA);
				
				nombre=tiposReportes.getP_NOMBRE_ARCHIVO();
				System.out.println("NOMBRE ARCHIVO: "+nombre);
				if (nombre != null) {
					pr.setStatus("Ejecución con exito");
				} else {
					pr.setStatus("Se presento un error inesperado");
				}
				}else {
					UIInput input = (UIInput) findComponent("listSelect");
					input.setValid(false);
					pr.setStatus("Seleccionar Tipo Reporte");
					 
				}
		
		}catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}	
		
			
	}
}
