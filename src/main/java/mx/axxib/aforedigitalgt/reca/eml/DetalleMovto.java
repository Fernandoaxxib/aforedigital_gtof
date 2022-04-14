package mx.axxib.aforedigitalgt.reca.eml;

import java.sql.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "DetalleMovto", classes = {
		@ConstructorResult(targetClass = DetalleMovto.class, columns = {
            @ColumnResult(name = "FOLIO_SOL",       type = Integer.class), 
            @ColumnResult(name = "CONS_SOL",        type = Integer.class),
            @ColumnResult(name = "REF_NUMERICA",    type = Integer.class),
            @ColumnResult(name = "IMPORTE",         type = Double.class),
            @ColumnResult(name = "NUM_MOV_DET",     type = Integer.class), 
            @ColumnResult(name = "FEC_MOV_DET",     type = Date.class),
            @ColumnResult(name = "TIPO_SALDO",      type = String.class),
            @ColumnResult(name = "DESC_TIPO_SALDO", type = String.class),
            @ColumnResult(name = "SIEFORE",         type = String.class), 
            @ColumnResult(name = "COD_CUENTA",      type = String.class),
            @ColumnResult(name = "DESCRIPCION",     type = String.class),
            @ColumnResult(name = "MON_MOVIMTO",     type = Double.class),
            @ColumnResult(name = "TIP_TRANSAC",     type = Integer.class)
        }) 
    }) 
})
public class DetalleMovto {
    /*
        FOLIO_SOL           NUMBER
        CONS_SOL            NUMBER
        REF_NUMERICA        NUMBER
        IMPORTE             NUMBER(13,2)
        NUM_MOV_DET         NUMBER
        FEC_MOV_DET         DATE
        TIPO_SALDO          VARCHAR2(4)
        DESC_TIPO_SALDO     VARCHAR2(20)
        SIEFORE             VARCHAR2(12)
        COD_CUENTA          VARCHAR2(15)
        DESCRIPCION         VARCHAR2(80)
        MON_MOVIMTO         NUMBER(18,6)
        TIP_TRANSAC         NUMBER
    */
    private Integer FOLIO_SOL;
    private Integer CONS_SOL;
    private Integer REF_NUMERICA;
    private Double  IMPORTE;
    private Integer NUM_MOV_DET;
    private Date    FEC_MOV_DET;
    private String  TIPO_SALDO;
    private String  DESC_TIPO_SALDO;
    private String  SIEFORE;
    private String  COD_CUENTA;
    private String  DESCRIPCION;
    private Double  MON_MOVIMTO;
    private Integer TIP_TRANSAC;
}
