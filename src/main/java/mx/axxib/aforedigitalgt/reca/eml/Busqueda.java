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
    @SqlResultSetMapping(name = "Busqueda", classes = {
		@ConstructorResult(targetClass = Busqueda.class, columns = {
            @ColumnResult(name = "FECHA",           type = String.class), 
            @ColumnResult(name = "CONS",            type = Integer.class), 
            @ColumnResult(name = "LOTE",            type = String.class), 
            @ColumnResult(name = "TOT_REG_LOTE",    type = Integer.class), 
            @ColumnResult(name = "IMPORTE_LOTE",    type = Double.class)
        }) 
    }) 
})
public class Busqueda {
    /*
        FECHA           VARCHAR(10)
        CONS            NUMBER
        LOTE            VARCHAR(16)
        TOT_REG_LOTE    NUMBER
        IMPORTE_LOTE    NUMBER(13,2)
    */
    private String FECHA;
    private Integer CONS;
    private String LOTE;
    private Integer TOT_REG_LOTE;
    private Double IMPORTE_LOTE;
}
