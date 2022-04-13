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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "Banco", classes = {
		@ConstructorResult(targetClass = Banco.class, columns = {
				@ColumnResult(name = "CLAVE_BANCO", type = Integer.class),
				@ColumnResult(name = "NOM_BANCO", type = String.class) }) }) })
public class Banco {
	private Integer cve;
	private String nombre;
}
