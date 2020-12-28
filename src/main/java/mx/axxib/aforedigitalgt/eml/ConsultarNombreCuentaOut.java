package mx.axxib.aforedigitalgt.eml;



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
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ConsultarNombreCuentaOut", classes = { @ConstructorResult(targetClass = ConsultarNombreCuentaOut.class, columns = {

				@ColumnResult(name ="P_CUENTA", type = String.class),
				@ColumnResult(name ="P_NOMBRE", type = String.class)
		}) })
})

public class ConsultarNombreCuentaOut {
	
	private String nombre;
	private String cuenta;

}
