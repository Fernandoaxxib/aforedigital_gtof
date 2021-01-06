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
		@SqlResultSetMapping(name = "CapturaRetiroParcialNoResolucionOut", 
				classes = { @ConstructorResult(targetClass = CapturaRetiroParcialNoResolucionOut.class, 
					columns = {
						@ColumnResult(name = "g_global", type = String.class),
						@ColumnResult(name = "p_Message", type = String.class)
					})
				})
})
public class CapturaRetiroParcialNoResolucionOut {
	
	private String g_global;
	private String p_Message;
	
}
