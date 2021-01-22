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
		@SqlResultSetMapping(name = "ResultOut", 
				classes = { @ConstructorResult(targetClass = ResultOut.class, 
				columns = {
						@ColumnResult(name = "p_Message", type = String.class),
						@ColumnResult(name = "p_Avance", type = String.class)					
					})
				})
})
public class ResultOut {
	private String p_Message;
    private String p_Avance;
}
