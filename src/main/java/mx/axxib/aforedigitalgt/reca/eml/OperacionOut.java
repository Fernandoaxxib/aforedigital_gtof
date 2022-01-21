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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "OperacionOut", classes = {
		@ConstructorResult(targetClass = OperacionOut.class, columns = {
				@ColumnResult(name = "oc_desc_concepto", type = String.class),
				@ColumnResult(name = "on_importes_aceptados", type = String.class),
				@ColumnResult(name = "on_monto_liquidado", type = String.class),
				@ColumnResult(name = "on_monto_abono", type = String.class),
				@ColumnResult(name = "od_fec_aplicado", type = String.class),
				@ColumnResult(name = "oc_estado", type = String.class) }) }) })

public class OperacionOut {
	private String oc_desc_concepto;
	private String on_importes_aceptados;
	private String on_monto_liquidado;
	private String on_monto_abono;
	private String od_fec_aplicado;
	private String oc_estado;
}
