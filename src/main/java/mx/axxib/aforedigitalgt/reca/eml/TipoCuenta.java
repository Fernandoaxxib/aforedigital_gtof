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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "TipoCuenta", classes = {
		@ConstructorResult(targetClass = TipoCuenta.class, columns = {
				@ColumnResult(name = "CVE_TIPO_CTA", type = String.class),
				@ColumnResult(name = "DESCRIPCION", type = String.class), }) }) })
public class TipoCuenta {
	private String cve;
	private String descripcion;
}
