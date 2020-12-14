package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "MarcasOut", 
				classes = { @ConstructorResult(targetClass = MarcasOut.class, 
					columns = {
						@ColumnResult(name = "COD_CUENTA", type = String.class),
						@ColumnResult(name = "CLAVE_PROCESO", type = Integer.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class),
						@ColumnResult(name = "INCLUIDO_POR", type = String.class),
						@ColumnResult(name = "FECHA_INICIO", type = Date.class),
						@ColumnResult(name = "FECHA_FIN", type = Date.class),
						@ColumnResult(name = "ESTADO", type = String.class)						
					})
				})
})

public class MarcasOut {
	private String codCuenta;
	private Integer claveProceso;
	private String descripcion;
	private String incluidoPor;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
}
//COD_CUENTA	VARCHAR2
//CLAVE_PROCESO	NUMBER
//DESCRIPCION	VARCHAR2
//INCLUIDO_POR	VARCHAR2
//FECHA_INICIO	DATE
//FECHA_FIN	DATE
//ESTADO	VARCHAR2

  