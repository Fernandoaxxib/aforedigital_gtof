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
	@SqlResultSetMapping(name = "PagoChequeOut", 
			classes = { @ConstructorResult(targetClass = VerPagoChequeOut.class, 
				columns = {
					@ColumnResult(name = "P_IDENTIFICADOR_OPERACION", type = Integer.class),
					@ColumnResult(name = "P_TIPO_SEGURO", type = String.class),
					@ColumnResult(name = "P_TIPO_PENSION", type = String.class),
					@ColumnResult(name = "P_TIPO_PRESTACION", type = String.class),
					@ColumnResult(name = "P_FECHA_OPERACION", type = Date.class),
					@ColumnResult(name = "P_IMPORTE_AUTORIZADO", type = String.class),
					@ColumnResult(name = "P_IMPORTE_SUBCTA_RCV", type = String.class),
					@ColumnResult(name = "P_SOLICITUD", type = String.class),
					@ColumnResult(name = "P_diag_Op17", type = String.class)
				})
			})
})

public class VerPagoChequeOut {
	private Integer   identificarOperacion;
	private String 	 tipoSeguro;
	private String   tipoPension;
	private String   tipoPrestacion;
	private Date     fechaOperacion;
	private String   importeAutorizado;
	private String   importeSubcontrato;
	private String   solicitud;
	private String   diagonalOp;
}
