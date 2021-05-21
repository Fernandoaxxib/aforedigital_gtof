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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "SaldoOut", classes = {
		@ConstructorResult(targetClass = SaldoOut.class, columns = {
				@ColumnResult(name = "cod_subproduct", type = String.class),
				@ColumnResult(name = "descripcion", type = String.class),
				@ColumnResult(name = "saldo_subproduct", type = Double.class),
				@ColumnResult(name = "total_saldos", type = Double.class)				
		}) }) })
public class SaldoOut {
	private String cod_subproduct;
	private String descripcion;
	private Double saldo_subproduct;
	private Double total_saldos;	
}
