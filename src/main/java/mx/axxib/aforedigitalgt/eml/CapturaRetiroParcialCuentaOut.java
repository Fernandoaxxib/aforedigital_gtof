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
		@SqlResultSetMapping(name = "CapturaRetiroParcialCuentaOut", 
				classes = { @ConstructorResult(targetClass = CapturaRetiroParcialCuentaOut.class, 
					columns = {
						@ColumnResult(name = "p_TipoPago", type = Integer.class),
						@ColumnResult(name = "p_Cuenta", type = Integer.class),
						@ColumnResult(name = "p_Banco", type = String.class),
						@ColumnResult(name = "p_Nombre_De", type = String.class),						
						@ColumnResult(name = "p_Rfc", type = String.class),
						@ColumnResult(name = "p_Telefono", type = String.class),
						@ColumnResult(name = "p_Message", type = String.class)
					})
				})
})
public class CapturaRetiroParcialCuentaOut {
	
	private Integer  p_TipoPago;
	private Integer  p_Cuenta;
	private String 	 p_Banco;
	private String 	 p_Nombre_De;
	private String 	 p_Rfc;
	private String 	 p_Telefono;
	private String 	 p_Message;
}
