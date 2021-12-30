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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "SieforeOut", classes = {
		@ConstructorResult(targetClass = SieforeOut.class, columns = {
				@ColumnResult(name = "clave_procesar", type = String.class), 
				@ColumnResult(name = "cod_inversion", type = String.class)
}) 
}) 
})

public class SieforeOut {
	private String clave_procesar;
	private String cod_inversion;	
}
