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
		@SqlResultSetMapping(name = "SalarioMinimoOut", 
				classes = { @ConstructorResult(targetClass = SalarioMinimoOut.class, 
					columns = {
						@ColumnResult(name = "MONTO_DIARIO", type = Integer.class),
						@ColumnResult(name = "USR_ID", type = Integer.class),
						@ColumnResult(name = "FCH_ULT_ACT", type = Date.class)
					})
				})
})
public class SalarioMinimoOut {
	
	private Integer montoDiario;
	private Integer userId;
	private Date   fechaUltimo;
	
	//MONTO_DIARIO--cp_cursor
	//USR_ID--cp_cursor
	//FCH_ULT_ACT-- CP_FECHA_MAX
}
