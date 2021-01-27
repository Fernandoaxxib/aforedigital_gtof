package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ConsultarDatosIcefasOut", classes = { @ConstructorResult(targetClass = ConsultarDatosIcefasOut.class, columns = {

				@ColumnResult(name ="INCLUIDO_POR", type = String.class),
				@ColumnResult(name ="MODIFICADO_POR", type = String.class),
				@ColumnResult(name ="FEC_INCLUSION", type = Date.class),
				@ColumnResult(name ="FEC_MODIFICACION", type = Date.class)
		}) })
		})
public class ConsultarDatosIcefasOut {
	
	private String INCLUIDO_POR;
	private String MODIFICADO_POR;
	private Date FEC_INCLUSION;
	private Date FEC_MODIFICACION;
}
