package mx.axxib.aforedigitalgt.eml;

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
		@SqlResultSetMapping(name = "TipoSubTransacOut", 
				classes = { @ConstructorResult(targetClass = TipoTransacOut.class, 
					columns = {
						@ColumnResult(name = "TIP_TRANSAC_RET", type = Integer.class),
						@ColumnResult(name = "SUBTIP_TRANSAC_RET", type = String.class),
						@ColumnResult(name = "DES_SUB", type = String.class),
						@ColumnResult(name = "MON_RETIRO", type = Integer.class),
						@ColumnResult(name = "REG", type = Integer.class)						
					})
				})
})
public class TipoSubTransacOut {
	private Integer tipTransacRet;
	private String subtipTransacRet;
	private Integer desSub;
	private Integer monRetiro;
	private Integer reg;

}