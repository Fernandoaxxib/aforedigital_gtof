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
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Compra Titulos
//** Interventor Principal: JSAS
//** Fecha Creación: 01/Mar/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "CompraTitMonto", 
				classes = { @ConstructorResult(targetClass = CompraTitMonto.class, 
					columns = {
						@ColumnResult(name = "CLAVE_PROCESAR", type = String.class),
						@ColumnResult(name = "TOT_COMPRAR", type = BigDecimal.class),
						@ColumnResult(name = "VAL_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "TOT_CUOTAS", type = BigDecimal.class),
						@ColumnResult(name = "APORTE_AFORE_TIT", type = BigDecimal.class),
						@ColumnResult(name = "APORTE_AFORE_MND", type = BigDecimal.class),
						@ColumnResult(name = "TOT_GENERAL_CUOTAS", type = BigDecimal.class),
					
					})
				})
})

public class CompraTitMonto {
	private String siefore;
	private BigDecimal totalPendCompra;
	private BigDecimal valorActCuota;
	private BigDecimal totalCuota;
	private BigDecimal aporteAfore;
	private BigDecimal aporteAforePesos;
	private BigDecimal totalCuotasComprar;
}
