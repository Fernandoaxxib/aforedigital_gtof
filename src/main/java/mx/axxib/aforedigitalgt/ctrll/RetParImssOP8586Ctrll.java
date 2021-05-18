package mx.axxib.aforedigitalgt.ctrll;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UIInput;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.LoteOP85Out;
import mx.axxib.aforedigitalgt.eml.ProcesResult;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.serv.RetParImssOP8586Serv;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "retParImssOP8586")
@ELBeanName(value = "retParImssOP8586")
public class RetParImssOP8586Ctrll extends ControllerBase {

	@Autowired
	private RetParImssOP8586Serv service;
	
	@Getter
	@Setter
	private List<LoteOP85Out> listLotes;
	
	@Getter
	@Setter
	private List<LoteOP85Out> filtro;
	
	@Getter
	@Setter
	private LoteOP85Out selectedLote;
	
	@Getter
	@Setter
	private LoteOP85Out lote1;
	
	@Getter
	@Setter
	private String ruta;
	
	@Getter
	@Setter
	private String archivo;
	
	@Getter
	@Setter
	private String ruta2;
	
	@Getter
	@Setter
	private String archivo2;
	
	@Getter
	@Setter
	private String ruta3;
	
	@Getter
	@Setter
	private String archivo3;
	
	@Getter
	@Setter
	private ProcesoOut proceso;
	
	@Getter
	@Setter
	private Date fecIni;
	
	@Getter
	@Setter
	private Date fecFin;
	
	@Getter
	@Setter
	private String lote;
	
	@Getter
	private Date today;
	@Getter
	@Setter
	private String radioSelected;

