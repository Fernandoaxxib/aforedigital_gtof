package mx.axxib.aforedigitalgt.eml;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
//@AllArgsConstructor
//@MappedSuperclass
//@SqlResultSetMappings({
//		@SqlResultSetMapping(name = "VerChequeOut", classes = { @ConstructorResult(targetClass = VerChequeOut.class, columns = {
//				@ColumnResult(name = "P_NOMBRE", type = String.class),
//				@ColumnResult(name = "P_CUENTA", type = String.class),
//				@ColumnResult(name = "P_MENSAJE", type = String.class)}) })
//})
public class VerChequeOut {
	
	private String nombre;
	private String cuenta;
	private String mensaje;
	private String status;
}
