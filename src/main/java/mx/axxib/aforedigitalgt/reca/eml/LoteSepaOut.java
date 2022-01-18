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
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "LoteSepaOut", 
				classes = { @ConstructorResult(targetClass = LoteSepaOut.class, 
				columns = {
						@ColumnResult(name = "lote_carga", type = String.class),
						@ColumnResult(name = "acept", type = Integer.class),
						@ColumnResult(name = "rech", type = Integer.class)
					})
				})
})
public class LoteSepaOut {

	private String lote_carga;
	private Integer acept;
	private Integer rech;	
	
}