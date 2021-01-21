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
						@ColumnResult(name = "CDG_ZONA", type = String.class),	
						@ColumnResult(name = "FCH_CALENDARIO", type = Date.class),
						@ColumnResult(name = "MONTO_DIARIO", type = Double.class),
						@ColumnResult(name = "FCH_ULT_ACT", type = Date.class),
						@ColumnResult(name = "USR_ID", type = String.class)
					})
				})
})
public class SalarioMinimoOut {
	private String cdZona;
	private Date fechaCalendario;
	private Double montoDiario;
	private Date   fechaUltimo;
	private String userId;
	
}
