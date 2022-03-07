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
		@SqlResultSetMapping(name = "Empresas", 
				classes = { @ConstructorResult(targetClass = Empresas.class, 
					columns = {
						@ColumnResult(name = "COD_EMPRESA", type = String.class),
						@ColumnResult(name = "TIT_REPORTES", type = String.class)
					
					})
				})
})

public class Empresas {
	private String empresa;
	private String descripcion;
}
