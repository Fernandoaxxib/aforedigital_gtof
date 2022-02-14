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
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Reporte Conciliación
//** Interventor Principal: JSAS
//** Fecha Creación: 14/Feb/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "Estatus", 
				classes = { @ConstructorResult(targetClass = Estatus.class, 
					columns = {
						@ColumnResult(name = "ID_ESTATUS", type = Integer.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class)
					
					})
				})
})

public class Estatus {
	private Integer estatus;
	private String descripcion;
}
