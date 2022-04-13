package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;
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
@SqlResultSetMappings({ @SqlResultSetMapping(name = "RedComercial", classes = {
		@ConstructorResult(targetClass = RedComercial.class, columns = {
				@ColumnResult(name = "NSS", type = String.class), 
				@ColumnResult(name = "CURP", type = String.class),
				@ColumnResult(name = "IMPORTE", type = BigDecimal.class), 
				@ColumnResult(name = "FEC_APORTA", type = String.class), 
				@ColumnResult(name = "FEC_CARGA", type = String.class), 
				@ColumnResult(name = "FEC_CERTIFIC", type = String.class), 
				@ColumnResult(name = "LOTE", type = String.class), 
				@ColumnResult(name = "CUENTA", type = String.class), 
				@ColumnResult(name = "ESTADO", type = String.class), 
				@ColumnResult(name = "ID_ROW", type = Integer.class), 
				@ColumnResult(name = "ESTATUS", type = String.class), 
		}) }) })
public class RedComercial {
	private String nss;
	private String curp;
	private BigDecimal importeAportacion;
	private String fechaAportacion;
	private String fechaCarga;
	private String fechaCertif;
	private String lote;
	private String cuenta;
	private String estado;
	private Integer rowid;
	private String estatus;	
}
