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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "LoteReporte", classes = {
		@ConstructorResult(targetClass = LoteReporte.class, columns = {
				@ColumnResult(name = "ID_LOTE", type = String.class),
				@ColumnResult(name = "FECHA_CARGA", type = String.class),
				@ColumnResult(name = "SECUENCIA", type = Integer.class) }) }) })
public class LoteReporte {
	private String id;
	private String fecha;
	private Integer secuencia;
}
