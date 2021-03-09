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
		@SqlResultSetMapping(name = "Subtransacciones", 
				classes = { @ConstructorResult(targetClass = Subtransacciones.class, 
					columns = {
						@ColumnResult(name = "SUBTIP_TRANSAC", type = String.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class)
					})
				})
})

public class Subtransacciones {
	private String idSubtransaccion;
	private String descripcion;
}