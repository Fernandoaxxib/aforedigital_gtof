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
    @SqlResultSetMapping(name = "DetalleFacturacion", classes = {
		@ConstructorResult(targetClass = DetalleFacturacion.class, columns = {
            @ColumnResult(name = "FOLIO_SOL",       type = Integer.class), 
            @ColumnResult(name = "CONS_FOLIO",      type = Integer.class),
            @ColumnResult(name = "NSS",             type = String.class),
            @ColumnResult(name = "CURP",            type = String.class),
            @ColumnResult(name = "REF_NUMERICA",    type = String.class), 
            @ColumnResult(name = "EST_SOL",         type = Integer.class),
            @ColumnResult(name = "FEC_CAPTURA",     type = Date.class),
            @ColumnResult(name = "NOMBRE",          type = String.class),
            @ColumnResult(name = "IMPORTE",         type = Double.class), 
            @ColumnResult(name = "BANCO",           type = String.class),
            @ColumnResult(name = "TIPO_CUENTA",     type = String.class),
            @ColumnResult(name = "BENEF_FISCAL",    type = String.class),
            @ColumnResult(name = "SIEFORE",         type = String.class), 
            @ColumnResult(name = "CLAVE_RECH",      type = String.class),
            @ColumnResult(name = "RECHAZO",         type = String.class),
            @ColumnResult(name = "USUARIO",         type = String.class)
        }) 
    }) 
})
public class DetalleFacturacion {
    /*
    FOLIO_SOL       NUMBER
    CONS_FOLIO      NUMBER
    NSS             VARCHAR2(11)
    CURP            VARCHAR2(18)
    REF_NUMERICA    NUMBER
    EST_SOL         VARCHAR2(80)
    FEC_CAPTURA     DATE
    NOMBRE          VARCHAR2(163)
    IMPORTE         NUMBER(13,2)
    BANCO           VARCHAR2(40)
    TIPO_CUENTA     VARCHAR2(80)
    BENEF_FISCAL    VARCHAR2(80)
    SIEFORE         VARCHAR2(12)
    CLAVE_RECH      VARCHAR2(2)
    RECHAZO         VARCHAR2(80)
    USUARIO         VARCHAR2(30)
    */
    private Integer FOLIO_SOL;
    private Integer CONS_FOLIO;
    private String  NSS;
    private String  CURP;
    private String  REF_NUMERICA;
    private Integer EST_SOL;
    private Date    FEC_CAPTURA;
    private String  NOMBRE;
    private Double  IMPORTE;
    private String  BANCO;
    private String  TIPO_CUENTA;
    private String  BENEF_FISCAL;
    private String  SIEFORE;
    private String  CLAVE_RECH;
    private String  RECHAZO;
    private String  USUARIO;
}
