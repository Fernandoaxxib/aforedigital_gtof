package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

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
		@SqlResultSetMapping(name = "LoteOP84Out", 
				classes = { @ConstructorResult(targetClass = LoteOP84Out.class, 
					columns = {
						@ColumnResult(name = "ID_LOTE", type = String.class),
						@ColumnResult(name = "FECHA_LOTE", type = Date.class),						
						@ColumnResult(name = "NUMERO_REGISTROS", type = Integer.class)
					})
				})
})
public class LoteOP84Out {
	private String ID_LOTE;
	private Date FECHA_LOTE;
	private Integer NUMERO_REGISTROS;
	
}
