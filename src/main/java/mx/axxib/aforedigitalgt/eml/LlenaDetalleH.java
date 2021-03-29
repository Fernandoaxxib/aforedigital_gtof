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
		@SqlResultSetMapping(name = "LlenaDetalleH", 
				classes = { @ConstructorResult(targetClass = LlenaDetalleH.class, 
					columns = {
							@ColumnResult(name = "NUM_MOVIMTO_DETA", type = String.class),
							@ColumnResult(name = "FEC_MOVIMTO_DETA", type = Date.class),
							@ColumnResult(name = "DESCRIPCION", type = String.class),
							@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
							@ColumnResult(name = "ESTADO", type = String.class),
							@ColumnResult(name = "COD_TIPSALDO", type = String.class),
							@ColumnResult(name = "COD_INVERSION", type = String.class),
							@ColumnResult(name = "TIP_TRANSAC", type = Integer.class),
							@ColumnResult(name = "SUBTIP_TRANSAC", type = String.class),
							@ColumnResult(name = "TOT_CUOTA", type = BigDecimal.class),
							@ColumnResult(name = "FEC_APLICADO", type = Date.class),
							@ColumnResult(name = "NUM_ASIENTO", type = Integer.class),
							@ColumnResult(name = "NUM_DOCUMENTO", type = Integer.class),
							@ColumnResult(name = "INCLUIDO_POR", type = String.class),
							@ColumnResult(name = "MODIFICADO_POR", type = String.class),
							@ColumnResult(name = "FEC_INCLUSION", type = Date.class),
							@ColumnResult(name = "FEC_MODIFICACION", type = Date.class),
							@ColumnResult(name = "DESC_TIPSALDO", type = String.class),
							@ColumnResult(name = "DESC_INVERSION", type = String.class),
							@ColumnResult(name = "DESC_TRANSACCION", type = String.class),
							@ColumnResult(name = "DESC_SUBTRANSAC", type = String.class)
							
					})
				})
})

public class LlenaDetalleH {
	private String noMovDetalle;
	private Date fechaMovDetalle;
	private String descripcion; //Agregado
	private BigDecimal montoMov; //
	private String estado; //
	private String codTipoSaldo;
	private String codInversion;
	private Integer tipoTransaccion;
	private String subtipoTransaccion;
	private BigDecimal totalCuota; //
	private Date fechaAplicado; //
	private Integer noAsiento; //Agregado
	private Integer noDocumento; //Agregado
	private String incluidoPor; //
	private String modificadoPor; //
	private Date fechaInclusion; //
	private Date fechaModificacion; //
	private String descTipoSaldo; //
	private String descInversion; //
	private String descTransaccion; //
	private String descSubtransaccion; //
	
}

//NUM_MOVIMTO_DETA STRING
//FEC_MOVIMTO_DETA DATE
//DESCRIPCION STRING
//MON_MOVIMTO BIGDECIMAL
//ESTADO STRING
//COD_TIPOSALDO STRING
//COD_INVERSION STRING
//TIP_TRANSAC INTEGER
//SUBTIP_TRANSAC STRING
//TOT_CUOTA BIGDECIMAL
//FEC_APLICADO DATE
//NUM_ASIENTO INTEGER
//NUM_DOCUMENTO INTEGER
//INCLUIDO_POR STRING
//FEC_INCLUSION DATE
//FEC_MODIFICACION DATE
//DESC_TIPSALDO STRING
//DESC_INVERSION STRING
//DESC_TRANSACCION STRING
//DESC_SUBTRANSAC STRING