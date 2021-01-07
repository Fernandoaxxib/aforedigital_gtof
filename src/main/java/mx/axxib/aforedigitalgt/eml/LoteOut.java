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
		@SqlResultSetMapping(name = "LoteOut", 
				classes = { @ConstructorResult(targetClass = LoteOut.class, 
					columns = {
						@ColumnResult(name = "ID_LOTE", type = String.class),
						@ColumnResult(name = "FEC_OPERACION", type = Date.class),						
						@ColumnResult(name = "REGISTROS", type = Integer.class)
					})
				})
})

public class LoteOut {
	private String ID_LOTE;
	private Date FEC_OPERACION;
	private Integer REGISTROS;
}