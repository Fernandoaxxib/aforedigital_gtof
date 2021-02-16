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
	@SqlResultSetMapping(name = "VerPagoChequeListOut", 
			classes = { @ConstructorResult(targetClass = VerPagoChequeListOut.class, 
				columns = {
					@ColumnResult(name = "IDENTIFICADOR_OPERACION", type = Integer.class),
					@ColumnResult(name = "TIPO_SEGURO", type = String.class),
					@ColumnResult(name = "TIPO_PENSION", type = String.class),
					@ColumnResult(name = "TIPO_PRESTACION", type = String.class),
					@ColumnResult(name = "FECHA_OPERACION", type = Date.class),
					@ColumnResult(name = "IMPORTE_AUTORIZADO", type = String.class),
					@ColumnResult(name = "IMPORTE_SUBCTA_RCV", type = String.class),
					@ColumnResult(name = "COD_CUENTA", type = Integer.class),
					@ColumnResult(name = "FECHA_RESPUESTA", type = String.class),
					@ColumnResult(name = "SOLICITUD", type = String.class),
					@ColumnResult(name = "OP17", type = String.class)
				})
			})
})

public class VerPagoChequeListOut {
	private Integer   identificarOperacion;
	private String 	 tipoSeguro;
	private String   tipoPension;
	private String   tipoPrestacion;
	private Date     fechaOperacion;
	private String   importeAutorizado;
	private String   importeSubcontrato;
	private Integer  codCuenta;
	private Date     fechaRespuesta;
	private String   solicitud;
	private String   diagonalOp;
}
