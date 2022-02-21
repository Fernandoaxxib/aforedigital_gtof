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
		@SqlResultSetMapping(name = "SaldosSief", 
				classes = { @ConstructorResult(targetClass = SaldosSief.class, 
					columns = {
						@ColumnResult(name = "SUBCUENTA", type = String.class),
						@ColumnResult(name = "ACCIONES", type = BigDecimal.class),
						@ColumnResult(name = "PRECIO_SIEFORE", type = BigDecimal.class),
						@ColumnResult(name = "IMPORTE", type = BigDecimal.class)
					})
				})
})

public class SaldosSief {
	private String subCuenta;
	private BigDecimal acciones;
	private BigDecimal precio;
	private BigDecimal importe;
	
}
//SUBCUENTA
//ACCIONES
//PRECIO_SIEFORE
//IMPORTE
