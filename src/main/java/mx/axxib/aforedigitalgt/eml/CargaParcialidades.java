package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
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
		@SqlResultSetMapping(name = "CargaParcialidades", 
				classes = { @ConstructorResult(targetClass = CargaParcialidades.class, 
					columns = {
						@ColumnResult(name = "PAGO", type = String.class),
						@ColumnResult(name = "SOLICITUD", type = String.class),
						@ColumnResult(name = "TOTAL_PAGO", type = BigDecimal.class),
						@ColumnResult(name = "TOTAL_ACCION", type = String.class),
						@ColumnResult(name = "ESTATUS", type = String.class),
						@ColumnResult(name = "FECHA", type = Date.class),
						@ColumnResult(name = "T_PAGO", type = String.class),
						@ColumnResult(name = "TIPO_PAGO", type = String.class),
						@ColumnResult(name = "DP_CUENTA", type = String.class),
						@ColumnResult(name = "DP_BANCO", type = String.class),
						@ColumnResult(name = "AUTORIZA_TRABAJADOR", type = String.class),
						@ColumnResult(name = "FECHA_MANIFIESTO_TRAB", type = Date.class),
						@ColumnResult(name = "FECHA_ENV_AUTORIZA_IMSS", type = Date.class),
						@ColumnResult(name = "FECHA_AUTORIZA_IMSS", type = Date.class),
						@ColumnResult(name = "AUTORIZA_IMSS", type = String.class),
						@ColumnResult(name = "ESTATUS_PAG_DESCRIP", type = String.class)
						
					})
				})
})

public class CargaParcialidades {
	private String pago;
	private String solicitud;
	private BigDecimal totalPago;
	private String totalAccion;
	private String estatus;
	private Date fecha;
	private String tPago;
	private String tipo_pago;
	private String dpCuenta;
	private String dpBanco;
	private String autorizaTrabajador;
	private Date fechaManifiestoTrab;
	private Date fechaEnvAutorizaImss;
	private Date fechaAutorizaImss;
	private String autorizaImss;
	private String estatusPagDescrip;
}

//PAGO	NUMBER ?
//SOLICITUD	NUMBER ?
//TOTAL_PAGO	NUMBER ?
//TOTAL_ACCION	NUMBER ?
//ESTATUS	VARCHAR2
//FECHA	DATE
//T_PAGO	VARCHAR2
//TIPO_PAGO	VARCHAR2
//DP_CUENTA	VARCHAR2
//DP_BANCO	VARCHAR2
//AUTORIZA_TRABAJADOR	VARCHAR2
//FECHA_MANIFIESTO_TRAB	DATE
//FECHA_ENV_AUTORIZA_IMSS	DATE
//FECHA_AUTORIZA_IMSS	DATE
//AUTORIZA_IMSS	VARCHAR2
//ESTATUS_PAG_DESCRIP	VARCHAR2

  