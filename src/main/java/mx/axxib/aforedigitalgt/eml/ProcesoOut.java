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
		@SqlResultSetMapping(name = "ProcesoOut", 
				classes = { @ConstructorResult(targetClass = ProcesoOut.class, 
					columns = {
						@ColumnResult(name = "SESION_ID", type = Integer.class),
						@ColumnResult(name = "ABREV_PROCESO", type = String.class),						
						@ColumnResult(name = "ESTADO_PROCESO", type = String.class),		
						@ColumnResult(name = "FECHA_HORA_INICIO", type = String.class),
						@ColumnResult(name = "FECHA_HORA_FINAL", type = String.class)						
					})
				})
})

public class ProcesoOut {
	private Integer sesionId;
	private String abrevProceso;	
	private String estadoProceso;	
	private String fechahoraInicio;
	private String fechahoraFinal;		
}

