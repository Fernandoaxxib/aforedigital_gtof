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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "LoteDesmarcar", classes = {
		@ConstructorResult(targetClass = LoteDesmarcar.class, columns = {
				@ColumnResult(name = "LOTE", type = String.class) }) }) })
public class LoteDesmarcar {
	private String lote;
}
