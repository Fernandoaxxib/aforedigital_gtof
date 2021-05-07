package mx.axxib.aforedigitalgt.ctrll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.BaseOut;
import mx.axxib.aforedigitalgt.eml.GeneraReporteUMAIn;
import mx.axxib.aforedigitalgt.eml.ValorUMA;
import mx.axxib.aforedigitalgt.eml.ValorUMAOut;
import mx.axxib.aforedigitalgt.serv.ValorUMAServ;
import mx.axxib.aforedigitalgt.util.DateUtil;
import mx.axxib.aforedigitalgt.util.ValidateUtil;

@Scope(value = "session")
@Component(value = "valorUMA")
@ELBeanName(value = "valorUMA")
public class ValorUMACtrll extends ControllerBase {

	@Autowired
	HikariDataSource dataSource;
	
	@Autowired
	private ValorUMAServ valorUMAService;

	@Getter
	@Setter
	private Date fechaInicial;

	@Getter
	@Setter
	private Date fechaFinal;

	@Getter
	private String ruta;

	@Getter
	private List<ValorUMA> valores;
	

	private String valorUMA;
	
	public String getValorUMA() {
		return valorUMA;
	}




	public void setValorUMA(String valorUMA) {
		this.valorUMA = valorUMA;
	}

	@Getter
	@Setter
	private Date fechaUMA;
	
	@Getter
	private ValorUMA seleccionado;
	
	@Getter
	private String mensaje;
	
	private int modo; //0=no definido, 1=nuevo, 2=edicion 3=eliminar
	
	private ProcessResult prG;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			mensaje = null;
			modo = 0;
			fechaInicial = null;
			fechaFinal = null;
			ruta = "/RESPALDOS/operaciones";
			valores = new ArrayList<ValorUMA>();

			valorUMA();

			// Cancelar inicialización sobre la misma pantalla
			init = false;
		}
	}
	
	
	
	
	public void nuevo() {
		seleccionado = null;
		mensaje = "Nuevo valor UMA";
		modo = 1;
		prG = new ProcessResult();
		prG.setFechaInicial(DateUtil.getNowDate());
		prG.setDescProceso("Nuevo valor UMA");
		valorUMA = null;
		fechaUMA = null;
	}
	
	public void editar(ValorUMA valor) {
		seleccionado = valor;
		mensaje = "Editar valor UMA";
		modo = 2;
		prG = new ProcessResult();
		prG.setFechaInicial(DateUtil.getNowDate());
		prG.setDescProceso("Editar valor UMA");
		valorUMA = valor.getMonto().toString();
		fechaUMA = valor.getFecha();
	}
	
	public void borrar(ValorUMA valor) {
		seleccionado = valor;
		modo = 3;
		prG = new ProcessResult();
		prG.setFechaInicial(DateUtil.getNowDate());
		prG.setDescProceso("Borrar valor UMA");
	}
	
	public void guardar() {
		if(modo == 1 || modo == 2) {
			
			if(!ValidateUtil.isDouble(valorUMA)) {
				UIInput fini = (UIInput) findComponent("valorUMA");
				fini.setValid(false);
				prG.setStatus("Valor UMA no válido");
				resultados.add(prG);
				return;
			}
			
			if (fechaUMA == null) {
				UIInput fini = (UIInput) findComponent("fechaUMA");
				fini.setValid(false);
				prG.setStatus("Fecha UMA es requerida");
				resultados.add(prG);
				return;
			}			
		}
		
		if(modo==1) {
			insertar();
		} else if(modo==2) {
			actualizar();
		} else if(modo==3) {
			eliminar();
		}
	}
	
	private void insertar() {
		try {
			ValorUMA parametros = new ValorUMA();
			parametros.setFecha(fechaUMA);
			parametros.setMonto(new BigDecimal (valorUMA));
			parametros.setUser(dataSource.getUsername());
			BaseOut res = valorUMAService.insertarUMA(parametros );
			if (res.getEstatus() == 1) {
				
				prG.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				ValorUMAOut val = valorUMAService.getValoresUMA();
				if (val.getEstatus() == 1) {
					valores = val.getValores();
				}
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					prG.setStatus(res.getMensaje());
				}
			}
		} catch (Exception e) {
			prG = GenericException(e);
		} finally {
			prG.setFechaFinal(DateUtil.getNowDate());
			resultados.add(prG);
		}

	}


	private void actualizar() {
		try {
			seleccionado.setMonto(new BigDecimal(valorUMA));
			if(fechaUMA.compareTo(seleccionado.getFecha()) != 0)
				seleccionado.setFechaNueva(fechaUMA);
			BaseOut res = valorUMAService.actualizarUMA(seleccionado);
			if (res.getEstatus() == 1) {
				
				prG.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				ValorUMAOut val = valorUMAService.getValoresUMA();
				if (val.getEstatus() == 1) {
					valores = val.getValores();
				}
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					prG.setStatus(res.getMensaje());
				}
			}
		} catch (Exception e) {
			prG = GenericException(e);
		} finally {
			prG.setFechaFinal(DateUtil.getNowDate());
			resultados.add(prG);
		}
	}
	

	private void eliminar() {
		try {
			BaseOut res = valorUMAService.eliminarUMA(seleccionado);
			if (res.getEstatus() == 1) {
				
				prG.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				ValorUMAOut val = valorUMAService.getValoresUMA();
				if (val.getEstatus() == 1) {
					valores = val.getValores();
				}
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					prG.setStatus(res.getMensaje());
				}
			}
		} catch (Exception e) {
			prG = GenericException(e);
		} finally {
			prG.setFechaFinal(DateUtil.getNowDate());
			resultados.add(prG);
		}
	}

	

	
	

	public void valorUMA() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Obtener valores UMA");

//			ValorUMA v1 = new ValorUMA();
//			v1.setUser("User1");
//			v1.setMonto(new BigDecimal(10));
//			v1.setFecha(new Date());
//			v1.setFechaUltAct(new Date());
//
//			ValorUMA v2 = new ValorUMA();
//			v2.setUser("User2");
//			v2.setMonto(new BigDecimal(11));
//			v2.setFecha(new Date());
//			v2.setFechaUltAct(new Date());
//
//			ValorUMA v3 = new ValorUMA();
//			v3.setUser("User3");
//			v3.setMonto(new BigDecimal(12));
//			v3.setFecha(new Date());
//			v3.setFechaUltAct(new Date());
//
//			valores.add(v1);
//			valores.add(v2);
//			valores.add(v3);


			ValorUMAOut res = valorUMAService.getValoresUMA();
			if (res.getEstatus() == 1) {
				valores = res.getValores();
				pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
			} else {
				if (res.getEstatus() == 2) {
					GenerarErrorNegocio(res.getMensaje());
				} else if (res.getEstatus() == 0) {
					pr.setStatus(res.getMensaje());
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

		return true;
	}

	public void generarReporte() {
		ProcessResult pr = new ProcessResult();
		try {
			pr.setFechaInicial(DateUtil.getNowDate());
			pr.setDescProceso("Generar reporte");

			if (isFormValid(pr)) {
				GeneraReporteUMAIn parametros = new GeneraReporteUMAIn();
				parametros.setFechaInicial(fechaInicial);
				parametros.setFechaFinal(fechaFinal);
				parametros.setRuta(ruta);
				BaseOut res = valorUMAService.getGeneraReporte(parametros);
				if (res.getEstatus() == 1) {
					pr.setStatus(
							aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null) + ": <BR>" + res.getMensaje());
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
