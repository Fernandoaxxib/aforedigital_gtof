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
		@SqlResultSetMapping(name = "SolicitudOut", 
				classes = { @ConstructorResult(targetClass = SolicitudOut.class, 
					columns = {
						@ColumnResult(name = "NUM_SOLICITUD", type = Integer.class),
						@ColumnResult(name = "EST_SOLICITUD", type = String.class),						
						@ColumnResult(name = "TRANSACCION", type = String.class),						
						@ColumnResult(name = "SUBTRANSACCION", type = String.class),												
						@ColumnResult(name = "FECHAOPERACION", type = Date.class),												
						@ColumnResult(name = "COD_CUENTA", type = String.class),						
						@ColumnResult(name = "NOMBRE", type = String.class)
					})
				})
})
public class SolicitudOut {
	private Integer numSolicitud;
	private String estSolicitud;
	private String transaccion;
	private String subTransaccion;
    private Date fechaOperacion;
    private String codCuenta;
    private String nombre;
}
