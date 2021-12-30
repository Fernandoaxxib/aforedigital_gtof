package mx.axxib.aforedigitalgt.reca.eml;

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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "LoteOp71Out", classes = {
		@ConstructorResult(targetClass = LoteOp71Out.class, columns = {
				@ColumnResult(name = "id_operacion", type = String.class), 
				@ColumnResult(name = "fec_lote", type = Integer.class),
				@ColumnResult(name = "consecutivo_dia", type = Integer.class) 
}) 
}) 
})
public class LoteOp71Out {
	private String id_operacion;
	private Integer fec_lote;
	private Integer consecutivo_dia;
}

