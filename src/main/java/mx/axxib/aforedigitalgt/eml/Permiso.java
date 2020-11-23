package mx.axxib.aforedigitalgt.eml;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "Permiso", classes = { @ConstructorResult(targetClass = Permiso.class, columns = {
				@ColumnResult(name = "ID_PERMISO", type = Integer.class),
				@ColumnResult(name = "TIPO", type = String.class), @ColumnResult(name = "CLAVE", type = String.class),
				@ColumnResult(name = "DESCRIPCION", type = String.class),
				@ColumnResult(name = "STATUS", type = String.class) }) })
})

public class Permiso {
	private Integer id;
	private String tipo;
	private String clave;
	private String descripcion;
	private String status;
}