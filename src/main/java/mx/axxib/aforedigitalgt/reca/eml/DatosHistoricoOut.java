package mx.axxib.aforedigitalgt.reca.eml;

import java.util.Date;

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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "DatosHistoricoOut", classes = {
		@ConstructorResult(targetClass = DatosHistoricoOut.class, columns = {
				@ColumnResult(name = "NSS", type = String.class), 
				@ColumnResult(name = "RFC", type = String.class),
				@ColumnResult(name = "NOMBRE", type = String.class),
				@ColumnResult(name = "ESTATUS", type = String.class),
				@ColumnResult(name = "FECHA", type = Date.class)
}) 
}) 
})
public class DatosHistoricoOut {
	private String NSS;
	private String RFC;
	private String NOMBRE;
	private String STATUS;
	private Date FECHA;
}
