package mx.axxib.aforedigitalgt.reca.eml;

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
		@SqlResultSetMapping(name = "LoteDispOut", 
				classes = { @ConstructorResult(targetClass = LoteDispOut.class, 
				columns = {
						@ColumnResult(name = "num_referencia", type = String.class)
					})
				})
})
public class LoteDispOut {
   private String num_referencia;
}
