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
		@SqlResultSetMapping(name = "GraficasTotalesOut", 
				classes = { @ConstructorResult(targetClass = GraficasTotalesOut.class, 
					columns = {
						@ColumnResult(name = "TIPO_RETIRO", type = String.class),
						@ColumnResult(name = "TOTAL_RETIROS", type = Integer.class),
						@ColumnResult(name = "RETIROS_CANCELADOS", type = Integer.class),
						@ColumnResult(name = "RETIROS_PRECAPTURA", type = Integer.class),
						@ColumnResult(name = "RETIROS_CAPTURADA", type = Integer.class),
						@ColumnResult(name = "RETIROS_RECHAZADA", type = Integer.class),
						@ColumnResult(name = "RETIROS_PREV_LIQUIDA", type = Integer.class),
						@ColumnResult(name = "RETIROS_LIQUIDADOS", type = Integer.class),
						@ColumnResult(name = "RETIRO_LIQUIDA_MENS", type = Integer.class)
					})
				})
})
public class GraficasTotalesOut {
	private String TIPO_RETIRO;	
	private Integer TOTAL_RETIROS;
	private Integer RETIROS_CANCELADOS;
	private Integer RETIROS_PRECAPTURA;
	private Integer RETIROS_CAPTURADA;
	private Integer RETIROS_RECHAZADA;
	private Integer RETIROS_PREV_LIQUIDA;
	private Integer RETIROS_LIQUIDADOS;
	private Integer RETIRO_LIQUIDA_MENS;
	

}