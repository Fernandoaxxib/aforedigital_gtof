package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ConsultaResolucionDataMartOut", classes = { @ConstructorResult(targetClass = ConsultaResolucionDataMartOut.class, columns = {

				@ColumnResult(name ="P_NSS_TRABAJADOR", type = Integer.class),
				@ColumnResult(name ="P_CURP", type = String.class),
				@ColumnResult(name ="P_NOMBRE_DATAMART", type = String.class),
				@ColumnResult(name ="P_NOMBRE_AFORE", type = String.class),
				@ColumnResult(name ="P_PATERNO_AFORE", type = String.class),
				@ColumnResult(name ="P_MATERNO_AFORE", type = String.class),
				@ColumnResult(name ="P_DERECHO", type = String.class),
				@ColumnResult(name ="P_DESC_DERECHO", type = String.class),
				@ColumnResult(name ="P_SEC_PENSION", type = String.class),
				@ColumnResult(name ="P_TIPO_MOVIMTO", type = String.class),
				@ColumnResult(name ="P_REGIMEN", type = String.class),
				@ColumnResult(name ="P_TIPO_RETIRO", type = String.class),
				@ColumnResult(name ="P_TIPO_SEGURO", type = String.class),
				@ColumnResult(name ="P_TIPO_PENSION", type = String.class),
				@ColumnResult(name ="P_TIPO_PRESTACION", type = String.class),
				@ColumnResult(name ="P_ART_NEGATIVA", type = String.class),
				@ColumnResult(name ="P_FRACC_NEGATIVA", type = String.class),
				@ColumnResult(name ="P_NUM_CONSIDERANDO", type = String.class),
				@ColumnResult(name ="P_NUM_RESOLUCION", type = String.class),
				@ColumnResult(name ="P_FEC_INI_PENSION", type = Date.class),
				@ColumnResult(name ="P_FEC_EMI_RESOLUCION", type = Date.class),
				@ColumnResult(name ="P_PORCENTAJE_VALUACION", type = String.class),
				@ColumnResult(name ="P_SEMANAS_COTIZADAS", type = Integer.class),
				@ColumnResult(name ="P_DIAG_REGISTRO", type = String.class),
				@ColumnResult(name ="P_SBC_TIPO_A", type = String.class),
				@ColumnResult(name ="P_SBC_TIPO_B", type = String.class),
				@ColumnResult(name ="P_PAGO_COMP", type = String.class),
				@ColumnResult(name ="P_MONTO_RETIRO_ORG", type = String.class),
				@ColumnResult(name ="P_SALDO_ANT", type = String.class),
				@ColumnResult(name ="P_TIPO_AP_CS", type = String.class)
		}) })
})
public class ConsultaResolucionDataMartOut {
	private Integer P_NSS_TRABAJADOR;
	private String  P_CURP;	
	private String  P_NOMBRE_DATAMART;	
	private String  P_NOMBRE_AFORE;	
	private String  P_PATERNO_AFORE;	
	private String  P_MATERNO_AFORE;	
	private String  P_DERECHO;	
	private String  P_DESC_DERECHO;	  
	private String  P_SEC_PENSION;	  
	private String  P_TIPO_MOVIMTO;	  
	private String  P_REGIMEN;	  
	private String  P_TIPO_RETIRO;	  
	private String  P_TIPO_SEGURO;	  
	private String  P_TIPO_PENSION;	  
	private String  P_TIPO_PRESTACION;	  
	private String  P_ART_NEGATIVA;	  
	private String  P_FRACC_NEGATIVA;	  
	private String  P_NUM_CONSIDERANDO;	  
	private String  P_NUM_RESOLUCION;	  
	private Date    P_FEC_INI_PENSION;	
	private Date    P_FEC_EMI_RESOLUCION;	
	private String  P_PORCENTAJE_VALUACION;	  
	private Integer P_SEMANAS_COTIZADAS;	
	private String  P_DIAG_REGISTRO;	  
	private String  P_SBC_TIPO_A;	  
	private String  P_SBC_TIPO_B;	  
	private String  P_PAGO_COMP;	  
	private String  P_MONTO_RETIRO_ORG;	  
	private String  P_SALDO_ANT;	  
	private String  P_TIPO_AP_CS;	  
}
