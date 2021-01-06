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
		@SqlResultSetMapping(name = "OrdenPagoArchivoOut", 
				classes = { @ConstructorResult(targetClass = OrdenPagoArchivoOut.class, 
					columns = {
						@ColumnResult(name = "NOMBRE_ARCHIVO", type = String.class),
						@ColumnResult(name = "P_MENSAJE", type = String.class)
					})
				})
})
public class OrdenPagoArchivoOut {
	
	private String nombreArchivo;
	private String mensaje;
}
