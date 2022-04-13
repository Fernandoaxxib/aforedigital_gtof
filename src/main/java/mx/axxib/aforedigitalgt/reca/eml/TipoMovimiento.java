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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "TipoMovimiento", classes = {
		@ConstructorResult(targetClass = TipoMovimiento.class, columns = {
				@ColumnResult(name = "CVE_MOVIMIENTO", type = String.class),
				@ColumnResult(name = "DESCRIPCION", type = String.class)			
				}) 
		}) })
public class TipoMovimiento {
   private String cve;
   private String descripcion;
}
