package mx.axxib.aforedigitalgt.reca.ctrll;

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
import mx.axxib.aforedigitalgt.reca.eml.LiquidarLoteOp71Out;
import mx.axxib.aforedigitalgt.reca.eml.LoteOp71Out;
import mx.axxib.aforedigitalgt.reca.eml.OperacionOut;
import mx.axxib.aforedigitalgt.reca.eml.SectorOut;
import mx.axxib.aforedigitalgt.reca.eml.SieforeOut;
import mx.axxib.aforedigitalgt.reca.serv.LiquidarLoteOp71Serv;
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de Liquidar lote OP71
//** Interventor Principal: JJSC
//** Fecha Creación: 18/01/2022
//** Última Modificación:
//***********************************************//

@Scope(value = "session")
@Component(value = "liquidarLoteOp71")
@ELBeanName(value = "liquidarLoteOp71")
public class LiquidarLoteOp71Ctrll extends ControllerBase {

	@Autowired
	private LiquidarLoteOp71Serv service;

	@Getter
	@Setter
	private String lote;

	@Getter
	@Setter
	private List<LoteOp71Out> listLotes;

	@Getter
	@Setter
	private List<LoteOp71Out> filtro;

	@Getter
	@Setter
	private LoteOp71Out selectedLote;
	@Getter
	@Setter
	private LoteOp71Out lote1;
	@Getter
	@Setter
	private Date fecha;
	@Getter
	@Setter
	private List<OperacionOut> listaOperaciones;
	@Getter
	@Setter
	private String id_operacion;
	@Getter
	@Setter
	private Integer consecutivo_dia;
	@Getter
	@Setter
	private List<SieforeOut> listaSiefore;
	@Getter
	@Setter
	private SieforeOut selectedSiefore;
	@Getter
	@Setter
	private List<SectorOut> listaSectores;
	@Getter
	@Setter
	private SectorOut selectedSector;
	@Getter
	@Setter
	private Integer fec_lote;

	@Getter
	private Date fecActual;

	@Getter
	@Setter
	private String border;

	@Getter
	@Setter
	private String agrupacion;

	@Getter
	@Setter
	private boolean band;

	@Getter
	@Setter
	private String selected;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			try {
				init = false;
				band = false;
				lote = null;
				lote1 = null;
				fecha = null;
				border = null;
				filtro = null;
				selected = null;
				listLotes = null;
				agrupacion = null;
				selectedLote = null;
				listaSiefore = null;
				selectedSiefore = null;
				listaOperaciones = null;
				fecActual = DateUtil.getNowDate();
				getLotes();
				getSiefore();
				getSectores();				
			} catch (Exception e) {
				resultados.add(GenericException(e));
			}
		}
	}

	public void getSiefore() throws Exception {
		LiquidarLoteOp71Out resp = service.getSiefore();
		if (resp.getOn_Estatus() == null) {
			listaSiefore = resp.getListaSiefore();
		} else {
			if (resp.getOn_Estatus() == 2) {
				GenerarErrorNegocio(resp.getOc_Mensaje());
			}
		}
	}

	public void getSectores() throws Exception {
		LiquidarLoteOp71Out resp = service.getSectores();
		if (resp.getOn_Estatus() == null) {
			listaSectores = resp.getListaSectores();
		} else {
			if (resp.getOn_Estatus() == 2) {
				GenerarErrorNegocio(resp.getOc_Mensaje());
			}
		}
	}

	public void getLotes() throws Exception {
		try {
			if (listLotes == null) {
				LiquidarLoteOp71Out resp = service.getLote();
				if (resp.getOn_Estatus() == null) {
					listLotes = resp.getListaLotes();
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
					}
				}
			} else {
				PrimeFaces.current().executeScript("PF('listaLotes').clearFilters()");
			}
			lote = null;
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		LoteOp71Out car = (LoteOp71Out) value;
		return car.getFec_lote().toString().contains(filterText);
	}

	public void onRowSelect(SelectEvent<LoteOp71Out> event) {
		lote1 = new LoteOp71Out();
		lote1.setId_operacion(event.getObject().getId_operacion());
		lote1.setConsecutivo_dia(event.getObject().getConsecutivo_dia());
		lote1.setFec_lote(event.getObject().getFec_lote());
	}

	public void opcionSeleccionada2() {
		if (lote1 != null) {
			// lote = lote1.getId_operacion();
			id_operacion = lote1.getId_operacion();
			consecutivo_dia = lote1.getConsecutivo_dia();
			fec_lote = lote1.getFec_lote();
		}
	}

	public void consultar() {
		ProcessResult pr = new ProcessResult();

		try {
			pr.setDescProceso("Consultar montos");
			pr.setFechaInicial(DateUtil.getNowDate());
			// LiquidarLoteOp71Out resp = service.getDetalle("71", "20191223", "799", "%",
			// "TOD");

			if (isFormValid(pr)) {
				LiquidarLoteOp71Out resp = service.getDetalle(id_operacion, Integer.toString(fec_lote),
						Integer.toString(consecutivo_dia), selectedSiefore.getCod_inversion(), agrupacion);
				if (resp.getOn_Estatus() == null) {
					listaOperaciones = resp.getListaOperaciones();
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (resp.getOn_Estatus() == 2) {
						GenerarErrorNegocio(resp.getOc_Mensaje());
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
		if (id_operacion == null) {
			border = "2px solid #ff0028 !important;";
			pr.setStatus("Por favor seleccione la información requerida");
			pr.setFechaFinal(DateUtil.getNowDate());			
			return false;
		} else {
			if (selectedSiefore == null) {
				UIInput siefore = (UIInput) findComponent("combSiefore");
				siefore.setValid(false);
				pr.setStatus("Por favor seleccione Siefore");
				pr.setFechaFinal(DateUtil.getNowDate());				
				return false;
			} else {
				if (agrupacion == null) {
					UIInput siefore = (UIInput) findComponent("combAgrupacion");
					siefore.setValid(false);
					pr.setStatus("Por favor seleccione agrupación");
					pr.setFechaFinal(DateUtil.getNowDate());					
					return false;
				}
			}
		}
		return true;
	}

	public void liquidar() {
	}

	public void habilitar() {
		if (band) {
			selected = "1";
		} else {
			selected = "0";
		}
	}
}
