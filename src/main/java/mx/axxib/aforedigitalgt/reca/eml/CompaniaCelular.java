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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "CompaniaCelular", classes = {
		@ConstructorResult(targetClass = CompaniaCelular.class, columns = {
				@ColumnResult(name = "CVE_COMPANIA", type = Integer.class),
				@ColumnResult(name = "COMPANIA", type = String.class)			
				}) 
		}) })
public class CompaniaCelular {
   private Integer cve;
   private String compania;
}
