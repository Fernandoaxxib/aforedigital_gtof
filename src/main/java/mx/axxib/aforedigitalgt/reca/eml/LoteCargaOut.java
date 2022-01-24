package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({ @SqlResultSetMapping(name = "LoteCargaOut", classes = {
		@ConstructorResult(targetClass = LoteCargaOut.class, columns = {
				@ColumnResult(name = "lote_carga", type = String.class)
}) 
}) 
})
public class LoteCargaOut {
	private String lote_carga;	
}