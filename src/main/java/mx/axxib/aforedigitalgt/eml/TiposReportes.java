package mx.axxib.aforedigitalgt.eml;

import java.util.Date;
import java.util.List;

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
//@NoArgsConstructor
//@MappedSuperclass
//@SqlResultSetMappings({
//		@SqlResultSetMapping(name = "TiposReportes", 
//				classes = { @ConstructorResult(targetClass = TiposReportes.class, 
//					columns = {
//						@ColumnResult(name = "P_NOMBRE_ARCHIVO", type = String.class),
//						@ColumnResult(name = "P_MENSAJE", type = String.class),
//						@ColumnResult(name = "P_ESTATUS", type = String.class)
//					})
//				})
//})
@Data
@NoArgsConstructor
@MappedSuperclass
public class TiposReportes {
	
	private String P_NOMBRE_ARCHIVO;
	private String P_MENSAJE;
	private Integer P_ESTATUS;
//	 P_NOMBRE_ARCHIVO OUT VARCHAR2,
//     P_MENSAJE    OUT VARCHAR2,
//     P_ESTATUS  OUT NUMBER);  
}
