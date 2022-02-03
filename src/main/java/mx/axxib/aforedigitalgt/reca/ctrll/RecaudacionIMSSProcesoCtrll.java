package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.reca.eml.Lote;
import mx.axxib.aforedigitalgt.reca.eml.LotesOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaProcesoEjecutarIn;
import mx.axxib.aforedigitalgt.reca.serv.RecaudacionIMSSProcesoServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Recaudacion IMSS Proceso
//** Interventor Principal: JSAS
//** Fecha Creación: 10/Ene/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "recaudacionProceso")
@ELBeanName(value = "recaudacionProceso")
public class RecaudacionIMSSProcesoCtrll extends ControllerBase {

	@Autowired
	private RecaudacionIMSSProcesoServ serv;

	@Getter
	@Setter
	private Lote lote;

	@Getter
	@Setter
	private String archivo;

	@Getter
	private String directorio;

	@Getter
	@Setter
	private Date fecha;

	@Getter
	@Setter
	private String opcion;

	@Getter
	@Setter
	private List<Lote> lotes;

	@Getter
	private boolean mostrarLote;
	
	@Getter
	private boolean mostrarFecha;
	
	@Getter
	private boolean mostrarArchivo;
	
	@Getter
	private boolean mostrarDirectorio;
	 
	@Getter
	private boolean mostrarEjecutar;
	
	@Getter
	@Setter
	private List<Lote> filtro;
	
	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			directorio = "/iprod/PROCESAR/TRANSMISION/AFORE/RECAUDACION";
			opcion = null;
			lotes = null;
			limpiar();
			

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		lote = null;
		archivo = null;
		fecha = null;
		
		mostrarLote = false;
		mostrarFecha = false;
		mostrarArchivo = false;
		mostrarDirectorio = false;
		mostrarEjecutar = false;
	}
	
	public String getLoteDesc() {
		if(lote != null) {
			return lote.getIdOperacion();
		}
		return null;
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		Lote lot = (Lote) value;
		return lot.getLote().toLowerCase().contains(filterText)
				|| lot.getIdOperacion().toLowerCase().contains(filterText)
				|| lot.getFechaLote().toLowerCase().contains(filterText)
				|| lot.getSecLote().toLowerCase().contains(filterText);
	}

	public void consultarLotes() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar lotes");
			LotesOut res = null;
			if(lotes == null) {
				 res = serv.lotes();
			} else {
				res = new LotesOut();
				res.setEstatus(null); // TODO: sustituir null por 1, actualmente el stored no devuelve status
				res.setLotes(lotes);
			}
				
			if (res.getEstatus() == null) { // TODO sustituir null por 1, actualmente el stored no devuelve status
				lotes = res.getLotes();
				if (lotes.size() == 0) {
					pr.setStatus("No se encontraron lotes");
					pr.setFechaFinal(DateUtil.getNowDate());
					resultados.add(pr);
				} else {
					PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
					PrimeFaces.current().executeScript("PF('dlg2').show();");
				}
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
				}
			}

		} catch (Exception e) {
			resultados.add(GenericException(e));
		}
	}
	

	public boolean isFormValid(ProcessResult pr) {
		if (opcion == null) {
			UIInput radio = (UIInput) findComponent("opcion");
			radio.setValid(false);

			pr.setStatus("Debe elegir una opción");
			return false;
		}
		switch (opcion) {
		case "10": // movs
			if (lote == null) {
				UIInput radio = (UIInput) findComponent("lotes");
				radio.setValid(false);

				pr.setStatus("Debe elegir un Id operación");
				return false;
			}
			if (fecha == null) {
				UIInput radio = (UIInput) findComponent("fecha");
				radio.setValid(false);

				pr.setStatus("Debe elegir una fecha");
				return false;
			}
			break;

		case "20": // gen archivo
			if (lote == null) {
				UIInput radio = (UIInput) findComponent("lotes");
				radio.setValid(false);

				pr.setStatus("Debe elegir un Id operación");
				return false;
			} 
			if (archivo == null || archivo.equals("")) {
				UIInput radio = (UIInput) findComponent("archivo");
				radio.setValid(false);

				pr.setStatus("Debe elegir un nombre de archivo");
				return false;
			} else if(!ValidateUtil.isValidFileName(archivo)) {
				UIInput radio = (UIInput) findComponent("archivo");
				radio.setValid(false);

				pr.setStatus("Nombre de archivo inválido");
				return false;
			}
			break;

		case "30": // car archivo
			if (archivo == null || archivo.equals("")) {
				UIInput radio = (UIInput) findComponent("archivo");
				radio.setValid(false);

				pr.setStatus("Debe elegir un nombre de archivo");
				return false;
			} else if(!ValidateUtil.isValidFileName(archivo)) {
				UIInput radio = (UIInput) findComponent("archivo");
				radio.setValid(false);

				pr.setStatus("Nombre de archivo inválido");
				return false;
			}
			break;

		case "40": // rev
			if (lote == null) {
				UIInput radio = (UIInput) findComponent("lotes");
				radio.setValid(false);

				pr.setStatus("Debe elegir un Id operación");
				return false;
			}
			break;
		}

		return true;
	}

	public void ejecutar() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Ejecutar");
			if (isFormValid(pr)) {
				RecaProcesoEjecutarIn parametros = new RecaProcesoEjecutarIn();
				parametros.setArchivo(archivo);
				parametros.setDirectorio(directorio);
				parametros.setFecha(fecha);
				parametros.setOpcion(opcion);
				if (lote != null) {
					parametros.setFechaLote(lote.getFechaLote());
					parametros.setIdOperacion(lote.getIdOperacion());
					parametros.setSecLote(lote.getSecLote());
				}
				BaseOut res = serv.procesoEjecutar(parametros);
				if (res.getEstatus() == null) { // TODO sustituir null por 1
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void changeOpcion() {
		limpiar();
		
		UIInput lot = (UIInput) findComponent("lotes");
		lot.setValid(true);
		UIInput fec = (UIInput) findComponent("fecha");
		fec.setValid(true);
		UIInput arc = (UIInput) findComponent("archivo");
		arc.setValid(true);
		
		switch (opcion) {
		case "10": // movs
			mostrarLote = true;
			mostrarFecha = true;
			mostrarEjecutar = true;
			break;

		case "20": // gen archivo
			mostrarLote = true;
			mostrarArchivo = true;
			mostrarDirectorio = true;
			mostrarEjecutar = true;
			break;

		case "30": // car archivo
			mostrarArchivo = true;
			mostrarDirectorio = true;
			mostrarEjecutar = true;
			break;

		case "40": // rev
			mostrarLote = true;
			mostrarEjecutar = true;
			break;
		}
	}
}
