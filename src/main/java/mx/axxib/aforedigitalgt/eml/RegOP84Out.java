package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
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
		@SqlResultSetMapping(name = "RegOP84Out", 
				classes = { @ConstructorResult(targetClass = RegOP84Out.class, 
				columns = {
						@ColumnResult(name = "TIPO_REGISTRO", type = String.class),
						@ColumnResult(name = "IDENTIFICADOR_SERVICIO", type = String.class),
						@ColumnResult(name = "IDENTIFICADOR_OPERACION", type = String.class),
						@ColumnResult(name = "NSS_TRABAJADOR", type = String.class),
						@ColumnResult(name = "CURP_TRABAJADOR", type = String.class),
						@ColumnResult(name = "DIAGNOSTICO_IMSS", type = String.class),
						@ColumnResult(name = "FECHA_ENVIO_NOTIFICACION_PAGO", type = String.class),
						@ColumnResult(name = "FECHA_LOTE", type = Date.class)						
					})
				})
})

public class RegOP84Out {
	private String TIPO_REGISTRO;
	private String IDENTIFICADOR_SERVICIO;
	private String IDENTIFICADOR_OPERACION;
	private String NSS_TRABAJADOR;
	private String CURP_TRABAJADOR;
	private String DIAGNOSTICO_IMSS;
	private String FECHA_ENVIO_NOTIFICACION_PAGO;
	private Date FECHA_LOTE;
}
