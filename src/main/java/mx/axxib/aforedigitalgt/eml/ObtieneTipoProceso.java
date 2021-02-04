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
		@SqlResultSetMapping(name = "ObtieneTipoProceso", 
				classes = { @ConstructorResult(targetClass = ObtieneTipoProceso.class, 
					columns = {
						@ColumnResult(name = "CLAVE_PROCESO", type = Integer.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class)
					})
				})
})

public class ObtieneTipoProceso {
	private Integer clave;
	private String descripcion;
}

	