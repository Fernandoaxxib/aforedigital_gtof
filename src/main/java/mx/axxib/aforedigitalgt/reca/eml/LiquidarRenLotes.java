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
		@SqlResultSetMapping(name = "LiquidarRenLotes", 
				classes = { @ConstructorResult(targetClass = LiquidarRenLotes.class, 
					columns = {
						@ColumnResult(name = "ID_LOTE", type = String.class)
					
					})
				})
})

public class LiquidarRenLotes {
	private String idLote;
	
}
