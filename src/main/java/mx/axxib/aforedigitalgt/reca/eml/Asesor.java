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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "Asesor", classes = {
		@ConstructorResult(targetClass = Asesor.class, columns = {
				@ColumnResult(name = "NOMINA", type = String.class),
				@ColumnResult(name = "EXPED_06", type = String.class), 
				@ColumnResult(name = "NOMBRE", type = String.class), 
		}) 
		}) 
})
public class Asesor {
	private String nomina;
	private String exp;
	private String nombre;
}
