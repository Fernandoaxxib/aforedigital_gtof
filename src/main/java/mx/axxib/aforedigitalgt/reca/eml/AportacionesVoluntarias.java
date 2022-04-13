package mx.axxib.aforedigitalgt.reca.eml;

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
@MappedSuperclass
@NoArgsConstructor
@SqlResultSetMappings({ @SqlResultSetMapping(name = "AportacionesVoluntarias", classes = {
		@ConstructorResult(targetClass = AportacionesVoluntarias.class, columns = {
				@ColumnResult(name = "FOLIO", type = Integer.class),
				@ColumnResult(name = "NSS", type = Integer.class),
				@ColumnResult(name = "IMPORTE", type = BigDecimal.class),
				@ColumnResult(name = "EFECTIVO", type = BigDecimal.class),
				@ColumnResult(name = "S.B.F", type = Integer.class),
				@ColumnResult(name = "CHEQUE_BANORTE", type = String.class),
				@ColumnResult(name = "EDO", type = String.class),
				@ColumnResult(name = "IMPORTE_DEVUELTO", type = BigDecimal.class),
				@ColumnResult(name = "FECHA", type = Date.class),
				@ColumnResult(name = "TIPO_APORT", type = String.class),
				@ColumnResult(name = "DEDUCCION", type = String.class),
				@ColumnResult(name = "DESC.APORTACION", type = String.class) }) }) })

public class AportacionesVoluntarias {
	private Integer folio;
	private Integer nss;
	private BigDecimal importe;
	private BigDecimal efectivo;
	private Integer sbf;
	private String chequeBanorte;
	private String edo;
	private BigDecimal importeDevuelto;
	private Date fecha;
	private String tipoAportacion;
	private String deduccion;
	private String descripcionAportacion;
	
}
