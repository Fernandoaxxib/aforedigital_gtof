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

		@SqlResultSetMapping(name = "DetCompraOut",

				classes = { @ConstructorResult(targetClass = DetCompraOut.class,

						columns = { 
								@ColumnResult(name = "ID_LOTE", type = String.class),
								@ColumnResult(name = "SUBCUENTA", type = String.class),
								@ColumnResult(name = "SIEFORE", type = String.class),
								@ColumnResult(name = "MONTO", type = Double.class),
								@ColumnResult(name = "ACCIONES", type = Double.class),
								@ColumnResult(name = "FEC_APLICADO", type = Date.class),
								@ColumnResult(name = "ESTADO", type = String.class) 
								}) 
				}) 
		})
public class DetCompraOut {

	private String ID_LOTE;
	private String SUBCUENTA;
	private String SIEFORE;
	private Double MONTO;
	private Double ACCIONES;
	private Date FEC_APLICADO;
	private String ESTADO;

}
