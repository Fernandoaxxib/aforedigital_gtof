package mx.axxib.aforedigitalgt.eml;



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
		@SqlResultSetMapping(name = "TotalesOut", 
				classes = { @ConstructorResult(targetClass = TotalesOut.class, 
					columns = {
						@ColumnResult(name = "TOTMON", type = Integer.class),
						@ColumnResult(name = "TOTACC", type = Integer.class),												
					})
				})
})

public class TotalesOut {
	private Integer TOTMON;
	private Integer TOTACC;
}
