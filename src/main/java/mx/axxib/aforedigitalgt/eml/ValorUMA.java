package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ValorUMA", 
				classes = { @ConstructorResult(targetClass = ValorUMA.class, 
					columns = {
						@ColumnResult(name = "MONTO_DIARIO", type = BigDecimal.class),
						@ColumnResult(name = "USR_ID", type = String.class),
						@ColumnResult(name = "FCH_CALENDARIO", type = Date.class),
						@ColumnResult(name = "FCH_ULT_ACT", type = Date.class),
						
						
					})
				})
})

public class ValorUMA {
	public ValorUMA(BigDecimal monto, String user, Date fecha, Date fechaUltAct) {
		super();
		this.monto = monto;
		this.user = user;
		this.fecha = fecha;
		this.fechaUltAct = fechaUltAct;
	}
	
	private BigDecimal monto;
	private String user;
	private Date fecha;
	private Date fechaUltAct;
	private Date fechaNueva;
}

