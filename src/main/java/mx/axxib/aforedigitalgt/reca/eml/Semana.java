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
import mx.axxib.aforedigitalgt.util.DateUtil;

//***********************************************//
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de conciliación semanas de cotización IMSS
//** Interventor Principal: JSAS
//** Fecha Creación: 31/Ene/2022
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "BuscarSemanas", 
				classes = { @ConstructorResult(targetClass = Semana.class, 
					columns = {
						@ColumnResult(name = "NSS", type = String.class),
						@ColumnResult(name = "NOMBRE", type = String.class),
						@ColumnResult(name = "SEM_REINT", type = String.class),
						@ColumnResult(name = "MR_NUMERO_RESOLUCION", type = String.class),
						@ColumnResult(name = "FEC_MOVIMTO", type = Date.class),
						@ColumnResult(name = "NUM_MOVIMTO", type = Integer.class),
						@ColumnResult(name = "SP_FEC_PAGO", type = String.class),
						@ColumnResult(name = "LQ_FEC_CONSULTA", type = String.class),
						@ColumnResult(name = "NP_FEC_RETIRO", type = String.class)
						
					
					})
				})
})

public class Semana {
	private String nss;
	private String nombre;
	private String semReintegradas;
	private String noResolucion;
	private Date fechaMov;
	private Integer noMov;
	private String fechaPago;
	private String fechaConsulta;
	private String fechaRetiro;
	
	public Date getFechaPagoDate() {
		 return DateUtil.getDateToString(fechaPago, "yyyy-MM-dd");
	}
}
