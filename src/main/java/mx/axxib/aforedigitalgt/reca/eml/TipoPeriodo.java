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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "TipoPeriodo", classes = {
		@ConstructorResult(targetClass = TipoPeriodo.class, columns = {
				@ColumnResult(name = "CVE_TIPO_PERIODO", type = Integer.class),
				@ColumnResult(name = "VALOR", type = String.class)			
				}) 
		}) })
public class TipoPeriodo {
    private Integer cve;
    private String valor;	
}
