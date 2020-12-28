package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

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
		@SqlResultSetMapping(name = "ReinversionProcesoOut", classes = { @ConstructorResult(targetClass = ReinversionProcesoOut.class, columns = {
				@ColumnResult(name = "p_Cuentas", type = Integer.class),
				@ColumnResult(name = "p_Avance", type = String.class),
				@ColumnResult(name = "p_Fecha_Trans", type = Integer.class),
				@ColumnResult(name = "p_Ruta_Arch_Trans", type = String.class),
				@ColumnResult(name = "p_Arch_Sal_Trans", type = Integer.class),
				@ColumnResult(name = "p_Message", type = String.class)}) })
})
public class ReinversionProcesoOut {
	
	
	private Integer cuentas;
	private String  avance;
	private Date    fechaTrans;
	private String  ruta;
	private String  nombre;
	private String  mensaje;

}
