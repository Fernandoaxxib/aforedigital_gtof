package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Reporte Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Feb/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "RefPago", 
				classes = { @ConstructorResult(targetClass = RefPago.class, 
					columns = {
						@ColumnResult(name = "SOL_PAGO", type = String.class),
						@ColumnResult(name = "NUM_RESOLUCION", type = String.class),
						@ColumnResult(name = "NSS", type = String.class),
						@ColumnResult(name = "curp_trabajador", type = String.class),
						@ColumnResult(name = "NOMBRE", type = String.class),
						@ColumnResult(name = "id_Estatus", type = String.class),
						@ColumnResult(name = "estatus", type = String.class),
						@ColumnResult(name = "monto", type = String.class),
						
						@ColumnResult(name = "Referencia_solicitud", type = String.class),
						@ColumnResult(name = "Referencia_busqueda", type = String.class),
						@ColumnResult(name = "hr_diagnostico_proceso", type = String.class),
						@ColumnResult(name = "mr_diagnostico_proceso", type = String.class),
						@ColumnResult(name = "np_diagnostico_proceso", type = String.class),
						@ColumnResult(name = "fec_movimto", type = String.class),
						@ColumnResult(name = "num_movimto", type = String.class),
						@ColumnResult(name = "lq_num_usuario", type = String.class),
						
						@ColumnResult(name = "curp_agente_servicio", type = String.class),
						@ColumnResult(name = "desc_envio_img", type = String.class),
						@ColumnResult(name = "sello_voluntad", type = String.class),
						@ColumnResult(name = "titular_bene", type = String.class),
						@ColumnResult(name = "mv_validador", type = String.class),
						@ColumnResult(name = "usuario", type = String.class),
						@ColumnResult(name = "fecha_hora_sol", type = String.class),
						@ColumnResult(name = "fecha_solicitud", type = String.class),
						
						@ColumnResult(name = "Fecha_pago", type = String.class),
						@ColumnResult(name = "Fecha_envio_img", type = String.class),
						@ColumnResult(name = "Fecha_liquidacion", type = String.class)					
					})
				})
})

public class RefPago {

	private String solPago;
	private String noResolucion;
	private String nss;
	private String curp;
	private String nombre;
	private String idEstatus;
	private String estatus;
	private String monto;
	
	private String refSol;
	private String refBus;
	private String hrDiag;
	private String mrDiag;
	private String npDiag;
	private String fechaMov;
	private String noMov;
	private String noUsuario;
	
	private String curpAgente;
	private String descEnvio;
	private String selloVol;
	private String titularBene;
	private String validador;
	private String usuario;
	private String fechaHoraSol;
	private String fechaSol;
	
	private String fechaPago;
	private String fechaEnvio;
	private String fechaLiq;
	
	public String getHeader() {
		final String SEPARATOR = "\t";
		final String END_OF_LINE = "\n";
		StringBuilder sb = new StringBuilder();

		sb.append("SOL_PAGO").append(SEPARATOR);
		sb.append("NUM_RESOLUCION").append(SEPARATOR);
		sb.append("NSS").append(SEPARATOR);
		sb.append("CURP_TRABAJADOR").append(SEPARATOR);
		sb.append("NOMBRE").append(SEPARATOR);
		sb.append("ID_ESTATUS").append(SEPARATOR);
		sb.append("ESTATUS").append(SEPARATOR);
		sb.append("MONTO").append(SEPARATOR);
		
		sb.append("REFERENCIA_SOLICITUD").append(SEPARATOR);
		sb.append("REFERENCIA_BUSQUEDA").append(SEPARATOR);
		sb.append("HR_DIAGNOSTICO_PROCESO").append(SEPARATOR);
		sb.append("MR_DIAGNOSTICO_PROCESO").append(SEPARATOR);
		sb.append("NP_DIAGNOSTICO_PROCESO").append(SEPARATOR);
		sb.append("FEC_MOVIMTO").append(SEPARATOR);
		sb.append("NUM_MOVIMTO").append(SEPARATOR);
		sb.append("LQ_NUM_USUARIO").append(SEPARATOR);
		
		sb.append("CURP_AGENTE_SERVICIO").append(SEPARATOR);
		sb.append("DESC_ENVIO_IMG").append(SEPARATOR);
		sb.append("SELLO_VOLUNTAD").append(SEPARATOR);
		sb.append("TITULAR_BENE").append(SEPARATOR);
		sb.append("MV_VALIDADOR").append(SEPARATOR);
		sb.append("USUARIO").append(SEPARATOR);
		sb.append("FECHA_HORA_SOL").append(SEPARATOR);
		sb.append("FECHA_SOLICITUD").append(SEPARATOR);		
	
		sb.append("FECHA_PAGO").append(SEPARATOR);
		sb.append("FECHA_ENVIO_IMG").append(SEPARATOR);
		sb.append("FECHA_LIQUIDACION").append(END_OF_LINE);
		
		return sb.toString();
	}
	
	public String getLine() {
		final String SEPARATOR = "\t";
		final String END_OF_LINE = "\n";
		StringBuilder sb = new StringBuilder();
		
		sb.append(solPago).append(SEPARATOR);
		sb.append(noResolucion).append(SEPARATOR);
		sb.append(nss).append(SEPARATOR);
		sb.append(curp).append(SEPARATOR);
		sb.append(nombre).append(SEPARATOR);
		sb.append(idEstatus).append(SEPARATOR);
		sb.append(estatus).append(SEPARATOR);
		sb.append(monto).append(SEPARATOR);
		
		sb.append(refSol).append(SEPARATOR);
		sb.append(refBus).append(SEPARATOR);
		sb.append(hrDiag).append(SEPARATOR);
		sb.append(mrDiag).append(SEPARATOR);
		sb.append(npDiag).append(SEPARATOR);
		sb.append(fechaMov).append(SEPARATOR);
		sb.append(noMov).append(SEPARATOR);
		sb.append(noUsuario).append(SEPARATOR);
		
		sb.append(curpAgente).append(SEPARATOR);
		sb.append(descEnvio).append(SEPARATOR);
		sb.append(selloVol).append(SEPARATOR);
		sb.append(titularBene).append(SEPARATOR);
		sb.append(validador).append(SEPARATOR);
		sb.append(usuario).append(SEPARATOR);
		sb.append(fechaHoraSol).append(SEPARATOR);
		sb.append(fechaSol).append(SEPARATOR);
		
		sb.append(fechaPago).append(SEPARATOR);
		sb.append(fechaEnvio).append(SEPARATOR);
		sb.append(fechaLiq).append(END_OF_LINE);
		
		return sb.toString();
	}
}

//SOL_PAGO
//NUM_RESOLUCION
//NSS
//curp_trabajador
//NOMBRE
//sp_pagado(id_Estatus)
//descripcion(estatus)
//MR_MONTO_REINTEGRAR(monto)
//REFERENCIA(Referencia_solicitud)
//referencia(Referencia_busqueda)
//hr_diagnostico_proceso
//mr_diagnostico_proceso
//np_diagnostico_proceso
//fec_movimto
//num_movimto
//lq_num_usuario
//curp_agente_servicio
//desc_envio_img
//sello_voluntad
//sol_def_tipo (titular_bene)
//mv_validador
//usuario
//fecha_hora_sol
//sp_fec_solicitud
//np_fec_pago_reintegro (Fecha_pago)
//fec_envio_img (Fecha_envio_img)
//SP_FEC_PAGO (Fecha_liquidacion)
