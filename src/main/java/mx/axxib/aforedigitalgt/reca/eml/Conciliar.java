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
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Carga Conciliados
//** Interventor Principal: JSAS
//** Fecha Creación: 21/Feb/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ConciliarArchivo", 
				classes = { @ConstructorResult(targetClass = Conciliar.class, 
					columns = {
						@ColumnResult(name = "REFERENCIA", type = String.class),
						@ColumnResult(name = "ESTADO", type = String.class)
					
					})
				})
})

public class Conciliar {
	private String referencia;
	private String estado;
}
