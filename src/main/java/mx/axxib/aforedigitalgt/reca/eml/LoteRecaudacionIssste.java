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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "LoteRecaudacionIssste", classes = {
		@ConstructorResult(targetClass = LoteRecaudacionIssste.class, columns = {
				@ColumnResult(name = "LOTE_CARGA", type = String.class) }) }) })
public class LoteRecaudacionIssste {
	private String lote_carga;
}