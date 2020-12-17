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
	@SqlResultSetMapping(name = "SolicitudChequeOut", 
			classes = { @ConstructorResult(targetClass = VerSolicitudChequeOut.class, 
				columns = {
					@ColumnResult(name = "P_FECHA_OPERACION", type = Date.class),
					@ColumnResult(name = "P_DIAGNOSTICO_CUENTA", type = String.class),
					@ColumnResult(name = "P_TIPO_PRESTACION", type = String.class),
					@ColumnResult(name = "P_NUMERO_SOLIC_RET", type = String.class),
					@ColumnResult(name = "P_fecha_solic", type = Date.class),
					@ColumnResult(name = "P_folio_solic", type = String.class),
					@ColumnResult(name = "P_fecha_pension", type = Date.class),
					@ColumnResult(name = "P_numero_resolucion", type = String.class),
					@ColumnResult(name = "P_fecha_resolucion", type = Date.class),
					@ColumnResult(name = "P_num_empleado", type = String.class),
					@ColumnResult(name = "P_rigth_fax", type = String.class)
				})
			})
})


public class VerSolicitudChequeOut {
	private Date   fechaOperacion;
	private String diagnosticoCuenta;
	private String tipoPrestacion;
	private String numeroSolicitud;
	private Date   fechaSolicitud;
	private String folioSolicitud;
	private Date   fechaPension;
	private String numeroResolucion;
	private Date   fechaResolucion;
	private String numeroEmpleado;
	private String rightFax;
}
