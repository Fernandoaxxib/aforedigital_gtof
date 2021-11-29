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
		@SqlResultSetMapping(name = "IssSieforeOut", 
				classes = { @ConstructorResult(targetClass = IssSieforeOut.class, 
				columns = {
						@ColumnResult(name = "cod_inversion", type = String.class),
						@ColumnResult(name = "descripcion", type = String.class)						
					})
				})
})
public class IssSieforeOut {
	private String cod_inversion;
	private String descripcion;
}
