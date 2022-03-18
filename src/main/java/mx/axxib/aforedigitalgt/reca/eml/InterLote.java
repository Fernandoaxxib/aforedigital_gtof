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
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Interfaces
//** Interventor Principal: JSAS
//** Fecha Creación: 16/Mar/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "InterLote", 
				classes = { @ConstructorResult(targetClass = InterLote.class, 
					columns = {
						@ColumnResult(name = "LOTE_CARGA", type = String.class)
					
					})
				})
})

public class InterLote {
	private String lote;
}
