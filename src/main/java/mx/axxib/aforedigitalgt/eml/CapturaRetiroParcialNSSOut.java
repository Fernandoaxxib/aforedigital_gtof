package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

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
		@SqlResultSetMapping(name = "CapturaRetiroParcialNSSOut", 
				classes = { @ConstructorResult(targetClass = CapturaRetiroParcialNSSOut.class, 
					columns = {
						@ColumnResult(name = "p_CodCuenta", type = Integer.class),
						@ColumnResult(name = "p_Nombre_Afil", type = String.class),
						@ColumnResult(name = "p_CURP", type = String.class),
						@ColumnResult(name = "p_Rfc_Afil", type = String.class),
						@ColumnResult(name = "p_Telefono", type = String.class),
						@ColumnResult(name = "p_NumeroSS", type = Integer.class),
						@ColumnResult(name = "p_Porcentaje", type = Integer.class),
						@ColumnResult(name = "p_TipoPago", type = String.class),
						@ColumnResult(name = "p_Origen", type = String.class),
						@ColumnResult(name = "p_Message", type = String.class)
					})
				})
})
public class CapturaRetiroParcialNSSOut {
	
	private Integer p_CodCuenta;                              
	private String p_Nombre_Afil;                          
	private String p_CURP;
	private String p_Rfc_Afil;
	private String p_Telefono;
	private Integer p_NumeroSS;
	private Integer p_Porcentaje;
	private String p_TipoPago;
	private String p_Origen;
	private String p_Message;
	
}
