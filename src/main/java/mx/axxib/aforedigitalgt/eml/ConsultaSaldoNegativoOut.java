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
		@SqlResultSetMapping(name = "ConsultaSaldoNegativoOut", classes = { @ConstructorResult(targetClass = ConsultaSaldoNegativoOut.class, columns = {
				@ColumnResult(name = "p_FecMov", type = Date.class),
				@ColumnResult(name = "p_Mensaje", type = String.class)}) })
})
public class ConsultaSaldoNegativoOut {
	
	private Date fechaMovimiento;
	private String mensaje;
	
}
