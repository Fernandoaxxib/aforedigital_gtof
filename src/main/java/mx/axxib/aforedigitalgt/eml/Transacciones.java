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
		@SqlResultSetMapping(name = "Transacciones", 
				classes = { @ConstructorResult(targetClass = Transacciones.class, 
					columns = {
						@ColumnResult(name = "TIP_TRANSACCION", type = String.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class)
					})
				})
})

public class Transacciones {
	private String idTransaccion;
	private String descripcion;
}