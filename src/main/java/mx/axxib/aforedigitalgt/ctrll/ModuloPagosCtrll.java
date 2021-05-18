package mx.axxib.aforedigitalgt.ctrll;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import mx.axxib.aforedigitalgt.com.ConstantesMsg;
import mx.axxib.aforedigitalgt.com.ProcessResult;
import mx.axxib.aforedigitalgt.eml.EjecucionResult;
import mx.axxib.aforedigitalgt.serv.ModPagosServ;
import mx.axxib.aforedigitalgt.util.DateUtil;

@Scope(value = "session")
@Component(value = "moduloPagos")
@ELBeanName(value = "moduloPagos")
public class ModuloPagosCtrll extends ControllerBase {

	@Autowired
	private ModPagosServ service;

	@Getter
	@Setter
	private String tiposPagos;
	@Getter
	@Setter
	private String institucion;
	@Getter
	@Setter
	private String pagosAfiliados;
	@Getter
	@Setter
	private String procesosRetiros;
	@Getter
	@Setter
	private Date fechaProceso;
	@Getter
	@Setter
	private Date fechaProcesoPagos;
	@Getter
	private String msj;
	@Getter
	private String ruta;
	@Getter
	private String archivo;
	@Getter
	private Date fecActual;

	@Override
	public void iniciar() {
		super.iniciar();
		if (init) {
			fechaProceso = DateUtil.getNowDate();
			fechaProcesoPagos = DateUtil.getNowDate();
			refresh();
			fecActual = DateUtil.getNowDate();
			msj = "";
			ruta = "";
			archivo = "";
			tiposPagos=null;
			institucion=null;
			pagosAfiliados=null;
			procesosRetiros=null;
		}
	}

	public void fechaProceso() {
	}

