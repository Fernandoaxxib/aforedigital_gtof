package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;

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
		@SqlResultSetMapping(name = "ObtenerTipoRetiro", 
				classes = { @ConstructorResult(targetClass = ObtenerTipoRetiro.class, 
					columns = {
						@ColumnResult(name = "DESCRIPCION", type = String.class),
						@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
						@ColumnResult(name = "SUBTIP_TRANSAC", type = String.class),
						@ColumnResult(name = "TIP_TRANSACCION", type = Integer.class)
						
					})
				})
})

public class ObtenerTipoRetiro {
	private String descripcion;
	private BigDecimal montoMovimiento;
	private String subTipoTransaccion;
	private Integer tipoTransaccion;
}
	
//DESCRIPCION VARCHAR (40 BYTE)
//MON_MOVIMTO  NUMBER (20,8)
//SUBTIP_TRANSAC VARCHAR2 (5 BYTE)
//TIP_TRANSACCION NUMBER (3)
