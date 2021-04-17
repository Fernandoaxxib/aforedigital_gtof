package mx.axxib.aforedigitalgt.ctrll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.context.FacesContext;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.AprobarSolicResult;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitor;
import mx.axxib.aforedigitalgt.eml.ObtieneMonitorOut;
import mx.axxib.aforedigitalgt.eml.SolicitudOut;
import mx.axxib.aforedigitalgt.serv.AprobSolicTipRetiroServ;
import mx.axxib.aforedigitalgt.serv.MonitorProcesosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value ="session")
@Component(value = "aprobSolicTipRetiro")
@ELBeanName(value = "aprobSolicTipRetiro")
public class AprobSolicTipRetiroCtrll  extends ControllerBase{

	@Autowired
	private AprobSolicTipRetiroServ service;
	
	@Autowired
	private MonitorProcesosServ monitorService;
	
	@Getter
	private List<SolicitudOut> listSolicitudes;
	
	
	@Getter
	private List<ObtieneMonitor> procesoEjecutado;
	
	@Getter
	@Setter
	private List<SolicitudOut> selectedSolicitud;
	
	@Getter
	@Setter
	private List<SolicitudOut> filtro;
	
	@Getter
	private Integer idProceso;
	
	@Getter
	private AprobarSolicResult res;
	
	@Getter
	@Setter
	private boolean seleccionado;		
	@Getter
	private Integer seleccionados;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			listSolicitudes=null;
			recuperarSolicPendientes();
			PrimeFaces.current().executeScript("PF('listSolicitudes').selectAllRows()");
			if(listSolicitudes!=null) {
				seleccionados=listSolicitudes.size();
			}else {
				seleccionados=0;
			}			
		}
	}
	
	public int getCount() {
	
		if(filtro!=null && !filtro.isEmpty()) {
			return filtro.size();
		}
		else if (listSolicitudes != null) {
			filtro=listSolicitudes;
			return listSolicitudes.size();
		}		
			return 0;		
	}
	
	public void recuperarSolicPendientes() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Recuperación de solicitudes pendientes");
		try {			
			listSolicitudes = service.getListSolicitudes();	
			pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
		    seleccionados=listSolicitudes.size();
		} catch (Exception e) {
			listSolicitudes=null;
			seleccionados=0;
			pr = GenericException(e);			
		}finally {
			filtro=new ArrayList<SolicitudOut>();
			DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:table1");
		    if (!dataTable.getFilterBy().isEmpty()) {
		        dataTable.reset();
		        PrimeFaces.current().ajax().update("form:table1");
		        PrimeFaces.current().ajax().update("form:table1:totalRegistros");
		    }		
		    pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}

	}
	public void recuperarProcesoEjecutado() {
		try {
			ObtieneMonitorOut res = monitorService.getMonitor();
			procesoEjecutado = res.getMonitor();
		} catch (Exception e) {
			GenericException(e);
		}
	}
	
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        
 
        SolicitudOut car = (SolicitudOut) value;
        
        String fechaOperacion= cadenaFecha(car.getFechaOperacion());
        return car.getNumSolicitud().toString().contains(filterText)
                || car.getTransaccion().toLowerCase().contains(filterText)
                || car.getSubTransaccion().toLowerCase().contains(filterText)
                || car.getCodCuenta().contains(filterText)   
                || fechaOperacion.contains(filterText)       
                || car.getNombre().toLowerCase().contains(filterText) ;
    }
	
	private String cadenaFecha(Date fecha) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
		String strDate = dateFormat.format(fecha);  
		
		return strDate;
	}
	


	
	public void aprobarSolicitud()  {
	 ProcessResult pr = new ProcessResult();	
	  if(listSolicitudes!=null && !listSolicitudes.isEmpty()) {		  	  
		if(selectedSolicitud!=null&&!selectedSolicitud.isEmpty()) {				    
				selectedSolicitud.forEach(p -> {	
					ProcessResult pr2 = new ProcessResult();
					try {						
						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss",Locale.getDefault());
						res = service.aprobarSolicitud(p.getNumSolicitud(),Integer.valueOf(p.getTransaccion().substring(0, 1)),p.getSubTransaccion().substring(0, 1));																	
						if(res.getOn_estatus()==1) {
							pr2.setFechaInicial(formatter.parse(res.getListaProceso().get(0).getFECHA_HORA_INICIO()));
							pr2.setFechaFinal(formatter.parse(res.getListaProceso().get(0).getFECHA_HORA_FINAL()));					    
						    pr2.setDescProceso(res.getListaProceso().get(0).getABREV_PROCESO());
							pr2.setStatus(res.getListaProceso().get(0).getESTADO_PROCESO());	
						}else {
							if(res.getOn_estatus() == 2) {
								GenerarErrorNegocio(res.getOcMensaje());
							} else if(res.getOn_estatus() == 0) {
								pr2.setStatus(res.getOcMensaje());
							} 
						}
										
					} catch (Exception e) {						
						pr2=GenericException(e);
						res=null;						
					}finally {					
						resultados.add(pr2);
					}
				});										
				selectedSolicitud=null;
				recuperarSolicPendientes();
		}else {			  
			  pr.setFechaInicial(DateUtil.getNowDate());
			  pr.setDescProceso("Aprobación de solicitud");
			  pr.setStatus("Debe seleccionar la solicitud para aprobar");
			  pr.setFechaFinal(DateUtil.getNowDate());
			  resultados.add(pr);
		}		
	  }	else {
		  pr.setFechaInicial(DateUtil.getNowDate());
		  pr.setDescProceso("Aprobación de solicitud");
		  pr.setStatus("No hay solicitudes por aprobar");
		  pr.setFechaFinal(DateUtil.getNowDate());
		  resultados.add(pr);
	  }
		
	 seleccionados=0;
	}
	
	 public void seleccion(SelectEvent<SolicitudOut> event) {
		 if(!selectedSolicitud.contains(event.getObject())){
			 selectedSolicitud.add(event.getObject());			 			 
		 }	  
		 if(selectedSolicitud!=null) {
			 seleccionados=selectedSolicitud.size();
		 }
	 } public void deseleccion(UnselectEvent<SolicitudOut> event) {		 
		 if(selectedSolicitud.contains(event.getObject())){
			 selectedSolicitud.remove(event.getObject());				
		 }
		 
		 if(selectedSolicitud!=null) {
			 seleccionados=selectedSolicitud.size();
		 }
	 }
	 public void marcarTodos() {
		 if(selectedSolicitud!=null) {
			 seleccionados=selectedSolicitud.size();
		 }
	 }
	 public void desmarcarTodos() {
		 if(selectedSolicitud!=null) {
			 seleccionados=selectedSolicitud.size();
		 }
	 }

}