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

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Reporte Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Feb/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "SaldosIndi", 
				classes = { @ConstructorResult(targetClass = SaldosIndi.class, 
					columns = {
						@ColumnResult(name = "NSS", type = String.class),
						@ColumnResult(name = "NOMBRE", type = String.class),
						@ColumnResult(name = "IMPOTE_A_REINTEGRAR", type = BigDecimal.class),
						@ColumnResult(name = "SIEFORE", type = String.class),
						@ColumnResult(name = "PRECIO", type = BigDecimal.class),
						@ColumnResult(name = "SUBCUENTA", type = String.class),
						@ColumnResult(name = "ACCIONES", type = BigDecimal.class),
						@ColumnResult(name = "IMPORTE", type = BigDecimal.class)
					})
				})
})

public class SaldosIndi {
	private String nss;
	private String nombre;
	private BigDecimal importeR;
	private String siefore;
	private BigDecimal precio;
	private String subCuenta;
	private BigDecimal acciones;
	private BigDecimal importe;
	
}
//NSS
//NOMBRE
//IMPOTE_A_REINTEGRAR
//SIEFORE
//PRECIO
//SUBCUENTA
//ACCIONES
//IMPORTE
