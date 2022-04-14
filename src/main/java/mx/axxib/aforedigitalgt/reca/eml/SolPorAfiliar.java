package mx.axxib.aforedigitalgt.reca.eml;

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
    @SqlResultSetMapping(name = "SolPorAfiliar", classes = {
		@ConstructorResult(targetClass = SolPorAfiliar.class, columns = {
            @ColumnResult(name = "FOLIO_SOL",           type = Integer.class), 
            @ColumnResult(name = "CONS_FOLIO",          type = Integer.class), 
            @ColumnResult(name = "NUM",                 type = Integer.class), 
            @ColumnResult(name = "CVE_EST_ARCH",        type = Integer.class), 
            @ColumnResult(name = "TIPO_REG",            type = String.class), 
            @ColumnResult(name = "NSS",                 type = String.class), 
            @ColumnResult(name = "CURP",                type = String.class), 
            @ColumnResult(name = "MTO_CARGO",           type = Double.class), 
            @ColumnResult(name = "CVE_BANCO",           type = String.class), 
            @ColumnResult(name = "NOMBRE",              type = String.class), 
            @ColumnResult(name = "CVE_TIPO_CTA",        type = String.class), 
            @ColumnResult(name = "NUM_CUENTA",          type = String.class), 
            @ColumnResult(name = "CVE_TIPO_PERSONA",    type = Integer.class), 
            @ColumnResult(name = "CVE_TIPO_IDENT",      type = Integer.class), 
            @ColumnResult(name = "NUM_IDENTIFICA",      type = String.class), 
            @ColumnResult(name = "ID_REFERENCIA",       type = String.class), 
            @ColumnResult(name = "DIVISA",              type = String.class), 
            @ColumnResult(name = "CVE_RECH",            type = String.class), 
            @ColumnResult(name = "CVE_EST_REG",         type = String.class)
        }) 
    }) 
})
public class SolPorAfiliar {
    /*
        FOLIO_SOL           NUMBER
        CONS_FOLIO          NUMBER
        NUM                 NUMBER
        CVE_EST_ARCH        NUMBER
        TIPO_REG            CHAR(1)
        NSS                 VARCHAR2(11)
        CURP                VARCHAR2(18)
        MTO_CARGO           NUMBER(13,2)
        CVE_BANCO           VARCHAR2(3)
        NOMBRE              VARCHAR2(163)
        CVE_TIPO_CTA        VARCHAR2(2)
        NUM_CUENTA          VARCHAR2(18)
        CVE_TIPO_PERSONA    NUMBER(1)
        CVE_TIPO_IDENT      NUMBER(1)
        NUM_IDENTIFICA      VARCHAR2(20)
        ID_REFERENCIA       VARCHAR2(40)
        DIVISA              CHAR(2)
        CVE_RECH            VARCHAR2()
        CVE_EST_REG         VARCHAR2(1)
    */
    private Integer FOLIO_SOL;
    private Integer CONS_FOLIO;
    private Integer NUM;
    private Integer CVE_EST_ARCH;
    private String TIPO_REG;
    private String NSS;
    private String CURP;
    private Double MTO_CARGO;
    private String CVE_BANCO;
    private String NOMBRE;
    private String CVE_TIPO_CTA;
    private String NUM_CUENTA;
    private Integer CVE_TIPO_PERSONA;
    private Integer CVE_TIPO_IDENT;
    private String NUM_IDENTIFICA;
    private String ID_REFERENCIA;
    private String DIVISA;
    private String CVE_RECH;
    private String CVE_EST_REG;
}
