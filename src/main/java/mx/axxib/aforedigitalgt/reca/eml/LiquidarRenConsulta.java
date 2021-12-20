package mx.axxib.aforedigitalgt.reca.eml;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de liquidar rendimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 20/Dic/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "LiquidarRenConsulta", 
				classes = { @ConstructorResult(targetClass = LiquidarRenConsulta.class, 
					columns = {
						@ColumnResult(name = "CLAVE", type = String.class), // no está definido en el stored pero respondieron que así debería ir ?
						@ColumnResult(name = "MONTO", type = String.class), // no está definido en el stored pero respondieron que así debería ir ?
						@ColumnResult(name = "MONTO_PENDIENTE", type = String.class) // no está definido en el stored pero respondieron que así debería ir ?
					
					})
				})
})


public class LiquidarRenConsulta {
	private String clave;
	private String monto;
	private String montoPendiente;
	
}
