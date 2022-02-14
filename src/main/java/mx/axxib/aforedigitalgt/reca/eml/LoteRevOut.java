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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "LoteRevOut", classes = {
		@ConstructorResult(targetClass = LoteRevOut.class, columns = {
				@ColumnResult(name = "IMP_TOTAL_IT", type = Double.class),
				@ColumnResult(name = "lote_carga", type = String.class),
				@ColumnResult(name = "Tot_Registros", type = String.class) }) }) })
public class LoteRevOut {

	private Double IMP_TOTAL_IT;
	private String lote_carga;
	private String registros;
}
