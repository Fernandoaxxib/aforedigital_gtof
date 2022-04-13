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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "OrigenCaptura", classes = {
		@ConstructorResult(targetClass = OrigenCaptura.class, columns = {
				@ColumnResult(name = "CVE_ORIG_CAPTURA", type = Integer.class),
				@ColumnResult(name = "DESCRIPCION", type = String.class)			
				}) 
		}) })
public class OrigenCaptura {
    private Integer cve;
    private String descripcion;   
}
