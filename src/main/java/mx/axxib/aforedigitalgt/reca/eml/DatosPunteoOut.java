package mx.axxib.aforedigitalgt.reca.eml;

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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "DatosPunteoOut", classes = {
		@ConstructorResult(targetClass = DatosPunteoOut.class, columns = {
				@ColumnResult(name = "NSS", type = String.class), 
				@ColumnResult(name = "RFC", type = String.class),
				@ColumnResult(name = "NOMBRE", type = String.class),
				@ColumnResult(name = "ESTATUS", type = String.class)
}) 
}) 
})
public class DatosPunteoOut {
	private String NSS;
	private String RFC;
	private String NOMBRE;
	private String STATUS;
}