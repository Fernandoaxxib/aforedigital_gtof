package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;

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
		@SqlResultSetMapping(name = "MovimientoImssOut", 
				classes = { @ConstructorResult(targetClass = MovimientoImssOut.class, 
				columns = {
						@ColumnResult(name = "SP_FEC_PAGO", type = String.class),
						@ColumnResult(name = "NSS", type = String.class),
						@ColumnResult(name = "NOMBRE", type = String.class),
						@ColumnResult(name = "MR_FEC_RETIRO", type = String.class),
						@ColumnResult(name = "MR_MONTO_REINTEGRAR", type = Double.class),
						@ColumnResult(name = "MR_SEMANAS_REINTEGRAR", type = String.class),
						@ColumnResult(name = "NUM_RESOLUCION", type = Integer.class),												
						@ColumnResult(name = "CVE_ENVIO_IMG", type = String.class),
						@ColumnResult(name = "DESC_ENVIO_IMG", type = String.class)
					})
				})
})
public class MovimientoImssOut{

	       private String SP_FEC_PAGO;
		   private String NSS;
		   private String NOMBRE;
		   private String MR_FEC_RETIRO;
		   private Double MR_MONTO_REINTEGRAR; 
		   private String MR_SEMANAS_REINTEGRAR;
		   private Integer NUM_RESOLUCION; 
		   private String CVE_ENVIO_IMG;
		   private String DESC_ENVIO_IMG; 

}
