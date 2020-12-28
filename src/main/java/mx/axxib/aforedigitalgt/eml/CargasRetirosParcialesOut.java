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

//@Data
//@AllArgsConstructor
//@MappedSuperclass
//@SqlResultSetMappings({
//	@SqlResultSetMapping(name = "CargasRetirosParcialesOut", classes = { @ConstructorResult(targetClass = CargasRetirosParcialesOut.class, columns = {
//			@ColumnResult(name = "P_AVANCE", type = Integer.class),
//			@ColumnResult(name = "P_ERROR", type = String.class) }) })
//})
@Data
@NoArgsConstructor
@MappedSuperclass
public class CargasRetirosParcialesOut {
	
	private String avance;//P_AVANCE
	private String error;//P_ERROR

}
