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
		@SqlResultSetMapping(name = "TipoTransacOut", 
				classes = { @ConstructorResult(targetClass = TipoTransacOut.class, 
					columns = {
						@ColumnResult(name = "TOT_PARCIAL", type = Integer.class),
						@ColumnResult(name = "TOT_TOTAL", type = Integer.class),												
					})
				})
})
public class TipoTransacOut {
	private Integer totParcial;
	private Integer totTotal;

}
