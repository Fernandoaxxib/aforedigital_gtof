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
		@SqlResultSetMapping(name = "GraficasDetalleOut", 
				classes = { @ConstructorResult(targetClass = GraficasDetalleOut.class, 
					columns = {
						@ColumnResult(name = "RETIROS", type = String.class),
						@ColumnResult(name = "ESTATUS", type = String.class),
						@ColumnResult(name = "IVRT", type = Integer.class),
						@ColumnResult(name = "NEG_PEN", type = Integer.class),
						@ColumnResult(name = "SAR_X_NEG", type = Integer.class),
						@ColumnResult(name = "SAR", type = Integer.class),
						@ColumnResult(name = "DESEMPLEO", type = Integer.class),
						@ColumnResult(name = "MATRIMONIO", type = Integer.class),
						@ColumnResult(name = "FRACC_AFORE", type = Integer.class),
						@ColumnResult(name = "FRACC_AFORE_B", type = Integer.class),
						@ColumnResult(name = "RETIRO_2_PORC", type = Integer.class)
					})
				})
})
public class GraficasDetalleOut {
	private String RETIROS;
	private String ESTATUS;
	private Integer IVRT;
	private Integer NEG_PEN;
	private Integer SAR_X_NEG;
	private Integer SAR;
	private Integer DESEMPLEO;
	private Integer MATRIMONIO;
	private Integer FRACC_AFORE;
	private Integer FRACC_AFORE_B;
	private Integer RETIRO_2_PORC;
}