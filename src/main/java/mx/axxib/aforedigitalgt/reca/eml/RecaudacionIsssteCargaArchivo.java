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
@MappedSuperclass
@NoArgsConstructor
@SqlResultSetMappings({ @SqlResultSetMapping(name = "RecaudacionIssste", classes = {
		@ConstructorResult(targetClass = RecaudacionIsssteCargaArchivo.class, columns = {
				@ColumnResult(name = "LOTE_CARGA", type = String.class),
				@ColumnResult(name = "MONTO", type = BigDecimal.class),
				@ColumnResult(name = "REGISTROS", type = Integer.class) }) }) })

public class RecaudacionIsssteCargaArchivo {
	private String loteCarga;
	private BigDecimal monto;
	private Integer registros;

}
