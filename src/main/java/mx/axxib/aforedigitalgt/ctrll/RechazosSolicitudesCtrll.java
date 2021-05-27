package mx.axxib.aforedigitalgt.ctrll;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.CatRechazos;
import mx.axxib.aforedigitalgt.eml.CatRechazosOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteIn;
import mx.axxib.aforedigitalgt.eml.GeneraReporteOut;
import mx.axxib.aforedigitalgt.eml.RechazosOut;
import mx.axxib.aforedigitalgt.serv.RechazosSolicitudesServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Controlador de rechazo de solicitudes
//** Interventor Principal: JSAS
//** Fecha Creación: 29/Ene/2021
//** Última Modificación:
//***********************************************//
@Scope(value = "session")
@Component(value = "rechazos")
@ELBeanName(value = "rechazos")
public class RechazosSolicitudesCtrll extends ControllerBase {

	@Autowired
	private RechazosSolicitudesServ rechazosService;

	@Getter
	@Setter
	private String valor;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	@Setter
	private String nombreArchivo;

	@Getter
	private RechazosOut rechazo;
	
	@Getter
	@Setter
	private CatRechazos rechazo1;
	
	@Getter
	@Setter
	private CatRechazos rechazo2;
	
	@Getter
	@Setter
	private CatRechazos rechazo3;

	
	@Getter
	private boolean deshabilitado;
	
	@Getter
	List<CatRechazos> catRechazos;

	@Getter
	@Setter
	private Integer opcion;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			limpiar();
			
			opcion = 1;
			valor = null;

			fechaInicial = null;
			fechaFinal = null;
			nombreArchivo = null;

			
			catRechazos = null;

