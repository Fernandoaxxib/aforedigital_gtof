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
	@SqlResultSetMapping(name = "ConsultaSaldo", classes = { @ConstructorResult(targetClass = ConsultaSaldoCliente.class, columns = {
			@ColumnResult(name = "COD_CUENTA", type = Integer.class),
			@ColumnResult(name = "DIAS_ACUMULADOS", type = String.class),
			@ColumnResult(name = "FEC_EMESION", type = String.class),
			@ColumnResult(name = "DIAS_PAGADOS", type = String.class),
			@ColumnResult(name = "COD_SUBPRODUCTO", type = String.class),
			@ColumnResult(name = "SAL_TOTALES", type = String.class) }) })
})

public class ConsultaSaldoCliente {

	private String noCuenta;
	private String diasAcumulados;
	private Date fechaEmision;
	private String diasPagados;
	private String agrupacion;
	private String saldos;
}
