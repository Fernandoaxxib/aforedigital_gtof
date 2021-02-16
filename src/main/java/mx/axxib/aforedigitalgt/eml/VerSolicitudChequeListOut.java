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
	@SqlResultSetMapping(name = "VerSolicitudChequeListOut", 
			classes = { @ConstructorResult(targetClass = VerSolicitudChequeListOut.class, 
				columns = {
					@ColumnResult(name = "FECHA_OPERACION", type = Date.class),
					@ColumnResult(name = "DIAGNOSTICO_CUENTA", type = String.class),
					@ColumnResult(name = "TIPO_PRESTACION", type = String.class),
					@ColumnResult(name = "NUMERO_SOLIC_RET", type = String.class),
					@ColumnResult(name = "FECHA_TRASPASO", type = Date.class),
					@ColumnResult(name = "FECHA_PAGO", type = Date.class),
					@ColumnResult(name = "NUMERO_RESOLUCION", type = String.class),
					@ColumnResult(name = "FECHA_SOLICITUD", type = Date.class),
					@ColumnResult(name = "CONSECUTIVO", type = Integer.class),
					@ColumnResult(name = "NUM_EMPLEADO", type = Integer.class),
					@ColumnResult(name = "RIGTH_FAX", type = String.class)
				})
			})
})


public class VerSolicitudChequeListOut {
	private Date   fechaOperacion;
	private String diagnosticoCuenta;
	private String tipoPrestacion;
	private String numeroSolicitud;
	private Date   fechaTraspaso;
	private Date   fechaPago;
	private String numeroResolucion;
	private Date   fechaSolicitud;
	private Integer consecutivo;
	private Integer numeroEmpleado;
	private String rightFax;
}
