package mx.axxib.aforedigitalgt.eml;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "LlenaMovimiento", 
				classes = { @ConstructorResult(targetClass = LlenaMovimiento.class, 
					columns = {
						@ColumnResult(name = "FEC_MOVIMTO", type = Date.class),
						@ColumnResult(name = "NUM_MOVIMTO", type = Integer.class),
						@ColumnResult(name = "TIP_TRANSAC", type = Integer.class),
						@ColumnResult(name = "SUBTIP_TRANSAC", type = String.class),
						@ColumnResult(name = "COD_AGENCIA", type = String.class),
						@ColumnResult(name = "MON_MOVIMTO", type = BigDecimal.class),
						@ColumnResult(name = "ID_LOTE", type = String.class),
						@ColumnResult(name = "ESTADO", type = String.class),
						@ColumnResult(name = "DESC_TRANSACCION", type = String.class),
						@ColumnResult(name = "DESC_SUBTRANSAC", type = String.class),
						@ColumnResult(name = "DESC_AGENCIA", type = String.class)
					})
				})
})

public class LlenaMovimiento {
	private Date fechaMov;
	private Integer noMov;
	private Integer tipoTransaccion;
	private String subtipoTransaccion;
	private String codAgencia;
	private BigDecimal montoMov;
	private String idLote;
	private String estado;
	private String descTransaccion;
	private String descSubtransaccion;
	private String descAgencia;
}

//FEC_MOVIMTO            DATE,             
//NUM_MOVIMTO            NUMBER
//TIP_TRANSAC            NUMBER             
//SUBTIP_TRANSAC         VARCHAR2(10 CHAR)   
//COD_AGENCIA            VARCHAR2(5 CHAR)   
//MON_MOVIMTO            NUMBER(25)         
//ID_LOTE                VARCHAR2(30 CHAR)  
//ESTADO                 VARCHAR2(5 CHAR)    
//DESC_TRANSACCION       VARCHAR2(100 CHAR)    
//DESC_SUBTRANSAC        VARCHAR2(100 CHAR) 
//DESC_AGENCIA           VARCHAR2(100 CHAR)    
//IDEN_MOVIMIENTO         VARCHAR2(20 CHAR)   