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
//@NoArgsConstructor
//@MappedSuperclass
//@SqlResultSetMappings({
//		@SqlResultSetMapping(name = "OrdenPagoFechasOut", 
//				classes = { @ConstructorResult(targetClass = OrdenPagoFechasOut.class, 
//					columns = {
//						@ColumnResult(name = "P_FECHA_INICIO", type = Date.class),
//						@ColumnResult(name = "P_FECHA_FINAL", type = Date.class)
//					})
//				})
//})
@Data
@NoArgsConstructor
@MappedSuperclass
public class OrdenPagoFechasOut {
	
	private Date fechaInicio;
	private Date fechaFin;

}
