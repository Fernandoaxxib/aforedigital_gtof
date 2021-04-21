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
//						
//					@ColumnResult(name = "pCuenta", type = String.class),
//					@ColumnResult(name = "pNombre", type = String.class),
//					@ColumnResult(name = "pNoPoliza_Pag", type = Long.class),
//					@ColumnResult(name = "pReferencia", type = String.class),
//					@ColumnResult(name = "pFechaMov_Pag", type = Date.class),
//					@ColumnResult(name = "pFecReinv_Pag", type = Date.class),
//					@ColumnResult(name = "pTelefono_Pag", type = String.class),
//					@ColumnResult(name = "pBenef_Re", type = String.class),
//					@ColumnResult(name = "pPorcent_Re", type = Double.class),
//					@ColumnResult(name = "pEstatus_Re", type = String.class),
//					@ColumnResult(name = "pPlaza_Re", type = String.class),
//					@ColumnResult(name = "pMonBruto_Re", type = Double.class),
//					@ColumnResult(name = "pMontoIsr_Re", type = Double.class),
//					@ColumnResult(name = "pMontoNeto_Re", type = Double.class),
//					@ColumnResult(name = "pMensaje", type = String.class),
//					@ColumnResult(name = "on_Estatus", type = Integer.class)
//					
//				})
//			})
//})
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class FopagosListOut {
	
	private String PCuenta;// OUT VARCHAR2,
	private String PNombre;// OUT VARCHAR2,
	private Long PNoPoliza_Pag;// OUT NUMBER,
    private String PReferencia;// OUT VARCHAR2,
    private Date PFechaMov_Pag;// OUT DATE,
    private Date PFecReinv_Pag;// OUT DATE,
    private String PTelefono_Pag;// OUT VARCHAR2,
    private String PBenef_Re;// OUT VARCHAR2,
    private Double PPorcent_Re;// OUT NUMBER,
    private String PEstatus_Re;// OUT VARCHAR2,
    private String PPlaza_Re;// OUT VARCHAR2,
    private Double PMonBruto_Re;// OUT NUMBER,
    private Double PMontoIsr_Re;// OUT NUMBER,
    private Double PMontoNeto_Re;// OUT NUMBER,
    private String PMensaje;// OUT VARCHAR2)
    private Integer On_Estatus;//OUT INTEGER
}
