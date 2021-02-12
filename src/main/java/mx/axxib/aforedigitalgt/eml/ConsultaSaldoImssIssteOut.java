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
@NoArgsConstructor
@MappedSuperclass
//@SqlResultSetMappings({
//		@SqlResultSetMapping(name = "ConsultaSaldoImssIssteOut", classes = { @ConstructorResult(targetClass = ConsultaSaldoImssIssteOut.class, columns = {
//				@ColumnResult(name = "p_Estatus", type = Integer.class),
//				@ColumnResult(name = "p_vMensaje", type = String.class),
//				@ColumnResult(name = "p_Mensaje", type = String.class)}) })
//})
public class ConsultaSaldoImssIssteOut {
	
	private Integer estatus;
	private String vMensaje;
	private String mensaje;
}
