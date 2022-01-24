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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "FechaOut", classes = {
		@ConstructorResult(targetClass = FechaOut.class, columns = {
				@ColumnResult(name = "FECHA", type = Date.class), 
				@ColumnResult(name = "ctas", type = Integer.class)
}) 
}) 
})
public class FechaOut {
	private Date FECHA;
	private Integer ctas;
}