	@Getter
	@Setter
	private String radioSelected2;
	@Getter
	private boolean seleccion1;
	@Getter
	private boolean seleccion2;
	@Getter
	private boolean disabled1;
	@Getter
	private boolean disabled2;
	@Getter
	private boolean disabled3;
	@Getter
	private boolean disabled4;
	@Getter
	private boolean disabled5;
	@Getter
	private String border;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {			
			reset();			
		}
	}
	public void radioSelected() {
		if (radioSelected != null) {
			radioSelected2 = null;
			lote = null;
			seleccion2 = false;
			disabled1 = false;
			disabled2 = false;
			disabled3 = false;
			disabled4 = true;
			disabled5 = false;
			archivo3=null;	
			border="";
		}

	}

	public void radioSelected2() {

		if (radioSelected2 != null) {
			fecIni = null;
			fecFin = null;
			radioSelected = null;
			seleccion1 = false;
			disabled1 = true;
			disabled2 = true;
			disabled3 = false;
			disabled4 = false;
			disabled5 = false;
			archivo3=null;
			border="";
		}

	}
	public void onRowSelect(SelectEvent<LoteOP85Out> event) {
		lote1 = new LoteOP85Out();
		lote1.setID_LOTE(event.getObject().getID_LOTE());
	}
	public void getLotes() {
		try {
			if(listLotes==null) {
			listLotes=service.getLotesOP85();
			}			
			fecIni=null;
			fecFin=null;
			PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
		} catch (Exception e) {
			GenericException(e);
		}
	}
	public void opcionSeleccionada2() {
		if(lote1!=null) {
			lote = lote1.getID_LOTE();
		}			
	}
	public void generarOP85(){
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Generación archivo OP85");
		if(archivo!=null && !archivo.isEmpty()) {					
			try {			
				ProcesResult res=service.cargarArchivoOP85(ruta, archivo);
				if (res.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));						
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getP_Message());
					} else if (res.getOn_Estatus() == 0) {
						pr.setStatus(res.getP_Message());
					}
				}								
			}catch(Exception e) {
				pr=GenericException(e);
			}finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}else {		
			pr.setStatus("Nombre de archivo requerido");
			UIInput radio = (UIInput) findComponent("vArchivo1");
			radio.setValid(false);
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	public void cargarOP86() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		if(archivo2!=null && !archivo2.isEmpty()) {		
		pr.setDescProceso("Carga archivo OP86");
			try {				
				ProcesResult res=service.cargarArchivoOP86(ruta2, archivo2);
				if (res.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));						
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getP_Message());
					} else if (res.getOn_Estatus() == 0) {
						pr.setStatus(res.getP_Message());
					}
				}	
			}catch(Exception e) {
				pr=GenericException(e);
			}finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		}else {
			pr.setDescProceso("Carga archivo OP86");
			pr.setStatus("Nombre de archivo requerido");
			UIInput radio = (UIInput) findComponent("idArchivo2");
			radio.setValid(false);
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	public void generarReporte() {	
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Generación de reporte");

		if (radioSelected != null || radioSelected2 != null) {
			if(isNombreValid(pr)) {
			if (radioSelected != null && radioSelected.equals("1")) {
				if (fecIni != null && fecFin != null) {
					if (DateUtil.isValidDates(fecIni, fecFin)) {					  
						  try {
							  ProcesResult res=service.generarReporteOP86(ruta3, archivo3, lote, fecIni, fecFin);
							  if (res.getOn_Estatus() == 1) {
									pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));						
								} else {
									if (res.getOn_Estatus() == 2) {
										GenerarErrorNegocio(res.getP_Message());
									} else if (res.getOn_Estatus() == 0) {
										pr.setStatus(res.getP_Message());
									}
								}	
							} catch (Exception e) {
								pr=GenericException(e);
							} finally {
								pr.setFechaFinal(DateUtil.getNowDate());
								resultados.add(pr);
							}					  
					} else {
						UIInput radio = (UIInput) findComponent("dfini");
						radio.setValid(false);

						UIInput radio2 = (UIInput) findComponent("dffin");
						radio2.setValid(false);

						pr.setStatus("La fecha inicial debe ser menor o igual a la fecha final");
						pr.setFechaFinal(DateUtil.getNowDate());
						resultados.add(pr);
					}
				} else {
					UIInput radio = (UIInput) findComponent("dfini");
					radio.setValid(false);

					UIInput radio2 = (UIInput) findComponent("dffin");
					radio2.setValid(false);

					pr.setStatus("Rango de fecha requerido");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
			if (radioSelected2 != null && radioSelected2.equals("1")) {
				if (lote != null) {	
					border="";
						  try {
								ProcesResult res=service.generarReporteOP86(ruta3, archivo3, lote, fecIni, fecFin);
								if (res.getOn_Estatus() == 1) {
									pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));						
								} else {
									if (res.getOn_Estatus() == 2) {
										GenerarErrorNegocio(res.getP_Message());
									} else if (res.getOn_Estatus() == 0) {
										pr.setStatus(res.getP_Message());
									}
								}	
							} catch (Exception e) {
								pr=GenericException(e);
							} finally {
								pr.setFechaFinal(DateUtil.getNowDate());
								resultados.add(pr);
							}					  
				} else {					
					border="2px solid #ff0028 !important;";
					pr.setStatus("Se requiere el número de lote");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				}
			}
		}
		} else {
			UIInput radio = (UIInput) findComponent("customRadio");
			radio.setValid(false);
			UIInput radio2 = (UIInput) findComponent("customRadio2");
			radio2.setValid(false);            
			pr.setStatus("Selección requerida");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}		
	}
	
	public boolean isNombreValid(ProcessResult pr) {
		 if(archivo3!=null && !archivo3.isEmpty()) {
			 if(ValidateUtil.isValidFileName(archivo3)) {
				 return true;
			 }else {
				  UIInput radio = (UIInput) findComponent("idArchivo3");
				  radio.setValid(false);					
				  pr.setStatus("Nombre de archivo no válido");
				  pr.setFechaFinal(DateUtil.getNowDate());
			      resultados.add(pr);
			      return false;
			 }
		 }else {
			  UIInput radio = (UIInput) findComponent("idArchivo3");
			  radio.setValid(false);				
			  pr.setStatus("El nombre de archivo es requerido");
			  pr.setFechaFinal(DateUtil.getNowDate());
		      resultados.add(pr);	
		      return false;
		 }
	}
	public void reset() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");		
		archivo = format.format(DateUtil.getNowDate());
		archivo=archivo+".OP85";
		archivo2=null;
		archivo3=null;
		border="";
		disabled1 = true;
		disabled2 = true;
		disabled3 = true;
		disabled4 = true;
		disabled5 = true;		
		fecIni=null;
		fecFin=null;
		filtro=null;
		listLotes=null;
		lote=null;
		lote1=null;		
		proceso=null;
		radioSelected = null;
		radioSelected2 = null;
		ruta="/iprod/PROCESAR/TRANSMISION/AFORE/RETIROS";
		ruta2="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
		ruta3="/iprod/PROCESAR/RECEPCION/AFORE/RETIROS";
		seleccion1 = false;
		seleccion2 = false;
		today= new Date();
	}
	public void limpiar() {
		lote=null;
	}
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        LoteOP85Out car = (LoteOP85Out) value;
        
        String fechaOperacion= cadenaFecha(car.getFECHA_LOTE());
        return car.getID_LOTE().toString().contains(filterText)  
                || fechaOperacion.contains(filterText);
    }
	private String cadenaFecha(Date fecha) {		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate = dateFormat.format(fecha);  		
		return strDate;
	}
}
