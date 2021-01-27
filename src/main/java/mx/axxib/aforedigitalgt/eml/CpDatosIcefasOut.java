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
		@SqlResultSetMapping(name = "CpDatosIcefasOut", 
				classes = { @ConstructorResult(targetClass = CpDatosIcefasOut.class, 
					columns = {
						@ColumnResult(name = "CLAVE_PROCESO", type = Integer.class),
						@ColumnResult(name = "DESC_PROCESO", type = String.class),
						@ColumnResult(name = "COD_TIPSALDO", type = String.class),
						@ColumnResult(name = "DESC_TIPSALDO", type = String.class),
						@ColumnResult(name = "ESTADO", type = String.class),
						@ColumnResult(name = "DESC_ESTADO", type = String.class),
						@ColumnResult(name = "FECHA_INICIO", type = Date.class),
						@ColumnResult(name = "FECHA_FIN", type = Date.class)						
					})
				})
})

public class CpDatosIcefasOut {
	
	private Integer CLAVE_PROCESO;
	private String  DESC_PROCESO;
	private String  COD_TIPSALDO;
	private String  DESC_TIPSALDO;
	private String  ESTADO;
	private String  DESC_ESTADO;
	private Date FECHA_INICIO;
	private Date FECHA_FIN;
	
}
