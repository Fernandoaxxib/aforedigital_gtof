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
		@SqlResultSetMapping(name = "LoteCOut", classes = { @ConstructorResult(targetClass = LoteCOut.class, columns = {
				@ColumnResult(name = "id_lote", type = String.class),
				@ColumnResult(name = "descripcion", type = String.class),
				@ColumnResult(name = "FEC_MOVIMTO", type = Date.class) }) }) })
public class LoteCOut {

	private String id_lote;
	private String descripcion;
	private Date FEC_MOVIMTO;
}
