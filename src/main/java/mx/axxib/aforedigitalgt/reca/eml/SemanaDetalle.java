package mx.axxib.aforedigitalgt.reca.eml;

import java.math.BigDecimal;
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
		@SqlResultSetMapping(name = "BuscarSemanasDetalle", 
				classes = { @ConstructorResult(targetClass = SemanaDetalle.class, 
					columns = {
						@ColumnResult(name = "COD_INVERSION", type = String.class),
						@ColumnResult(name = "DESC_INV", type = String.class),
						@ColumnResult(name = "COD_TIPSALDO", type = String.class),
						@ColumnResult(name = "DESC_SUBCTA", type = String.class),
						@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
						@ColumnResult(name = "FEC_MOVIMTO", type = Date.class),
						@ColumnResult(name = "FEC_APLICADO", type = Date.class),
						@ColumnResult(name = "ESTADO", type = String.class)
						
						
					
					})
				})
})

public class SemanaDetalle {
	private String codInversion;
	private String descInversion; //Siefore
	private String codTipSaldo;
	private String descSubcuenta; //Subcuenta
	private BigDecimal montoMov; //Monto
	private Date fechaMov; //Fecha movimiento
	private Date fechaAplicado; //Fecha aplicado
	private Integer estado; //Estado
	private Date fechaPago;
	private Date fechaConsulta;
	private Date fechaRetiro;
}
