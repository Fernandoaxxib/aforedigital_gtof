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
		@SqlResultSetMapping(name = "RetparDeta", 
				classes = { @ConstructorResult(targetClass = RetparDeta.class, 
					columns = {
						@ColumnResult(name = "NUMERO_PAGO", type = Integer.class),
						@ColumnResult(name = "COD_TIPSALDO", type = String.class),
						@ColumnResult(name = "MONTO_ACCION", type = Integer.class),
						@ColumnResult(name = "MONTO_PESOS", type = Integer.class),
						@ColumnResult(name = "ESTATUS_PAGO", type = String.class),
						@ColumnResult(name = "NUM_SOLICITUD", type = Integer.class),
						@ColumnResult(name = "COD_INVERSION", type = String.class),
						@ColumnResult(name = "SUBCUENTA", type = String.class),
						@ColumnResult(name = "P_DESCRIPCION_PAGO", type = String.class),
						@ColumnResult(name = "P_DESCRIPCION", type = String.class)
						
					})
				})
})

public class RetparDeta {
	private Integer noPago;
	private String codTipSaldo;
	private Integer montoAccion;
	private Integer montoPesos;
	private String estatusPago;
	private Integer noSolicitud;
	private String codInversion;
	private String subcuenta;
	private String descripcionPago;
	private String descripcion;
}

//NUMERO_PAGO	NUMBER
//COD_TIPSALDO	VARCHA2
//MONTO_ACCION	NUMBER
//MONTO_PESOS	NUMBER
//ESTATUS_PAGO	VARCHA2
//NUM_SOLICITUD	NUMBER
//COD_INVERSION	VARCHA2
//SUBCUENTA	VARCHA2
//P_DESCRIPCION_PAGO	VARCHA2
//P_DESCRIPCION	VARCHA2

  