package mx.axxib.aforedigitalgt.eml;

import lombok.Data;

@Data
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
}
