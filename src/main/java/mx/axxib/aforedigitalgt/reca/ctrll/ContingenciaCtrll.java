package mx.axxib.aforedigitalgt.reca.ctrll;

import java.util.ArrayList;
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
import mx.axxib.aforedigitalgt.reca.eml.Sello;
import mx.axxib.aforedigitalgt.reca.eml.SellosOut;
import mx.axxib.aforedigitalgt.reca.serv.ContingenciaServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Contingencia
//** Interventor Principal: JSAS
//** Fecha Creación: 7/Feb/2022
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "contingencia")
@ELBeanName(value = "contingencia")
public class ContingenciaCtrll extends ControllerBase {

	@Autowired
	private ContingenciaServ serv;
	
	@Getter
	@Setter
	private String folioSol;
	
	@Getter
	private List<Sello> sellos;
	
	@Getter
	@Setter
	private List<Sello> filtro;
	
	@Getter
	@Setter
	private Sello selectedSello;
	
	
	
	@Getter
	@Setter
	private String noSol;
	
	@Getter
	@Setter
	private String opcion;

	public String getSello() {
		if(selectedSello != null) {
			return selectedSello.getSello();
		}
		return null;
	}

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			
			limpiar();
			
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}

	private void limpiar() {
		folioSol = null;
		sellos = null;
		selectedSello = null;
		noSol = null;
		opcion = null;
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		Sello sell = (Sello) value;
		return sell.getSello().toLowerCase().contains(filterText)
				|| sell.getNss().toLowerCase().contains(filterText)
				|| sell.getCurp().toLowerCase().contains(filterText)
				|| sell.getEstatusFolio().toLowerCase().contains(filterText);
	}
	
	public void buscarSellos() {
		ProcessResult pr = new ProcessResult();
		try {
			sellos = null;
			selectedSello = null;
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar sellos");
			if (isFormValid(pr)) {
				SellosOut res = serv.sellos(Integer.valueOf(folioSol));
				if (res.getEstatus() == 1) { 
					sellos = res.getSellos();
					
					sellos = new ArrayList<Sello>(); // TODO: Quitar dummy
					
					Sello s1 = new Sello();
					s1.setSello("Sello");
					s1.setCurp("CURP");
					s1.setNss("0123456789");
					s1.setEstatusFolio("Estatus");
					sellos.add(s1);
					
					Sello s2 = new Sello();
					s2.setSello("Sello2");
					s2.setCurp("CURP");
					s2.setNss("987654321");
					s2.setEstatusFolio("Estatus2");
					sellos.add(s2);
					
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
					PrimeFaces.current().executeScript("PF('listaSellos').clearFilters()");
					PrimeFaces.current().executeScript("PF('dlg2').show();");
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
	
	
	public boolean isFormValid(ProcessResult pr) {
		if (folioSol == null || folioSol.equals("")) {
			UIInput fini = (UIInput) findComponent("folioSol");
			fini.setValid(false);

			pr.setStatus("Debe introducir un folio de solicitud");
			return false;
		} else {
			if(!ValidateUtil.isInteger(folioSol)) {
				UIInput fini = (UIInput) findComponent("folioSol");
				fini.setValid(false);

				pr.setStatus("Debe introducir un folio válido");
				return false;
			}
		}
		
		return true;
	}

	public void asignar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Asignar");
			if (selectedSello != null) {
				BaseOut res = serv.asignar(selectedSello.getSello(), folioSol);
				if (res.getEstatus() == 1) { 
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
					}
				}
			} else {
				UIInput fini = (UIInput) findComponent("sello");
				fini.setValid(false);

				pr.setStatus("Debe elegir un sello");
			}
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	public boolean isFormValid2(ProcessResult pr) {
		if (noSol == null || noSol.equals("")) {
			UIInput fini = (UIInput) findComponent("noSol");
			fini.setValid(false);

			pr.setStatus("Debe introducir un número de solicitud");
			return false;
		}
		if (opcion == null) {
			UIInput radio = (UIInput) findComponent("opcion");
			radio.setValid(false);

			pr.setStatus("Debe elegir un tipo de envío");
			return false;
		}
		return true;
	}
	
	public void enviarExpediente() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Enviar expediente");
			if (isFormValid2(pr)) {
				BaseOut res = serv.expediente(opcion, noSol); //opcion="MASI" || "lo que sea para individual" 
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
		} catch (Exception e) {
			resultados.add(GenericException(e));
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
}
