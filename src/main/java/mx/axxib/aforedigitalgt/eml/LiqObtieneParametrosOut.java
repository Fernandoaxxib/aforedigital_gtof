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

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class LiqObtieneParametrosOut {
	private String idLote;
	private Date fecha;
}

//P_ID_LOTE OUT VARCHAR2,
//P_TIP_TRANSAC_TOTAL OUT VARCHAR2, no se usa
//P_TIP_TRANSAC_PARCIAL OUT VARCHAR2, no se usa
//P_FECHA_SISTEMA OUT DATE)