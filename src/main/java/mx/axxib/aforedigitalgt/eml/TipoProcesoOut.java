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
		@SqlResultSetMapping(name = "TipoProcesoOut", 
				classes = { @ConstructorResult(targetClass = LoteOut.class, 
					columns = {
						@ColumnResult(name = "CLAVE_PROCESO", type = String.class)
						//@ColumnResult(name = "DESCRIPCION", type = String.class)
					})
				})
})

public class TipoProcesoOut {
	private String CLAVE_PROCESO;
	//private String DESCRIPCION;

}
