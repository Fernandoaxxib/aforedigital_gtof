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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "DetalleSaldoOut", classes = {
		@ConstructorResult(targetClass = DetalleSaldoOut.class, columns = {
				@ColumnResult(name = "INV", type = String.class),
				@ColumnResult(name = "cod_tipsaldo", type = String.class),
				@ColumnResult(name = "cuotas", type = Double.class),
				@ColumnResult(name = "saldos", type = Double.class),
				@ColumnResult(name = "desc_siefore", type = String.class),
				@ColumnResult(name = "descripcion", type = String.class) }) }) })
public class DetalleSaldoOut {
	private String INV;
    private String cod_tipsaldo;
    private Double cuotas;
    private Double saldos;
    private String desc_siefore;
    private String descripcion;
}
