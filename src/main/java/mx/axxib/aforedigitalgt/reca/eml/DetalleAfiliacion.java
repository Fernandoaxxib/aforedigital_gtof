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
    @SqlResultSetMapping(name = "DetalleAfiliacion", classes = {
		@ConstructorResult(targetClass = DetalleAfiliacion.class, columns = {
            @ColumnResult(name = "NSS",             type = String.class), 
            @ColumnResult(name = "CURP",            type = String.class),
            @ColumnResult(name = "REF_SERVICIO",    type = String.class),
            @ColumnResult(name = "FOLIO_SOL",       type = Integer.class),
            @ColumnResult(name = "CONS_SOL",        type = Integer.class), 
            @ColumnResult(name = "FEC_SOLICITUD",   type = Date.class),
            @ColumnResult(name = "ORIG_MONTO",      type = String.class),
            @ColumnResult(name = "PERIODICIDAD",    type = String.class),
            @ColumnResult(name = "FRECUENCIA",      type = String.class), 
            @ColumnResult(name = "MONTO_MAX",       type = Double.class),
            @ColumnResult(name = "BANCO",           type = String.class),
            @ColumnResult(name = "CUENTA",          type = String.class),
            @ColumnResult(name = "ESTATUS",         type = String.class), 
            @ColumnResult(name = "FEC_CAPTURA",     type = Date.class),
            @ColumnResult(name = "CVE_EST_SOL",     type = Integer.class),
            @ColumnResult(name = "FEC_CREACION",    type = Date.class)
        }) 
    }) 
})
public class DetalleAfiliacion {
    /*
    NSS             varchar2(11)
    CURP            varchar2(18)
    REF_SERVICIO    varchar2(40)
    FOLIO_SOL       NUMBER
    CONS_SOL        NUMBER
    FEC_SOLICITUD   DATE
    ORIG_MONTO      VARCHAR2(300)
    PERIODICIDAD    varchar2(80)
    FRECUENCIA      VARCHAR2(158)
    MONTO_MAX       NUMBER(13,2)
    BANCO           VARCHAR2(40)
    CUENTA          VARCHAR2(18)
    ESTATUS         VARCHAR2(80)
    FEC_CAPTURA     DATE
    CVE_EST_SOL     NUMBER(2)
    FEC_CREACION    DATE
    */
    private String  NSS;
    private String  CURP;
    private String  REF_SERVICIO;
    private Integer FOLIO_SOL;
    private Integer CONS_SOL;
    private Date    FEC_SOLICITUD;
    private String  ORIG_MONTO;
    private String  PERIODICIDAD;
    private String  FRECUENCIA;
    private Double  MONTO_MAX;
    private String  BANCO;
    private String  CUENTA;
    private String  ESTATUS;
    private Date    FEC_CAPTURA;
    private Integer CVE_EST_SOL;
    private Date    FEC_CREACION;
}
