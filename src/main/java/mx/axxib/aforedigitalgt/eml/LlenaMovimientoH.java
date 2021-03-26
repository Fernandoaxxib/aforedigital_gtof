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
		@SqlResultSetMapping(name = "LlenaMovimientoH", 
				classes = { @ConstructorResult(targetClass = LlenaMovimientoH.class, 
					columns = {
							@ColumnResult(name = "NUM_MOVIMTO", type = Integer.class),
							@ColumnResult(name = "FEC_MOVIMTO", type = Date.class),
							@ColumnResult(name = "DESCRIPCION", type = String.class),
							@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
							@ColumnResult(name = "TIP_TRANSAC", type = Integer.class),
							@ColumnResult(name = "SUBTIP_TRANSAC", type = String.class),
							@ColumnResult(name = "COD_AGENCIA", type = String.class),
							@ColumnResult(name = "DESC_TRANSACCION", type = String.class),
							@ColumnResult(name = "DESC_SUBTRANSAC", type = String.class),
							@ColumnResult(name = "DESC_AGENCIA", type = String.class)
					})
				})
})

public class LlenaMovimientoH {
	private Integer noMov;
	private Date fechaMov;
	private String descripcion; //Agregado
	private BigDecimal montoMov;
	private Integer tipoTransaccion;
	private String subtipoTransaccion;
	private String codAgencia;
	private String descTransaccion;
	private String descSubtransaccion;
	private String descAgencia;
}

//NUM_MOVIMTO INTEGER
//FEC_MOVIMTO DATE
//DESCRIPCION STRING
//MON_MOVIMTO BIGDECIMAL
//TIP_TRANSAC INTEGER
//SUBTIP_TRANSAC STRING
//COD_AGENCIA STRING
//DESC_TRANSACCION STRING
//DESC_SUBTRANSAC STRING
//DESC_AGENCIA STRING

