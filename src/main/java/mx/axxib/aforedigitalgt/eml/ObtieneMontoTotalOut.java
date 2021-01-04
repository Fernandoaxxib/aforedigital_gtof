package mx.axxib.aforedigitalgt.eml;

import java.math.BigDecimal;

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
		@SqlResultSetMapping(name = "ObtieneMontoTotalOut", 
				classes = { @ConstructorResult(targetClass = ObtieneMontoTotalOut.class, 
				columns = {
						@ColumnResult(name = "TOT_VENDER", type = BigDecimal.class),
						@ColumnResult(name = "VAL_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "TOT_CUOTAS", type = BigDecimal.class),
						@ColumnResult(name = "RETIRO_AFORE_TIT", type = BigDecimal.class),
						@ColumnResult(name = "RETIRO_AFORE_MND", type = BigDecimal.class),
						@ColumnResult(name = "TOT_GENERAL_CUOTAS", type = BigDecimal.class),
						@ColumnResult(name = "SIEFORE", type = String.class),
						@ColumnResult(name = "IND_CUOTA_REND", type = String.class)
					})
				})
})

public class ObtieneMontoTotalOut {
	private BigDecimal totVender;
	private BigDecimal valCuota;
	private BigDecimal totCuotas;
	private BigDecimal retiroAforeTIT;
	private BigDecimal retiroAforeMND;
	private BigDecimal totGeneralCuotas;
	private String siefore;
	private String indCuotaRend;
}
	
//   TOT_VENDER         NUMBER
//   VAL_CUOTA           NUMBER
//   TOT_CUOTAS          NUMBER
//   RETIRO_AFORE_TIT    NUMBER
//   RETIRO_AFORE_MND    NUMBER
//   TOT_GENERAL_CUOTAS  NUMBER
//   SIEFORE             VARCHAR2(50 BYTE)
//   IND_CUOTA_REND      VARCHAR2(2 BYTE)
