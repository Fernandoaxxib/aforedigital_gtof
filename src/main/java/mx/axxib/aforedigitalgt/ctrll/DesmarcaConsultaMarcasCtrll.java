package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

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
import mx.axxib.aforedigitalgt.eml.DesmarcaCargaConsultaMasivaOut;
import mx.axxib.aforedigitalgt.eml.ProcesoOut;
import mx.axxib.aforedigitalgt.eml.TipoProcesoOut;
import mx.axxib.aforedigitalgt.serv.DesmarcaCargaConsultaMasivaService;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "desmarcaConsultaMarcas")
@ELBeanName(value = "desmarcaConsultaMarcas")
public class DesmarcaConsultaMarcasCtrll extends ControllerBase {
	
	
	@Autowired
	private DesmarcaCargaConsultaMasivaService cargaMasiva;
	
	@Autowired
	private AforeMessage aforeMessage;
	
	@Getter
	@Setter
	private String rutaCarga;
	 
	@Getter
	@Setter
	private String nombreArchivoCarga;
	
	@Getter
	@Setter
	private String selectedTipoClave;
	
	@Getter
	@Setter
	private DesmarcaCargaConsultaMasivaOut desmarcaCargaConsultaMasivaOut;
	
	@Getter
	@Setter
	private List<TipoProcesoOut> listaTipoProceso;
	

	
	@Getter
	private Date today;
	

	@Override
	public void iniciar() {
		super.iniciar();
		if(init) {
			rutaCarga="/RESPALDOS/operaciones/pruebas";		
			today= new Date();
			consultarTodo();
			reset();
		}
	}
	
	public void reset() {
		nombreArchivoCarga=null;
		selectedTipoClave=null;
	}
	
	
	public List<TipoProcesoOut> consultarTodo(){
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Cargar Clave Proceso");
		try {
			listaTipoProceso=cargaMasiva.consultarTodo();
			 pr.setStatus("Proceso ejecutado Correctamente");
		}catch (Exception e) {
				GenericException(e);
			} finally {
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
			}
		return listaTipoProceso;
	}
	
	public void cargarArchivo() {
		
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Consulta Marca por Nss y/o Curp");
		
		if(isNombreValido(pr)) {
				try {	
				
			desmarcaCargaConsultaMasivaOut =cargaMasiva.consultaMarcasArchivo(rutaCarga, nombreArchivoCarga);
	
			System.out.println("cargar archivo XLS: "+desmarcaCargaConsultaMasivaOut);
			if(desmarcaCargaConsultaMasivaOut.getEstatus()==1 ) {
				pr.setStatus(desmarcaCargaConsultaMasivaOut.getMensaje());
			}else {
					if (desmarcaCargaConsultaMasivaOut.getEstatus() == 2) {
						GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getMensaje());
					} else if (desmarcaCargaConsultaMasivaOut.getEstatus() == 0) {
						pr.setStatus(desmarcaCargaConsultaMasivaOut.getMensaje());
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
	
	
	public void reversaArchivo() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Consulta Marca por Proceso Operativo");
		
		try {
		
		if(selectedTipoClave==null || selectedTipoClave.isEmpty()) {
			 UIInput inputTipo = (UIInput) findComponent("tipoProceso");
			 inputTipo.setValid(false);				
			 pr.setStatus("Seleccionar Clave Proceso ");
			
		}else {
			
			 String[] parts = selectedTipoClave.split("-");
			 String part1 = parts[0]; // 123
			 String part2 = parts[1]; // 654321
			 
			desmarcaCargaConsultaMasivaOut =cargaMasiva.consultaMarcas(part1,part2);
			System.out.println("reversa archivo: "+desmarcaCargaConsultaMasivaOut);
			if(desmarcaCargaConsultaMasivaOut.getEstatus()==1 ) {

				pr.setStatus("Proceso ejecutado Correctamente");

				}else {

					if (desmarcaCargaConsultaMasivaOut.getEstatus() == 2) {
						GenerarErrorNegocio(desmarcaCargaConsultaMasivaOut.getMensaje());
					} else if (desmarcaCargaConsultaMasivaOut.getEstatus() == 0) {
						pr.setStatus(desmarcaCargaConsultaMasivaOut.getMensaje());
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
	
	public boolean isNombreValido(ProcessResult pr) {
		if (nombreArchivoCarga == null || nombreArchivoCarga.isEmpty()) {
			UIInput radio = (UIInput) findComponent("nombreCarga");
			radio.setValid(false);
			pr.setStatus("Nombre de archivo requerido");
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
			return false;
		} else {
			Pattern pattern = Pattern.compile("[-_ A-Za-z0-9]+(.xls|.XLS)$");
			if (!pattern.matcher(nombreArchivoCarga.toUpperCase()).matches()) {
				UIInput radio = (UIInput) findComponent("nombreCarga");
				radio.setValid(false);
				pr.setStatus("el nombre del archivo debe tener extensión .xls");
				pr.setFechaFinal(DateUtil.getNowDate());
				resultados.add(pr);
				return false;
			}
			return true;
		}
	}

}
