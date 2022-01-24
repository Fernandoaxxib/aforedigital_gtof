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
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de recaudacion imss rechazo
//** Interventor Principal: JSAS
//** Fecha Creación: 20/Dic/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "RecaudacionPatronal", 
				classes = { @ConstructorResult(targetClass = RecaudacionPatronal.class, 
					columns = {
						@ColumnResult(name = "identificador_operacion", type = String.class), 
						@ColumnResult(name = "tipo_registro", type = Integer.class), 
						@ColumnResult(name = "identificador_servicio", type = String.class),
						@ColumnResult(name = "consecutivo", type = Integer.class),
						@ColumnResult(name = "numero_nss_trabajador", type = String.class),
						@ColumnResult(name = "curp", type = String.class),
						@ColumnResult(name = "clave_motivo_rechazo", type = String.class),
						@ColumnResult(name = "RCV", type = String.class),
						@ColumnResult(name = "aportaciones_voluntarias", type = String.class),
						@ColumnResult(name = "aportaciones_complementarias", type = String.class),
						@ColumnResult(name = "MOTIVO_RECHAZO1", type = String.class)
						
					
					})
				})
})


public class RecaudacionPatronal {
	private String idOper;
	private Integer tipoRegistro;
	private String idServicio;
	private Integer consecutivo;
	private String nss;
	private String curp;
	private String claveMotivoRechazo;
	private String rcv;
	private String aportacionesVol;
	private String aportacionesCom;
	private String motivoRechazo;
	
}

//
//SELECT SRV.identificador_operacion   
//,SRV.tipo_registro           
//,SRV.identificador_servicio      
//,SRV.consecutivo              
//,SRV.numero_nss_trabajador
//,SRV.curp                    
//,SRV.clave_motivo_rechazo     
//,(SRV.pago_retiro + SRV.actualizacion_retiro + SRV.pago_cv + SRV.actualiza_cesantia_vejez) RCV
//,SRV.aportaciones_voluntarias
//,SRV.aportaciones_complementarias
//,CAT.descripcion_recha AS MOTIVO_RECHAZO1
