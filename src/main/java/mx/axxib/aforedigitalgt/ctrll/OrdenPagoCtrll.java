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
			fechaInicio=null;
			fechaUltima=null;
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
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
			
				//BaseOut res=ordenPagoServ.generarArchivo(ordenPagoFechasOut, boxDos);
				BaseOut res=ordenPagoServ.generarArchivo(ordenPagoFechasOut, boxDos);				
				if (res.getEstatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
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
			
				if(seleccionarA == null && (fechaInicio == null && fechaUltima == null)) {
					
					UIInput inputSelect = (UIInput) findComponent("listSelect");
					inputSelect.setValid(false);
					 UIInput inputInicio = (UIInput) findComponent("fechaInicio");
					 inputInicio.setValid(false);
					 UIInput inputFinal = (UIInput) findComponent("fechaUltima");
					 inputFinal.setValid(false);				
					 pr.setStatus("Seleccionar Tipo Reporte, Fecha Inicio y Final ");
					
				
				}else {
						if(seleccionarA ==null ) {
						
							
							UIInput inputSelect = (UIInput) findComponent("listSelect");
							inputSelect.setValid(false);
							pr.setStatus("Seleccionar Tipo Reporte");
					
					}else {
						
							if( fechaInicio == null && fechaUltima== null) {
								
								 UIInput inputInicio = (UIInput) findComponent("fechaInicio");
								 inputInicio.setValid(false);
								 UIInput inputFinal = (UIInput) findComponent("fechaUltima");
								 inputFinal.setValid(false);				
								 pr.setStatus("Seleccionar Fechas ");
							
						}else {
							
							if( fechaInicio == null) {
								
								 UIInput inputInicio = (UIInput) findComponent("fechaInicio");
								 inputInicio.setValid(false);
								 pr.setStatus("Seleccionar Fecha Inicio ");
						
						}else {
							
							
							
							if(fechaUltima == null) {
								
								 UIInput inputFinal = (UIInput) findComponent("fechaUltima");
								 inputFinal.setValid(false);				
								 pr.setStatus("Seleccionar Fecha Final ");
								
								
						}else {
							
							if (DateUtil.isValidDates(fechaInicio, fechaUltima)) {
							
							if(boxUno != null){	
								impresoraReporte();
								}
								if(boxDos!= null){	
								generarArchivo();
								}
													
						if(seleccionarA=="Alerta Aportaciones"|| seleccionarA.equals("Alerta Aportaciones")) {
							tiposReportes=ordenPagoServ.creaTipoReporte("A",fechaInicio,fechaUltima);
						}
						else {
							tiposReportes=ordenPagoServ.creaTipoReporte("R",fechaInicio,fechaUltima);
						}
						
						
						nombre=tiposReportes.getP_NOMBRE_ARCHIVO();						
						if (tiposReportes.getP_ESTATUS()==1) {
							pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
						} else {
							if (tiposReportes.getP_ESTATUS() == 2) {
								GenerarErrorNegocio("Se presento un error inesperado");
							} else if (tiposReportes.getP_ESTATUS() == 0) {
								pr.setStatus("Error al crear Archivo");
							}	
							
						}
						
							} else {
								UIInput fechaIni = (UIInput) findComponent("fechaInicio");
								fechaIni.setValid(false);

								UIInput fechaFin = (UIInput) findComponent("fechaUltima");
								fechaFin.setValid(false);
								pr.setStatus("Fecha inicio debe ser menor o igual a la fecha fin");
							}
						
						
						}
							 
						}			
							 
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
}
