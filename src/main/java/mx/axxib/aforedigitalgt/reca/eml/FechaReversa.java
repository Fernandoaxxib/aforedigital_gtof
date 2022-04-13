package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;

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
		@SqlResultSetMapping(name = "FechaReversa", classes = { @ConstructorResult(targetClass = FechaReversa.class, columns = {
				@ColumnResult(name = "FECHA_CARGA", type = String.class),
				@ColumnResult(name = "SECUENCIA", type = Integer.class),
				@ColumnResult(name = "CONTADOR_VOLUNTARIAS", type = Integer.class),
				@ColumnResult(name = "IMPORTE_VOLUNTARIAS", type = BigDecimal.class),
				@ColumnResult(name = "CONTADOR_GF", type = Integer.class),
				@ColumnResult(name = "IMPORTE_GF", type = BigDecimal.class),
				@ColumnResult(name = "TIPO_REGISTRO", type = Integer.class)
		}) }) })
public class FechaReversa {
	private String fechaCarga;
	private Integer secuencia;
	private Integer contadorVoluntarias;
	private BigDecimal importeVoluntarias;
	private Integer contadorGF;
	private BigDecimal importeGF;
	private Integer tipoRegistro;
}
