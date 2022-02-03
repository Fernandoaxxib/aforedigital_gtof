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
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de Contingencia
//** Interventor Principal: JSAS
//** Fecha Creación: 27/Ene/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "BuscarSellos", 
				classes = { @ConstructorResult(targetClass = Sello.class, 
					columns = {
						@ColumnResult(name = "SELLO", type = String.class),
						@ColumnResult(name = "ESTATUS_FOLIO", type = String.class),
						@ColumnResult(name = "CURP_EMPLEADO", type = String.class),
						@ColumnResult(name = "CURP_TRABAJADOR", type = String.class),
						@ColumnResult(name = "NSS", type = String.class),
						@ColumnResult(name = "FECHA_HORA_SOL", type = Date.class),
						@ColumnResult(name = "FECHA_ALTA", type = Date.class),
						@ColumnResult(name = "ESTATUS_SOL", type = String.class)
						
						
					
					})
				})
})

public class Sello {
	private String sello;
	private String estatusFolio;
	private String curp;
	private String curpTrabajador;
	private String nss;
	private Date fechaSol;
	private Date fechaAlta;
	private String estatusSol;
	
}
