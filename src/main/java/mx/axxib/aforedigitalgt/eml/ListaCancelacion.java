package mx.axxib.aforedigitalgt.eml;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de desempleo parcialidades
//** Interventor Principal: JSAS
//** Fecha Creación: 30/Nov/2020
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ListaCancelacion", 
				classes = { @ConstructorResult(targetClass = ListaCancelacion.class, 
					columns = {
						@ColumnResult(name = "CLAVE_PROCESO", type = Integer.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class)
					})
				})
})

public class ListaCancelacion {
	private Integer claveProceso;
	private String descripcion;
}
//CLAVE_PROCESO	NUMBER
//DESCRIPCION	VARCHAR2

  