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
		@SqlResultSetMapping(name = "ObtenerLoteTraspasos", 
				classes = { @ConstructorResult(targetClass = ObtenerLoteTraspasos.class, 
					columns = {
						@ColumnResult(name = "ID_LOTE", type = String.class),
						@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
						
					})
				})
})

public class ObtenerLoteTraspasos {
	private String idLote;
	private BigDecimal montoMovimiento;
}
	
//ID_LOTE VARCHAR2 (20 Byte)
//MON_MOVIMTO  NUMBER (20,8)