			obtenerCatalogo();
			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}
	
	private void limpiar() {
		rechazo = null;
		deshabilitado = true;
		rechazo1 = null;
		rechazo2 = null;
		rechazo3 = null;
	}
	
	public boolean isFormValid(ProcessResult pr) {
		if (valor == null || valor.equals("")) {
			UIInput fini = (UIInput) findComponent("valor");
			fini.setValid(false);
			pr.setStatus("Valor es requerido");
			return false;
		} else {
			if(opcion == 1) {
				if(!ValidateUtil.isInteger(valor) || valor.length()<6) {
					UIInput fini = (UIInput) findComponent("valor");
					fini.setValid(false);
					pr.setStatus("Folio no válido");
					return false;
				}
			} else if(opcion == 2) {
				if(!ValidateUtil.isInteger(valor) || valor.length()<6) {
					UIInput fini = (UIInput) findComponent("valor");
					fini.setValid(false);
					pr.setStatus("Resolución no válido");
					return false;
				}
			} else if(opcion == 3) {
				if(!ValidateUtil.isNSS(valor)) {
					UIInput fini = (UIInput) findComponent("valor");
					fini.setValid(false);
					pr.setStatus("NSS no válido");
					return false;
				}
			}
		}

		return true;
	}

	public void buscar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Buscar");
			limpiar();
			if (isFormValid(pr)) {
				RechazosOut res = rechazosService.getConsultaRechazos(valor, opcion);
				if (res.getEstatus() == 1) {
					rechazo = res;
					//deshabilitado = false; //TODO quitar
					
					if(rechazo.getCausaRechazo() != null) {
						Optional<CatRechazos> match1 = catRechazos.stream().
							    filter(p -> p.getClave().equals( Integer.parseInt(rechazo.getCausaRechazo()) )).
							    findFirst();
						rechazo1 = match1.get();
						
						if(rechazo.getCausaRechazo2() != null) {
							Optional<CatRechazos> match2 = catRechazos.stream().
								    filter(p -> p.getClave().equals( Integer.parseInt(rechazo.getCausaRechazo2()) )).
								    findFirst();
							rechazo2 = match2.get();
						}
						
						if(rechazo.getCausaRechazo3() != null) {
							Optional<CatRechazos> match3 = catRechazos.stream().
								    filter(p -> p.getClave().equals( Integer.parseInt(rechazo.getCausaRechazo3()) )).
								    findFirst();
							rechazo3 = match3.get();
						}
						
					} else {
						deshabilitado = false;
					}
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
			pr = GenericException(e);
		} finally {
			pr.setFechaFinal(DateUtil.getNowDate());
			resultados.add(pr);
		}
	}

	public void obtenerCatalogo() {
		try {
			CatRechazosOut res = rechazosService.getCatalogo();
			catRechazos = res.getRechazos();
		} catch (Exception e) {
			GenericException(e);
		}
	}

	public boolean isFormValidR(ProcessResult pr) {
		if (fechaInicial == null) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);
			pr.setStatus("Fecha inicio es requerida");
			return false;
		}

		if (fechaFinal == null) {
			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);
			pr.setStatus("Fecha fin es requerida");
			return false;
		}

		if (!DateUtil.isValidDates(fechaInicial, fechaFinal)) {
			UIInput fini = (UIInput) findComponent("fechaInicial");
			fini.setValid(false);

			UIInput ffin = (UIInput) findComponent("fechaFinal");
			ffin.setValid(false);

			pr.setStatus("La fecha final debe ser mayor o igual a la inicial");
			return false;
		}

		if (nombreArchivo != null && !nombreArchivo.equals("")) {
			if (!ValidateUtil.isValidFileName(nombreArchivo)) {
				UIInput input = (UIInput) findComponent("archivo");
				input.setValid(false);
				pr.setStatus("Nombre de archivo no válido");
				return false;
			}
		} else {
			UIInput input = (UIInput) findComponent("archivo");
			input.setValid(false);
			pr.setStatus("Nombre de archivo es requerido");
			return false;
		}

		return true;
	}
	
	public boolean isFormValidRechazar(ProcessResult pr) {
		if (rechazo1 == null) {
			UIInput fini = (UIInput) findComponent("rechazo1");
			fini.setValid(false);
			pr.setStatus("Rechazo 1 es requerido");
			return false;
		}
		
		if(rechazo1 != null && rechazo2 != null && rechazo3 == null) {
			if(rechazo1.equals(rechazo2)) {
				UIInput fini = (UIInput) findComponent("rechazo1");
				fini.setValid(false);
				UIInput fini2 = (UIInput) findComponent("rechazo2");
				fini2.setValid(false);
				pr.setStatus("Los rechazos deben ser diferentes");
				return false;
			}
		}
		
		if(rechazo1 != null && rechazo2 != null && rechazo3 != null) {
			if(rechazo1.equals(rechazo2)) {
				UIInput fini = (UIInput) findComponent("rechazo1");
				fini.setValid(false);
				UIInput fini2 = (UIInput) findComponent("rechazo2");
				fini2.setValid(false);
				pr.setStatus("Los rechazos deben ser diferentes");
				return false;
			}
			if(rechazo1.equals(rechazo3)) {
				UIInput fini = (UIInput) findComponent("rechazo1");
				fini.setValid(false);
				UIInput fini2 = (UIInput) findComponent("rechazo3");
				fini2.setValid(false);
				pr.setStatus("Los rechazos deben ser diferentes");
				return false;
			}
			if(rechazo2.equals(rechazo3)) {
				UIInput fini = (UIInput) findComponent("rechazo2");
				fini.setValid(false);
				UIInput fini2 = (UIInput) findComponent("rechazo3");
				fini2.setValid(false);
				pr.setStatus("Los rechazos deben ser diferentes");
				return false;
			}
		}

		

		return true;
	}
	
	public void rechazar() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Rechazar");
			if (isFormValidRechazar(pr)) {
				
				if(rechazo1 != null) {
					rechazo.setCausaRechazo(rechazo1.getClave().toString());
					rechazo.setDescCausa(rechazo1.getDescripcion());
				}
				
				if(rechazo2 != null) {
					rechazo.setCausaRechazo2(rechazo2.getClave().toString());
					rechazo.setDescCausa2(rechazo2.getDescripcion());
				}
				
				if(rechazo3 != null) {
					rechazo.setCausaRechazo3(rechazo3.getClave().toString());
					rechazo.setDescCausa3(rechazo3.getDescripcion());
				}
				
				
				BaseOut res = rechazosService.ingresarRechazos(rechazo);
				
				if (res.getEstatus() == 1) {
					deshabilitado = true;
					pr.setStatus(
							aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
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

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Generar reporte");
			if (isFormValidR(pr)) {
				GeneraReporteIn parametros = new GeneraReporteIn();
				parametros.setFechaInicial(fechaInicial);
				parametros.setFechaFinal(fechaFinal);
				parametros.setNombreArchivo(nombreArchivo);
				GeneraReporteOut res = rechazosService.generaReporte(parametros);

				if (res.getEstatus() == 1) {
					pr.setStatus(
							aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null) + ": <br>" + res.getMensaje());
				} else {
					if (res.getEstatus() == 2) {
						GenerarErrorNegocio(res.getMensaje());
					} else if (res.getEstatus() == 0) {
						pr.setStatus(res.getMensaje());
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
