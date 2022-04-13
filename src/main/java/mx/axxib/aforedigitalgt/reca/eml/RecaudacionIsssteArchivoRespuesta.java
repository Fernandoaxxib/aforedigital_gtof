package mx.axxib.aforedigitalgt.reca.eml;

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
@MappedSuperclass
@NoArgsConstructor
@SqlResultSetMappings({ @SqlResultSetMapping(name = "RecaudacionIsssteArchivoRespuesta", classes = {
		@ConstructorResult(targetClass = RecaudacionIsssteArchivoRespuesta.class, columns = {
				@ColumnResult(name = "LOTE", type = String.class),
				@ColumnResult(name = "ID_OPERACION", type = Integer.class) }) }) })

public class RecaudacionIsssteArchivoRespuesta {
	private String lote;
	private Integer idOperacion;
	
}
