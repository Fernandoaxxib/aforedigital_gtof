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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "TipoPersona", classes = {
		@ConstructorResult(targetClass = TipoPersona.class, columns = {
				@ColumnResult(name = "CVE_TIPO_PERSONA", type = Integer.class),
				@ColumnResult(name = "DESCRIPCION", type = String.class), }) }) })
public class TipoPersona {
    private Integer cve;
    private String descripcion;
}
