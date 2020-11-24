package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
		@SqlResultSetMapping(name = "MonitorOut", 
				classes = { @ConstructorResult(targetClass = ObtieneMonitorOut.class, 
					columns = {
						@ColumnResult(name = "SESION_ID", type = Integer.class),
						@ColumnResult(name = "ABREV_PROCESO", type = String.class),
						@ColumnResult(name = "DESC_PROCESO", type = String.class),
						@ColumnResult(name = "ESTADO_PROCESO", type = String.class),
						@ColumnResult(name = "AVANCE_PROCESO", type = String.class),
						@ColumnResult(name = "FECHA_HORA_INICIO", type = Date.class),
						@ColumnResult(name = "FECHA_HORA_FINAL", type = Date.class),
						@ColumnResult(name = "FECHA_HORA_AVANCE", type = Date.class),
						@ColumnResult(name = "INCLUIDO_POR", type = String.class)
					})
				})
})

public class ObtieneMonitorOut {
	private Integer sesionId;
	private String abrevProceso;
	private String descProceso;
	private String estadoProceso;
	private String avanceProceso;
	private Date fechahoraInicio;
	private Date fechahoraFinal;
	private Date fechahoraAvance;
	private String incluidoPor;
}

//ABREV_PROCESO	VARCHAR2 (30 Byte)	
//AVANCE_PROCESO	VARCHAR2 (500 Byte)	
//DESC_PROCESO	VARCHAR2 (100 Byte)	
//ESTADO_PROCESO	VARCHAR2 (50 Byte)	
//FECHA_HORA_AVANCE DATE			
//FECHA_HORA_FINAL  DATE			
//FECHA_HORA_INICIO DATE			
//INCLUIDO_POR	VARCHAR2 (25 Byte)	
//SESION_ID	NUMBER (15)

//SESION_ID,
//ABREV_PROCESO,
//DESC_PROCESO,
//ESTADO_PROCESO,
//AVANCE_PROCESO,
//FECHA_HORA_INICIO,
//FECHA_HORA_FINAL,
//FECHA_HORA_AVANCE,
//INCLUIDO_POR