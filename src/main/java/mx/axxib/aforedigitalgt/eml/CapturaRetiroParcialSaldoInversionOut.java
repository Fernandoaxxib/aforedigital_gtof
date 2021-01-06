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
		@SqlResultSetMapping(name = "CapturaRetiroParcialSaldoInversionOut", 
				classes = { @ConstructorResult(targetClass = CapturaRetiroParcialSaldoInversionOut.class, 
					columns = {
						@ColumnResult(name = "p_MonPrin_Saldo", type = Integer.class),
						@ColumnResult(name = "p_Desc_Siefore", type = String.class)
					})
				})
})
public class CapturaRetiroParcialSaldoInversionOut {
	private Integer p_MonPrin_Saldo;
	private String p_Desc_Siefore;
}
