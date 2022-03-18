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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "DatosTrabajador", classes = {
		@ConstructorResult(targetClass = DatosTrabajador.class, columns = {
				@ColumnResult(name = "NSS_TRABAJADOR", type = String.class),
				@ColumnResult(name = "CURP", type = String.class),
				@ColumnResult(name = "COD_CUENTA", type = String.class),
				@ColumnResult(name = "COD_INVERSION", type = String.class),
				@ColumnResult(name = "RESUL_OPERA", type = String.class) }) }) })
public class DatosTrabajador {
	private String nss;
	private String curp;
	private String codCuenta;
	private String codInversion;
	private String resulOpera;
}