package mx.axxib.aforedigitalgt.reca.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de recaudación imss
//** Interventor Principal: JSAS
//** Fecha Creación: 13/Dic/2020
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "Lotes", 
				classes = { @ConstructorResult(targetClass = Lote.class, 
					columns = {
						@ColumnResult(name = "LOTE_PROCESO", type = String.class),
						@ColumnResult(name = "IDOPER", type = String.class),
						@ColumnResult(name = "FEC_TRANS", type = Date.class),
						@ColumnResult(name = "SECUENCIA", type = String.class)
					
					})
				})
})

public class Lote {
	private String lote;
	private String idOperacion;
	private Date fechaLote;
	private String secLote;
	
}
