package mx.axxib.aforedigitalgt.reca.ctrll;

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
import mx.axxib.aforedigitalgt.ctrll.ControllerBase;
import mx.axxib.aforedigitalgt.reca.eml.IssSieforeOut;
import mx.axxib.aforedigitalgt.reca.eml.LoteSepaOut;
import mx.axxib.aforedigitalgt.reca.eml.MontosRecaOut;
import mx.axxib.aforedigitalgt.reca.eml.RecaIssSieforeOut;
import mx.axxib.aforedigitalgt.reca.eml.RespuestaOut;
import mx.axxib.aforedigitalgt.reca.serv.ActualizaSaldosBonoSepaServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Actualiza Saldos y Bono de Pensión - Separación
//** Interventor Principal: JJSC
//** Fecha Creación: 10/01/2021
//** Última Modificación:

@Scope(value = "session")
@Component(value = "actualizaSaldosBonoSepa")
@ELBeanName(value = "actualizaSaldosBonoSepa")
public class ActualizaSaldosBonoSepaCtrll extends ControllerBase{

	@Autowired
	private ActualizaSaldosBonoSepaServ service;
	
	@Getter
	@Setter
	private String lote;

	@Getter
	@Setter
	private List<LoteSepaOut> listLotes;

	@Getter
	@Setter
	private List<IssSieforeOut> listaIssSiefore;

	@Getter
	@Setter
	private IssSieforeOut selectedIssSiefore;

	@Getter
	@Setter
	private List<LoteSepaOut> filtro;

	@Getter
	@Setter
	private LoteSepaOut selectedLote;

	@Getter
	@Setter
	private LoteSepaOut lote1;

	@Getter
	@Setter
	MontosRecaOut montos;
	
	@Getter
	private String border;
	
	@Getter
	@Setter
	private Date fecha;
	
	@Getter
	@Setter
	private String fechaCadena;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			init = false;
			try {
				lote = null;
				lote1 = null;
				fecha=null;
				border="";
				montos = null;
				filtro = null;
				listLotes = null;
				fechaCadena=null;
				selectedLote = null;
				listaIssSiefore = null;
				selectedIssSiefore = null;
				getLotes();
				getCodInversion();
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getCodInversion() throws Exception {
		RecaIssSieforeOut resp = service.getIssSiefore();
		listaIssSiefore = resp.getListaIssSiefore();
	}

	public void getLotes() {
		try {
			if (listLotes == null) {
				listLotes = service.getSeparacionLote().getLotesSepa();
			} else {
				PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
			}
			lote = null;
		} catch (Exception e) {
			GenericException(e);
		}

	}

	public void consultar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Consultar montos");

			if (isFormValid(pr)) {
				RespuestaOut res = service.getMontosSepa(lote,Integer.valueOf(selectedIssSiefore.getCod_inversion()));
				if(res.getOn_Estatus()==null) {
					montos = res.getMontosRecaIsss();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				}else {
					if(res.getOn_Estatus()==2) {
						GenerarErrorNegocio("Error en base de datos");
					}
				}							
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public boolean isFormValid(ProcessResult pr) {
		if (lote == null) {
			border = "2px solid #ff0028 !important;";
			pr.setStatus("Se requiere el número de lote");
			pr.setFechaFinal(DateUtil.getNowDate());
			return false;
		} else {
			border="";			
			if(fecha==null) {
				UIInput radio = (UIInput) findComponent("fechaAplicado");
				radio.setValid(false);
				pr.setDescProceso("Fecha de aplicado");
				pr.setStatus("La fecha de aplicado es requerida");
				return false;
			}else {
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				fechaCadena = dateFormat.format(fecha);
				if (selectedIssSiefore == null) {			
					pr.setDescProceso("Selección de código de inversión");
					pr.setStatus("El código de inversión es requerido");
					return false;
				}
			}			
		}
		return true;
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteSepaOut car = (LoteSepaOut) value;
		return car.getLote_carga().toString().contains(filterText);
	}

	public void onRowSelect(SelectEvent<LoteSepaOut> event) {
		lote1 = new LoteSepaOut();
		lote1.setLote_carga(event.getObject().getLote_carga());
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			lote = lote1.getLote_carga();
		}
	}
	public void ejecutar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Ejecutar");

			if (isFormValid(pr)) {
				RespuestaOut res = service.ejecutarSeparacion(lote,fechaCadena,selectedIssSiefore.getCod_inversion());
				if (res.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getOc_Mensaje());
					} else if (res.getOn_Estatus() == 0) {
						pr.setStatus(res.getOc_Mensaje());
					}
				}				
			}
		} catch (Exception e) {
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}
	
	
}
