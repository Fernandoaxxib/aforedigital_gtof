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

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@MappedSuperclass
//@SqlResultSetMappings({
//	@SqlResultSetMapping(name = "FopagosListOut", 
//			classes = { @ConstructorResult(targetClass = FopagosListOut.class, 
//				columns = {
//					@ColumnResult(name = "pNoPoliza_Pag", type = Integer.class),
//					@ColumnResult(name = "pReferencia", type = String.class),
//					@ColumnResult(name = "pFechaMov_Pag", type = Date.class),
//					@ColumnResult(name = "pFecReinv_Pag", type = Date.class),
//					@ColumnResult(name = "pTelefono_Pag", type = String.class),
//					@ColumnResult(name = "pBenef_Re", type = String.class),
//					@ColumnResult(name = "pPorcent_Re", type = Integer.class),
//					@ColumnResult(name = "pEstatus_Re", type = String.class),
//					@ColumnResult(name = "pPlaza_Re", type = String.class),
//					@ColumnResult(name = "pMonBruto_Re", type = Integer.class),
//					@ColumnResult(name = "pMontoIsr_Re", type = Integer.class),
//					@ColumnResult(name = "pMontoNeto_Re", type = Integer.class),
//					@ColumnResult(name = "pMensaje", type = String.class)
//				})
//			})
//})
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class FopagosListOut {
	
	private Integer pNoPoliza_Pag;// OUT NUMBER,
    private String pReferencia;// OUT VARCHAR2,
    private Date pFechaMov_Pag;// OUT DATE,
    private Date pFecReinv_Pag;// OUT DATE,
    private String pTelefono_Pag;// OUT VARCHAR2,
    private String pBenef_Re;// OUT VARCHAR2,
    private Integer pPorcent_Re;// OUT NUMBER,
    private String pEstatus_Re;// OUT VARCHAR2,
    private String pPlaza_Re;// OUT VARCHAR2,
    private Integer pMonBruto_Re;// OUT NUMBER,
    private Integer pMontoIsr_Re;// OUT NUMBER,
    private Integer pMontoNeto_Re;// OUT NUMBER,
    private String pMensaje;// OUT VARCHAR2)
    private Integer on_Estatus;//OUT INTEGER
}
