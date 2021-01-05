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
		@SqlResultSetMapping(name = "RegisSinSalarioOut", 
				classes = { @ConstructorResult(targetClass = RegisSinSalarioOut.class, 
					columns = {
						@ColumnResult(name = "num_solicitud", type = Integer.class),
						@ColumnResult(name = "nss", type = String.class),
						@ColumnResult(name = "sbc_tipo_a", type = Integer.class),
						@ColumnResult(name = "fecha_carga", type = Date.class),
						@ColumnResult(name = "curp", type = String.class),
						@ColumnResult(name = "primer_apellido", type = String.class),
						@ColumnResult(name = "segundo_apellido", type = String.class),
						@ColumnResult(name = "nombre", type = String.class)																         
					})
				})
})
public class RegisSinSalarioOut {

	private Integer onNumSolicitud;
    private String onNss;
    private Integer onSbcTipoA;
    private Date odFechaCarga;
    private String ocCurp;
    private String ocPrimerApellido;
    private String ocSegundoApellido;
    private String ocNombre;
}
