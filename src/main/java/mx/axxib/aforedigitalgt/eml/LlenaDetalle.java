package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
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
		@SqlResultSetMapping(name = "LlenaDetalle", 
				classes = { @ConstructorResult(targetClass = LlenaDetalle.class, 
					columns = {
						@ColumnResult(name = "FEC_MOVIMTO", type = Date.class),
						@ColumnResult(name = "NUM_MOVIMTO", type = Integer.class),
						@ColumnResult(name = "NUM_MOVIMTO_DETA", type = String.class),
						@ColumnResult(name = "COD_TIPSALDO", type = String.class),
						@ColumnResult(name = "FEC_MOVIMTO_DETA", type = Date.class),
						@ColumnResult(name = "COD_INVERSION", type = String.class),
						@ColumnResult(name = "TIP_TRANSAC", type = Integer.class),
						@ColumnResult(name = "SUBTIP_TRANSAC", type = String.class),
						@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
						@ColumnResult(name = "TOT_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "ESTADO", type = String.class),
						@ColumnResult(name = "INCLUIDO_POR", type = String.class),
						@ColumnResult(name = "FEC_INCLUSION", type = Date.class),
						@ColumnResult(name = "MODIFICADO_POR", type = String.class),
						@ColumnResult(name = "FEC_MODIFICACION", type = Date.class),
						@ColumnResult(name = "FEC_APLICADO", type = Date.class),
						@ColumnResult(name = "VAL_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "DESC_TIPSALDO", type = String.class),
						@ColumnResult(name = "DESC_INVERSION", type = String.class),
						@ColumnResult(name = "DESC_TRANSACCION", type = String.class),
						@ColumnResult(name = "DESC_SUBTRANSAC", type = String.class),
						@ColumnResult(name = "IDENT_REPORTE", type = String.class)
					})
				})
})

public class LlenaDetalle {
	private Date fechaMov;
	private Integer noMov;
	private String noMovDetalle;
	private String codTipoSaldo;
	private Date fechaMovDetalle;
	private String codInversion;
	private Integer tipoTransaccion;
	private String subtipoTransaccion;
	private BigDecimal montoMov;
	private BigDecimal totalCuota;
	private String estado;
	private String incluidoPor;
	private Date fechaInclusion;
	private String modificadoPor;
	private Date fechaModificacion;
	private Date fechaAplicado;
	private BigDecimal valorCuota;
	private String descTipoSaldo;
	private String descInversion;
	private String descTransaccion;
	private String descSubtransaccion;
	private String idReporte;
}

//- FEC_MOVIMTO DATE                                                                        
//- NUM_MOVIMTO  NUMBER          
//- NUM_MOVIMTO_DETA  NUMBER   
//- COD_TIPSALDO  VARCHAR2(5 CHAR)
//- FEC_MOVIMTO_DETA  DATE     
//- COD_INVERSION  VARCHAR2(5 CHAR)
//- TIP_TRANSAC  NUMBER         
//- SUBTIP_TRANSAC  VARCHAR2(5 CHAR)
//- MON_MOVIMTO  NUMBER(30,6)   
//- TOT_CUOTA  NUMBER(30)       
//- ESTADO  VARCHAR2(2 CHAR)    
//- INCLUIDO_POR  VARCHAR2(10 CHAR)
//- FEC_INCLUSION  DATE          
//- MODIFICADO_POR  VARCHAR2(10 CHAR)
//- FEC_MODIFICACION  DATE       
//- FEC_APLICADO  DATE           
//- VAL_CUOTA  NUMBER(30)        
//- DESC_TIPSALDO  VARCHAR2(100 CHAR)
//- DESC_INVERSION  VARCHAR2(100 CHAR)
//- DESC_TRANSACCION  VARCHAR2(100 CHAR)
//- DESC_SUBTRANSAC  VARCHAR2(100 CHAR)
//- IDENT_REPORTE  VARCHAR2(5 CHAR)    