	public void refresh() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Proceso inicial");
		try {
			if (fechaProceso != null && fechaProcesoPagos != null) {
				EjecucionResult res = service.refresh("OK", fechaProceso, fechaProcesoPagos);
				if (res.getOn_Estatus() == 1) {
					pr.setStatus(aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null));
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getOcMensaje());
					} else if (res.getOn_Estatus() == 0) {
						pr.setStatus(res.getOcMensaje());
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

	public void generarFondos() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Genera interface fondos");
		if (fechaProceso != null && pagosAfiliados != null) {
			try {
				EjecucionResult res = service.generarFondos(fechaProceso, procesosRetiros, tiposPagos, pagosAfiliados,
						institucion);
				if (res.getOn_Estatus() == 1) {
					msj = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					ruta = "Ruta= /iprod/PROCESAR/RECEPCION/AFORE/RETIROS/";
					msj = msj.concat(" , ").concat(res.getOcMensaje()).concat(" , ").concat(ruta);
					pr.setStatus(msj);
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getOcMensaje());
					} else if (res.getOn_Estatus() == 0) {
						msj = res.getOcMensaje();
						pr.setStatus(res.getOcMensaje());
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

	public void generarPagos() {
		ProcessResult pr = new ProcessResult();
		pr.setFechaInicial(DateUtil.getNowDate());
		pr.setDescProceso("Genera interface pagos");
		if (procesosRetiros != null && tiposPagos != null && institucion != null && fechaProcesoPagos != null) {
			try {
				EjecucionResult res = service.generarPagos(fechaProcesoPagos, procesosRetiros, institucion, tiposPagos);
				if (res.getOn_Estatus() == 1) {
					obtenerRutaNombre();
					msj = aforeMessage.getMessage(ConstantesMsg.EJECUCION_SP_OK, null);
					if(!ruta.isEmpty()) {
						msj = msj.concat(" - ARCHIVOS GENERADOS: ").concat(archivo).concat(" - RUTA: ").concat(ruta);
					}else {
						msj = msj.concat(" - ").concat(archivo);
					}
					
					
					pr.setStatus(msj);
				} else {
					if (res.getOn_Estatus() == 2) {
						GenerarErrorNegocio(res.getOcMensaje());
					} else if (res.getOn_Estatus() == 0) {
						msj = res.getOcMensaje();
						pr.setStatus(res.getOcMensaje());
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

	public void obtenerRutaNombre() {
		this.archivo = "";
		this.ruta = "/iprod/PROCESAR/RECEPCION/AFORE/RETIROS/";

		switch (procesosRetiros) {
		case "DISP":
			proceso1();
			break;

		case "PARC":
			proceso2();
			break;

		case "VOLUN":
			proceso3();
			break;

		case "PMG":
			proceso4();
			break;

		case "TOD_RET":
			proceso5();
			break;

		}
	}

	public void proceso1() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String f = format.format(fechaProcesoPagos);

		if (institucion.equals("IS")) {
			switch (tiposPagos) {
			case "TI":
				archivo = "No se tienen Retiros de Transferencias Interbancarias del ISSSTE";
				ruta = "";
				break;

			case "OP":
				archivo = "No se tienen Disposiciones ISSSTE Ordenes de Pago";
				ruta = "";
				break;

			case "CH":
			case "TOD_PAG":
				archivo = archivo.concat("CH_DISP_IS_").concat(f).concat(".txt");				
				break;

			case "TDP":
				archivo = archivo.concat("No se tienen TDP (Tarjeta de prepago) Del ISSSTE");
				ruta = "";
				break;
			}
		} else {
			if (institucion.equals("IM")) {
				switch (tiposPagos) {
				case "TI":
					archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt");					
					break;

				case "OP":
					archivo = archivo.concat("OP_DISP_IM_").concat(f).concat(".txt");					
					break;

				case "CH":
					archivo = archivo.concat("CH_DISP_IM_").concat(f).concat(".txt");					
					break;

				case "TDP":
					archivo = archivo.concat("TDP_DISP_IM_").concat(f).concat(".txt");					
					break;

				case "TOD_PAG":
					archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt, TI_DISP_IS_").concat(f)
							.concat(".txt, OP_DISP_IM_").concat(f).concat(".txt, CH_DISP_IM_").concat(f)
							.concat(".txt, TDP_DISP_IM_").concat(f).concat(".txt");					
					break;
				}
			} else {
				if (institucion.equals("TOD_INST")) {
					switch (tiposPagos) {
					case "TI":
						archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt, TI_DISP_IS_").concat(f)
								.concat(".txt");						
						break;

					case "OP":
						archivo = archivo.concat("OP_DISP_IM_").concat(f).concat(".txt");						
						break;

					case "CH":
						archivo = archivo.concat("CH_DISP_IM_").concat(f).concat(".txt, CH_DISP_IS_").concat(f)
								.concat(".txt");						
						break;

					case "TDP":
						archivo = archivo.concat("TDP_DISP_IM_").concat(f).concat(".txt");						
						break;

					case "TOD_PAG":
						archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt, TI_DISP_IS_").concat(f)
								.concat(".txt, OP_DISP_IM_").concat(f).concat(".txt, CH_DISP_IM_").concat(f)
								.concat(".txt, CH_DISP_IS_").concat(f).concat(".txt, TDP_DISP_IM_").concat(f)
								.concat(".txt");						
						break;
					}
				}
			}
		}
	}

	public void proceso2() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String f = format.format(fechaProcesoPagos);

		if (institucion.equals("IS")) {

			switch (tiposPagos) {
			case "TI":
				archivo = archivo.concat("TI_PARC_IS_").concat(f).concat(".txt");				
				break;

			case "OP":
				archivo = archivo.concat("OP_PARC_IS_").concat(f).concat(".txt");				
				break;

			case "CH":
				archivo = archivo.concat("CH_PARC_IS_").concat(f).concat(".txt");				
				break;

			case "TDP":
				archivo = archivo.concat("No se tienen TDP Parciales del ISSSTE");
				ruta = "";
				break;

			case "TOD_PAG":
				archivo = archivo.concat("TI_PARC_IS_").concat(f).concat(".txt, OP_PARC_IS_").concat(f)
						.concat(".txt, CH_PARC_IS_").concat(f).concat(".txt");				
				break;
			}
		} else {
			if (institucion.equals("IM")) {

				switch (tiposPagos) {
				case "TI":
					archivo = archivo.concat("TI_PARC_IM_").concat(f).concat(".txt");					
					break;

				case "OP":
					archivo = archivo.concat("OP_PARC_IM_").concat(f).concat(".txt");					
					break;

				case "CH":
					archivo = archivo.concat("CH_PARC_IM_").concat(f).concat(".txt");					
					break;

				case "TDP":
					archivo = "No se tienen TDP Parciales del IMSS";
					ruta = "";
					break;

				case "TOD_PAG":
					archivo = archivo.concat("TI_PARC_IM_").concat(f).concat(".txt, OP_PARC_IM_").concat(f)
							.concat(".txt, CH_PARC_IM_").concat(f).concat(".txt");					
					break;
				}
			} else {
				if (institucion.equals("TOD_INST")) {
					switch (tiposPagos) {
					case "TI":
						archivo = archivo.concat("TI_PARC_IM_").concat(f).concat(".txt, TI_PARC_IS_").concat(f)
								.concat(".txt");						
						break;

					case "OP":
						archivo = archivo.concat("OP_PARC_IM_").concat(f).concat(".txt, OP_PARC_IS_").concat(f)
								.concat(".txt");						
						break;

					case "CH":
						archivo = archivo.concat("CH_PARC_IM_").concat(f).concat(".txt, CH_PARC_IS_").concat(f)
								.concat(".txt");						
						break;

					case "TDP":
						archivo = "No se tienen TDP Parciales";
						ruta = "";
						break;

					case "TOD_PAG":
						archivo = archivo.concat("TI_PARC_IM_").concat(f).concat(".txt, TI_PARC_IS_").concat(f)
								.concat(".txt, OP_PARC_IM_").concat(f).concat(".txt, OP_PARC_IS_").concat(f)
								.concat(".txt, CH_PARC_IM_").concat(f).concat(".txt, CH_PARC_IS_").concat(f)
								.concat(".txt");						
						break;
					}
				}
			}
		}
	}

	public void proceso3() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String f = format.format(fechaProcesoPagos);

		if (institucion.equals("IS")) {

			switch (tiposPagos) {
			case "TI":
				archivo = "No se tienen Tranferencias Interbancarias Voluntarias De ISSSTE";
				ruta = "";
				break;

			case "OP":
				archivo = "No se tienen Ordenes de Pago Voluntarias De ISSSTE";
				ruta = "";
				break;

			case "CH":
				archivo = "No se tienen Cheques Voluntarias De ISSSTE";
				ruta = "";
				break;

			case "TDP":
				archivo = "No se tienen Tarjetas de Prepago Voluntarias Del ISSSTE";
				ruta = "";
				break;

			case "TOD_PAG":
				archivo = "No se tienen Tranferencias Interbancarias Voluntarias De ISSSTE";
				ruta = "";
				break;
			}
		} else {
			if (institucion.equals("IM")) {

				switch (tiposPagos) {
				case "TI":
					archivo = archivo.concat("TI_VOLUN_IM_").concat(f).concat(".txt");					
					break;

				case "OP":
					archivo = archivo.concat("OP_VOLUN_IM_").concat(f).concat(".txt");					
					break;

				case "CH":
					archivo = archivo.concat("CH_VOLUN_IM_").concat(f).concat(".txt");					
					break;

				case "TDP":
					archivo = "No se tienen Tarjetas de Prepago Voluntarias Del IMSS";
					ruta = "";
					break;

				case "TOD_PAG":
					archivo = archivo.concat("TI_VOLUN_IM_").concat(f).concat(".txt, OP_VOLUN_IM_").concat(f)
							.concat(".txt, CH_VOLUN_IM_").concat(f).concat(".txt");					
					break;
				}
			} else {
				if (institucion.equals("TOD_INST")) {
					switch (tiposPagos) {
					case "TI":
						archivo = archivo.concat("TI_VOLUN_IM_").concat(f).concat(".txt");						
						break;

					case "OP":
						archivo = archivo.concat("OP_VOLUN_IM_").concat(f).concat(".txt");						
						break;

					case "CH":
						archivo = archivo.concat("CH_VOLUN_IM_").concat(f).concat(".txt");					
						break;

					case "TDP":
						archivo = "No se tienen TDP Voluntarios";
						ruta = "";
						break;

					case "TOD_PAG":
						archivo = archivo.concat("TI_VOLUN_IM_").concat(f).concat(".txt, OP_VOLUN_IM_").concat(f)
								.concat(".txt, CH_VOLUN_IM_").concat(f).concat(".txt");						
						break;
					}
				}
			}
		}
	}

	public void proceso4() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String f = format.format(fechaProcesoPagos);

		if (institucion.equals("IS")) {

			switch (tiposPagos) {
			case "TI":
				archivo = "No se tienen Tranferencias Interbancarias pmg DeL ISSSTE";
				ruta = "";
				break;

			case "OP":
				archivo = "No se tienen Ordenes de Pago PMG Del ISSSTE";
				ruta = "";
				break;

			case "CH":
				archivo = "No se tienen Cheques PMG Del ISSSTE";
				ruta = "";
				break;

			case "TDP":
				archivo = "No se tienen TDP de Retiros PMG del ISSSTE Pago PMG Del ISSSTE";
				ruta = "";
				break;

			case "TOD_PAG":
				archivo = "No se tienen  PMG Del ISSSTE de ningun tipo de pago";
				ruta = "";
				break;
			}
		} else {
			if (institucion.equals("IM")) {

				switch (tiposPagos) {
				case "TI":
					archivo = archivo.concat("TI_PMG_IM_").concat(f).concat(".txt");					
					break;

				case "OP":
					archivo = archivo.concat("OP_PMG_IM_").concat(f).concat(".txt");					
					break;

				case "CH":
					archivo = archivo.concat("CH_PMG_IM_").concat(f).concat(".txt");					
					break;

				case "TDP":
					archivo = "No se tienen TDP de Retiros PMG del IMSS";
					ruta = "";
					break;

				case "TOD_PAG":
					archivo = archivo.concat("TI_PMG_IM_").concat(f).concat(".txt, OP_PMG_IM_").concat(f)
							.concat(".txt, CH_PMG_IM_").concat(f).concat(".txt");					
					break;
				}
			} else {
				if (institucion.equals("TOD_INST")) {
					switch (tiposPagos) {
					case "TI":
						archivo = archivo.concat("TI_PMG_IM_").concat(f).concat(".txt");						
						break;

					case "OP":
						archivo = archivo.concat("OP_PMG_IM_").concat(f).concat(".txt");						
						break;

					case "CH":
						archivo = archivo.concat("CH_PMG_IM_").concat(f).concat(".txt");						
						break;

					case "TDP":
						archivo = "No se tienen Tarjetas de Prepago PMG";
						ruta = "";
						break;

					case "TOD_PAG":
						archivo = archivo.concat("TI_PMG_IM_").concat(f).concat(".txt, OP_PMG_IM_").concat(f)
								.concat(".txt, CH_PMG_IM_").concat(f).concat(".txt");						
						break;
					}
				}
			}
		}
	}

	public void proceso5() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String f = format.format(fechaProcesoPagos);

		if (institucion.equals("IS")) {

			switch (tiposPagos) {
			case "TI":
				archivo = archivo.concat("TI_DISP_IS_").concat(f).concat(".txt, TI_PARC_IS_").concat(f).concat(".txt");				
				break;

			case "OP":
				archivo = archivo.concat("OP_DISP_IS_").concat(f).concat(".txt, OP_PARC_IS_").concat(f).concat(".txt");				
				break;

			case "CH":
				archivo = archivo.concat("CH_DISP_IS_").concat(f).concat(".txt, CH_PARC_IS_").concat(f).concat(".txt");				
				break;

			case "TDP":
				archivo = "No se tienen TDP de todos los retiros del ISSSTE";
				ruta = "";
				break;

			case "TOD_PAG":
				archivo = archivo.concat("TI_DISP_IS_").concat(f).concat(".txt, TI_PARC_IS_").concat(f)
						.concat(".txt, OP_DISP_IS_").concat(f).concat(".txt, OP_PARC_IS_").concat(".txt, CH_DISP_IS_")
						.concat(f).concat(".txt, CH_PARC_IS_").concat(f).concat(".txt");				
				break;
			}
		} else {
			if (institucion.equals("IM")) {

				switch (tiposPagos) {
				case "TI":
					archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt, TI_PARC_IM_").concat(f)
							.concat(".txt, TI_VOLUN_IM_").concat(f).concat(".txt, TI_PMG_IM_").concat(f).concat(".txt");					
					break;

				case "OP":
					archivo = archivo.concat("OP_DISP_IM_").concat(f).concat(".txt, OP_PARC_IM_").concat(f)
							.concat(".txt, OP_VOLUN_IM_").concat(f).concat(".txt, OP_PMG_IM_").concat(f).concat(".txt");					
					break;

				case "CH":
					archivo = archivo.concat("CH_DISP_IM_").concat(f).concat(".txt, CH_PARC_IM_").concat(f)
							.concat(".txt, CH_VOLUN_IM_").concat(f).concat(".txt, CH_PMG_IM_").concat(f).concat(".txt");					
					break;

				case "TDP":
					archivo = archivo.concat("TDP_DISP_IM_").concat(f).concat(".txt");					
					break;

				case "TOD_PAG":
					archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt, TI_PARC_IM_").concat(f)
							.concat(".txt, TI_VOLUN_IM_").concat(f).concat(".txt, TI_PMG_IM_").concat(f)
							.concat(".txt, ").concat("OP_DISP_IM_").concat(f).concat(".txt, OP_PARC_IM_").concat(f)
							.concat(".txt, OP_VOLUN_IM_").concat(f).concat(".txt, OP_PMG_IM_").concat(f)
							.concat(".txt, ").concat("CH_DISP_IM_").concat(f).concat(".txt, CH_PARC_IM_").concat(f)
							.concat(".txt, CH_VOLUN_IM_").concat(f).concat(".txt, CH_PMG_IM_").concat(f)
							.concat(".txt, TDP_DISP_IM_").concat(f).concat(".txt");					
					break;
				}
			} else {
				if (institucion.equals("TOD_INST")) {
					switch (tiposPagos) {
					case "TI":
						archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt, TI_DISP_IS_").concat(f)
								.concat(".txt, TI_PARC_IM_").concat(f).concat(".txt, TI_PARC_IS_")
								.concat(".txt, TI_VOLUN_IM_").concat(f).concat(".txt, TI_PMG_IM_").concat(f)
								.concat(".txt");						
						break;

					case "OP":
						archivo = archivo.concat("OP_DISP_IM_").concat(f).concat(".txt, OP_DISP_IS_").concat(f)
								.concat(".txt, OP_PARC_IM_").concat(f).concat(".txt, OP_PARC_IS_")
								.concat(".txt, OP_VOLUN_IM_").concat(f).concat(".txt, OP_PMG_IM_").concat(f)
								.concat(".txt");						
						break;

					case "CH":
						archivo = archivo.concat("CH_DISP_IM_").concat(f).concat(".txt, CH_DISP_IS_").concat(f)
								.concat(".txt, CH_PARC_IM_").concat(f).concat(".txt, CH_PARC_IS_")
								.concat(".txt, CH_VOLUN_IM_").concat(f).concat(".txt, CH_PMG_IM_").concat(f)
								.concat(".txt");						
						break;

					case "TDP":
						archivo = archivo.concat("TDP_DISP_IM_").concat(f).concat(".txt");						
						break;

					case "TOD_PAG":
						archivo = archivo.concat("TI_DISP_IM_").concat(f).concat(".txt, TI_PARC_IM_").concat(f)
								.concat(".txt, TI_VOLUN_IM_").concat(f).concat(".txt, TI_DISP_IS_").concat(f)
								.concat(".txt, TI_PARC_IS_").concat(f).concat(".txt, TI_PMG_IM_").concat(f)
								.concat(".txt, OP_DISP_IM_").concat(f).concat(".txt, OP_PARC_IM_").concat(f)
								.concat(".txt, OP_VOLUN_IM_").concat(f).concat(".txt, OP_DISP_IS_").concat(f)
								.concat(".txt, OP_PARC_IS_").concat(f).concat(".txt, OP_PMG_IM_").concat(f)
								.concat(".txt, CH_DISP_IM_").concat(f).concat(".txt, CH_PARC_IM_").concat(f)
								.concat(".txt, CH_VOLUN_IM_").concat(f).concat(".txt, CH_DISP_IS_").concat(f)
								.concat(".txt, CH_PARC_IS_").concat(f).concat(".txt, CH_PMG_IM_").concat(f)
								.concat(".txt, TDP_DISP_IM_").concat(f).concat(".txt");						
						break;
					}
				}
			}
		}
	}
}
