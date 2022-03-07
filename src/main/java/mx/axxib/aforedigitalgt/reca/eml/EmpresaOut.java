package mx.axxib.aforedigitalgt.reca.eml;

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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "EmpresaOut", classes = {
		@ConstructorResult(targetClass = EmpresaOut.class, columns = {
				@ColumnResult(name = "Empresa", type = String.class), 
				@ColumnResult(name = "Lote", type = String.class),
				@ColumnResult(name = "Importe", type = Double.class), 
				@ColumnResult(name = "Registros", type = Integer.class)
}) 
}) 
})
public class EmpresaOut {
	private String empresa;
	private String lote;
	private Double importe;
	private Integer registros;
}
