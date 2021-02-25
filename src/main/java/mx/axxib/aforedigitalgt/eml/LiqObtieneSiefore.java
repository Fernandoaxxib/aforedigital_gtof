package mx.axxib.aforedigitalgt.eml;

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
		@SqlResultSetMapping(name = "LiqObtieneSiefore", 
				classes = { @ConstructorResult(targetClass = LiqObtieneSiefore.class, 
					columns = {
						@ColumnResult(name = "COD_INVERSION", type = String.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class)
					})
				})
})

public class LiqObtieneSiefore {
	private String siefore;
	private String descripcion;
}

//- COD_INVERSION string
//- DESCRIPCION string