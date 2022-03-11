package mx.axxib.aforedigitalgt.reca.eml;

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
@MappedSuperclass
@NoArgsConstructor
@SqlResultSetMappings({ @SqlResultSetMapping(name = "Recaudacion", classes = {
		@ConstructorResult(targetClass = Recaudacion.class, columns = {
				@ColumnResult(name = "IDENTIFICADOR_OPERACION", type = String.class),
				@ColumnResult(name = "FECHA_TRANSFERENCIA", type = Date.class),
				@ColumnResult(name = "SECUENCIA_LOTE", type = Integer.class) }) }) })

public class Recaudacion {
	private String identificador;
	private Date fechaTransferencia;
	private Integer secuenciaLote;
}
