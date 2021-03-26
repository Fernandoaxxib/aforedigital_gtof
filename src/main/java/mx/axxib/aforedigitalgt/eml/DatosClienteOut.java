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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "DatosClienteOut", classes = {
		@ConstructorResult(targetClass = DatosClienteOut.class, columns = {
				@ColumnResult(name = "NUM_SEGURO_SOCIAL", type = String.class),
				@ColumnResult(name = "COD_ESTADO", type = String.class),
				@ColumnResult(name = "cod_cliente", type = String.class),
				@ColumnResult(name = "COD_CUENTA", type = String.class),
				@ColumnResult(name = "DIAS_ACUMULADOS", type = Integer.class),
				@ColumnResult(name = "EST_CUENTA", type = String.class),
				@ColumnResult(name = "DIAS_PAGADOS", type = Integer.class),
				@ColumnResult(name = "COD_PRODUCTO", type = String.class),			
				@ColumnResult(name = "FEC_EMISION", type = Date.class),
				@ColumnResult(name = "descripcion", type = String.class),
				@ColumnResult(name = "nombre", type = String.class),
		}) }) })
public class DatosClienteOut {

	private String NUM_SEGURO_SOCIAL;
	private String COD_ESTADO;
	private String cod_cliente;
	private String COD_CUENTA;
	private Integer DIAS_ACUMULADOS;
	private String EST_CUENTA;
	private Integer DIAS_PAGADOS;
	private String COD_PRODUCTO;
	private Date FEC_EMISION;
	private String descripcion;
	private String nombre;
}
