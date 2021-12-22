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
//** FUNCIONALIDAD DEL OBJETO: Entidad de retorno de liquidar movimientos
//** Interventor Principal: JSAS
//** Fecha Creación: 17/Nov/2021
//** Última Modificación:
//***********************************************//
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "LiquidarLote", 
				classes = { @ConstructorResult(targetClass = LiquidarLotes.class, 
					columns = {
						@ColumnResult(name = "ID_LOTE", type = String.class),
						@ColumnResult(name = "NUM_MOVIMTO", type = String.class),
						@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
						@ColumnResult(name = "FEC_MOVIMTO", type = Date.class),
						@ColumnResult(name = "ESTADO", type = String.class),
						@ColumnResult(name = "COD_TIPSALDO", type = String.class),
						@ColumnResult(name = "TOT_CUOTA", type = BigDecimal.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class),
						@ColumnResult(name = "COD_INVERSION", type = String.class),
						@ColumnResult(name = "DESCRIPCION", type = String.class)
					})
				})
})

public class LiquidarLotes {
	private String idLote;
	private String noMovimientos;
	private BigDecimal monto;
	private Date fecha;
	private String estado;
	private String codTipoSaldo;
	private BigDecimal totalCuota;
	private String descSiefore;
	private String codInversion;
	private String descSubcuenta;
}

//ID_LOTE
//NUM_MOVIMTO(MOVIMIENTOS )
//MON_MOVIMTO (MONTO)
//FEC_MOVIMTO   
//ESTADO   
//COD_TIPSALDO  
//TOT_CUOTA   
//DESCRIPCION(DESC_SIEFORE)
//COD_INVERSION   
//DESCRIPCION(DESC_SUBCTA )

   


   
   
  
   





