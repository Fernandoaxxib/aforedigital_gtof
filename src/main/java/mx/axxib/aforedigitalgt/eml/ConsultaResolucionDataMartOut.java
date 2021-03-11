package mx.axxib.aforedigitalgt.eml;


import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ConsultaResolucionDataMartOut", classes = { @ConstructorResult(targetClass = ConsultaResolucionDataMartOut.class, columns = {

				@ColumnResult(name ="SEC_PENSION", type = String.class),
				@ColumnResult(name ="TIPO_MOVIMTO", type = String.class),
				@ColumnResult(name ="REGIMEN", type = String.class),
				@ColumnResult(name ="TIPO_RETIRO", type = String.class),
				@ColumnResult(name ="TIPO_SEGURO", type = String.class),
				@ColumnResult(name ="TIPO_PENSION", type = String.class),
				@ColumnResult(name ="TIPO_PRESTACION", type = String.class),
				@ColumnResult(name ="ART_NEGATIVA", type = String.class),
				@ColumnResult(name ="FRACC_NEGATIVA", type = String.class),
				@ColumnResult(name ="NUM_CONSIDERANDO", type = String.class),
				@ColumnResult(name ="FEC_INI_PENSION", type = Date.class),
				@ColumnResult(name ="FEC_EMI_RESOLUCION", type = Date.class),
				@ColumnResult(name ="PORCENTAJE_VALUACION", type = Integer.class),
				@ColumnResult(name ="SEMANAS_COTIZADAS", type = Integer.class),
				@ColumnResult(name ="DIAG_REGISTRO", type = String.class),
				@ColumnResult(name ="NUM_RESOLUCION", type = String.class),
				@ColumnResult(name ="TIPO_AP_CS", type = String.class),
				@ColumnResult(name ="SBC_TIPO_A", type = Integer.class),
				@ColumnResult(name ="SBC_TIPO_B", type = Integer.class),
				@ColumnResult(name ="PAGO_COMP", type = String.class),
				@ColumnResult(name ="MONTO_RETIRO_ORG", type = Integer.class),
				@ColumnResult(name ="SALDO_ANT", type = Integer.class)
		})
		})
})

public class ConsultaResolucionDataMartOut {
	
	private String  secPension;	  
	private String  tipoMovimto;	  
	private String  regimen;	  
	private String  tipoRETIRO;	  
	private String  tipoSEGURO;	  
	private String  tipoPENSION;	  
	private String  tipoPRESTACION;	  
	private String  artNEGATIVA;	  
	private String  fraccNEGATIVA;	  
	private String  numCONSIDERANDO;	  
	private Date    fecINI_PENSION;	
	private Date    fecEMI_RESOLUCION;	
	private Integer  porcentajeVALUACION;	  
	private Integer semanasCOTIZADAS;	
	private String  diagREGISTRO;	  
	private String  numRESOLUCION;	  
	private String  tipoAP_CS;	
	private Integer  sbcTIPO_A;	  
	private Integer  sbcTIPO_B;	  
	private String  pagoCOMP;	  
	private Integer  montoRETIRO_ORG;	  
	private Integer  saldoANT;	  
	  
}

