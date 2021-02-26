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
		@SqlResultSetMapping(name = "ProcesMonitorOut", 
				classes = { @ConstructorResult(targetClass = ProcesMonitorOut.class, 
					columns = {
						@ColumnResult(name = "SESION_ID", type = Integer.class),
						@ColumnResult(name = "ABREV_PROCESO", type = String.class),					
						@ColumnResult(name = "ESTADO_PROCESO", type = String.class),						
						@ColumnResult(name = "FECHA_HORA_INICIO", type = String.class),
						@ColumnResult(name = "FECHA_HORA_FINAL", type = String.class)
					})
				})
})
public class ProcesMonitorOut {	
	private Integer SESION_ID;
	private String ABREV_PROCESO;
	private String ESTADO_PROCESO;
    private String FECHA_HORA_INICIO;
    private String FECHA_HORA_FINAL;
}
