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
    @SqlResultSetMapping(name = "Pendiente", classes = {
		@ConstructorResult(targetClass = Pendiente.class, columns = {
            @ColumnResult(name = "ID_REFERENCIA",       type = String.class), 
            @ColumnResult(name = "CVE_COD_DIV",         type = String.class), 
            @ColumnResult(name = "CVE_RECH_AFIL",       type = String.class), 
            @ColumnResult(name = "CVE_EST_REG",         type = String.class), 
            @ColumnResult(name = "NOM_ARCHIVO",         type = String.class), 
            @ColumnResult(name = "FEC_GENERACION",      type = Integer.class),
            @ColumnResult(name = "CONS_FEC_GEN",        type = Integer.class), 
            @ColumnResult(name = "NUM_CONS",            type = Integer.class), 
            @ColumnResult(name = "NUM",                 type = Integer.class), 
            @ColumnResult(name = "CVE_EST_ARCH",        type = Integer.class),
            @ColumnResult(name = "CVE_TIPO_REG",        type = String.class), 
            @ColumnResult(name = "NSS",                 type = String.class), 
            @ColumnResult(name = "CURP",                type = String.class),  
            @ColumnResult(name = "MTO_CARGO",           type = Integer.class),
            @ColumnResult(name = "CVE_BANCO",           type = String.class), 
            @ColumnResult(name = "NOMBRE",              type = String.class), 
            @ColumnResult(name = "CVE_TIPO_CTA",        type = String.class), 
            @ColumnResult(name = "NUM_CUENTA",          type = String.class),  
            @ColumnResult(name = "CVE_TIPO_PERSONA",    type = Integer.class), 
            @ColumnResult(name = "CVE_TIPO_IDENT",      type = Integer.class), 
            @ColumnResult(name = "NUM_IDENTIFICA",      type = String.class)
        }) 
    }) 
})
public class Pendiente {
    /*
    ID_REFERENCIA       VARCHAR2(40)
    CVE_COD_DIV         VARCHAR2(2)
    CVE_RECH_AFIL       VARCHAR2(2)
    CVE_EST_REG         VARCHAR2(1)
    NOM_ARCHIVO         VARCHAR2(100)
    FEC_GENERACION      NUMBER
    CONS_FEC_GEN        NUMBER
    NUM_CONS            NUMBER
    NUM                 NUMBER
    CVE_EST_ARCH        NUMBER
    CVE_TIPO_REG        VARCHAR2(1)
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
    */
    private String  ID_REFERENCIA   ;
    private String  CVE_COD_DIV     ;
    private String  CVE_RECH_AFIL   ;
    private String  CVE_EST_REG     ;
    private String  NOM_ARCHIVO     ;
    private Integer FEC_GENERACION  ;
    private Integer CONS_FEC_GEN    ;
    private Integer NUM_CONS        ;
    private Integer NUM             ;
    private Integer CVE_EST_ARCH    ;
    private String  CVE_TIPO_REG    ;
    private String  NSS             ;
    private String  CURP            ;
    private Integer MTO_CARGO       ;
    private String  CVE_BANCO       ;
    private String  NOMBRE          ;
    private String  CVE_TIPO_CTA    ;
    private String  NUM_CUENTA      ;
    private Integer CVE_TIPO_PERSONA;
    private Integer CVE_TIPO_IDENT  ;
    private String  NUM_IDENTIFICA  ;
}
