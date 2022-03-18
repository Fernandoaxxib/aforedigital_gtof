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
		@SqlResultSetMapping(name = "InterInversion", 
				classes = { @ConstructorResult(targetClass = InterInversion.class, 
					columns = {
						@ColumnResult(name = "CLAVE_PROCESAR", type = String.class),
						@ColumnResult(name = "COD_INVERSION", type = Integer.class)
					})
				})
})

public class InterInversion {
	private String clave;
	private Integer codInversion;
	
	
}
